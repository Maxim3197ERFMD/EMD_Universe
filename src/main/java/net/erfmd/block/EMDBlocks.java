package net.erfmd.block;

import net.erfmd.EMDUniverse;
import net.erfmd.block.custom.*;
import net.erfmd.block.custom.crops.GoldenFlowerCropBlock;
import net.erfmd.block.custom.crops.ParsleyCropBlock;
import net.erfmd.block.custom.crying_obsidian_custom.CryingChiseledObsidianBlock;
import net.erfmd.block.custom.crying_obsidian_custom.CryingObsidianBrickBlock;
import net.erfmd.block.custom.crying_obsidian_custom.CryingObsidianPillarBlock;
import net.erfmd.block.custom.crying_obsidian_custom.CryingPolishedObsidianBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class EMDBlocks {

    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.GOLD_ORE)));
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_GOLD_ORE)));
    public static final Block NETHER_RUBY_ORE = registerBlock("nether_ruby_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHER_QUARTZ_ORE)));
    public static final Block NETHER_IRON_ORE = registerBlock("nether_iron_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHER_GOLD_ORE)));
    public static final Block RAW_RUBY_BLOCK = registerBlock("raw_ruby_block",
            new Block(AbstractBlock.Settings.copy(Blocks.RAW_GOLD_BLOCK)));
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK)));
    public static final Block STONE_TILES = registerBlock("stone_tiles",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block CRACKED_STONE_TILES = registerBlock("cracked_stone_tiles",
            new Block(AbstractBlock.Settings.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final Block POLISHED_BLACKSTONE_TILES = registerBlock("polished_blackstone_tiles",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final Block CRACKED_POLISHED_BLACKSTONE_TILES = registerBlock("cracked_polished_blackstone_tiles",
            new Block(AbstractBlock.Settings.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block SMOOTH_END_STONE = registerBlock("smooth_end_stone",
            new Block(AbstractBlock.Settings.copy(Blocks.END_STONE)));
    public static final Block END_STONE_TILES = registerBlock("end_stone_tiles",
            new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)));



    public static final Block STONE_TILE_STAIRS = registerBlock("stone_tile_stairs",
            new StairsBlock(EMDBlocks.STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)));
    public static final Block STONE_TILE_SLAB = registerBlock("stone_tile_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)));
    public static final Block POLISHED_BLACKSTONE_TILE_STAIRS = registerBlock("polished_blackstone_tile_stairs",
            new StairsBlock(EMDBlocks.POLISHED_BLACKSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS)));
    public static final Block POLISHED_BLACKSTONE_TILE_SLAB = registerBlock("polished_blackstone_tile_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB)));

    public static final Block SMOOTH_END_STONE_STAIRS = registerBlock("smooth_end_stone_stairs",
            new StairsBlock(EMDBlocks.STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_STAIRS)));
    public static final Block SMOOTH_END_STONE_SLAB = registerBlock("smooth_end_stone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_SLAB)));

    public static final Block END_STONE_TILE_STAIRS = registerBlock("end_stone_tile_stairs",
            new StairsBlock(EMDBlocks.STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_STAIRS)));
    public static final Block END_STONE_TILE_SLAB = registerBlock("end_stone_tile_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_SLAB)));



    public static final Block STONE_TILE_WALL = registerBlock("stone_tile_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)));
    public static final Block POLISHED_BLACKSTONE_TILE_WALL = registerBlock("polished_blackstone_tile_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICK_WALL)));
    public static final Block END_STONE_TILE_WALL = registerBlock("end_stone_tile_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL)));



    public static final Block REDSTONE_HEART = registerBlock("redstone_heart",
            new RedstoneHeartBlock(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)));



    public static final Block BLACK_REDSTONE_LAMP = registerBlock("black_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block BLUE_REDSTONE_LAMP = registerBlock("blue_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block BROWN_REDSTONE_LAMP = registerBlock("brown_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block CYAN_REDSTONE_LAMP = registerBlock("cyan_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block GRAY_REDSTONE_LAMP = registerBlock("gray_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block GREEN_REDSTONE_LAMP = registerBlock("green_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block LIGHT_BLUE_REDSTONE_LAMP = registerBlock("light_blue_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block LIGHT_GRAY_REDSTONE_LAMP = registerBlock("light_gray_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block LIME_REDSTONE_LAMP = registerBlock("lime_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block MAGENTA_REDSTONE_LAMP = registerBlock("magenta_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block ORANGE_REDSTONE_LAMP = registerBlock("orange_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block PINK_REDSTONE_LAMP = registerBlock("pink_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block PURPLE_REDSTONE_LAMP = registerBlock("purple_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block RED_REDSTONE_LAMP = registerBlock("red_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block WHITE_REDSTONE_LAMP = registerBlock("white_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block YELLOW_REDSTONE_LAMP = registerBlock("yellow_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));
    public static final Block COLORED_REDSTONE_LAMP = registerBlock("colored_redstone_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP)
                    .luminance(state -> state.get(RedstoneLampBlock.LIT) ? 15 : 0 )));



    public static final Block IRON_BAR_DOOR = registerBlock("iron_bar_door",
            new DoorBlock(BlockSetType.BAMBOO, AbstractBlock.Settings.copy(Blocks.IRON_DOOR).nonOpaque()));
    public static final Block IRON_BAR_TRAPDOOR = registerBlock("iron_bar_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(Blocks.CHAIN)
                    .sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block IRON_BAR_BLOCK = registerBlock("iron_bar_block",
            new IronBarBlock(AbstractBlock.Settings.copy(Blocks.CHAIN).sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_BAR_STAIRS = registerBlock("iron_bar_stairs",
            new StairsBlock(EMDBlocks.IRON_BAR_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.CHAIN).sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_BAR_SLAB = registerBlock("iron_bar_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.CHAIN).sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_BAR_WALL = registerBlock("iron_bar_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.CHAIN).sounds(BlockSoundGroup.METAL)));



    public static final Block CHOCOLATE_CAKE = registerBlockWithoutBlockItem("chocolate_cake",
            new ChocolateCakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Block EXPERIMENT_115 = registerBlockWithoutBlockItem("experiment_115",
            new Experiment_115_Block(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Block EXPERIMENT_803S = registerBlockWithoutBlockItem("experiment_803-s",
            new Experiment_803S_Block(AbstractBlock.Settings.copy(Blocks.CAKE)));



    public static final Block HONEYSUCKLE_BERRY_BUSH = registerBlockWithoutBlockItem("honeysuckle_berry_bush",
            new HoneysuckleBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));



    public static final Block POTATO_BLOCK = registerBlock("potato_block",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHER_WART_BLOCK)));
    public static final Block POISONOUS_POTATO_BLOCK = registerBlock("poisonous_potato_block",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHER_WART_BLOCK)));



    public static final Block ROSE_GOLD_BLOCK = registerBlock("rose_gold_block",
            new Block(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK)));

    public static final Block POLISHED_STONE = registerBlock("polished_stone",
            new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)));
    public static final Block POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            new StairsBlock(EMDBlocks.POLISHED_STONE.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.STONE_STAIRS)));
    public static final Block POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE_SLAB)));
    public static final Block POLISHED_STONE_WALL = registerBlock("polished_stone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE_WALL)));
    public static final Block POLISHED_STONE_PILLAR = registerBlock("polished_stone_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));

    public static final Block SANDSTONE_BRICKS = registerBlock("sandstone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CUT_SANDSTONE)));
    public static final Block CHISELED_SANDSTONE_BRICKS = registerBlock("chiseled_sandstone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CUT_SANDSTONE)));
    public static final Block SANDSTONE_BRICK_STAIRS = registerBlock("sandstone_brick_stairs",
            new StairsBlock(EMDBlocks.SANDSTONE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.SANDSTONE_STAIRS)));
    public static final Block SANDSTONE_BRICK_SLAB = registerBlock("sandstone_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE_SLAB)));
    public static final Block SANDSTONE_BRICK_WALL = registerBlock("sandstone_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE_WALL)));

    public static final Block POLISHED_NETHER_BRICKS = registerBlock("polished_nether_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHER_BRICKS)));
    public static final Block POLISHED_NETHER_BRICK_STAIRS = registerBlock("polished_nether_brick_stairs",
            new StairsBlock(EMDBlocks.POLISHED_NETHER_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_STAIRS)));
    public static final Block POLISHED_NETHER_BRICK_SLAB = registerBlock("polished_nether_bricks_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_SLAB)));

    public static final Block WARP_NETHER_BRICKS = registerBlock("warp_nether_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));
    public static final Block WARP_NETHER_BRICK_STAIRS = registerBlock("warp_nether_brick_stairs",
            new StairsBlock(EMDBlocks.WARP_NETHER_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICK_STAIRS)));
    public static final Block WARP_NETHER_BRICK_SLAB = registerBlock("warp_nether_bricks_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICK_SLAB)));
    public static final Block WARP_NETHER_BRICK_WALL = registerBlock("warp_nether_bricks_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICK_WALL)));



    public static final Block SNOW_BRICKS = registerBlock("snow_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
    public static final Block SNOW_BRICK_STAIRS = registerBlock("snow_brick_stairs",
            new StairsBlock(EMDBlocks.SNOW_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
    public static final Block SNOW_BRICK_SLAB = registerBlock("snow_bricks_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
    public static final Block SNOW_BRICK_WALL = registerBlock("snow_bricks_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));



    public static final Block OBSIDIAN_BRICKS = registerBlock("obsidian_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block OBSIDIAN_BRICK_STAIRS = registerBlock("obsidian_brick_stairs",
            new StairsBlock(EMDBlocks.OBSIDIAN_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block OBSIDIAN_BRICK_SLAB = registerBlock("obsidian_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block OBSIDIAN_BRICK_WALL = registerBlock("obsidian_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));

    public static final Block POLISHED_OBSIDIAN = registerBlock("polished_obsidian",
            new Block(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block POLISHED_OBSIDIAN_STAIRS = registerBlock("polished_obsidian_stairs",
            new StairsBlock(EMDBlocks.POLISHED_OBSIDIAN.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block POLISHED_OBSIDIAN_SLAB = registerBlock("polished_obsidian_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block POLISHED_OBSIDIAN_WALL = registerBlock("polished_obsidian_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block CHISELED_OBSIDIAN = registerBlock("chiseled_obsidian",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block OBSIDIAN_PILLAR = registerBlock("obsidian_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));



    public static final Block CRYING_OBSIDIAN_BRICKS = registerBlock("crying_obsidian_bricks",
            new CryingObsidianBrickBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_OBSIDIAN_BRICK_STAIRS = registerBlock("crying_obsidian_brick_stairs",
            new StairsBlock(EMDBlocks.CRYING_OBSIDIAN_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_OBSIDIAN_BRICK_SLAB = registerBlock("crying_obsidian_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_OBSIDIAN_BRICK_WALL = registerBlock("crying_obsidian_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));

    public static final Block CRYING_POLISHED_OBSIDIAN = registerBlock("crying_polished_obsidian",
            new CryingPolishedObsidianBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_POLISHED_OBSIDIAN_STAIRS = registerBlock("crying_polished_obsidian_stairs",
            new StairsBlock(EMDBlocks.CRYING_POLISHED_OBSIDIAN.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_POLISHED_OBSIDIAN_SLAB = registerBlock("crying_polished_obsidian_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_POLISHED_OBSIDIAN_WALL = registerBlock("crying_polished_obsidian_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_CHISELED_OBSIDIAN = registerBlock("crying_chiseled_obsidian",
            new CryingChiseledObsidianBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));
    public static final Block CRYING_OBSIDIAN_PILLAR = registerBlock("crying_obsidian_pillar",
            new CryingObsidianPillarBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN)));



    public static final Block TERRACOTTA_BRICKS = registerBlock("terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.TERRACOTTA)));
    public static final Block WHITE_TERRACOTTA_BRICKS = registerBlock("white_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_TERRACOTTA)));
    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS = registerBlock("light_gray_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)));
    public static final Block GRAY_TERRACOTTA_BRICKS = registerBlock("gray_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.GRAY_TERRACOTTA)));
    public static final Block BLACK_TERRACOTTA_BRICKS = registerBlock("black_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.BLACK_TERRACOTTA)));
    public static final Block BROWN_TERRACOTTA_BRICKS = registerBlock("brown_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.BROWN_TERRACOTTA)));
    public static final Block RED_TERRACOTTA_BRICKS = registerBlock("red_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.RED_TERRACOTTA)));
    public static final Block ORANGE_TERRACOTTA_BRICKS = registerBlock("orange_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.ORANGE_TERRACOTTA)));
    public static final Block YELLOW_TERRACOTTA_BRICKS = registerBlock("yellow_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.YELLOW_TERRACOTTA)));
    public static final Block LIME_TERRACOTTA_BRICKS = registerBlock("lime_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.LIME_TERRACOTTA)));
    public static final Block GREEN_TERRACOTTA_BRICKS = registerBlock("green_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.GREEN_TERRACOTTA)));
    public static final Block CYAN_TERRACOTTA_BRICKS = registerBlock("cyan_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CYAN_TERRACOTTA)));
    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS = registerBlock("light_blue_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_TERRACOTTA)));
    public static final Block BLUE_TERRACOTTA_BRICKS = registerBlock("blue_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.BLUE_TERRACOTTA)));
    public static final Block PURPLE_TERRACOTTA_BRICKS = registerBlock("purple_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.PURPLE_TERRACOTTA)));
    public static final Block MAGENTA_TERRACOTTA_BRICKS = registerBlock("magenta_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.MAGENTA_TERRACOTTA)));
    public static final Block PINK_TERRACOTTA_BRICKS = registerBlock("pink_terracotta_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.PINK_TERRACOTTA)));



    public static final Block PARSLEY_CROP = registerBlockWithoutBlockItem("parsley_crop",
            new ParsleyCropBlock(AbstractBlock.Settings.create().noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
                            .mapColor(MapColor.DARK_GREEN)));

    public static final Block GOLDEN_FLOWER_CROP = registerBlockWithoutBlockItem("golden_flower_crop",
            new GoldenFlowerCropBlock(AbstractBlock.Settings.create().noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
                            .mapColor(MapColor.DARK_GREEN)));

    public static final Block GOLDEN_FLOWER = registerBlockWithoutBlockItem("golden_flower",
            new FlowerBlock(StatusEffects.INSTANT_DAMAGE, 1.0F, AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)
                            .offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block BEEKEEPING_TABLE = registerBlock("beekeeping_table",
            new BeekeepingTableBlock(AbstractBlock.Settings.copy(Blocks.FLETCHING_TABLE).nonOpaque()));



    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(EMDUniverse.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(EMDUniverse.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(EMDUniverse.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){
        EMDUniverse.LOGGER.info("Registering Mod Items for " + EMDUniverse.MOD_ID);
    }
}
