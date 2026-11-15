package net.erfmd.item.custom.railgun;

import net.erfmd.item.EMDItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RailgunItem extends Item {
    // Кол-во выстрелов после установки/замены батареи.
    public static final int BATTERY_SHOTS = 5;
    // Наименование NBT-компонента, отвечающего за наличие батареи.
    private static final String BATTERY_KEY = "railgun_battery_key";
    // Наименования NBT-компонентов, отвечающих за поведения рельсотрона, если он разряжен.
    private static final String EMPTY_WARNING_KEY = "railgun_empty_warning";
    private static final String EMPTY_ATTEMPTS_KEY = "railgun_empty_attempts";
    private static final String EMPTY_LIMIT_KEY = "railgun_empty_limit";
    // Время установки батареи. 20 тиков = 1 секунда.
    public static final int CHARGE_TICKS = 20;
    // Максимальная продолжительность удерживания ПКМ при установке батареи. 20 тиков = 1 секунда.
    private static final int MAX_USE_TIME = 72000;

    public RailgunItem(Settings settings) {
        super(settings);
    }

    /*
     * =========================================================
     * ПОВЕДЕНИЕ РАЗРЯЖЕННОГО РЕЛЬСОТРОНА
     * =========================================================
     */

    private static boolean hasEmptyWarning(ItemStack stack) {
        NbtComponent component = stack.get(DataComponentTypes.CUSTOM_DATA);

        if (component == null) {
            return false;
        }

        return component.copyNbt().getBoolean(EMPTY_WARNING_KEY);
    }

    private static int getEmptyAttempts(ItemStack stack) {
        NbtComponent component = stack.get(DataComponentTypes.CUSTOM_DATA);

        if (component == null) {
            return 0;
        }

        return component.copyNbt().getInt(EMPTY_ATTEMPTS_KEY);
    }

    private static int getEmptyLimit(ItemStack stack) {
        NbtComponent component = stack.get(DataComponentTypes.CUSTOM_DATA);

        if (component == null) {
            return 0;
        }

        return component.copyNbt().getInt(EMPTY_LIMIT_KEY);
    }

    private static void startEmptyWarning(ItemStack stack, int limit) {
        NbtComponent.set(DataComponentTypes.CUSTOM_DATA, stack, nbt -> {
            nbt.putBoolean(EMPTY_WARNING_KEY, true);
            nbt.putInt(EMPTY_ATTEMPTS_KEY, 0);
            nbt.putInt(EMPTY_LIMIT_KEY, limit);
        });
    }

    private static int incrementEmptyAttempts(ItemStack stack) {
        int attempts = getEmptyAttempts(stack) + 1;

        NbtComponent.set(DataComponentTypes.CUSTOM_DATA, stack, nbt -> nbt.putInt(EMPTY_ATTEMPTS_KEY, attempts));

        return attempts;
    }

    private static void resetEmptyWarning(ItemStack stack) {
        NbtComponent.set(DataComponentTypes.CUSTOM_DATA, stack, nbt -> {
            nbt.putBoolean(EMPTY_WARNING_KEY, false);
            nbt.putInt(EMPTY_ATTEMPTS_KEY, 0);
            nbt.putInt(EMPTY_LIMIT_KEY, 0);
        });
    }

    /*
     * =========================================================
     * СОСТОЯНИЕ БАТАРЕИ
     * =========================================================
     */

    public static boolean hasBattery(ItemStack stack) {
        NbtComponent component = stack.get(DataComponentTypes.CUSTOM_DATA);

        if (component == null) {
            return false;
        }

        return component.copyNbt().getBoolean(BATTERY_KEY);
    }

    private static void setBattery(ItemStack stack, boolean value) {
        NbtComponent.set(DataComponentTypes.CUSTOM_DATA, stack, nbt -> nbt.putBoolean(BATTERY_KEY, value));
    }

    /*
     * Оставляем это имя специально для существующих:
     *
     * EMDModelPredicates
     * PlayerEntityRendererMixin
     * HeldItemRendererMixin
     *
     * Теперь "charged" означает: батарея установлена и ещё содержит энергию.
     */
    public static boolean isCharged(ItemStack stack) {
        return hasBattery(stack) && getRemainingShots(stack) > 0;
    }

    /*
     * Количество оставшихся выстрелов.
     *
     * damage 0 = 5/5
     * damage 1 = 4/5
     * damage 2 = 3/5
     * damage 3 = 2/5
     * damage 4 = 1/5
     * damage 5 = 0/5
     */
    public static int getRemainingShots(ItemStack stack) {
        if (!hasBattery(stack)) {
            return 0;
        }

        return Math.max(0, BATTERY_SHOTS - stack.getDamage());
    }

    /*
     * =========================================================
     * ПОИСК БАТАРЕИ
     * =========================================================
     */

    private static ItemStack findBattery(PlayerEntity player) {
        // Сначала левая рука.
        ItemStack offHand = player.getOffHandStack();

        if (offHand.isOf(EMDItems.HIGH_VOLTAGE_BATTERY)) {
            return offHand;
        }

        // Затем весь инвентарь.
        PlayerInventory inventory = player.getInventory();

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack candidate = inventory.getStack(i);

            if (candidate.isOf(EMDItems.HIGH_VOLTAGE_BATTERY)) {
                return candidate;
            }
        }

        return ItemStack.EMPTY;
    }

    private static boolean hasBatteryAvailable(PlayerEntity player) {
        return !findBattery(player).isEmpty();
    }

    /*
     * =========================================================
     * УСТАНОВКА БАТАРЕИ
     * =========================================================
     */

    private static boolean insertBattery(ServerPlayerEntity player, ItemStack railgun) {
        ItemStack battery = findBattery(player);

        if (battery.isEmpty()) {
            return false;
        }

        // Расходуем физическую батарею.
        if (!player.getAbilities().creativeMode) {
            battery.decrement(1);
        }

        setBattery(railgun, true);

        // Полная батарея = damage 0.
        railgun.setDamage(0);

        // После установки батареи, предыдущие ошибочные попытки выстрела забываются.
        resetEmptyWarning(railgun);

        player.getInventory().markDirty();
        player.currentScreenHandler.sendContentUpdates();

        return true;
    }

    /*
     * =========================================================
     * РАСХОД ОДНОГО ВЫСТРЕЛА
     * =========================================================
     */

    private static void consumeShot(ItemStack railgun) {
        if (!hasBattery(railgun)) {
            return;
        }

        int damage = Math.min(BATTERY_SHOTS, railgun.getDamage() + 1);

        // Именно setDamage(), а не damage(): сам рельсотрон уничтожаться не должен.
        railgun.setDamage(damage);

        // Пятый выстрел исчерпал батарею.
        if (damage >= BATTERY_SHOTS) {
            setBattery(railgun, false);
            railgun.setDamage(BATTERY_SHOTS);
            resetEmptyWarning(railgun);
        }
    }

    /*
     * =========================================================
     * ПОЛОСКА ЗАРЯДА
     * =========================================================
     */

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return isCharged(stack);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0F * getRemainingShots(stack) / BATTERY_SHOTS);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        float charge = (float) getRemainingShots(stack) / BATTERY_SHOTS;

        return MathHelper.hsvToRgb(charge / 3.0F, 1.0F, 1.0F);
    }

    /*
     * =========================================================
     * АНИМАЦИЯ ВСТАВКИ БАТАРЕИ
     * =========================================================
     */

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack railgun = user.getStackInHand(hand);

        // Рельсотрон должен находиться именно в основной/правой руке.
        if (hand != Hand.MAIN_HAND) {
            return TypedActionResult.fail(railgun);
        }

        if (!hasBattery(railgun)) {
            /*
             * =====================================================
             * ПРОВЕРКА КУЛДАУНА
             * =====================================================
             *
             * Пока не пройдет кулдаун:
             * - нельзя вставить новую батарею;
             * - предупреждение не показывается и, соответственно, аварий не произойдет.
             */

            if (user.getItemCooldownManager().isCoolingDown(this)) {
                return TypedActionResult.fail(railgun);
            }

            // На сервере дополнительно проверяем сохраняемый RailgunCooldown.
            if (!world.isClient() && user instanceof ServerPlayerEntity player && RailgunCooldown.isCoolingDown(player)) {
                return TypedActionResult.fail(railgun);
            }

            /*
             * =====================================================
             * БАТАРЕЯ ЕСТЬ В ИНВЕНТАРЕ / ЛЕВОЙ РУКЕ
             * =====================================================
             *
             * Тогда после кулдауна, ПКМ запускает установку батареи.
             */

            if (hasBatteryAvailable(user)) {
                user.setCurrentHand(hand);

                return TypedActionResult.success(railgun, world.isClient());
            }

            /*
             * =====================================================
             * БАТАРЕИ НЕТ НИГДЕ
             * =====================================================
             *
             * Игрок всё равно пытается произвести выстрел.
             * Запускаем подготовку, но через 20 тиков RailgunShotManager взорвёт оружие.
             */

            if (world.isClient()) {
                return TypedActionResult.success(railgun, true);
            }

            if (!(user instanceof ServerPlayerEntity player)) {
                return TypedActionResult.fail(railgun);
            }

            /*
             * -----------------------------------------------------
             * Первое нажатие
             * -----------------------------------------------------
             */
            if (!hasEmptyWarning(railgun)) {
                // Скрыто выбираем число повторных попыток: 1, 2 или 3.
                int limit = 1 + player.getRandom().nextInt(3);

                startEmptyWarning(railgun, limit);

                player.sendMessage(Text.literal("Для рельсотрона требуется высоковольтная батарея"), true);
                player.getInventory().markDirty();
                player.currentScreenHandler.sendContentUpdates();

                return TypedActionResult.success(railgun, false);
            }

            /*
             * -----------------------------------------------------
             * Игрок проигнорировал предупреждение
             * -----------------------------------------------------
             */
            int attempts = incrementEmptyAttempts(railgun);
            int limit = getEmptyLimit(railgun);

            // Пока критическое число попыток ещё не достигнуто — ничего не происходит.
            if (attempts < limit) {
                player.getInventory().markDirty();
                player.currentScreenHandler.sendContentUpdates();

                return TypedActionResult.success(railgun, false);
            }

            /*
             * -----------------------------------------------------
             * КРИТИЧЕСКАЯ ОШИБКА!!! - Игрок слишком много раз попытался запустить пустой рельсотрон.
             * -----------------------------------------------------
             */

            if (!RailgunShotManager.startMalfunction(player)) {
                return TypedActionResult.fail(railgun);
            }

            // Сбрасываем счётчик только после того, как аварийный процесс действительно запущен.
            resetEmptyWarning(railgun);

            player.getInventory().markDirty();
            player.currentScreenHandler.sendContentUpdates();

            return TypedActionResult.success(railgun, false);
        }

        /*
         * =====================================================
         * БАТАРЕЯ УЖЕ УСТАНОВЛЕНА
         * =====================================================
         *
         * Никакой повторной 20-тиковой зарядки.
         */

        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(railgun);
        }

        if (world.isClient()) {
            return TypedActionResult.success(railgun, true);
        }

        if (!(user instanceof ServerPlayerEntity player)) {
            return TypedActionResult.fail(railgun);
        }

        if (RailgunCooldown.isCoolingDown(player)) {
            return TypedActionResult.fail(railgun);
        }

        // Запускаем 20-тиковую подготовку перед выстрелом.
        if (!RailgunShotManager.start(player)) {
            return TypedActionResult.fail(railgun);
        }

        // Только успешно произведённый выстрел отнимает единицу заряда батареи.
        consumeShot(railgun);

        int remaining = getRemainingShots(railgun);

        if (remaining == 0) {
            player.sendMessage(Text.literal("Батарея рельсотрона разряжена"), true);
        } else {
            player.sendMessage(Text.literal("Заряд рельсотрона: " + remaining + "/" + BATTERY_SHOTS), true);
        }

        player.getInventory().markDirty();
        player.currentScreenHandler.sendContentUpdates();

        return TypedActionResult.success(railgun, false);
    }

    /*
     * =========================================================
     * ВСТАВКА БАТАРЕИ — 20 ТИКОВ
     * =========================================================
     */

    @Override
    public void usageTick(World world, LivingEntity entity, ItemStack railgun, int remainingUseTicks) {
        // Если батарея уже установлена, ничего заряжать больше не нужно.
        if (hasBattery(railgun)) {
            return;
        }

        if (!(entity instanceof PlayerEntity player)) {
            return;
        }

        int usedTicks = MAX_USE_TIME - remainingUseTicks;

        if (usedTicks < CHARGE_TICKS) {
            return;
        }

        // Пока шла анимация, игрок мог выбросить батарею.
        if (!hasBatteryAvailable(player)) {
            return;
        }

        /*
         * Клиент сразу отображает состояние CROSSBOW_HOLD.
         * Сам предмет батареи расходует только сервер.
         */
        if (world.isClient()) {
            setBattery(railgun, true);
            railgun.setDamage(0);
            return;
        }

        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return;
        }

        if (!insertBattery(serverPlayer, railgun)) {
            return;
        }

        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS, 1.4F, 1.25F
        );

        serverPlayer.sendMessage(Text.literal("Батарея установлена: " + BATTERY_SHOTS + "/" + BATTERY_SHOTS), true);
    }
}