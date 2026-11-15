package net.erfmd.trim;

import net.erfmd.EMDUniverse;
import net.erfmd.item.EMDItems;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

/**
 * Создание кастомных шаблонов для брони
 */

public class EMDTrimPatterns {
    public static final RegistryKey<ArmorTrimPattern> OIL_SUN = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
            Identifier.of(EMDUniverse.MOD_ID, "oil_sun"));
    public static final RegistryKey<ArmorTrimPattern> LOVE_HAREM = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
            Identifier.of(EMDUniverse.MOD_ID, "love_harem"));
    public static final RegistryKey<ArmorTrimPattern> SCORPION = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
            Identifier.of(EMDUniverse.MOD_ID, "scorpion"));

    public static void bootstrap(Registerable<ArmorTrimPattern> context) {
        register(context, EMDItems.OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE, OIL_SUN);
        register(context, EMDItems.LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE, LOVE_HAREM);
        register(context, EMDItems.SCORPION_ARMOR_TRIM_SMITHING_TEMPLATE, SCORPION);
    }

    private static void register(Registerable<ArmorTrimPattern> context, Item item, RegistryKey<ArmorTrimPattern> key) {
        ArmorTrimPattern trimPattern = new ArmorTrimPattern(key.getValue(), Registries.ITEM.getEntry(item),
                Text.translatable(Util.createTranslationKey("trim_pattern", key.getValue())), false);

        context.register(key, trimPattern);
    }
}
