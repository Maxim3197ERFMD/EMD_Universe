package net.erfmd.item.custom.railgun;

import net.erfmd.network.RailgunBeamPayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class RailgunShotManager {
    // Задержка перед появлением луча. 20 тиков = 1 секунда.
    public static final int CHARGE_TICKS = 20;
    // Продолжительность активного луча. 80 тиков = 4 секунды.
    public static final int BEAM_TICKS = 80;
    // Максимальная дальность луча.
    public static final double RANGE = 100.0;
    // Радиус 0.25 = полный диаметр поражения 0.5 блока.
    public static final double BEAM_RADIUS = 0.25;
    // Активный выстрел для каждого игрока.
    private static final Map<UUID, Shot> SHOTS = new HashMap<>();
    private static int nextShotId = 1;

    private RailgunShotManager() {}

    /*
     * =========================================================
     * СЕРВЕРНЫЙ TICK
     * =========================================================
     */

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            List<UUID> finished = new ArrayList<>();

            for (Map.Entry<UUID, Shot> entry : SHOTS.entrySet()) {
                UUID playerId = entry.getKey();

                Shot shot = entry.getValue();

                ServerPlayerEntity player = server.getPlayerManager().getPlayer(playerId);

                // Игрок вышел или умер.
                if (player == null || !player.isAlive()) {
                    if (player != null && !shot.malfunction) {
                        RailgunCooldown.start(player);
                    }

                    finished.add(playerId);
                    continue;
                }

                // Игрок сменил измерение.
                if (player.getWorld() != shot.world) {
                    if (!shot.malfunction) {
                        RailgunCooldown.start(player);
                    }

                    finished.add(playerId);
                    continue;
                }

                // Продвигаем таймер выстрела.
                shot.tick();

                /*
                 * =================================================
                 * ФАЗА 1: ПОДГОТОВКА К ВЫСТРЕЛУ
                 * =================================================
                 *
                 * Первые 20 тиков луча ещё нет.
                 */

                if (shot.age < CHARGE_TICKS) {
                    continue;
                }

                if (shot.age == CHARGE_TICKS) {
                    shot.start = calculateMuzzlePosition(player, shot.direction);
                }

                /*
                 * =================================================
                 * АВАРИЯ: БАТАРЕИ НЕТ
                 * =================================================
                 *
                 * Подготовка закончилась, но вместо луча рельсотрон взрывается в руках игрока.
                 */
                if (shot.malfunction) {
                    explodeRailgun(player, shot);

                    finished.add(playerId);
                    continue;
                }

                /*
                 * =================================================
                 * ФАЗА 2: ЗАВЕРШЕНИЕ ВЫСТРЕЛА
                 * =================================================
                 *
                 * 20 тиков подготовки + 80 тиков активного луча = 100 тиков полного цикла.
                 */

                if (shot.age >= CHARGE_TICKS + BEAM_TICKS) {
                    // Теперь ставим полную перезарядку после исчезновения луча.
                    RailgunCooldown.start(player);

                    finished.add(playerId);
                    continue;
                }

                /*
                 * =================================================
                 * ФАЗА 1.5: АКТИВНЫЙ ЛУЧ
                 * =================================================
                 */

                // Первый тик появления луча.
                if (shot.age == CHARGE_TICKS) {
                    shot.world.playSound(null, shot.start.x, shot.start.y, shot.start.z,
                            SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 3.0F, 0.8F
                    );
                }

                // Луч уже существует.
                Vec3d end = findBeamEnd(shot);

                // Каждый тик поражаем сущности.
                damageEntities(shot, end);

                // Каждый тик отправляем луч клиентам.
                sendBeam(shot, end);
            }

            // Удаляем закончившиеся выстрелы после обхода HashMap.
            for (UUID playerId : finished) {
                SHOTS.remove(playerId);
            }
        });
    }

    /*
     * =========================================================
     * НАЧАЛО ВЫСТРЕЛА
     * =========================================================
     */

    public static boolean start(ServerPlayerEntity player) {
        UUID playerId = player.getUuid();

        // Предыдущий луч ещё работает.
        if (SHOTS.containsKey(playerId)) {
            return false;
        }

        // Идёт 30-минутный cooldown.
        if (RailgunCooldown.isCoolingDown(player)) {
            return false;
        }

        /*
         * Предварительно ставим cooldown - защиту от выхода игрока во время подготовки или активного луча.
         * После нормального завершения луча, появляется cooldown, поэтому фактический отсчёт начинается после выстрела.
         */
        RailgunCooldown.start(player);

        ServerWorld world = (ServerWorld) player.getWorld();

        /*
         * Фиксируем положение и направление В МОМЕНТ нажатия ПКМ.
         * Следующие 20 тиков игрок может поворачивать камеру, но направление будущего луча уже не изменится.
         */
        Vec3d eyePos = player.getEyePos();
        Vec3d lookDirection = player.getRotationVec(1.0F).normalize();

        // Сначала определяем точку, куда действительно целится перекрестие.
        Vec3d aimEnd = eyePos.add(lookDirection.multiply(RANGE));

        BlockHitResult aimHit = world.raycast(new RaycastContext(eyePos, aimEnd, RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE, player)
        );

        Vec3d target = aimHit.getType() == HitResult.Type.MISS ? aimEnd : aimHit.getPos();

        // Вектор вправо относительно направления игрока.
        Vec3d right = new Vec3d(-lookDirection.z, 0.0, lookDirection.x).normalize();

        /*
         * Приблизительная позиция дула.
         *
         * 1.15 блока вперёд,
         * 0.32 вправо,
         * 0.28 вниз от глаз.
         */
        Vec3d start = eyePos.add(lookDirection.multiply(1.15)).add(right.multiply(0.32))
                .add(0.0, -0.28, 0.0);

        // Луч из дула направляется точно в точку перекрестия.
        Vec3d direction = target.subtract(start).normalize();

        Shot shot = new Shot(nextShotId++, world, playerId, player, direction, false);

        SHOTS.put(playerId, shot);

        // Звук НАЧАЛА подготовки, не выстрела.
        world.playSound(null, start.x, start.y, start.z, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS,
                1.5F, 0.65F
        );

        return true;
    }

    public static boolean startMalfunction(ServerPlayerEntity player) {
        UUID playerId = player.getUuid();

        // Уже идёт другой выстрел/авария.
        if (SHOTS.containsKey(playerId)) {
            return false;
        }

        ServerWorld world = (ServerWorld) player.getWorld();

        Vec3d direction = player.getRotationVec(1.0F).normalize();

        Shot shot = new Shot(nextShotId++, world, playerId, player, direction, true);

        SHOTS.put(playerId, shot);

        // Подготовка выглядит как обычный выстрел.
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE,
                SoundCategory.PLAYERS, 1.5F, 0.65F
        );

        return true;
    }

    /*
     * =========================================================
     * АВАРИЙНЫЙ ВЗРЫВ
     * =========================================================
     */

    private static void explodeRailgun(ServerPlayerEntity player, Shot shot) {
        shot.world.createExplosion(null, player.getX(), player.getY() + 1.0, player.getZ(), 4.0F,
                false, World.ExplosionSourceType.TNT
        );
    }

    /*
     * =========================================================
     * ОПРЕДЕЛЕНИЕ НАЧАЛА ЛУЧА
     * =========================================================
     */
    private static Vec3d calculateMuzzlePosition(ServerPlayerEntity player, Vec3d direction) {
        Vec3d forward = direction.normalize();

        // Правая сторона относительно направления оружия.
        Vec3d worldUp = new Vec3d(0.0, 1.0, 0.0);

        Vec3d right = forward.crossProduct(worldUp);

        // Если игрок смотрит почти строго вверх/вниз, crossProduct становится почти нулевым.
        if (right.lengthSquared() < 0.0001) {
            float yaw = player.getYaw() * ((float) Math.PI / 180.0F);
            right = new Vec3d(Math.cos(yaw), 0.0, Math.sin(yaw));
        } else {
            right = right.normalize();
        }

        /*
         * "Верх" самого оружия.
         * Он уже поворачивается вместе с направлением рельсотрона вверх/вниз.
         */
        Vec3d up = right.crossProduct(forward).normalize();
        Vec3d eyePos = player.getEyePos();

        /*
         * Положение дула относительно камеры.
         *
         * forward = вперёд вдоль ствола
         * right = вправо относительно оружия
         * up = вверх относительно оружия
         */
        return eyePos.add(forward.multiply(0.55)).add(right.multiply(0.03)).add(up.multiply(-0.18));
    }

    /*
     * =========================================================
     * ОПРЕДЕЛЕНИЕ КОНЦА ЛУЧА
     * =========================================================
     */

    private static Vec3d findBeamEnd(Shot shot) {
        Vec3d maxEnd = shot.start.add(shot.direction.multiply(RANGE));

        BlockHitResult hit = shot.world.raycast(new RaycastContext(shot.start, maxEnd, RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE, shot.shooter)
        );

        return hit.getType() == HitResult.Type.MISS ? maxEnd : hit.getPos();
    }

    /*
     * =========================================================
     * ПОРАЖЕНИЕ СУЩНОСТЕЙ
     * =========================================================
     */

    private static void damageEntities(Shot shot, Vec3d end) {
        ServerPlayerEntity shooter = shot.world.getServer().getPlayerManager().getPlayer(shot.owner);

        if (shooter == null) {
            return;
        }

        Box searchArea = new Box(shot.start, end).expand(BEAM_RADIUS + 1.0);

        List<Entity> targets = shot.world.getOtherEntities(shooter, searchArea, Entity::isAlive);

        for (Entity target : targets) {
            Box hitBox = target.getBoundingBox().expand(BEAM_RADIUS);

            if (hitBox.raycast(shot.start, end).isPresent()) {
                target.kill();
            }
        }
    }

    /*
     * =========================================================
     * СИНХРОНИЗАЦИЯ ЛУЧА
     * =========================================================
     */

    private static void sendBeam(Shot shot, Vec3d end) {
        /*
         * Сколько осталось именно АКТИВНОМУ лучу.
         *
         * При age == 20: 80 - (20 - 20) = 80
         * При age == 99: 80 - (99 - 20) = 1
         */

        int activeBeamAge = shot.age - CHARGE_TICKS;
        int remaining = BEAM_TICKS - activeBeamAge;

        RailgunBeamPayload payload = new RailgunBeamPayload(shot.id, shot.start, end, remaining);

        Box beamArea = new Box(shot.start, end).expand(160.0);

        for (ServerPlayerEntity viewer : shot.world.getPlayers()) {
            if (!beamArea.contains(viewer.getEyePos())) {
                continue;
            }

            ServerPlayNetworking.send(viewer, payload);
        }
    }

    /*
     * =========================================================
     * ДАННЫЕ ВЫСТРЕЛА
     * =========================================================
     */

    private static final class Shot {
        private final ServerPlayerEntity shooter;
        private final int id;
        private final ServerWorld world;
        private final UUID owner;
        private Vec3d start;
        private final Vec3d direction;
        private final boolean malfunction;
        private int age = 0;

        private Shot(int id, ServerWorld world, UUID owner, ServerPlayerEntity shooter, Vec3d direction,
                     boolean malfunction) {
            this.id = id;
            this.world = world;
            this.owner = owner;
            this.shooter = shooter;
            this.start = null;
            this.direction = direction;
            this.malfunction = malfunction;
        }

        private void tick() {
            age++;
        }
    }
}