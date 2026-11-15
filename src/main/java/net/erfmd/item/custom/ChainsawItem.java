package net.erfmd.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;

public class ChainsawItem extends SwordItem {
    public ChainsawItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return state.isIn(BlockTags.AXE_MINEABLE) ? 12.0f : 1.0f;
    }

    public boolean isCorrectForDrops(ItemStack stack, BlockState state) {
        return state.isIn(BlockTags.AXE_MINEABLE);
    }
}