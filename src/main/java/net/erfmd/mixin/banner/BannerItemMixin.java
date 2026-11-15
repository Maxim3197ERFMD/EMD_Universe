package net.erfmd.mixin.banner;

import net.minecraft.item.BannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BannerItem.class)
public class BannerItemMixin {
    @ModifyConstant(method = "appendBannerTooltip", constant = @Constant(intValue = 6))
    private static int modifyMaxBannerCount(int original_limit) {
        return 16;
    }
}

