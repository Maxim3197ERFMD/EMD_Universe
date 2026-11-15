package net.erfmd.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.erfmd.item.EMDItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class EMDItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public EMDItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(EMDItems.ROSE_GOLDEN_HELMET)
                .add(EMDItems.ROSE_GOLDEN_CHESTPLATE)
                .add(EMDItems.ROSE_GOLDEN_LEGGINGS)
                .add(EMDItems.ROSE_GOLDEN_BOOTS);

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(EMDItems.ROSE_GOLD_INGOT);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(EMDItems.OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE)
                .add(EMDItems.LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE)
                .add(EMDItems.SCORPION_ARMOR_TRIM_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(EMDItems.ROSE_GOLDEN_SWORD)
                .add(EMDItems.DISASSEMBLY_CHAINSAW)
                .add(EMDItems.LM85_BLADE);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(EMDItems.LM85_BLADE)
                .add(EMDItems.ROSE_GOLDEN_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(EMDItems.ROSE_GOLDEN_SHOVEL);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(EMDItems.DISASSEMBLY_CHAINSAW)
                .add(EMDItems.ROSE_GOLDEN_AXE);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(EMDItems.ROSE_GOLDEN_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(EMDItems.ROSE_GOLDEN_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(EMDItems.ROSE_GOLDEN_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(EMDItems.ROSE_GOLDEN_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(EMDItems.ROSE_GOLDEN_BOOTS);

        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(EMDItems.RUBY)
                .add(EMDItems.ROSE_GOLD_INGOT);

        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(EMDItems.NETHERITE_BOW);

        getOrCreateTagBuilder(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(EMDItems.NETHERITE_CROSSBOW);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(EMDItems.NETHERITE_BOW)
                .add(EMDItems.NETHERITE_CROSSBOW);
    }
}