package net.erfmd.mixin.lavagen;

import net.erfmd.EMDUniverse;
import net.minecraft.block.Blocks;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({NoiseChunkGenerator.class})
public class NoiseChunkGeneratorMixin {
    /**
     * @author Особая благодарность пользователю Matteo_fey (@warior456)
     * @reason Фикс спавна пещер с лавой начиная -54 высоты (была привязана к уровню моря)
     */
    @Overwrite
    private static AquiferSampler.FluidLevelSampler createFluidLevelSampler(ChunkGeneratorSettings settings) {
        AquiferSampler.FluidLevel fluidLevel = new AquiferSampler.FluidLevel(settings.seaLevel() - EMDUniverse.CONFIG.distance_between_sea_and_lava_level, Blocks.LAVA.getDefaultState());
        int i = settings.seaLevel();
        AquiferSampler.FluidLevel fluidLevel2 = new AquiferSampler.FluidLevel(i, settings.defaultFluid());
        AquiferSampler.FluidLevel fluidLevel3 = new AquiferSampler.FluidLevel(DimensionType.MIN_HEIGHT * 2, Blocks.AIR.getDefaultState());
        return (x, y, z) -> {
            if (y < Math.min(settings.seaLevel() - EMDUniverse.CONFIG.distance_between_sea_and_lava_level, i)) {
                return fluidLevel;
            }
            return fluidLevel2;
        };
    }
}
