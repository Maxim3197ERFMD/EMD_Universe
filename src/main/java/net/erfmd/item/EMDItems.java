package net.erfmd.item;

import net.erfmd.EMDUniverse;
import net.erfmd.block.EMDBlocks;
import net.erfmd.item.custom.ChainsawItem;
import net.erfmd.item.custom.LM85;
import net.erfmd.item.custom.NanoMachines;
import net.erfmd.item.custom.railgun.RailgunItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class EMDItems {
    public static final Item EMD_ICON = registerItem("test", new Item(new Item.Settings().maxCount(1)));
    public static final Item RUBY = registerItem("ruby", new Item(new Item.Settings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new Item.Settings()));



    public static final Item DISASSEMBLY_CHAINSAW = registerItem("disassembly_chainsaw",
            new ChainsawItem(EMDToolMaterials.SPECIAL_TOOLITEM, new Item.Settings().attributeModifiers(ChainsawItem.createAttributeModifiers
                    (EMDToolMaterials.SPECIAL_TOOLITEM, 4, -2f)).maxCount(1).rarity(Rarity.RARE)));
    public static final Item COPPER_COIL = registerItem("copper_coil", new Item(new Item.Settings()));
    public static final Item COPPER_WIRE = registerItem("copper_wire", new Item(new Item.Settings()));
    public static final Item ACCUMULATOR = registerItem("accumulator", new Item(new Item.Settings()));
    public static final Item ENERGY_BALL = registerItem("energy_ball", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item SPECIAL_STORAGE = registerItem("special_storage", new Item(new Item.Settings()));
    public static final Item EMERALD_DUST = registerItem("emerald_dust", new Item(new Item.Settings()));
    public static final Item DIAMOND_DUST = registerItem("diamond_dust", new Item(new Item.Settings()));
    public static final Item METAL_ALLOY = registerItem("metal_alloy", new Item(new Item.Settings()));
    public static final Item WARPED_NETHERITE_INGOT = registerItem("warped_netherite_ingot",
            new Item(new Item.Settings()));
    public static final Item HIGH_VOLTAGE_BATTERY = registerItem("high_voltage_battery",
            new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item RAILGUN = registerItem("railgun",
            new RailgunItem(new Item.Settings().maxDamage(RailgunItem.BATTERY_SHOTS).maxCount(1).fireproof().rarity(Rarity.EPIC)));



    public static final Item PIECE_OF_CAKE = registerItem("piece_of_cake",
            new Item(new Item.Settings().food(EMDFoodComponents.PIECE_OF_CAKE)));
    public static final Item PIECE_OF_CHOCOLATE_CAKE = registerItem("piece_of_chocolate_cake",
            new Item(new Item.Settings().food(EMDFoodComponents.PIECE_OF_CHOCOLATE_CAKE)));
    public static final Item PIECE_OF_EXPERIMENT_115 = registerItem("piece_of_experiment_115",
            new Item(new Item.Settings().food(EMDFoodComponents.PIECE_OF_EXPERIMENT_115).rarity(Rarity.RARE)));
    public static final Item PIECE_OF_EXPERIMENT_803S = registerItem("piece_of_experiment_803-s",
            new Item(new Item.Settings().food(EMDFoodComponents.PIECE_OF_EXPERIMENT_803S).rarity(Rarity.EPIC)));


    
    public static final Item CHOCOLATE_CAKE = registerItem("chocolate_cake",
            new AliasedBlockItem(EMDBlocks.CHOCOLATE_CAKE, new Item.Settings().maxCount(1)));
    public static final Item EXPERIMENT_115 = registerItem("experiment_115",
            new AliasedBlockItem(EMDBlocks.EXPERIMENT_115, new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item EXPERIMENT_803S = registerItem("experiment_803-s",
            new AliasedBlockItem(EMDBlocks.EXPERIMENT_803S, new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));



    public static final Item CARAMEL = registerItem("caramel",
            new Item(new Item.Settings().food(EMDFoodComponents.CARAMEL)));
    public static final Item CARAMEL_APPLE = registerItem("caramel_apple",
            new Item(new Item.Settings().food(EMDFoodComponents.CARAMEL_APPLE)));
    public static final Item HAM = registerItem("ham",
            new Item(new Item.Settings().food(EMDFoodComponents.HAM).maxCount(1)));
    public static final Item HAM_BAT = registerItem("ham_bat",
            new SwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers
                    (ToolMaterials.IRON,2, -2.0f)).maxCount(1).food(EMDFoodComponents.HAM_BAT)));
    public static final Item LOLLICHOP = registerItem("lollichop",
            new SwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers
                    (ToolMaterials.IRON,3, -2.2f)).maxCount(1).food(EMDFoodComponents.LOLLICHOP)));
    public static final Item CANDY_CANE = registerItem("candy_cane",
            new SwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers
                    (ToolMaterials.IRON,3, -2.2f)).maxCount(1).food(EMDFoodComponents.CANDY_CANE)));
    public static final Item WORKER_DRONE_ARM = registerItem("worker_drone_arm",
            new SwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers
                    (ToolMaterials.IRON,3, -2.2f)).maxCount(1).food(EMDFoodComponents.WORKER_DRONE_ARM)));
    public static final Item ROSE_GOLDEN_APPLE = registerItem("rose_golden_apple",
            new Item(new Item.Settings().food(EMDFoodComponents.ROSE_GOLDEN_APPLE).rarity(Rarity.RARE)));



    public static final Item ROSE_GOLD_INGOT = registerItem("rose_gold_ingot", new Item(new Item.Settings()));
    public static final Item ROSE_GOLDEN_SWORD = registerItem("rose_golden_sword",
            new SwordItem(EMDToolMaterials.ROSE_GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers
                    (EMDToolMaterials.ROSE_GOLD,4, -2.4f)).maxCount(1)));
    public static final Item ROSE_GOLDEN_AXE = registerItem("rose_golden_axe",
            new AxeItem(EMDToolMaterials.ROSE_GOLD, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers
                    (EMDToolMaterials.ROSE_GOLD,6, -3.0f)).maxCount(1)));
    public static final Item ROSE_GOLDEN_PICKAXE = registerItem("rose_golden_pickaxe",
            new PickaxeItem(EMDToolMaterials.ROSE_GOLD, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers
                    (EMDToolMaterials.ROSE_GOLD,1.5f, -2.8f)).maxCount(1)));
    public static final Item ROSE_GOLDEN_SHOVEL = registerItem("rose_golden_shovel",
            new ShovelItem(EMDToolMaterials.ROSE_GOLD, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers
                    (EMDToolMaterials.ROSE_GOLD,2.5f, -3.0f)).maxCount(1)));
    public static final Item ROSE_GOLDEN_HOE = registerItem("rose_golden_hoe",
            new HoeItem(EMDToolMaterials.ROSE_GOLD, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers
                    (EMDToolMaterials.ROSE_GOLD,-3, 0.0f)).maxCount(1)));



    public static final Item ROSE_GOLDEN_HELMET = registerItem("rose_golden_helmet",
            new ArmorItem(EMDArmorMaterials.ROSE_GOLD_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(35))));
    public static final Item ROSE_GOLDEN_CHESTPLATE = registerItem("rose_golden_chestplate",
            new ArmorItem(EMDArmorMaterials.ROSE_GOLD_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(35))));
    public static final Item ROSE_GOLDEN_LEGGINGS = registerItem("rose_golden_leggings",
            new ArmorItem(EMDArmorMaterials.ROSE_GOLD_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(35))));
    public static final Item ROSE_GOLDEN_BOOTS = registerItem("rose_golden_boots",
            new ArmorItem(EMDArmorMaterials.ROSE_GOLD_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(35))));



    public static final Item CHERRIES = registerItem("cherries",
            new Item(new Item.Settings().food(EMDFoodComponents.CHERRIES)));
    public static final Item BANANA = registerItem("banana",
            new Item(new Item.Settings().food(EMDFoodComponents.BANANA)));
    public static final Item HONEYSUCKLE = registerItem("honeysuckle",
            new AliasedBlockItem(EMDBlocks.HONEYSUCKLE_BERRY_BUSH, new Item.Settings().food(EMDFoodComponents.HONEYSUCKLE)));
    public static final Item CHERRY_PIE = registerItem("cherry_pie",
            new Item(new Item.Settings().food(EMDFoodComponents.CHERRY_PIE)));
    public static final Item HONEYSUCKLE_PIE = registerItem("honeysuckle_pie",
            new Item(new Item.Settings().food(EMDFoodComponents.HONEYSUCKLE_PIE)));



    public static final Item LM85_BLADE = registerItem("lm85_blade",
            new LM85(EMDToolMaterials.SPECIAL_TOOLITEM, new Item.Settings().attributeModifiers(LM85.createAttributeModifiers
                    (EMDToolMaterials.SPECIAL_TOOLITEM,4, -2f)).maxCount(1).rarity(Rarity.RARE)));



    public static final Item OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("oil_sun_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(EMDUniverse.MOD_ID, "oil_sun"), FeatureFlags.VANILLA));
    public static final Item LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("love_harem_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(EMDUniverse.MOD_ID, "love_harem"), FeatureFlags.VANILLA));
    public static final Item SCORPION_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("scorpion_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(EMDUniverse.MOD_ID, "scorpion"), FeatureFlags.VANILLA));



    public static final Item WARHAMMER_SOUP = registerItem("warhammer_soup",
            new Item(new Item.Settings().food(EMDFoodComponents.WARHAMMER_SOUP).maxCount(1).rarity(Rarity.UNCOMMON)));



    public static final Item NETHERITE_BOW = registerItem("netherite_bow",
            new BowItem(new Item.Settings().maxDamage(2031).fireproof()));
    public static final Item NETHERITE_CROSSBOW = registerItem("netherite_crossbow",
            new CrossbowItem(new Item.Settings().maxDamage(2031).fireproof()
                    .component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT)));



    public static final Item PARSLEY_SEEDS = registerItem("parsley_seeds",
            new AliasedBlockItem(EMDBlocks.PARSLEY_CROP, new Item.Settings()));
    public static final Item PARSLEY = registerItem("parsley", new Item(new Item.Settings()));


    public static final Item GOLDEN_FLOWER_PETALS = registerItem("golden_flower_petals",
            new AliasedBlockItem(EMDBlocks.GOLDEN_FLOWER_CROP, new Item.Settings().food(EMDFoodComponents.GOLDEN_FLOWER_PETALS)));
    public static final Item GOLDEN_FLOWER = registerItem("golden_flower",
            new AliasedBlockItem(EMDBlocks.GOLDEN_FLOWER, new Item.Settings()));



    public static final Item NANOMACHINES = registerItem("nanomachines",
            new NanoMachines(EMDToolMaterials.SPECIAL_TOOLITEM, new Item.Settings().attributeModifiers(NanoMachines.createAttributeModifiers
                    (EMDToolMaterials.SPECIAL_TOOLITEM,4, -2f)).maxCount(1).fireproof().rarity(Rarity.EPIC)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EMDUniverse.MOD_ID, name), item);
    }

    public static void registerModItems() {
        EMDUniverse.LOGGER.info("Registering Mod Items for " + EMDUniverse.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {});
    }
}