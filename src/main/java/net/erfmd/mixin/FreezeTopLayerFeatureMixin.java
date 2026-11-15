package net.erfmd.mixin;

import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FreezeTopLayerFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * Фикс спавна снега под деревьями
 */

@Mixin(FreezeTopLayerFeature.class)
public class FreezeTopLayerFeatureMixin {
    @ModifyArg(method = "generate", at = @At(value="INVOKE", target = "Lnet/minecraft/world/StructureWorldAccess;getTopY(Lnet/minecraft/world/Heightmap$Type;II)I"))
    private Heightmap.Type snowUnderTrees(Heightmap.Type heightmap) {
        return Heightmap.Type.MOTION_BLOCKING_NO_LEAVES;
    }
}
