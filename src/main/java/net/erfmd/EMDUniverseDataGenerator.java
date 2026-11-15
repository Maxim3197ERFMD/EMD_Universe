package net.erfmd;

import net.erfmd.datagen.*;
import net.erfmd.datagen.lang.*;
import net.erfmd.trim.EMDTrimMaterials;
import net.erfmd.trim.EMDTrimPatterns;
import net.erfmd.world.EMDConfiguredFeatures;
import net.erfmd.world.EMDPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class EMDUniverseDataGenerator implements DataGeneratorEntrypoint {
	/**
	 * Генератор данных №1
	 */
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EMDAdvancementProvider::new);
		pack.addProvider(EMDBlockTagProvider::new);
		pack.addProvider(EMDItemTagProvider::new);
		pack.addProvider(EMDLootTableProvider::new);
		pack.addProvider(EMDModelProvider::new);
		pack.addProvider(EMDRecipeProvider::new);
		pack.addProvider(EMDRegistryDataGenerator::new);

		pack.addProvider(EMDLangEnglishProvider::new);
		pack.addProvider(EMDLangRussianProvider::new);
		pack.addProvider(EMDLangUkrainianProvider::new);
		pack.addProvider(EMDLangBelarusianProvider::new);
		pack.addProvider(EMDLangGermanProvider::new);
		pack.addProvider(EMDLangFrenchProvider::new);
		pack.addProvider(EMDLangFinnishProvider::new);
	}

	/**
	 * Генератор данных №2
	 */
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, EMDConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, EMDPlacedFeatures::boostrap);

		registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, EMDTrimMaterials::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, EMDTrimPatterns::bootstrap);
	}
}
