package net.erfmd.mixin;

import net.erfmd.item.EMDItems;
import net.erfmd.item.custom.railgun.RailgunItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Особая благодарность пользователю sleepykairo
 * Фикс анимации от первого лица для кастомных вариаций арбалета и некоторых предметов (рельсотрона и т.д.)
 */

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Redirect(method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean inject(ItemStack instance, Item item) {
        return instance.getItem() instanceof CrossbowItem || instance.isOf(EMDItems.RAILGUN);
    }

    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
    private static void addRailgunArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(EMDItems.RAILGUN)) {
            return;
        }

        if (player.isUsingItem() && player.getActiveHand() == hand && !RailgunItem.isCharged(stack)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            return;
        }

        if (RailgunItem.isCharged(stack)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}