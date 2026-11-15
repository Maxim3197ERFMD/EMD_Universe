package net.erfmd.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class BeekeepingTableBlock extends HorizontalFacingBlock {

    public static final MapCodec<BeekeepingTableBlock> CODEC = createCodec(BeekeepingTableBlock::new);
    private static final VoxelShape SHAPE_NORTH = VoxelShapes.union(
            Block.createCuboidShape(0, 12, 0, 16, 16, 16),
            Block.createCuboidShape(1, 0, 2, 2, 12, 14),
            Block.createCuboidShape(14, 0, 2, 15, 12, 14),
            Block.createCuboidShape(2, 5, 3, 14, 12, 13)
    );
    private static final VoxelShape SHAPE_SOUTH = rotateShape(Direction.NORTH, Direction.SOUTH, SHAPE_NORTH);
    private static final VoxelShape SHAPE_EAST  = rotateShape(Direction.NORTH, Direction.EAST, SHAPE_NORTH);
    private static final VoxelShape SHAPE_WEST  = rotateShape(Direction.NORTH, Direction.WEST, SHAPE_NORTH);

    public BeekeepingTableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case EAST  -> SHAPE_EAST;
            case WEST  -> SHAPE_WEST;
            default    -> SHAPE_NORTH;
        };
    }

    private static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[] { shape, VoxelShapes.empty() };
        int times = (to.getHorizontal() - from.getHorizontal() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
                buffer[1] = VoxelShapes.union(buffer[1],
                        VoxelShapes.cuboid(
                                1 - maxZ, minY, minX,
                                1 - minZ, maxY, maxX
                        )
                );
            });
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }
        return buffer[0];
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}