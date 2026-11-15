package net.erfmd.item;

import net.erfmd.EMDUniverse;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class EMDArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> ROSE_GOLD_ARMOR_MATERIAL = registerArmorMaterial("rose_gold",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 9);
            }), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> Ingredient.ofItems(EMDItems.ROSE_GOLD_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(EMDUniverse.MOD_ID, "rose_gold"))), 1.0F, 0.1F));


    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(EMDUniverse.MOD_ID, name), material.get());
    }
}