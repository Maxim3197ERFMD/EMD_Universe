package net.erfmd.mixin.banner;

import net.minecraft.client.gui.screen.ingame.LoomScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LoomScreen.class)
public class LoomScreenMixin {
    @ModifyConstant(method = "onInventoryChanged()V", constant = @Constant(intValue = 6))
    private int modifyMaxBannerCount(int original_limit) {
        return 16;
    }
}

