package net.erfmd.datagen;

import net.erfmd.EMDUniverse;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.erfmd.block.EMDBlocks;
import net.erfmd.item.EMDItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EMDRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(EMDItems.RAW_RUBY,
            EMDBlocks.RUBY_ORE, EMDBlocks.DEEPSLATE_RUBY_ORE, EMDBlocks.NETHER_RUBY_ORE);
    private static final List<ItemConvertible> IRON_SMELTABLES = List.of(EMDBlocks.NETHER_IRON_ORE);
    private static final List<ItemConvertible> METAL_SMELTABLES = List.of(EMDItems.WORKER_DRONE_ARM);
    private static final List<ItemConvertible> STONE_SMELTABLES = List.of(EMDBlocks.STONE_TILES);
    private static final List<ItemConvertible> BLACKSTONE_SMELTABLES = List.of(EMDBlocks.POLISHED_BLACKSTONE_TILES);
    private static final List<ItemConvertible> END_STONE_SMELTABLES = List.of(Blocks.END_STONE);
    private static final List<ItemConvertible> COOKING_SUGAR_SMELTABLES = List.of(Items.SUGAR);
    private static final List<ItemConvertible> COOKING_MEAT_SMELTABLES = List.of(EMDItems.HAM);

    public EMDRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    public static <T extends AbstractCookingRecipe> void offerCustomMultipleOptions(RecipeExporter exporter, RecipeSerializer<T> serializer, AbstractCookingRecipe.RecipeFactory<T> recipeFactory, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, String suffix) {
        for (ItemConvertible itemConvertible : inputs) {
            CookingRecipeJsonBuilder.create(Ingredient.ofItems(itemConvertible), category, output, experience, cookingTime, serializer, recipeFactory).group(group).criterion(hasItem(itemConvertible), conditionsFromItem(itemConvertible)).offerTo(exporter, Identifier.of(EMDUniverse.MOD_ID, getItemPath(output) + suffix + "_" + getItemPath(itemConvertible)));
        }
    }

    public static void offerCustomSmelting(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        offerCustomMultipleOptions(exporter, RecipeSerializer.SMELTING, SmeltingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_smelting");
    }

    public static void offerCustomBlasting(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        offerCustomMultipleOptions(exporter, RecipeSerializer.BLASTING, BlastingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_blasting");
    }

    public static void offerSmoking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        offerCustomMultipleOptions(exporter, RecipeSerializer.SMOKING, SmokingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_smoking");
    }
    public static void offerCampfireCooking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        offerCustomMultipleOptions(exporter, RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_campfire_cooking");
    }

    public static void offerCustomReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(hasItem(compactItem), conditionsFromItem(compactItem)).offerTo(exporter, Identifier.of(EMDUniverse.MOD_ID, reverseId));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem).input('#', baseItem).pattern("###").pattern("###").pattern("###").group(compactingGroup).criterion(hasItem(baseItem), conditionsFromItem(baseItem)).offerTo(exporter, Identifier.of(EMDUniverse.MOD_ID, compactingId));
    }

    public static void offerCustomReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
        offerCustomReversibleCompactingRecipes(exporter, reverseCategory, baseItem, compactingCategory, compactItem, getRecipeName(compactItem), null, getRecipeName(baseItem), null);
    }

    public static void offerCustomStonecuttingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input) {
        offerCustomStonecuttingRecipe(exporter, category, output, input, 1);
    }

    public static void offerCustomStonecuttingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input, int count) {
        StonecuttingRecipeJsonBuilder var10000 = StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(input), category, output, count).criterion(hasItem(input), conditionsFromItem(input));
        String var10002 = convertBetween(output, input);
        var10000.offerTo(exporter, Identifier.of(EMDUniverse.MOD_ID,var10002 + "_stonecutting"));
    }

    @Override
    public void generate(RecipeExporter exporter) {

        offerCustomSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, EMDItems.RUBY,
                0.7f, 200, "ruby");
        offerCustomBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, EMDItems.RUBY,
                0.7f, 100, "ruby");
        offerCustomSmelting(exporter, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT,
                0.7f, 200, "iron_ingot");
        offerCustomBlasting(exporter, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT,
                0.7f, 100, "iron_ingot");
        offerCustomSmelting(exporter, METAL_SMELTABLES, RecipeCategory.MISC, Items.IRON_NUGGET,
                0.1f, 200, "iron_nugget");
        offerCustomBlasting(exporter, METAL_SMELTABLES, RecipeCategory.MISC, Items.IRON_NUGGET,
                0.1f, 100, "iron_nugget");
        offerCustomSmelting(exporter, BLACKSTONE_SMELTABLES, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRACKED_POLISHED_BLACKSTONE_TILES,
                0.1f, 200, "blackstone");
        offerCustomSmelting(exporter, STONE_SMELTABLES, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRACKED_STONE_TILES,
                0.1f, 200, "stone");
        offerCustomSmelting(exporter, END_STONE_SMELTABLES, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SMOOTH_END_STONE,
                0.1f, 200, "end_stone");



        offerCustomSmelting(exporter, COOKING_SUGAR_SMELTABLES, RecipeCategory.FOOD, EMDItems.CARAMEL,
                0.1f, 200, "food");
        offerCustomSmelting(exporter, COOKING_MEAT_SMELTABLES, RecipeCategory.FOOD, EMDItems.HAM_BAT,
                0.1f, 200, "food");
        offerSmoking(exporter, COOKING_MEAT_SMELTABLES, RecipeCategory.FOOD, EMDItems.HAM_BAT,
                0.1f, 100, "food");
        offerCampfireCooking(exporter, COOKING_MEAT_SMELTABLES, RecipeCategory.FOOD, EMDItems.HAM_BAT,
                0.1f, 600, "food");



        offerCustomReversibleCompactingRecipes(exporter, RecipeCategory.MISC, EMDItems.RUBY, RecipeCategory.DECORATIONS,
                EMDBlocks.RUBY_BLOCK);
        offerCustomReversibleCompactingRecipes(exporter, RecipeCategory.MISC, EMDItems.RAW_RUBY, RecipeCategory.DECORATIONS,
                EMDBlocks.RAW_RUBY_BLOCK);
        offerCustomReversibleCompactingRecipes(exporter, RecipeCategory.MISC, EMDItems.ROSE_GOLD_INGOT, RecipeCategory.DECORATIONS,
                EMDBlocks.ROSE_GOLD_BLOCK);



        offerCustomReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, Items.POTATO, RecipeCategory.DECORATIONS,
                EMDBlocks.POTATO_BLOCK);
        offerCustomReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, Items.POISONOUS_POTATO, RecipeCategory.DECORATIONS,
                EMDBlocks.POISONOUS_POTATO_BLOCK);



        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.PIECE_OF_CAKE, 7)
                .input(Items.CAKE)
                .criterion(hasItem(Items.CAKE), conditionsFromItem(Items.CAKE))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.PIECE_OF_CHOCOLATE_CAKE, 7)
                .input(EMDItems.CHOCOLATE_CAKE)
                .criterion(hasItem(EMDItems.CHOCOLATE_CAKE), conditionsFromItem(EMDItems.CHOCOLATE_CAKE))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.PIECE_OF_EXPERIMENT_115, 7)
                .input(EMDItems.EXPERIMENT_115)
                .criterion(hasItem(EMDItems.EXPERIMENT_115), conditionsFromItem(EMDItems.EXPERIMENT_115))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.PIECE_OF_EXPERIMENT_803S, 7)
                .input(EMDItems.EXPERIMENT_803S)
                .criterion(hasItem(EMDItems.EXPERIMENT_803S), conditionsFromItem(EMDItems.EXPERIMENT_803S))
                .offerTo(exporter);



        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.WARHAMMER_SOUP)
                .input(Items.CAULDRON)
                .input(Items.HONEY_BOTTLE)
                .input(Items.CHORUS_FLOWER)
                .input(Items.GLOW_LICHEN)
                .input(Items.PUFFERFISH)
                .input(EMDItems.HAM_BAT)
                .input(Items.WARPED_FUNGUS)
                .input(Items.RED_MUSHROOM)
                .input(Items.BLAZE_ROD)
                .criterion(hasItem(Items.CAULDRON), conditionsFromItem(Items.CAULDRON))
                .offerTo(exporter);



        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.ROSE_GOLD_INGOT)
                .input(EMDItems.RUBY)
                .input(Items.COPPER_INGOT)
                .input(Items.COPPER_INGOT)
                .input(Items.COPPER_INGOT)
                .input(Items.COPPER_INGOT)
                .input(Items.GOLD_INGOT)
                .input(Items.GOLD_INGOT)
                .input(Items.GOLD_INGOT)
                .input(Items.GOLD_INGOT)
                .criterion(hasItem(EMDItems.RUBY), conditionsFromItem(EMDItems.RUBY))
                .offerTo(exporter, Identifier.of(EMDUniverse.MOD_ID, "rose_gold_ingot_from_rose_gold_block"));



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILES, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.POLISHED_BLACKSTONE_BRICKS)
                .criterion(hasItem(Blocks.POLISHED_BLACKSTONE_BRICKS), conditionsFromItem(Blocks.POLISHED_BLACKSTONE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILES, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.STONE_BRICKS)
                .criterion(hasItem(Blocks.STONE_BRICKS), conditionsFromItem(Blocks.STONE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILES, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.END_STONE_BRICKS)
                .criterion(hasItem(Blocks.END_STONE_BRICKS), conditionsFromItem(Blocks.END_STONE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.STONE_TILES)
                .criterion(hasItem(EMDBlocks.STONE_TILES), conditionsFromItem(EMDBlocks.STONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.STONE_TILES)
                .criterion(hasItem(EMDBlocks.STONE_TILES), conditionsFromItem(EMDBlocks.STONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_BLACKSTONE_TILES)
                .criterion(hasItem(EMDBlocks.POLISHED_BLACKSTONE_TILES), conditionsFromItem(EMDBlocks.POLISHED_BLACKSTONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_BLACKSTONE_TILES)
                .criterion(hasItem(EMDBlocks.POLISHED_BLACKSTONE_TILES), conditionsFromItem(EMDBlocks.POLISHED_BLACKSTONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SMOOTH_END_STONE_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.SMOOTH_END_STONE)
                .criterion(hasItem(EMDBlocks.SMOOTH_END_STONE), conditionsFromItem(EMDBlocks.SMOOTH_END_STONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SMOOTH_END_STONE_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.SMOOTH_END_STONE)
                .criterion(hasItem(EMDBlocks.SMOOTH_END_STONE), conditionsFromItem(EMDBlocks.SMOOTH_END_STONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.END_STONE_TILES)
                .criterion(hasItem(EMDBlocks.END_STONE_TILES), conditionsFromItem(EMDBlocks.END_STONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.END_STONE_TILES)
                .criterion(hasItem(EMDBlocks.END_STONE_TILES), conditionsFromItem(EMDBlocks.END_STONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDBlocks.STONE_TILE_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.STONE_TILES)
                .criterion(hasItem(EMDBlocks.STONE_TILES), conditionsFromItem(EMDBlocks.STONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_BLACKSTONE_TILES)
                .criterion(hasItem(EMDBlocks.POLISHED_BLACKSTONE_TILES), conditionsFromItem(EMDBlocks.POLISHED_BLACKSTONE_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDBlocks.END_STONE_TILE_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.END_STONE_TILES)
                .criterion(hasItem(EMDBlocks.END_STONE_TILES), conditionsFromItem(EMDBlocks.END_STONE_TILES))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.BLACK_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.BLACK_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.BLUE_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.BLUE_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.BROWN_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.BROWN_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.CYAN_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.CYAN_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.GRAY_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.GRAY_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.GREEN_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.GREEN_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.LIGHT_BLUE_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.LIGHT_BLUE_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.LIGHT_GRAY_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.LIGHT_GRAY_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.LIME_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.LIME_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.MAGENTA_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.MAGENTA_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.ORANGE_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.ORANGE_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.PINK_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.PINK_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.PURPLE_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.PURPLE_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.RED_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.RED_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.WHITE_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.WHITE_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.YELLOW_REDSTONE_LAMP, 1)
                .pattern("SRS")
                .pattern("RGR")
                .pattern("SRS")
                .input('G', Blocks.GLOWSTONE)
                .input('R', Items.REDSTONE)
                .input('S', Blocks.YELLOW_STAINED_GLASS)
                .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.HIGH_VOLTAGE_BATTERY, 1)
                .pattern("#S#")
                .pattern("SAS")
                .pattern("#S#")
                .input('A', EMDItems.ENERGY_BALL)
                .input('S', EMDItems.ACCUMULATOR)
                .input('#', EMDItems.EMERALD_DUST)
                .criterion(hasItem(EMDItems.ENERGY_BALL), conditionsFromItem(EMDItems.ENERGY_BALL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.COPPER_COIL, 1)
                .pattern("###")
                .pattern("#S#")
                .pattern("#S#")
                .input('#', EMDItems.COPPER_WIRE)
                .input('S', Items.IRON_NUGGET)
                .criterion(hasItem(EMDItems.COPPER_WIRE), conditionsFromItem(EMDItems.COPPER_WIRE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.COPPER_WIRE, 3)
                .pattern("#S")
                .input('#', Items.SHEARS)
                .input('S', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.EMERALD_DUST, 2)
                .pattern("#S#")
                .pattern("G#G")
                .pattern("AAA")
                .input('#', Items.EMERALD)
                .input('S', Items.IRON_PICKAXE)
                .input('G', Items.GOLD_INGOT)
                .input('A', Items.LAPIS_LAZULI)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.ACCUMULATOR, 1)
                .pattern("#S#")
                .pattern("SRS")
                .pattern("ASA")
                .input('#', Items.IRON_INGOT)
                .input('S', EMDItems.COPPER_WIRE)
                .input('R', Items.REDSTONE)
                .input('A', EMDItems.COPPER_COIL)
                .criterion(hasItem(EMDItems.COPPER_COIL), conditionsFromItem(EMDItems.COPPER_COIL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.ENERGY_BALL, 1)
                .pattern("#R#")
                .pattern("SES")
                .pattern("#R#")
                .input('E', EMDItems.ACCUMULATOR)
                .input('R', Items.REDSTONE)
                .input('S', EMDItems.EMERALD_DUST)
                .input('#', EMDItems.DIAMOND_DUST)
                .criterion(hasItem(EMDItems.ACCUMULATOR), conditionsFromItem(EMDItems.ACCUMULATOR))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.DIAMOND_DUST, 2)
                .pattern("#R#")
                .pattern("SES")
                .pattern("#R#")
                .input('E', Items.IRON_PICKAXE)
                .input('R', Items.DIAMOND)
                .input('S', Items.GOLD_INGOT)
                .input('#', Items.REDSTONE)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.SPECIAL_STORAGE, 1)
                .pattern("#R#")
                .pattern("SES")
                .pattern("#R#")
                .input('E', Blocks.GOLD_BLOCK)
                .input('R', EMDItems.DIAMOND_DUST)
                .input('S', EMDItems.ENERGY_BALL)
                .input('#', EMDItems.EMERALD_DUST)
                .criterion(hasItem(EMDItems.ENERGY_BALL), conditionsFromItem(EMDItems.ENERGY_BALL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.METAL_ALLOY, 1)
                .pattern("###")
                .pattern("#ES")
                .pattern("SSS")
                .input('E', EMDItems.WARPED_NETHERITE_INGOT)
                .input('S', Blocks.IRON_BLOCK)
                .input('#', Blocks.COPPER_BLOCK)
                .criterion(hasItem(EMDItems.WARPED_NETHERITE_INGOT), conditionsFromItem(EMDItems.WARPED_NETHERITE_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMDItems.WARPED_NETHERITE_INGOT, 1)
                .pattern("#D#")
                .pattern("E#E")
                .pattern("#E#")
                .input('D', EMDItems.DIAMOND_DUST)
                .input('#', Items.NETHERITE_INGOT)
                .input('E', EMDItems.EMERALD_DUST)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EMDBlocks.REDSTONE_HEART, 1)
                .pattern("###")
                .pattern("#R#")
                .pattern("###")
                .input('#', Items.NETHERITE_INGOT)
                .input('R', Blocks.REDSTONE_BLOCK)
                .criterion(hasItem(Blocks.REDSTONE_BLOCK), conditionsFromItem(Blocks.REDSTONE_BLOCK))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDBlocks.CHOCOLATE_CAKE, 1)
                .pattern("###")
                .pattern("ARA")
                .pattern("BBB")
                .input('#', Items.MILK_BUCKET)
                .input('R', Items.EGG)
                .input('A', Items.COCOA_BEANS)
                .input('B', Items.WHEAT)
                .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDBlocks.EXPERIMENT_115, 1)
                .pattern("###")
                .pattern("ARA")
                .pattern("BBB")
                .input('#', Items.GOLDEN_APPLE)
                .input('R', Items.EGG)
                .input('A', Items.SWEET_BERRIES)
                .input('B', Items.COOKIE)
                .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDBlocks.EXPERIMENT_803S, 1)
                .pattern("###")
                .pattern("ARA")
                .pattern("BBB")
                .input('#', Items.HONEY_BOTTLE)
                .input('R', Items.SPIDER_EYE)
                .input('A', Items.ROTTEN_FLESH)
                .input('B', Items.WHEAT)
                .criterion(hasItem(Items.SPIDER_EYE), conditionsFromItem(Items.SPIDER_EYE))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.CARAMEL_APPLE, 1)
                .pattern("###")
                .pattern("#A#")
                .pattern("###")
                .input('#', EMDItems.CARAMEL)
                .input('A', Items.APPLE)
                .criterion(hasItem(EMDItems.CARAMEL), conditionsFromItem(EMDItems.CARAMEL))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LIGHT, 1)
                .pattern("T")
                .pattern("T")
                .input('T', Items.TORCH)
                .criterion(hasItem(Items.TORCH), conditionsFromItem(Items.TORCH))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.ROSE_GOLDEN_APPLE, 1)
                .pattern("RRR")
                .pattern("RAR")
                .pattern("RRR")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .input('A', Items.APPLE)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EMDItems.ROSE_GOLDEN_SWORD, 1)
                .pattern(" R ")
                .pattern(" R ")
                .pattern(" S ")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EMDItems.ROSE_GOLDEN_HELMET, 1)
                .pattern("RRR")
                .pattern("R R")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EMDItems.ROSE_GOLDEN_CHESTPLATE, 1)
                .pattern("R R")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EMDItems.ROSE_GOLDEN_LEGGINGS, 1)
                .pattern("RRR")
                .pattern("R R")
                .pattern("R R")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EMDItems.ROSE_GOLDEN_BOOTS, 1)
                .pattern("R R")
                .pattern("R R")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EMDItems.ROSE_GOLDEN_AXE, 1)
                .pattern("RR ")
                .pattern("RS ")
                .pattern(" S ")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EMDItems.ROSE_GOLDEN_PICKAXE, 1)
                .pattern("RRR")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EMDItems.ROSE_GOLDEN_SHOVEL, 1)
                .pattern(" R ")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EMDItems.ROSE_GOLDEN_HOE, 1)
                .pattern("RR ")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', EMDItems.ROSE_GOLD_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(EMDItems.ROSE_GOLD_INGOT), conditionsFromItem(EMDItems.ROSE_GOLD_INGOT))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.HONEYSUCKLE_PIE, 1)
                .pattern("BBB")
                .pattern("#A#")
                .input('B', EMDItems.HONEYSUCKLE)
                .input('A', Items.EGG)
                .input('#', Items.WHEAT)
                .criterion(hasItem(EMDItems.HONEYSUCKLE), conditionsFromItem(EMDItems.HONEYSUCKLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.CHERRY_PIE, 1)
                .pattern("SSS")
                .pattern("#A#")
                .input('S', EMDItems.CHERRIES)
                .input('A', Items.EGG)
                .input('#', Items.WHEAT)
                .criterion(hasItem(EMDItems.CHERRIES), conditionsFromItem(EMDItems.CHERRIES))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.LOLLICHOP, 1)
                .pattern(" S ")
                .pattern(" # ")
                .pattern(" # ")
                .input('S', EMDItems.CARAMEL)
                .input('#', Items.STICK)
                .criterion(hasItem(EMDItems.CARAMEL), conditionsFromItem(EMDItems.CARAMEL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EMDItems.CANDY_CANE, 1)
                .pattern("SS")
                .pattern(" S")
                .pattern(" S")
                .input('S', EMDItems.CARAMEL)
                .criterion(hasItem(EMDItems.CARAMEL), conditionsFromItem(EMDItems.CARAMEL))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.IRON_BAR_BLOCK, 4)
                .pattern("#B#")
                .pattern("B#B")
                .pattern("#B#")
                .input('B', Blocks.IRON_BARS)
                .input('#', Items.IRON_NUGGET)
                .criterion(hasItem(Blocks.IRON_BARS), conditionsFromItem(Blocks.IRON_BARS))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.IRON_BAR_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.IRON_BAR_BLOCK)
                .criterion(hasItem(EMDBlocks.IRON_BAR_BLOCK), conditionsFromItem(EMDBlocks.IRON_BAR_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.IRON_BAR_SLAB, 6)
                .pattern("BBB")
                .input('B', EMDBlocks.IRON_BAR_BLOCK)
                .criterion(hasItem(EMDBlocks.IRON_BAR_BLOCK), conditionsFromItem(EMDBlocks.IRON_BAR_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.IRON_BAR_TRAPDOOR, 2)
                .pattern("BBB")
                .pattern("BBB")
                .input('B', EMDBlocks.IRON_BAR_BLOCK)
                .criterion(hasItem(EMDBlocks.IRON_BAR_BLOCK), conditionsFromItem(EMDBlocks.IRON_BAR_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.IRON_BAR_DOOR, 3)
                .pattern("##")
                .pattern("BB")
                .pattern("##")
                .input('B', Blocks.IRON_BARS)
                .input('#', Items.IRON_INGOT)
                .criterion(hasItem(Blocks.IRON_BARS), conditionsFromItem(Blocks.IRON_BARS))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE, 1)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.STONE_SLAB)
                .criterion(hasItem(Blocks.STONE_SLAB), conditionsFromItem(Blocks.STONE_SLAB))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_STONE)
                .criterion(hasItem(EMDBlocks.POLISHED_STONE), conditionsFromItem(EMDBlocks.POLISHED_STONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_STONE)
                .criterion(hasItem(EMDBlocks.POLISHED_STONE), conditionsFromItem(EMDBlocks.POLISHED_STONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_STONE)
                .criterion(hasItem(EMDBlocks.POLISHED_STONE), conditionsFromItem(EMDBlocks.POLISHED_STONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_PILLAR, 1)
                .pattern("S")
                .pattern("S")
                .input('S', EMDBlocks.POLISHED_STONE)
                .criterion(hasItem(EMDBlocks.POLISHED_STONE), conditionsFromItem(EMDBlocks.POLISHED_STONE))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.CUT_SANDSTONE)
                .criterion(hasItem(Blocks.CUT_SANDSTONE), conditionsFromItem(Blocks.CUT_SANDSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.SANDSTONE_BRICKS)
                .criterion(hasItem(EMDBlocks.SANDSTONE_BRICKS), conditionsFromItem(EMDBlocks.SANDSTONE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.SANDSTONE_BRICKS)
                .criterion(hasItem(EMDBlocks.SANDSTONE_BRICKS), conditionsFromItem(EMDBlocks.SANDSTONE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.SANDSTONE_BRICKS)
                .criterion(hasItem(EMDBlocks.SANDSTONE_BRICKS), conditionsFromItem(EMDBlocks.SANDSTONE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CHISELED_SANDSTONE_BRICKS, 1)
                .pattern("S")
                .pattern("S")
                .input('S', EMDBlocks.SANDSTONE_BRICK_SLAB)
                .criterion(hasItem(EMDBlocks.SANDSTONE_BRICK_SLAB), conditionsFromItem(EMDBlocks.SANDSTONE_BRICK_SLAB))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICKS, 4)
                .pattern("NN")
                .pattern("NN")
                .input('N', Blocks.NETHER_BRICKS)
                .criterion(hasItem(Blocks.NETHER_BRICKS), conditionsFromItem(Blocks.NETHER_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICK_STAIRS, 4)
                .pattern("N  ")
                .pattern("NN ")
                .pattern("NNN")
                .input('N', EMDBlocks.POLISHED_NETHER_BRICKS)
                .criterion(hasItem(EMDBlocks.POLISHED_NETHER_BRICKS), conditionsFromItem(EMDBlocks.POLISHED_NETHER_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICK_SLAB, 6)
                .pattern("NNN")
                .input('N', EMDBlocks.POLISHED_NETHER_BRICKS)
                .criterion(hasItem(EMDBlocks.POLISHED_NETHER_BRICKS), conditionsFromItem(EMDBlocks.POLISHED_NETHER_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WARP_NETHER_BRICKS, 1)
                .pattern("*N")
                .pattern("N*")
                .input('N', Blocks.NETHER_BRICKS)
                .input('*', Blocks.WARPED_WART_BLOCK)
                .criterion(hasItem(Blocks.NETHER_BRICKS), conditionsFromItem(Blocks.NETHER_BRICKS))
                .criterion(hasItem(Blocks.WARPED_WART_BLOCK), conditionsFromItem(Blocks.WARPED_WART_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WARP_NETHER_BRICK_STAIRS, 4)
                .pattern("N  ")
                .pattern("NN ")
                .pattern("NNN")
                .input('N', EMDBlocks.WARP_NETHER_BRICKS)
                .criterion(hasItem(EMDBlocks.WARP_NETHER_BRICKS), conditionsFromItem(EMDBlocks.WARP_NETHER_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WARP_NETHER_BRICK_SLAB, 6)
                .pattern("NNN")
                .input('N', EMDBlocks.WARP_NETHER_BRICKS)
                .criterion(hasItem(EMDBlocks.WARP_NETHER_BRICKS), conditionsFromItem(EMDBlocks.WARP_NETHER_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WARP_NETHER_BRICK_WALL, 6)
                .pattern("NNN")
                .pattern("NNN")
                .input('N', EMDBlocks.WARP_NETHER_BRICKS)
                .criterion(hasItem(EMDBlocks.WARP_NETHER_BRICKS), conditionsFromItem(EMDBlocks.WARP_NETHER_BRICKS))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SNOW_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.SNOW_BLOCK)
                .criterion(hasItem(Blocks.SNOW_BLOCK), conditionsFromItem(Blocks.SNOW_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SNOW_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.SNOW_BRICKS)
                .criterion(hasItem(EMDBlocks.SNOW_BRICKS), conditionsFromItem(EMDBlocks.SNOW_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SNOW_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.SNOW_BRICKS)
                .criterion(hasItem(EMDBlocks.SNOW_BRICKS), conditionsFromItem(EMDBlocks.SNOW_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SNOW_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.SNOW_BRICKS)
                .criterion(hasItem(EMDBlocks.SNOW_BRICKS), conditionsFromItem(EMDBlocks.SNOW_BRICKS))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.TERRACOTTA)
                .criterion(hasItem(Blocks.TERRACOTTA), conditionsFromItem(Blocks.TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WHITE_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.WHITE_TERRACOTTA)
                .criterion(hasItem(Blocks.WHITE_TERRACOTTA), conditionsFromItem(Blocks.WHITE_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.LIGHT_GRAY_TERRACOTTA)
                .criterion(hasItem(Blocks.LIGHT_GRAY_TERRACOTTA), conditionsFromItem(Blocks.LIGHT_GRAY_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.GRAY_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.GRAY_TERRACOTTA)
                .criterion(hasItem(Blocks.GRAY_TERRACOTTA), conditionsFromItem(Blocks.GRAY_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.BLACK_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.BLACK_TERRACOTTA)
                .criterion(hasItem(Blocks.BLACK_TERRACOTTA), conditionsFromItem(Blocks.BLACK_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.BROWN_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.BROWN_TERRACOTTA)
                .criterion(hasItem(Blocks.BROWN_TERRACOTTA), conditionsFromItem(Blocks.BROWN_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.RED_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.RED_TERRACOTTA)
                .criterion(hasItem(Blocks.RED_TERRACOTTA), conditionsFromItem(Blocks.RED_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.ORANGE_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.ORANGE_TERRACOTTA)
                .criterion(hasItem(Blocks.ORANGE_TERRACOTTA), conditionsFromItem(Blocks.ORANGE_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.YELLOW_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.YELLOW_TERRACOTTA)
                .criterion(hasItem(Blocks.YELLOW_TERRACOTTA), conditionsFromItem(Blocks.YELLOW_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.LIME_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.LIME_TERRACOTTA)
                .criterion(hasItem(Blocks.LIME_TERRACOTTA), conditionsFromItem(Blocks.LIME_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.GREEN_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.GREEN_TERRACOTTA)
                .criterion(hasItem(Blocks.GREEN_TERRACOTTA), conditionsFromItem(Blocks.GREEN_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CYAN_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.CYAN_TERRACOTTA)
                .criterion(hasItem(Blocks.CYAN_TERRACOTTA), conditionsFromItem(Blocks.CYAN_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.LIGHT_BLUE_TERRACOTTA)
                .criterion(hasItem(Blocks.LIGHT_BLUE_TERRACOTTA), conditionsFromItem(Blocks.LIGHT_BLUE_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.BLUE_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.BLUE_TERRACOTTA)
                .criterion(hasItem(Blocks.BLUE_TERRACOTTA), conditionsFromItem(Blocks.BLUE_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.PURPLE_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.PURPLE_TERRACOTTA)
                .criterion(hasItem(Blocks.PURPLE_TERRACOTTA), conditionsFromItem(Blocks.PURPLE_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.MAGENTA_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.MAGENTA_TERRACOTTA)
                .criterion(hasItem(Blocks.MAGENTA_TERRACOTTA), conditionsFromItem(Blocks.MAGENTA_TERRACOTTA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.PINK_TERRACOTTA_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.PINK_TERRACOTTA)
                .criterion(hasItem(Blocks.PINK_TERRACOTTA), conditionsFromItem(Blocks.PINK_TERRACOTTA))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.OBSIDIAN)
                .criterion(hasItem(Blocks.OBSIDIAN), conditionsFromItem(Blocks.OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.POLISHED_OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.POLISHED_OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.POLISHED_OBSIDIAN))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', EMDBlocks.POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.POLISHED_OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.OBSIDIAN_BRICKS)
                .criterion(hasItem(EMDBlocks.OBSIDIAN_BRICKS), conditionsFromItem(EMDBlocks.OBSIDIAN_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.OBSIDIAN_BRICKS)
                .criterion(hasItem(EMDBlocks.OBSIDIAN_BRICKS), conditionsFromItem(EMDBlocks.OBSIDIAN_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.OBSIDIAN_BRICKS)
                .criterion(hasItem(EMDBlocks.OBSIDIAN_BRICKS), conditionsFromItem(EMDBlocks.OBSIDIAN_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CHISELED_OBSIDIAN)
                .pattern("S")
                .pattern("S")
                .input('S', EMDBlocks.POLISHED_OBSIDIAN_SLAB)
                .criterion(hasItem(EMDBlocks.POLISHED_OBSIDIAN_SLAB), conditionsFromItem(EMDBlocks.POLISHED_OBSIDIAN_SLAB))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_PILLAR)
                .pattern("S")
                .pattern("S")
                .input('S', EMDBlocks.POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.POLISHED_OBSIDIAN))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.CRYING_OBSIDIAN)
                .criterion(hasItem(Blocks.CRYING_OBSIDIAN), conditionsFromItem(Blocks.CRYING_OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.CRYING_POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.CRYING_POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.CRYING_POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', EMDBlocks.CRYING_POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', EMDBlocks.CRYING_OBSIDIAN_BRICKS)
                .criterion(hasItem(EMDBlocks.CRYING_OBSIDIAN_BRICKS), conditionsFromItem(EMDBlocks.CRYING_OBSIDIAN_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', EMDBlocks.CRYING_OBSIDIAN_BRICKS)
                .criterion(hasItem(EMDBlocks.CRYING_OBSIDIAN_BRICKS), conditionsFromItem(EMDBlocks.CRYING_OBSIDIAN_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', EMDBlocks.CRYING_OBSIDIAN_BRICKS)
                .criterion(hasItem(EMDBlocks.CRYING_OBSIDIAN_BRICKS), conditionsFromItem(EMDBlocks.CRYING_OBSIDIAN_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_CHISELED_OBSIDIAN)
                .pattern("S")
                .pattern("S")
                .input('S', EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB)
                .criterion(hasItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB), conditionsFromItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_PILLAR)
                .pattern("S")
                .pattern("S")
                .input('S', EMDBlocks.CRYING_POLISHED_OBSIDIAN)
                .criterion(hasItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN), conditionsFromItem(EMDBlocks.CRYING_POLISHED_OBSIDIAN))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EMDBlocks.BEEKEEPING_TABLE)
                .pattern("SAS")
                .pattern("SBS")
                .pattern("SSS")
                .input('S', ItemTags.PLANKS)
                .input('A', Blocks.BEEHIVE)
                .input('B', Items.HONEYCOMB)
                .criterion(hasItem(Blocks.BEEHIVE), conditionsFromItem(Blocks.BEEHIVE))
                .offerTo(exporter);



        offerSmithingTrimRecipe(exporter, EMDItems.OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE, Identifier.of(EMDUniverse.MOD_ID, "oil_sun"));
        offerSmithingTrimRecipe(exporter, EMDItems.LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE, Identifier.of(EMDUniverse.MOD_ID, "love_harem"));
        offerSmithingTrimRecipe(exporter, EMDItems.SCORPION_ARMOR_TRIM_SMITHING_TEMPLATE, Identifier.of(EMDUniverse.MOD_ID, "scorpion"));



        offerNetheriteUpgradeRecipe(exporter, Items.BOW, RecipeCategory.COMBAT, EMDItems.NETHERITE_BOW);
        offerNetheriteUpgradeRecipe(exporter, Items.CROSSBOW, RecipeCategory.COMBAT, EMDItems.NETHERITE_CROSSBOW);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILES, Blocks.STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILES, Blocks.STONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_STAIRS, Blocks.STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_STAIRS, Blocks.STONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_STAIRS, EMDBlocks.STONE_TILES);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_SLAB, Blocks.STONE, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_SLAB, Blocks.STONE_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_SLAB, EMDBlocks.STONE_TILES, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_WALL, Blocks.STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_WALL, Blocks.STONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.STONE_TILE_WALL, EMDBlocks.STONE_TILES);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SMOOTH_END_STONE_STAIRS, EMDBlocks.SMOOTH_END_STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SMOOTH_END_STONE_SLAB, EMDBlocks.SMOOTH_END_STONE, 2);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILES, Blocks.END_STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILES, Blocks.END_STONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_STAIRS, Blocks.END_STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_STAIRS, Blocks.END_STONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_STAIRS, EMDBlocks.END_STONE_TILES);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_SLAB, Blocks.END_STONE, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_SLAB, Blocks.END_STONE_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.END_STONE_TILE_SLAB, EMDBlocks.END_STONE_TILES, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.MISC, EMDBlocks.END_STONE_TILE_WALL, Blocks.END_STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.MISC, EMDBlocks.END_STONE_TILE_WALL, Blocks.END_STONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.MISC, EMDBlocks.END_STONE_TILE_WALL, EMDBlocks.END_STONE_TILES);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILES, Blocks.BLACKSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILES, Blocks.POLISHED_BLACKSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILES, Blocks.POLISHED_BLACKSTONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, Blocks.BLACKSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, Blocks.POLISHED_BLACKSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, EMDBlocks.POLISHED_BLACKSTONE_TILES);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, Blocks.POLISHED_BLACKSTONE, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, EMDBlocks.POLISHED_BLACKSTONE_TILES, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.MISC, EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, Blocks.BLACKSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.MISC, EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, Blocks.POLISHED_BLACKSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.MISC, EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, Blocks.POLISHED_BLACKSTONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.MISC, EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, EMDBlocks.POLISHED_BLACKSTONE_TILES);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE, Blocks.STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_STAIRS, Blocks.STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_SLAB, Blocks.STONE, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_WALL, Blocks.STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_PILLAR, Blocks.STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_STAIRS, EMDBlocks.POLISHED_STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_SLAB, EMDBlocks.POLISHED_STONE, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_WALL, EMDBlocks.POLISHED_STONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_STONE_PILLAR, EMDBlocks.POLISHED_STONE);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICKS, Blocks.SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CHISELED_SANDSTONE_BRICKS, Blocks.SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_STAIRS, Blocks.SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_SLAB, Blocks.SANDSTONE, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_WALL, Blocks.SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICKS, Blocks.CUT_SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CHISELED_SANDSTONE_BRICKS, Blocks.CUT_SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_STAIRS, Blocks.CUT_SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_SLAB, Blocks.CUT_SANDSTONE, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_WALL, Blocks.CUT_SANDSTONE);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CHISELED_SANDSTONE_BRICKS, EMDBlocks.SANDSTONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_STAIRS, EMDBlocks.SANDSTONE_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_SLAB, EMDBlocks.SANDSTONE_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.SANDSTONE_BRICK_WALL, EMDBlocks.SANDSTONE_BRICKS);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICKS, Blocks.NETHER_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICK_STAIRS, Blocks.NETHER_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICK_SLAB, Blocks.NETHER_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICK_STAIRS, EMDBlocks.POLISHED_NETHER_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_NETHER_BRICK_SLAB, EMDBlocks.POLISHED_NETHER_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WARP_NETHER_BRICK_STAIRS, EMDBlocks.WARP_NETHER_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WARP_NETHER_BRICK_SLAB, EMDBlocks.WARP_NETHER_BRICKS,2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WARP_NETHER_BRICK_WALL, EMDBlocks.WARP_NETHER_BRICKS);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.TERRACOTTA_BRICKS, Blocks.TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.WHITE_TERRACOTTA_BRICKS, Blocks.WHITE_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, Blocks.LIGHT_GRAY_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.GRAY_TERRACOTTA_BRICKS, Blocks.GRAY_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.BLACK_TERRACOTTA_BRICKS, Blocks.BLACK_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.BROWN_TERRACOTTA_BRICKS, Blocks.BROWN_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.RED_TERRACOTTA_BRICKS, Blocks.RED_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.ORANGE_TERRACOTTA_BRICKS, Blocks.ORANGE_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.YELLOW_TERRACOTTA_BRICKS, Blocks.YELLOW_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.LIME_TERRACOTTA_BRICKS, Blocks.LIME_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.GREEN_TERRACOTTA_BRICKS, Blocks.GREEN_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CYAN_TERRACOTTA_BRICKS, Blocks.CYAN_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, Blocks.LIGHT_BLUE_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.BLUE_TERRACOTTA_BRICKS, Blocks.BLUE_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.PURPLE_TERRACOTTA_BRICKS, Blocks.PURPLE_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.MAGENTA_TERRACOTTA_BRICKS, Blocks.MAGENTA_TERRACOTTA);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.PINK_TERRACOTTA_BRICKS, Blocks.PINK_TERRACOTTA);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_SLAB, Blocks.OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_STAIRS, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_WALL, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICKS, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_SLAB, Blocks.OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_STAIRS, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_WALL, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CHISELED_OBSIDIAN, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_PILLAR, Blocks.OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_SLAB, EMDBlocks.POLISHED_OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_STAIRS, EMDBlocks.POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.POLISHED_OBSIDIAN_WALL, EMDBlocks.POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICKS, EMDBlocks.POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_SLAB, EMDBlocks.POLISHED_OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_STAIRS, EMDBlocks.POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_WALL, EMDBlocks.POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CHISELED_OBSIDIAN, EMDBlocks.POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_PILLAR, EMDBlocks.POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_SLAB, EMDBlocks.OBSIDIAN_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_STAIRS, EMDBlocks.OBSIDIAN_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.OBSIDIAN_BRICK_WALL, EMDBlocks.OBSIDIAN_BRICKS);



        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB, Blocks.CRYING_OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_WALL, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICKS, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB, Blocks.CRYING_OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_STAIRS, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_WALL, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_CHISELED_OBSIDIAN, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_PILLAR, Blocks.CRYING_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB, EMDBlocks.CRYING_POLISHED_OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS, EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN_WALL, EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICKS, EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB, EMDBlocks.CRYING_POLISHED_OBSIDIAN, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_STAIRS, EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_WALL, EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_CHISELED_OBSIDIAN, EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_PILLAR, EMDBlocks.CRYING_POLISHED_OBSIDIAN);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB, EMDBlocks.CRYING_OBSIDIAN_BRICKS, 2);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_STAIRS, EMDBlocks.CRYING_OBSIDIAN_BRICKS);
        offerCustomStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, EMDBlocks.CRYING_OBSIDIAN_BRICK_WALL, EMDBlocks.CRYING_OBSIDIAN_BRICKS);

    }
}