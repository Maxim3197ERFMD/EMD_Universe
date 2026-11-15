package net.erfmd.client;

import net.erfmd.network.RailgunBeamPayload;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class RailgunBeamRenderer {
    private static final Map<Integer, Beam> BEAMS = new HashMap<>();
    // Полный диаметр луча — 0,5 блока.
    private static final float INNER_RADIUS = 0.18F;
    private static final float OUTER_RADIUS = 0.25F;
    // Зелёный цвет с непрозрачной альфой.
    private static final int GREEN = 0xFF20FF40;

    private RailgunBeamRenderer() {}

    public static void register() {
        // Удаляем устаревшие эффекты.
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.world == null) {
                BEAMS.clear();
                return;
            }

            long now = client.world.getTime();

            Iterator<Beam> iterator = BEAMS.values().iterator();

            while (iterator.hasNext()) {
                Beam beam = iterator.next();

                if (now > beam.lastUpdate + 2) {
                    iterator.remove();
                }
            }
        });

        // Рисуем лучи после рендеринга сущностей.
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.world == null || BEAMS.isEmpty()) {
                return;
            }

            MatrixStack matrices = context.matrixStack();

            VertexConsumerProvider consumers = context.consumers();

            if (matrices == null || consumers == null) {
                return;
            }

            Vec3d cameraPos = context.camera().getPos();

            for (Beam beam : BEAMS.values()) {
                Vec3d visualStart = beam.start;
                if (client.player != null && client.options.getPerspective().isFirstPerson()
                        && cameraPos.squaredDistanceTo(beam.start) < 4.0) {
                    visualStart = calculateFirstPersonStart(cameraPos, beam.start, beam.end);
                }
                renderBeam(matrices, consumers, cameraPos, visualStart, beam.end, client.world.getTime());
            }
        });
    }

    // Визуал луча от первого лица.
    private static Vec3d calculateFirstPersonStart(Vec3d cameraPos, Vec3d serverStart, Vec3d end) {
        Vec3d forward = end.subtract(serverStart).normalize();
        Vec3d worldUp = new Vec3d(0.0, 1.0, 0.0);
        Vec3d right = forward.crossProduct(worldUp);

        if (right.lengthSquared() < 0.0001) {
            right = new Vec3d(1.0, 0.0, 0.0);
        } else {
            right = right.normalize();
        }

        Vec3d up = right.crossProduct(forward).normalize();

        return cameraPos.add(forward.multiply(0.20)).add(right.multiply(0.03)).add(up.multiply(-0.38));
    }

    // Принимаем обновление луча от сервера.
    public static void update(RailgunBeamPayload payload) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.world == null) {
            return;
        }

        BEAMS.put(payload.shotId(), new Beam(payload.start(), payload.end(), client.world.getTime()));
    }

    private static void renderBeam(MatrixStack matrices, VertexConsumerProvider consumers, Vec3d cameraPos, Vec3d start, Vec3d end, long worldTime) {
        Vec3d vector = end.subtract(start);

        double length = vector.length();

        if (length < 0.01) {
            return;
        }

        Vec3d direction = vector.normalize();

        matrices.push();

        // Переносим начало координат к точке выстрела.
        matrices.translate(start.x - cameraPos.x, start.y - cameraPos.y, start.z - cameraPos.z);

        // Ванильный луч маяка направлен вверх вдоль положительной оси Y. Поворачиваем его в сторону выстрела.
        Quaternionf rotation = new Quaternionf().rotationTo(0.0F, 1.0F, 0.0F,
                (float) direction.x, (float) direction.y, (float) direction.z
        );

        matrices.multiply(rotation);
        matrices.translate(-0.5F, 0.0F, -0.5F);

        // Рисуем настоящий луч маяка.
        BeaconBlockEntityRenderer.renderBeam(matrices, consumers, BeaconBlockEntityRenderer.BEAM_TEXTURE,
                0.0F, 1.0F, worldTime, 0, (int) Math.ceil(length),
                GREEN, INNER_RADIUS, OUTER_RADIUS
        );

        matrices.pop();
    }

    private record Beam(Vec3d start, Vec3d end, long lastUpdate) { }
}