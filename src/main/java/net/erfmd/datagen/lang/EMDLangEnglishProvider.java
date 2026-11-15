package net.erfmd.datagen.lang;

import net.erfmd.block.EMDBlocks;
import net.erfmd.item.EMDItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EMDLangEnglishProvider extends FabricLanguageProvider {
    public EMDLangEnglishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(EMDItems.RAW_RUBY, "Raw Ruby");
        translationBuilder.add(EMDItems.RUBY, "Ruby");

        translationBuilder.add(EMDItems.PIECE_OF_CAKE, "Piece Of Cake");
        translationBuilder.add(EMDItems.PIECE_OF_CHOCOLATE_CAKE, "Piece Of Chocolate Cake");
        translationBuilder.add(EMDItems.PIECE_OF_EXPERIMENT_115, "Piece Of \"Experiment 115\"");
        translationBuilder.add(EMDItems.PIECE_OF_EXPERIMENT_803S, "Piece Of \"Experiment 803-S\"");
        translationBuilder.add(EMDItems.CARAMEL, "Caramel");
        translationBuilder.add(EMDItems.CARAMEL_APPLE, "Caramel Apple");

        translationBuilder.add(EMDItems.CHOCOLATE_CAKE, "Chocolate Cake");
        translationBuilder.add(EMDItems.EXPERIMENT_115, "Experiment 115");
        translationBuilder.add(EMDItems.EXPERIMENT_803S, "Experiment 803-S");

        translationBuilder.add(EMDItems.ROSE_GOLDEN_APPLE, "Rose Golden Apple");

        translationBuilder.add(EMDItems.ROSE_GOLD_INGOT, "Rose Gold Ingot");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_SWORD, "Rose Golden Sword");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_AXE, "Rose Golden Axe");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_PICKAXE, "Rose Golden Pickaxe");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_SHOVEL, "Rose Golden Shovel");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_HOE, "Rose Golden Hoe");

        translationBuilder.add(EMDItems.ROSE_GOLDEN_HELMET, "Rose Golden Helmet");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_CHESTPLATE, "Rose Golden Chestplate");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_LEGGINGS, "Rose Golden Leggings");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_BOOTS, "Rose Golden Boots");

        translationBuilder.add(EMDItems.COPPER_COIL, "Copper Coil");
        translationBuilder.add(EMDItems.COPPER_WIRE, "Copper Cable");
        translationBuilder.add(EMDItems.ACCUMULATOR, "Accumulator");
        translationBuilder.add(EMDItems.ENERGY_BALL, "Energy Ball");
        translationBuilder.add(EMDItems.SPECIAL_STORAGE, "Special Storage");
        translationBuilder.add(EMDItems.EMERALD_DUST, "Emerald Dust");
        translationBuilder.add(EMDItems.DIAMOND_DUST, "Diamond Dust");
        translationBuilder.add(EMDItems.METAL_ALLOY, "Metal Alloy");
        translationBuilder.add(EMDItems.WARPED_NETHERITE_INGOT, "Warped Netherite Ingot");
        translationBuilder.add(EMDItems.HIGH_VOLTAGE_BATTERY, "High Voltage Battery");

        translationBuilder.add(EMDItems.DISASSEMBLY_CHAINSAW, "Disassembly Chainsaw");
        translationBuilder.add(EMDItems.LM85_BLADE, "LM-85 Blade");
        translationBuilder.add(EMDItems.NANOMACHINES, "Nano-Machines");

        translationBuilder.add(EMDItems.NETHERITE_BOW, "Netherite Bow");
        translationBuilder.add(EMDItems.NETHERITE_CROSSBOW, "Netherite Crossbow");

        translationBuilder.add(EMDItems.HAM, "Ham");
        translationBuilder.add(EMDItems.HAM_BAT, "Ham Bat");
        translationBuilder.add(EMDItems.LOLLICHOP, "Lollichop");
        translationBuilder.add(EMDItems.CANDY_CANE, "Candy Cane");
        translationBuilder.add(EMDItems.WORKER_DRONE_ARM, "Worker Drone Arm");

        translationBuilder.add(EMDItems.CHERRIES, "Cherries");
        translationBuilder.add(EMDItems.BANANA, "Banana");
        translationBuilder.add(EMDItems.HONEYSUCKLE, "Honeysuckle");
        translationBuilder.add(EMDItems.CHERRY_PIE, "Cherry Pie");
        translationBuilder.add(EMDItems.HONEYSUCKLE_PIE, "Honeysuckle Pie");

        translationBuilder.add(EMDItems.WARHAMMER_SOUP, "Warhammer Soup");

        translationBuilder.add(EMDBlocks.POTATO_BLOCK, "Potato Block");
        translationBuilder.add(EMDBlocks.POISONOUS_POTATO_BLOCK, "Poisonous Potato Block");

        translationBuilder.add(EMDBlocks.RUBY_ORE, "Ruby Ore");
        translationBuilder.add(EMDBlocks.DEEPSLATE_RUBY_ORE, "Deepslate Ruby Ore");
        translationBuilder.add(EMDBlocks.NETHER_RUBY_ORE, "Iron Bar Trapdoor");
        translationBuilder.add(EMDBlocks.NETHER_IRON_ORE, "Nether Iron Ore");
        translationBuilder.add(EMDBlocks.RAW_RUBY_BLOCK, "Block of Raw Ruby");
        translationBuilder.add(EMDBlocks.RUBY_BLOCK, "Block of Ruby");
        translationBuilder.add(EMDBlocks.ROSE_GOLD_BLOCK, "Block of Rose Gold");

        translationBuilder.add(EMDBlocks.STONE_TILES, "Stone Tiles");
        translationBuilder.add(EMDBlocks.STONE_TILE_SLAB, "Stone Tile Slab");
        translationBuilder.add(EMDBlocks.STONE_TILE_STAIRS, "Stone Tile Stairs");
        translationBuilder.add(EMDBlocks.STONE_TILE_WALL, "Stone Tile Wall");
        translationBuilder.add(EMDBlocks.CRACKED_STONE_TILES, "Cracked Stone Tiles");

        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILES, "Polished Blackstone Tiles");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, "Polished Blackstone Tile Slab");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, "Polished Blackstone Tile Wall");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, "Polished Blackstone Tile Stairs");
        translationBuilder.add(EMDBlocks.CRACKED_POLISHED_BLACKSTONE_TILES, "Cracked Polished Blackstone Tiles");

        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE, "Smooth End Stone");
        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE_SLAB, "Smooth End Stone Slab");
        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE_STAIRS, "Smooth End Stone Stairs");
        translationBuilder.add(EMDBlocks.END_STONE_TILES, "End Stone Tiles");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_SLAB, "End Stone Tile Slab");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_STAIRS, "End Stone Tile Stairs");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_WALL, "End Stone Tile Wall");

        translationBuilder.add(EMDBlocks.POLISHED_STONE, "Polished Stone");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_SLAB, "Polished Stone Slab");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_STAIRS, "Polished Stone Stairs");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_WALL, "Polished Stone Wall");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_PILLAR, "Polished Stone Pillar");

        translationBuilder.add(EMDBlocks.SANDSTONE_BRICKS, "Sandstone Bricks");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_SLAB, "Sandstone Brick Slab");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_STAIRS, "Sandstone Brick Stairs");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_WALL, "Sandstone Brick Wall");
        translationBuilder.add(EMDBlocks.CHISELED_SANDSTONE_BRICKS, "Chiseled Sandstone Bricks");

        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICKS, "Polished Nether Bricks");
        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICK_SLAB, "Polished Nether Brick Slab");
        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICK_STAIRS, "Polished Nether Brick Stairs");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICKS, "Warp Nether Bricks");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_SLAB, "Warp Nether Brick Slab");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_STAIRS, "Warp Nether Brick Stairs");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_WALL, "Warp Nether Brick Wall");

        translationBuilder.add(EMDBlocks.SNOW_BRICKS, "Snow Bricks");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_SLAB, "Snow Brick Slab");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_STAIRS, "Snow Brick Stairs");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_WALL, "Snow Brick Wall");

        translationBuilder.add(EMDBlocks.TERRACOTTA_BRICKS, "Terracotta Bricks");
        translationBuilder.add(EMDBlocks.WHITE_TERRACOTTA_BRICKS, "White Terracotta Bricks");
        translationBuilder.add(EMDBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, "Light Gray Terracotta Bricks");
        translationBuilder.add(EMDBlocks.GRAY_TERRACOTTA_BRICKS, "Gray Terracotta Bricks");
        translationBuilder.add(EMDBlocks.BLACK_TERRACOTTA_BRICKS, "Black Terracotta Bricks");
        translationBuilder.add(EMDBlocks.BROWN_TERRACOTTA_BRICKS, "Brown Terracotta Bricks");
        translationBuilder.add(EMDBlocks.RED_TERRACOTTA_BRICKS, "Red Terracotta Bricks");
        translationBuilder.add(EMDBlocks.ORANGE_TERRACOTTA_BRICKS, "Orange Terracotta Bricks");
        translationBuilder.add(EMDBlocks.YELLOW_TERRACOTTA_BRICKS, "Yellow Terracotta Bricks");
        translationBuilder.add(EMDBlocks.LIME_TERRACOTTA_BRICKS, "Lime Terracotta Bricks");
        translationBuilder.add(EMDBlocks.GREEN_TERRACOTTA_BRICKS, "Green Terracotta Bricks");
        translationBuilder.add(EMDBlocks.CYAN_TERRACOTTA_BRICKS, "Cyan Terracotta Bricks");
        translationBuilder.add(EMDBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, "Light Blue Terracotta Bricks");
        translationBuilder.add(EMDBlocks.BLUE_TERRACOTTA_BRICKS, "Blue Terracotta Bricks");
        translationBuilder.add(EMDBlocks.PURPLE_TERRACOTTA_BRICKS, "Purple Terracotta Bricks");
        translationBuilder.add(EMDBlocks.MAGENTA_TERRACOTTA_BRICKS, "Magenta Terracotta Bricks");
        translationBuilder.add(EMDBlocks.PINK_TERRACOTTA_BRICKS, "Pink Terracotta Bricks");

        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICKS, "Obsidian Bricks");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_SLAB, "Obsidian Brick Slab");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_STAIRS, "Obsidian Brick Stairs");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_WALL, "Obsidian Brick Wall");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN, "Polished Obsidian");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_SLAB, "Polished Obsidian Slab");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_STAIRS, "Polished Obsidian Stairs");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_WALL, "Polished Obsidian Wall");
        translationBuilder.add(EMDBlocks.CHISELED_OBSIDIAN, "Chiseled Obsidian");
        translationBuilder.add(EMDBlocks.OBSIDIAN_PILLAR, "Obsidian Pillar");

        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICKS, "Crying Obsidian Bricks");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB, "Crying Obsidian Brick Slab");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICK_STAIRS, "Crying Obsidian Brick Stairs");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICK_WALL, "Crying Obsidian Brick Wall");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN, "Crying Polished Obsidian");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB, "Crying Polished Obsidian Slab");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS, "Crying Polished Obsidian Stairs");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN_WALL, "Crying Polished Obsidian Wall");
        translationBuilder.add(EMDBlocks.CRYING_CHISELED_OBSIDIAN, "Crying Chiseled Obsidian");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_PILLAR, "Crying Obsidian Pillar");

        translationBuilder.add(EMDBlocks.IRON_BAR_DOOR, "Iron Bar Door");
        translationBuilder.add(EMDBlocks.IRON_BAR_BLOCK, "Iron Bar Block");
        translationBuilder.add(EMDBlocks.IRON_BAR_STAIRS, "Iron Bar Stairs");
        translationBuilder.add(EMDBlocks.IRON_BAR_SLAB, "Iron Bar Slab");
        translationBuilder.add(EMDBlocks.IRON_BAR_TRAPDOOR, "Iron Bar Trapdoor");
        translationBuilder.add(EMDBlocks.IRON_BAR_WALL, "Iron Bar Wall");

        translationBuilder.add(EMDBlocks.REDSTONE_HEART, "Redstone Heart");

        translationBuilder.add(EMDBlocks.BEEKEEPING_TABLE, "Beekeeping Table");

        translationBuilder.add(EMDBlocks.COLORED_REDSTONE_LAMP, "Colored Redstone Lamp");
        translationBuilder.add(EMDBlocks.BLACK_REDSTONE_LAMP, "Black Redstone Lamp");
        translationBuilder.add(EMDBlocks.BLUE_REDSTONE_LAMP, "Blue Redstone Lamp");
        translationBuilder.add(EMDBlocks.BROWN_REDSTONE_LAMP, "Brown Redstone Lamp");
        translationBuilder.add(EMDBlocks.CYAN_REDSTONE_LAMP, "Cyan Redstone Lamp");
        translationBuilder.add(EMDBlocks.GRAY_REDSTONE_LAMP, "Gray Redstone Lamp");
        translationBuilder.add(EMDBlocks.GREEN_REDSTONE_LAMP, "Green Redstone Lamp");
        translationBuilder.add(EMDBlocks.LIGHT_BLUE_REDSTONE_LAMP, "Light Blue Redstone Lamp");
        translationBuilder.add(EMDBlocks.LIGHT_GRAY_REDSTONE_LAMP, "Light Gray Redstone Lamp");
        translationBuilder.add(EMDBlocks.LIME_REDSTONE_LAMP, "Lime Redstone Lamp");
        translationBuilder.add(EMDBlocks.MAGENTA_REDSTONE_LAMP, "Magenta Redstone Lamp");
        translationBuilder.add(EMDBlocks.ORANGE_REDSTONE_LAMP, "Orange Redstone Lamp");
        translationBuilder.add(EMDBlocks.PINK_REDSTONE_LAMP, "Pink Redstone Lamp");
        translationBuilder.add(EMDBlocks.PURPLE_REDSTONE_LAMP, "Purple Redstone Lamp");
        translationBuilder.add(EMDBlocks.RED_REDSTONE_LAMP, "Red Redstone Lamp");
        translationBuilder.add(EMDBlocks.WHITE_REDSTONE_LAMP, "White Redstone Lamp");
        translationBuilder.add(EMDBlocks.YELLOW_REDSTONE_LAMP, "Yellow Redstone Lamp");

        translationBuilder.add(EMDItems.OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE, "Smithing Template");
        translationBuilder.add(EMDItems.LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE, "Smithing Template");
        translationBuilder.add(EMDItems.SCORPION_ARMOR_TRIM_SMITHING_TEMPLATE, "Smithing Template");

        translationBuilder.add(EMDBlocks.PARSLEY_CROP, "Parsley Crop");

        translationBuilder.add(EMDItems.PARSLEY, "Parsley");
        translationBuilder.add(EMDItems.PARSLEY_SEEDS, "Parsley Seeds");

        translationBuilder.add(EMDBlocks.GOLDEN_FLOWER_CROP, "Golden Flower Crop");

        translationBuilder.add(EMDItems.GOLDEN_FLOWER, "Golden Flower");
        translationBuilder.add(EMDItems.GOLDEN_FLOWER_PETALS, "Golden Buttercups");

        translationBuilder.add("entity.minecraft.villager.beekeeper", "Beekeeper");

        translationBuilder.add("block.minecraft.banner.absolute_solver", "Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.black", "Black Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.blue", "Blue Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.brown", "Brown Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.cyan", "Cyan Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.gray", "Gray Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.green", "Green Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.light_blue", "Light Blue Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.light_gray", "Light Gray Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.lime", "Lime Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.magenta", "Magenta Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.orange", "Orange Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.pink", "Pink Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.purple", "Purple Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.red", "Red Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.white", "White Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.yellow", "Yellow Absolute Solver");

        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull", "Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.black", "Black Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.blue", "Blue Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.brown", "Brown Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.cyan", "Cyan Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.gray", "Gray Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.green", "Green Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.light_blue", "Light Blue Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.light_gray", "light Gray Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.lime", "Lime Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.magenta", "Magenta Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.orange", "Orange Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.pink", "Pink Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.purple", "Purple Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.red", "Red Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.white", "White Disassembly Drone Skull");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.yellow", "Yellow Disassembly Drone Skull");

        translationBuilder.add("block.minecraft.banner.jc_jenson_logo", "JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.black", "Чёрный JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.blue", "Синий JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.brown", "Коричневый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.cyan", "Бирюзовый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.gray", "Серый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.green", "Зелёный JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.light_blue", "Голубой JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.light_gray", "Светло-серый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.lime", "Лаймовый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.magenta", "Пурпурный JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.orange", "Оранжевый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.pink", "Розовый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.purple", "Фиолетовый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.red", "Красный JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.white", "Белый JCJenson Logo");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.yellow", "Жёлтый JCJenson Logo");

        translationBuilder.add("block.minecraft.banner.worker_drone_avatar", "Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.black", "Чёрный Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.blue", "Синий Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.brown", "Коричневый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.cyan", "Бирюзовый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.gray", "Серый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.green", "Зелёный Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.light_blue", "Голубой Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.light_gray", "Светло-серый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.lime", "Лаймовый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.magenta", "Пурпурный Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.orange", "Оранжевый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.pink", "Розовый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.purple", "Фиолетовый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.red", "Красный Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.white", "Белый Worker Drone Avatar");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.yellow", "Жёлтый Worker Drone Avatar");

        translationBuilder.add("item.minecraft.potion.effect.wither", "Potion of Wither");
        translationBuilder.add("item.minecraft.potion.effect.long_wither", "Potion of Wither");
        translationBuilder.add("item.minecraft.potion.effect.strong_wither", "Potion of Wither");
        translationBuilder.add("item.minecraft.splash_potion.effect.wither", "Splash Potion of Wither");
        translationBuilder.add("item.minecraft.splash_potion.effect.long_wither", "Splash Potion of Wither");
        translationBuilder.add("item.minecraft.splash_potion.effect.strong_wither", "Splash Potion of Wither");
        translationBuilder.add("item.minecraft.lingering_potion.effect.wither", "Lingering Potion of Wither");
        translationBuilder.add("item.minecraft.lingering_potion.effect.long_wither", "Lingering Potion of Wither");
        translationBuilder.add("item.minecraft.lingering_potion.effect.strong_wither", "Lingering Potion of Wither");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.wither", "Arrow of Wither");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.long_wither", "Arrow of Wither");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.strong_wither", "Arrow of Wither");

        translationBuilder.add("item.minecraft.potion.effect.blindness", "Potion of Blindness");
        translationBuilder.add("item.minecraft.potion.effect.long_blindness", "Potion of Blindness");
        translationBuilder.add("item.minecraft.splash_potion.effect.blindness", "Splash Potion of Blindness");
        translationBuilder.add("item.minecraft.splash_potion.effect.long_blindness", "Splash Potion of Blindness");
        translationBuilder.add("item.minecraft.lingering_potion.effect.blindness", "Lingering Potion of Blindness");
        translationBuilder.add("item.minecraft.lingering_potion.effect.long_blindness", "Lingering Potion of Blindness");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.blindness", "Arrow of Blindness");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.long_blindness", "Arrow of Blindness");

        translationBuilder.add("item.minecraft.potion.effect.unluck", "Potion of Unluck");
        translationBuilder.add("item.minecraft.splash_potion.effect.unluck", "Splash Potion of Unluck");
        translationBuilder.add("item.minecraft.lingering_potion.effect.unluck", "Lingering Potion of Unluck");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.unluck", "Arrow of Unluck");

        translationBuilder.add("advancements.husbandry.warhammer_soup.title", "Warhammer Soup");
        translationBuilder.add("advancements.husbandry.warhammer_soup.description", "Prepare and eat soup before going into battle for the Emperor!");

        translationBuilder.add("trim_pattern.emd_universe.oil_sun", "«Oil Sun» Armor Trim");
        translationBuilder.add("trim_pattern.emd_universe.love_harem", "«Love Harem» Armor Trim");
        translationBuilder.add("trim_pattern.emd_universe.scorpion", "Scorpion Armor Trim");

        translationBuilder.add("trim_material.emd_universe.rose", "Rose Gold Material");

        translationBuilder.add("painting.emd_universe.chainsaw_mode.title", "Chainsaw Mode");
        translationBuilder.add("painting.emd_universe.chainsaw_mode.author", "Chesnok81");

        translationBuilder.add("painting.emd_universe.cosmic_top_secret.title", "Top Secret Science");
        translationBuilder.add("painting.emd_universe.cosmic_top_secret.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.dushnila.title", "Toxic SD-L");
        translationBuilder.add("painting.emd_universe.dushnila.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.extremal_mafia_drones.title", "Extremal Mafia Drones");
        translationBuilder.add("painting.emd_universe.extremal_mafia_drones.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.family.title", "Family Photo");
        translationBuilder.add("painting.emd_universe.family.author", "Glitch Productions");

        translationBuilder.add("painting.emd_universe.finland_logo.title", "Legion Logo");
        translationBuilder.add("painting.emd_universe.finland_logo.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.gigachats.title", "Gigachat Drones");
        translationBuilder.add("painting.emd_universe.gigachats.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.gnida.title", "Solver Monster");
        translationBuilder.add("painting.emd_universe.gnida.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.gon.title", "GON's Photo");
        translationBuilder.add("painting.emd_universe.gon.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.hotline.title", "Hotline Uzi");
        translationBuilder.add("painting.emd_universe.hotline.author", "[Unknown author name]");

        translationBuilder.add("painting.emd_universe.how_to_use.title", "Absolute Solver Tutorial");
        translationBuilder.add("painting.emd_universe.how_to_use.author", "Liam Vickers");

        translationBuilder.add("painting.emd_universe.kolokal.title", "Meet the Kolokal");
        translationBuilder.add("painting.emd_universe.kolokal.author", "Kolokal (feat. ERFMD Team)");

        translationBuilder.add("painting.emd_universe.no_dogs.title", "No dogs on my favorite doors!!!");
        translationBuilder.add("painting.emd_universe.no_dogs.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.old_photo.title", "Old Photo");
        translationBuilder.add("painting.emd_universe.old_photo.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.oni_logo.title", "ONI Logo");
        translationBuilder.add("painting.emd_universe.oni_logo.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.poster.title", "Movie Poster");
        translationBuilder.add("painting.emd_universe.poster.author", "Glitch Productions");

        translationBuilder.add("painting.emd_universe.pribeyblyat.title", "Wanted");
        translationBuilder.add("painting.emd_universe.pribeyblyat.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.sd_f.title", "Confused SD-F");
        translationBuilder.add("painting.emd_universe.sd_f.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.summer_episode.title", "Beach Episode");
        translationBuilder.add("painting.emd_universe.summer_episode.author", "❖BLACK TEARS❖ (feat. ERFMD Team)");

        translationBuilder.add("painting.emd_universe.tau_59.title", "Welcome to Tau-59");
        translationBuilder.add("painting.emd_universe.tau_59.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.tessa.title", "Meet the Tessa");
        translationBuilder.add("painting.emd_universe.tessa.author", "Liam Vickers");

        translationBuilder.add("painting.emd_universe.unknown_logo.title", "???");
        translationBuilder.add("painting.emd_universe.unknown_logo.author", "ERFMD Team");

        translationBuilder.add("painting.emd_universe.wayne.title", "Meet the Wayne");
        translationBuilder.add("painting.emd_universe.wayne.author", "ERFMD Team");

        translationBuilder.add("itemgroup.test", "Test");
    }
}
