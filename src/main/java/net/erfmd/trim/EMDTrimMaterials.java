package net.erfmd.trim;

import net.erfmd.EMDUniverse;
import net.erfmd.item.EMDArmorMaterials;
import net.erfmd.item.EMDItems;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

/**
 * Создание кастомных материалов для шаблонов
 */

public class EMDTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> ROSE_GOLD = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(EMDUniverse.MOD_ID, "rose"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        registerWithOverride(registerable, ROSE_GOLD, Registries.ITEM.getEntry(EMDItems.ROSE_GOLD_INGOT),
                Style.EMPTY.withColor(TextColor.parse("#ea8880").getOrThrow()), 0.66f,
                        Map.of(EMDArmorMaterials.ROSE_GOLD_ARMOR_MATERIAL, "rose_darker"));
    }

    private static void registerWithoutOverride(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey,
                                                RegistryEntry<Item> item, Style style, float itemModelIndex) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(armorTrimKey.getValue().getPath(), item, itemModelIndex, Map.of(),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        registerable.register(armorTrimKey, trimMaterial);
    }

    private static void registerWithOverride(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey,
                                 RegistryEntry<Item> item, Style style, float itemModelIndex, Map<RegistryEntry<ArmorMaterial>, String> overrideArmorMaterials) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(armorTrimKey.getValue().getPath(), item, itemModelIndex, overrideArmorMaterials,
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        registerable.register(armorTrimKey, trimMaterial);
    }
}
