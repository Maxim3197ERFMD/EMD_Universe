package net.erfmd.datagen;

import net.erfmd.EMDUniverse;
import net.erfmd.item.EMDItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class EMDAdvancementProvider extends FabricAdvancementProvider {
    public EMDAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry parentEntry = new AdvancementEntry(Identifier.of("minecraft", "husbandry/root"),
                null);
        AdvancementEntry WarhammerSoup = Advancement.Builder.create()
                .parent(parentEntry)
                .rewards(AdvancementRewards.Builder.experience(100))
                .display(
                        EMDItems.WARHAMMER_SOUP,
                        Text.translatable("advancements.husbandry.warhammer_soup.title"),
                        Text.translatable("advancements.husbandry.warhammer_soup.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("warhammer_soup", ConsumeItemCriterion.Conditions.item(EMDItems.WARHAMMER_SOUP))
                .build(consumer, EMDUniverse.MOD_ID + ":warhammer_soup");
    }
}
