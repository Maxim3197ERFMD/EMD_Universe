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
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HeldItemRenderer.class)
public class HeldItemRenderer_OLD_Mixin {
    @ModifyVariable(method = "getHandRenderType", at = @At(value = "STORE"), ordinal = 1)
    private static boolean addCustomCrossbowToCheck(boolean original, @Local(ordinal = 0) ItemStack itemStack, @Local(ordinal = 1) ItemStack itemStack2) {
        return original || itemStack.getItem() instanceof CrossbowItem || itemStack2.getItem() instanceof CrossbowItem;
    }

    /**
     * @author Особая благодарность пользователю sleepykairo
     * @reason Фикс анимации от первого лица для кастомных вариаций арбалета
     */
    @Overwrite
    private static boolean isChargedCrossbow(ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(stack);
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
    public boolean renderFirstPersonItemRedirect(ItemStack instance, Item item) {
        return instance.getItem() instanceof CrossbowItem;
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private static boolean inject(ItemStack instance, Item item) {
        return !(instance.getItem() instanceof BowItem);
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private static boolean inject1(ItemStack instance, Item item) {
        return !(instance.getItem() instanceof CrossbowItem);
    }

    // Клиентский рендерер осведомлённым о заряженном рельсотроне
    @Inject(method = "isChargedCrossbow", at = @At("HEAD"), cancellable = true)
    private static void emd$railgunChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(EMDItems.RAILGUN) && RailgunItem.isCharged(stack)) {
            cir.setReturnValue(true);
        }
    }
}
