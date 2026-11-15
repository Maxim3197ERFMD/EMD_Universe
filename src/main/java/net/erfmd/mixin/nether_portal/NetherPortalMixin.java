package net.erfmd.mixin.nether_portal;

import net.erfmd.util.EMDTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.dimension.NetherPortal;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetherPortal.class)
public class NetherPortalMixin {
    @Redirect(
            method = {
                    "getWidth(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)I",
                    "isHorizontalFrameValid",
                    "getPotentialHeight"
            },
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/AbstractBlock$ContextPredicate;test(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z"
            )
    )
    private boolean allowCustomFrame(AbstractBlock.ContextPredicate instance, BlockState state, BlockView world, BlockPos pos) {
        return instance.test(state, world, pos)
                || state.isIn(EMDTags.Blocks.CUSTOM_PORTAL_FRAME);
    }
}
