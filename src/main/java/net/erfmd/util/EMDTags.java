package net.erfmd.util;

import net.erfmd.EMDUniverse;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * Регистрация кастомных тэгов
 */
public class EMDTags {
    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_ROSE_GOLD_TOOL = createTag("incorrect_for_rose_gold_tool");
        public static final TagKey<Block> NEEDS_ROSE_GOLD_TOOL = createTag("needs_rose_gold_tool");
        public static final TagKey<Block> CUSTOM_PORTAL_FRAME = createTag("custom_portal_frame");
        public static final TagKey<Block> RUBY_ORES = createTag("ruby_ores");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(EMDUniverse.MOD_ID, name));
        }
    }
}