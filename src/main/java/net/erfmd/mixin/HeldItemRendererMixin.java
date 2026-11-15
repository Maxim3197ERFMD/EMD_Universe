package net.erfmd.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.erfmd.item.EMDItems;
import net.erfmd.item.custom.railgun.RailgunItem;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @ModifyVariable(method = "getHandRenderType", at = @At(value = "STORE"), ordinal = 1)
    private static boolean addCustomChargedWeapons(boolean original, @Local(ordinal = 0) ItemStack mainHand, @Local(ordinal = 1) ItemStack offHand) {
        return original || mainHand.getItem() instanceof CrossbowItem || offHand.getItem() instanceof CrossbowItem || isChargedRailgun(mainHand) || isChargedRailgun(offHand);
    }

    /**
     * @author Особая благодарность пользователю sleepykairo
     * @reason Поддержка кастомных арбалетов и рельсотрона.
     */
    @Overwrite
    private static boolean isChargedCrossbow(ItemStack stack) {
        if (stack.getItem() instanceof CrossbowItem) {
            return CrossbowItem.isCharged(stack);
        }
        return isChargedRailgun(stack);
    }

    @Redirect(method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderMapInOneHand(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IFLnet/minecraft/util/Arm;FLnet/minecraft/item/ItemStack;)V"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;isCharged(Lnet/minecraft/item/ItemStack;)Z")
            )
    )
    private boolean renderFirstPersonItemRedirect(ItemStack instance, Item item) {
        return instance.getItem() instanceof CrossbowItem || instance.isOf(EMDItems.RAILGUN);
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;isCharged(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean isRailgunCharged(ItemStack stack) {
        if (stack.isOf(EMDItems.RAILGUN)) {
            return RailgunItem.isCharged(stack);
        }
        return CrossbowItem.isCharged(stack);
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private static boolean injectCustomBow(ItemStack instance, Item item) {
        return !(instance.getItem() instanceof BowItem);
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private static boolean injectCustomCrossbow(ItemStack instance, Item item) {
        return !(instance.getItem() instanceof CrossbowItem || instance.isOf(EMDItems.RAILGUN));
    }

    private static boolean isChargedRailgun(ItemStack stack) {
        return stack.isOf(EMDItems.RAILGUN) && RailgunItem.isCharged(stack);
    }
}