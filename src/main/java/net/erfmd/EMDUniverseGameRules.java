package net.erfmd;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class EMDUniverseGameRules {
    public static GameRules.Key<GameRules.IntRule> MAX_BANNER_STACK;

    public static void setupGamerules() {
        MAX_BANNER_STACK = GameRuleRegistry.register("maxBannerPatternStack", GameRules.Category.MISC, GameRuleFactory.createIntRule(6, 0, 10000));
    }
}