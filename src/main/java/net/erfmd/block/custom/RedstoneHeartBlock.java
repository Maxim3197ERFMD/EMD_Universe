package net.erfmd.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class RedstoneHeartBlock extends Block {
    public static final MapCodec<RedstoneHeartBlock> CODEC = createCodec(RedstoneHeartBlock::new);

    public MapCodec<RedstoneHeartBlock> getCodec() {
        return CODEC;
    }

    public RedstoneHeartBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return 120;
    }
}
