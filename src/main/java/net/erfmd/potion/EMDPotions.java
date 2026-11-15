package net.erfmd.potion;

import net.erfmd.EMDUniverse;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

/**
 * Создание кастомных зелий
 */

public class EMDPotions {
    public static final RegistryEntry<Potion> WITHER = registerPotion("wither",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 900)));

    public static final RegistryEntry<Potion> LONG_WITHER = registerPotion("long_wither",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 1800)));

    public static final RegistryEntry<Potion> STRONG_WITHER = registerPotion("strong_wither",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 440, 1)));



    public static final RegistryEntry<Potion> BLINDNESS = registerPotion("blindness",
            new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 900)));

    public static final RegistryEntry<Potion> LONG_BLINDNESS = registerPotion("long_blindness",
            new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 1800)));



    public static final RegistryEntry<Potion> UNLUCK = registerPotion("unluck",
            new Potion(new StatusEffectInstance(StatusEffects.UNLUCK, 6000)));



    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(EMDUniverse.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        EMDUniverse.LOGGER.info("Registering Mod Potions for " + EMDUniverse.MOD_ID);
    }
}
