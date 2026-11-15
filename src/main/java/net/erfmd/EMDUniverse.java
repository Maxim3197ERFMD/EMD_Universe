package net.erfmd;

import net.erfmd.block.EMDBlocks;
import net.erfmd.config.Config;
import net.erfmd.config.ConfigHandler;
import net.erfmd.item.EMDGroups;
import net.erfmd.item.EMDItems;
import net.erfmd.item.custom.railgun.RailgunCooldown;
import net.erfmd.item.custom.railgun.RailgunShotManager;
import net.erfmd.network.RailgunBeamPayload;
import net.erfmd.potion.EMDPotions;
import net.erfmd.util.EMDLootTableModifiers;
import net.erfmd.villager.EMDVillagers;
import net.erfmd.world.gen.EMDWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.impl.transfer.fluid.CauldronStorage;
import net.fabricmc.fabric.mixin.transfer.BucketItemAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.GameRules;
import net.minecraft.world.event.GameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static net.minecraft.text.Text.literal;

/**
 * @author Maxim3197
 * @version 1.5.0
 */

public class EMDUniverse implements ModInitializer {
	public static final String MOD_ID = "emd_universe";
	public static ConfigHandler CONFIG;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final BooleanProperty LOUD = BooleanProperty.of("loud");
	public static float loudnessMultiplier = 0.05f;

	private final Set<PlayerEntity> playersInTheEnd = new HashSet<>();

	public static GameRules currentGameRules;

	/**
	 * Основной регистратор
	 */
	@Override
	public void onInitialize() {
		var fallbackDrainBehavior = (ItemDispenserBehavior) DispenserBlock.BEHAVIORS.get(Items.WATER_BUCKET);
		var newDrainBehavior = new ItemDispenserBehavior() {
			private ItemStack replace(BlockPointer pointer, ItemStack oldStack, ItemStack newStack) {
				pointer.world().emitGameEvent(null, GameEvent.FLUID_PLACE, pointer.pos());
				return this.decrementStackWithRemainder(pointer, oldStack, newStack);
			}

			@Override
			public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
				BlockPos blockPos;
				ServerWorld worldAccess = pointer.world();
				BlockState blockState = worldAccess
						.getBlockState(blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING)));
				Block block = blockState.getBlock();

				if (!(block instanceof AbstractCauldronBlock)) {
					return fallbackDrainBehavior.dispense(pointer, stack);
				}

				CauldronStorage storage = CauldronStorage.get(worldAccess, blockPos);

				// cauldron is not empty
				if (!storage.isResourceBlank() || storage.getAmount() != 0)
					return stack;

				try (var transaction = Transaction.openOuter()) {
					var bucketFluid = ((BucketItemAccessor) stack.getItem()).fabric_getFluid();
					var resource = FluidVariant.of(bucketFluid);

					if (storage.insert(resource, FluidConstants.BUCKET, transaction) != FluidConstants.BUCKET) {
						return stack;
					}

					// save the fluid transfer
					transaction.commit();

					// play the fill sound
					worldAccess.playSound(null, blockPos, FluidVariantAttributes.getEmptySound(resource),
							SoundCategory.BLOCKS, 1.0f, 1.0f);

					// replace the full bucket with an empty one
					return this.replace(pointer, stack, Items.BUCKET.getDefaultStack());
				}
			}
		};
		for (var fluid : Registries.FLUID) {
			var bucket = fluid.getBucketItem();
			if (bucket == null)
				continue;
			DispenserBlock.registerBehavior(bucket, newDrainBehavior);
		}

		var fallbackFillBehavior = (ItemDispenserBehavior) DispenserBlock.BEHAVIORS.get(Items.BUCKET);
		DispenserBlock.registerBehavior(Items.BUCKET, new ItemDispenserBehavior() {
			private ItemStack replace(BlockPointer pointer, ItemStack oldStack, ItemStack newStack) {
				pointer.world().emitGameEvent(null, GameEvent.FLUID_PICKUP, pointer.pos());
				return this.decrementStackWithRemainder(pointer, oldStack, newStack);
			}

			@Override
			public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
				BlockPos blockPos;
				ServerWorld worldAccess = pointer.world();
				BlockState blockState = worldAccess
						.getBlockState(blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING)));
				Block block = blockState.getBlock();

				if (!(block instanceof AbstractCauldronBlock)) {
					return fallbackFillBehavior.dispense(pointer, stack);
				}

				CauldronStorage storage = CauldronStorage.get(worldAccess, blockPos);

				if (storage.isResourceBlank() || storage.getAmount() == 0)
					return stack;

				var resource = storage.getResource();
				var bucket = resource.getFluid().getBucketItem();

				if (bucket == null)
					return stack;

				try (var transaction = Transaction.openOuter()) {
					if (storage.extract(resource, FluidConstants.BUCKET, transaction) != FluidConstants.BUCKET) {
						return stack;
					}

					transaction.commit();

					worldAccess.playSound(null, blockPos, FluidVariantAttributes.getFillSound(resource),
							SoundCategory.BLOCKS, 1.0f, 1.0f);

					return this.replace(pointer, stack, bucket.getDefaultStack());
				}
			}
		});

		Config.loadConfig();

		Path configPath = FabricLoader.getInstance().getConfigDir().resolve("waxed_pistons.txt");
		try {
			if(!Files.exists(configPath)) {
				Files.createFile(configPath);
				Files.write(configPath, Float.toString(loudnessMultiplier).getBytes());
			}
			loudnessMultiplier = Float.parseFloat(Files.readAllLines(configPath).getFirst());
		} catch (IOException e) {
			throw new RuntimeException("Loudness file could not be read properly. Proper format is a text file containing a number in decimal form.");
		}

		ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
			if (destination.getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
				playersInTheEnd.add(player);
			}
		});

		EntityElytraEvents.ALLOW.register(entity -> {
			if (entity.getEntityWorld().getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
				if (entity instanceof PlayerEntity player) {
					playersInTheEnd.add(player);
					return false;
				}
			}
			return true;
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			playersInTheEnd.removeIf(player ->
					!player.isAlive() ||
					!player.getWorld().getRegistryKey().getValue().toString().equals("minecraft:the_end"));

			for (PlayerEntity player : playersInTheEnd) {
				player.sendMessage(literal("§cПолёт на элитрах в Энде отключён!"), true);
			}
		});

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			PlayerEntity player = handler.getPlayer();
			if (player.getWorld().getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
				playersInTheEnd.add(player);
			}
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			if (newPlayer.getWorld().getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
				playersInTheEnd.add(newPlayer);
			}
		});

		PayloadTypeRegistry.playS2C().register(RailgunBeamPayload.ID, RailgunBeamPayload.CODEC);

		RailgunShotManager.register();
		RailgunCooldown.register();

		EMDGroups.registerItemGroups();

		EMDItems.registerModItems();
		EMDBlocks.registerModBlocks();

		EMDPotions.registerPotions();

		EMDLootTableModifiers.modifyLootTables();

		EMDWorldGeneration.generateModWorldGen();

		EMDUniverseGameRules.setupGamerules();

		registerCustomTrades();
		registerCustomWanderingTrades();

		EMDVillagers.registerVillagers();

		CompostingChanceRegistry.INSTANCE.add(EMDItems.CHERRIES, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(EMDItems.BANANA, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(EMDItems.HONEYSUCKLE, 0.15f);

		CompostingChanceRegistry.INSTANCE.add(EMDItems.PARSLEY, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(EMDItems.PARSLEY_SEEDS, 0.05f);

		CompostingChanceRegistry.INSTANCE.add(EMDItems.GOLDEN_FLOWER, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(EMDItems.GOLDEN_FLOWER_PETALS, 0.05f);

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.MUNDANE, Items.WITHER_SKELETON_SKULL, EMDPotions.WITHER);
			builder.registerPotionRecipe(EMDPotions.WITHER, Items.REDSTONE, EMDPotions.LONG_WITHER);
			builder.registerPotionRecipe(EMDPotions.WITHER, Items.GLOWSTONE, EMDPotions.STRONG_WITHER);

			builder.registerPotionRecipe(Potions.MUNDANE, Items.GLOW_INK_SAC, EMDPotions.BLINDNESS);
			builder.registerPotionRecipe(EMDPotions.BLINDNESS, Items.REDSTONE, EMDPotions.LONG_BLINDNESS);

			builder.registerPotionRecipe(Potions.HEALING, Items.GOLDEN_APPLE, Potions.LUCK);

			builder.registerPotionRecipe(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, EMDPotions.UNLUCK);
		});
	}

	/**
	 * Регистратор игровых правил
	 */
	public static void setGameRules(GameRules gameRules){
		currentGameRules = gameRules;
	}

	/**
	 * Регистратор кастомной торговли с жителями
	 */
	public static void registerCustomTrades() {
		TradeOfferHelper.registerVillagerOffers(EMDVillagers.BEEKEEPER, 1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.PEONY, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.LILAC, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.SUNFLOWER, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.ROSE_BUSH, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.DANDELION, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.AZURE_BLUET, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.ALLIUM, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.BLUE_ORCHID, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.POPPY, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.CORNFLOWER, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.LILY_OF_THE_VALLEY, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.RED_TULIP, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.ORANGE_TULIP, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.PINK_TULIP, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.WHITE_TULIP, 10),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 1),
					new ItemStack(Items.HONEYCOMB, 3),
					16, 2, 0f
			));
		});
		TradeOfferHelper.registerVillagerOffers(EMDVillagers.BEEKEEPER, 2, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.CAMPFIRE, 1),
					new ItemStack(Items.EMERALD, 1),
					1, 10, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
                    Optional.of(new TradedItem(Items.GLASS_BOTTLE, 1)),
					new ItemStack(Items.HONEY_BOTTLE, 1),
					16, 5, 0.05f
			));
			factories.add((entity, random) -> {
				int emeralds = 3 + random.nextInt(4);
				return new TradeOffer(
						new TradedItem(Items.BEEHIVE, 1),
						new ItemStack(Items.EMERALD, emeralds),
						1, 20, 0f
				);
			});
		});
		TradeOfferHelper.registerVillagerOffers(EMDVillagers.BEEKEEPER, 3, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.HONEY_BLOCK, 1),
					new ItemStack(Items.EMERALD, 4),
					8, 5, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 1),
					new ItemStack(Items.CANDLE, 4),
					6, 5, 0f
			));
		});
		TradeOfferHelper.registerVillagerOffers(EMDVillagers.BEEKEEPER, 4, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.HONEYCOMB_BLOCK, 1),
					new ItemStack(Items.EMERALD, 1),
					16, 5, 0f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 1),
					new ItemStack(Items.SUGAR, 3),
					16, 5, 0f
			));
		});
		TradeOfferHelper.registerVillagerOffers(EMDVillagers.BEEKEEPER, 5, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 1),
					new ItemStack(Items.CAKE, 1),
					12, 15, 0f
			));
			factories.add((entity, random) -> {
				int price = 13 + random.nextInt(23);
				return new TradeOffer(
						new TradedItem(Items.EMERALD, price),
						Optional.of(new TradedItem(Items.BEEHIVE, 1)),
						new ItemStack(Items.BEE_SPAWN_EGG, 1),
						1, 5, 0f
				);
			});
		});

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 4, factories ->
				factories.add((entity, random) -> new TradeOffer(
                        new TradedItem(EMDItems.RUBY, 2),
                        new ItemStack(Items.PHANTOM_MEMBRANE, 1),
                        6, 5, 0.05f
				))
		);

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 3, factories -> {
			factories.add((entity, random) -> new TradeOffer(
							new TradedItem(Items.EMERALD, 2),
							new ItemStack(EMDItems.CHERRIES, 1),
							6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
							new TradedItem(Items.EMERALD, 2),
							new ItemStack(EMDItems.BANANA, 1),
							6, 5, 0.05f
			));
		});

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 4, factories ->
				factories.add((entity, random) -> new TradeOffer(
                        new TradedItem(Items.EMERALD, 10),
                        new ItemStack(EMDItems.HAM_BAT, 1),
                        6, 5, 0.05f
				))
		);
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 5, factories ->
			factories.add((entity, random) -> new TradeOffer(
							new TradedItem(Items.EMERALD, 2),
							new ItemStack(EMDItems.HONEYSUCKLE, 10),
							6, 5, 0.05f
			))
		);

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 5, factories ->
				factories.add((entity, random) -> new TradeOffer(
                        new TradedItem(EMDItems.RUBY, 10),
                        new ItemStack(Items.BUNDLE, 1),
                        6, 5, 0.05f
				))
		);

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(EMDItems.OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(EMDItems.LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(EMDItems.SCORPION_ARMOR_TRIM_SMITHING_TEMPLATE, 1),
					6, 5, 0.05f
			));
		});
	}

	/**
	 * Регистратор кастомной торговли со странствующими торговцами
	 */
	public static void registerCustomWanderingTrades() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.OCHRE_FROGLIGHT, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.VERDANT_FROGLIGHT, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.PEARLESCENT_FROGLIGHT, 1),
					6, 5, 0.05f
			));

			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(Items.TUBE_CORAL_FAN, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(Items.BRAIN_CORAL_FAN, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(Items.BUBBLE_CORAL_FAN, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(Items.FIRE_CORAL_FAN, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(Items.HORN_CORAL_FAN, 1),
					6, 5, 0.05f
			));

			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 2),
					new ItemStack(Items.TUBE_CORAL, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.BRAIN_CORAL, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.BUBBLE_CORAL, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.FIRE_CORAL, 1),
					6, 5, 0.05f
			));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(EMDItems.RUBY, 1),
					new ItemStack(Items.HORN_CORAL, 1),
					6, 5, 0.05f
			));
		});
	}
}