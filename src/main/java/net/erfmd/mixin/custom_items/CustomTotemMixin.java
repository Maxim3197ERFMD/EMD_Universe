package net.erfmd.mixin.custom_items;

import net.erfmd.item.EMDItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Механика для кастомных тотемов бессмертия
 */

@Mixin(LivingEntity.class)
public abstract class CustomTotemMixin {

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void useCustomTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity)(Object)this;
        for (Hand hand : Hand.values()) {
            ItemStack stack = self.getStackInHand(hand);

            if (stack.isOf(EMDItems.NANOMACHINES)) {

                stack.decrement(1);

                self.setHealth(1.0F);
                self.clearStatusEffects();

                self.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                self.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                self.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));

                self.getWorld().sendEntityStatus(self, (byte)35);

                self.playSound(SoundEvents.ITEM_TOTEM_USE, 1.0F, 1.0F);

                cir.setReturnValue(true);
                cir.cancel();
                return;
            }
        }
    }
}
