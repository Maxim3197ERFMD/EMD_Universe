package net.erfmd.block.custom.crops;

import net.erfmd.item.EMDItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class GoldenFlowerCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = IntProperty.of("age", 0, 7);

    public GoldenFlowerCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return EMDItems.GOLDEN_FLOWER_PETALS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
