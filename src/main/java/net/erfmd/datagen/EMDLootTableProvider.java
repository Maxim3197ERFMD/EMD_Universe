package net.erfmd.datagen;

import net.erfmd.block.custom.HoneysuckleBerryBushBlock;
import net.erfmd.block.custom.crops.GoldenFlowerCropBlock;
import net.erfmd.block.custom.crops.ParsleyCropBlock;
import net.erfmd.item.EMDItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.erfmd.block.EMDBlocks;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EMDLootTableProvider extends FabricBlockLootTableProvider {

    public EMDLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(EMDBlocks.RUBY_BLOCK);
        addDrop(EMDBlocks.RAW_RUBY_BLOCK);
        addDrop(EMDBlocks.ROSE_GOLD_BLOCK);
        addDrop(EMDBlocks.STONE_TILES);
        addDrop(EMDBlocks.CRACKED_STONE_TILES);
        addDrop(EMDBlocks.POLISHED_BLACKSTONE_TILES);
        addDrop(EMDBlocks.CRACKED_POLISHED_BLACKSTONE_TILES);
        addDrop(EMDBlocks.SMOOTH_END_STONE);
        addDrop(EMDBlocks.END_STONE_TILES);
        addDrop(EMDBlocks.IRON_BAR_BLOCK);
        addDrop(EMDBlocks.IRON_BAR_TRAPDOOR);
        addDrop(EMDBlocks.BLACK_REDSTONE_LAMP);
        addDrop(EMDBlocks.BLUE_REDSTONE_LAMP);
        addDrop(EMDBlocks.BROWN_REDSTONE_LAMP);
        addDrop(EMDBlocks.CYAN_REDSTONE_LAMP);
        addDrop(EMDBlocks.GRAY_REDSTONE_LAMP);
        addDrop(EMDBlocks.GREEN_REDSTONE_LAMP);
        addDrop(EMDBlocks.LIGHT_BLUE_REDSTONE_LAMP);
        addDrop(EMDBlocks.LIGHT_GRAY_REDSTONE_LAMP);
        addDrop(EMDBlocks.LIME_REDSTONE_LAMP);
        addDrop(EMDBlocks.MAGENTA_REDSTONE_LAMP);
        addDrop(EMDBlocks.ORANGE_REDSTONE_LAMP);
        addDrop(EMDBlocks.PINK_REDSTONE_LAMP);
        addDrop(EMDBlocks.PURPLE_REDSTONE_LAMP);
        addDrop(EMDBlocks.RED_REDSTONE_LAMP);
        addDrop(EMDBlocks.WHITE_REDSTONE_LAMP);
        addDrop(EMDBlocks.YELLOW_REDSTONE_LAMP);
        addDrop(EMDBlocks.REDSTONE_HEART);
        addDrop(EMDBlocks.POTATO_BLOCK);
        addDrop(EMDBlocks.POISONOUS_POTATO_BLOCK);
        addDrop(EMDBlocks.POLISHED_STONE);
        addDrop(EMDBlocks.POLISHED_STONE_PILLAR);
        addDrop(EMDBlocks.SANDSTONE_BRICKS);
        addDrop(EMDBlocks.CHISELED_SANDSTONE_BRICKS);
        addDrop(EMDBlocks.POLISHED_NETHER_BRICKS);
        addDrop(EMDBlocks.WARP_NETHER_BRICKS);
        addDrop(EMDBlocks.TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.WHITE_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.GRAY_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.BLACK_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.BROWN_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.RED_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.ORANGE_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.YELLOW_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.LIME_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.GREEN_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.CYAN_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.BLUE_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.PURPLE_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.MAGENTA_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.PINK_TERRACOTTA_BRICKS);
        addDrop(EMDBlocks.POLISHED_NETHER_BRICKS);
        addDrop(EMDBlocks.WARP_NETHER_BRICKS);
        addDrop(EMDBlocks.OBSIDIAN_BRICKS);
        addDrop(EMDBlocks.POLISHED_OBSIDIAN);
        addDrop(EMDBlocks.CHISELED_OBSIDIAN);
        addDrop(EMDBlocks.OBSIDIAN_PILLAR);
        addDrop(EMDBlocks.CRYING_OBSIDIAN_BRICKS);
        addDrop(EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        addDrop(EMDBlocks.CRYING_CHISELED_OBSIDIAN);
        addDrop(EMDBlocks.CRYING_OBSIDIAN_PILLAR);

        addDrop(EMDBlocks.SMOOTH_END_STONE_STAIRS);
        addDrop(EMDBlocks.STONE_TILE_STAIRS);
        addDrop(EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS);
        addDrop(EMDBlocks.END_STONE_TILE_STAIRS);
        addDrop(EMDBlocks.IRON_BAR_STAIRS);
        addDrop(EMDBlocks.POLISHED_STONE_STAIRS);
        addDrop(EMDBlocks.SANDSTONE_BRICK_STAIRS);
        addDrop(EMDBlocks.POLISHED_NETHER_BRICK_STAIRS);
        addDrop(EMDBlocks.WARP_NETHER_BRICK_STAIRS);
        addDrop(EMDBlocks.OBSIDIAN_BRICK_STAIRS);
        addDrop(EMDBlocks.POLISHED_OBSIDIAN_STAIRS);
        addDrop(EMDBlocks.CRYING_OBSIDIAN_BRICK_STAIRS);
        addDrop(EMDBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS);

        addDrop(EMDBlocks.IRON_BAR_DOOR, doorDrops(EMDBlocks.IRON_BAR_DOOR));

        addDrop(EMDBlocks.SMOOTH_END_STONE_SLAB, slabDrops(EMDBlocks.SMOOTH_END_STONE_SLAB));
        addDrop(EMDBlocks.STONE_TILE_SLAB, slabDrops(EMDBlocks.STONE_TILE_SLAB));
        addDrop(EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, slabDrops(EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB));
        addDrop(EMDBlocks.END_STONE_TILE_SLAB, slabDrops(EMDBlocks.END_STONE_TILE_SLAB));
        addDrop(EMDBlocks.IRON_BAR_SLAB, slabDrops(EMDBlocks.IRON_BAR_SLAB));
        addDrop(EMDBlocks.POLISHED_STONE_SLAB, slabDrops(EMDBlocks.POLISHED_STONE_SLAB));
        addDrop(EMDBlocks.SANDSTONE_BRICK_SLAB, slabDrops(EMDBlocks.SANDSTONE_BRICK_SLAB));
        addDrop(EMDBlocks.POLISHED_NETHER_BRICK_SLAB, slabDrops(EMDBlocks.POLISHED_NETHER_BRICK_SLAB));
        addDrop(EMDBlocks.WARP_NETHER_BRICK_SLAB, slabDrops(EMDBlocks.WARP_NETHER_BRICK_SLAB));
        addDrop(EMDBlocks.SNOW_BRICK_SLAB, slabDrops(EMDBlocks.SNOW_BRICK_SLAB));
        addDrop(EMDBlocks.OBSIDIAN_BRICK_SLAB, slabDrops(EMDBlocks.OBSIDIAN_BRICK_SLAB));
        addDrop(EMDBlocks.POLISHED_OBSIDIAN_SLAB, slabDrops(EMDBlocks.POLISHED_OBSIDIAN_SLAB));
        addDrop(EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB, slabDrops(EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB));
        addDrop(EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB, slabDrops(EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB));

        addDrop(EMDBlocks.STONE_TILE_WALL);
        addDrop(EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL);
        addDrop(EMDBlocks.END_STONE_TILE_WALL);
        addDrop(EMDBlocks.POLISHED_STONE_WALL);
        addDrop(EMDBlocks.SANDSTONE_BRICK_WALL);
        addDrop(EMDBlocks.WARP_NETHER_BRICK_WALL);
        addDrop(EMDBlocks.SNOW_BRICK_WALL);
        addDrop(EMDBlocks.OBSIDIAN_BRICK_WALL);
        addDrop(EMDBlocks.POLISHED_OBSIDIAN_WALL);
        addDrop(EMDBlocks.CRYING_OBSIDIAN_BRICK_WALL);
        addDrop(EMDBlocks.CRYING_POLISHED_OBSIDIAN_WALL);
        addDrop(EMDBlocks.IRON_BAR_WALL);

        addDrop(EMDBlocks.RUBY_ORE, multipleOreDrops(EMDBlocks.RUBY_ORE, EMDItems.RAW_RUBY, 1, 1));
        addDrop(EMDBlocks.DEEPSLATE_RUBY_ORE, multipleOreDrops(EMDBlocks.DEEPSLATE_RUBY_ORE, EMDItems.RAW_RUBY, 1, 1));
        addDrop(EMDBlocks.NETHER_RUBY_ORE, multipleOreDrops(EMDBlocks.NETHER_RUBY_ORE, EMDItems.RAW_RUBY, 1, 1));
        addDrop(EMDBlocks.NETHER_IRON_ORE, multipleOreDrops(EMDBlocks.NETHER_IRON_ORE, Items.IRON_NUGGET, 2, 5));

        addDrop(EMDBlocks.BEEKEEPING_TABLE);



        this.addDrop(EMDBlocks.HONEYSUCKLE_BERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block,LootTable.builder().pool(LootPool.builder().conditionally(
                                                BlockStatePropertyLootCondition.builder(EMDBlocks.HONEYSUCKLE_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(HoneysuckleBerryBushBlock.AGE, 3))
                                        )
                                        .with(ItemEntry.builder(EMDItems.HONEYSUCKLE))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).pool(LootPool.builder().conditionally(
                                        BlockStatePropertyLootCondition.builder(EMDBlocks.HONEYSUCKLE_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(HoneysuckleBerryBushBlock.AGE, 2))
                                ).with(ItemEntry.builder(EMDItems.HONEYSUCKLE))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));



        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(EMDBlocks.PARSLEY_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(ParsleyCropBlock.AGE, ParsleyCropBlock.MAX_AGE));
        this.addDrop(EMDBlocks.PARSLEY_CROP, this.cropDrops(EMDBlocks.PARSLEY_CROP, EMDItems.PARSLEY, EMDItems.PARSLEY_SEEDS, builder));

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(EMDBlocks.GOLDEN_FLOWER_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(GoldenFlowerCropBlock.AGE, GoldenFlowerCropBlock.MAX_AGE));
        this.addDrop(EMDBlocks.GOLDEN_FLOWER_CROP, this.cropDrops(EMDBlocks.GOLDEN_FLOWER_CROP, EMDItems.GOLDEN_FLOWER, EMDItems.GOLDEN_FLOWER_PETALS, builder2));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}