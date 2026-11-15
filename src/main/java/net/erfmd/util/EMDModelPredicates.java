package net.erfmd.util;

import net.erfmd.EMDUniverse;
import net.erfmd.item.EMDItems;
import net.erfmd.item.custom.railgun.RailgunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

/**
 * Добавление анимаций для лука, арбалета и др. огнестрельных орудий
 */

public class EMDModelPredicates {
    public static void registerModelPredicates() {
        registerCustomBow(EMDItems.NETHERITE_BOW);
        registerCustomCrossbow(EMDItems.NETHERITE_CROSSBOW);
        registerRailgun(EMDItems.RAILGUN);
    }

    private static void registerCustomBow(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });

        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }

    private static void registerCustomCrossbow(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return CrossbowItem.isCharged(stack) ? 0.0F : (float) (stack.getMaxUseTime(entity) - entity
                        .getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack, entity);
            }
        });

        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pulling"), (stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
        );

        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("charged"), (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("firework"), (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            return chargedProjectilesComponent != null && chargedProjectilesComponent.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }

    private static void registerRailgun(Item item) {
        // 1. Прогресс зарядки рельсотрона.
        ModelPredicateProviderRegistry.register(item, Identifier.of(EMDUniverse.MOD_ID, "pull"), (stack, world, entity, seed) -> {
            if (entity == null || !entity.isUsingItem() || entity.getActiveItem() != stack || RailgunItem.isCharged(stack)) {
                return 0.0F;
            }

            float progress = (float) entity.getItemUseTime() / RailgunItem.CHARGE_TICKS;
            return Math.min(1.0F, progress);
        });

        // 2. Проверяем, удерживает ли игрок ПКМ.
        ModelPredicateProviderRegistry.register(item, Identifier.of(EMDUniverse.MOD_ID, "pulling"), (stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !RailgunItem.isCharged(stack) ? 1.0F : 0.0F);

        // 3. Проверяем, заряжено ли оружие.
        ModelPredicateProviderRegistry.register(item, Identifier.of(EMDUniverse.MOD_ID, "charged"), (stack, world, entity, seed) ->
                RailgunItem.isCharged(stack) ? 1.0F : 0.0F);

        // 4. Существующая перезарядка после выстрела.
        ModelPredicateProviderRegistry.register(item, Identifier.of(EMDUniverse.MOD_ID, "cooldown"), (stack, world, entity, seed) -> {
            PlayerEntity player = entity instanceof PlayerEntity p ? p : MinecraftClient.getInstance().player;
            return player != null && player.getItemCooldownManager().isCoolingDown(EMDItems.RAILGUN) ? 1.0F : 0.0F;
        });
    }
}
