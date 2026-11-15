package net.erfmd.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.erfmd.item.EMDItems;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

/**
 * <h2>Модификатор выпадения лута</h2>
 * <h3>Пример №1: Добавить выпадение предмета (алмаза) из блока:</h3>
 * {@snippet :
 * LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
 *     // Блок, из которого выпадет предмет
 *     if (Blocks.COBBLESTONE.getLootTableKey() == key && source.isBuiltin()) {
 *         LootPool.Builder pool = LootPool.builder()
 *             // Кол-во предметов
 *             .rolls(ConstantLootNumberProvider.create(1))
 *             // Имя предмета
 *             .with(ItemEntry.builder(Items.DIAMOND))
 *             // Условие, при котором может выпасть предмет
 *             .conditionally(SurvivesExplosionLootCondition.builder())
 *             .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
 *
 *         tableBuilder.pool(pool);
 *     }
 * });
 * }
 * <h3>Пример №2: Добавить выпадение предмета (алмаза) из моба:</h3>
 * {@snippet :
 * LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
 *     // Моб, из которго выпадет предмет
 *     if(EntityType.ZOMBIE.getLootTableId() == key && source.isBuiltin()) {
 *         LootPool.Builder pool = LootPool.builder()
 *             // Кол-во предметов
 *             .rolls(ConstantLootNumberProvider.create(1))
 *             // Имя предмета
 *             .with(ItemEntry.builder(Items.DIAMOND))
 *             // Условие, при котором может выпасть предмет
 *             .conditionally(SurvivesExplosionLootCondition.builder())
 *             .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
 *
 *         tableBuilder.pool(pool);
 *     }
 * });
 * }
 */
public class EMDLootTableModifiers {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if(EntityType.ZOMBIE.getLootTableId() == key && source.isBuiltin()) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(EMDItems.WORKER_DRONE_ARM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder);
            }

            if(EntityType.PIG.getLootTableId() == key && source.isBuiltin()) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(EMDItems.HAM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
