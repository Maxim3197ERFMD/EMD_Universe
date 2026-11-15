package net.erfmd.item.custom;

import net.minecraft.block.DispenserBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class NanoMachines extends SwordItem implements Equipment {
    public NanoMachines(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    public String getTranslationKey(ItemStack stack) {
        DyeColor dyeColor = (DyeColor)stack.get(DataComponentTypes.BASE_COLOR);
        if (dyeColor != null) {
            String var10000 = this.getTranslationKey();
            return var10000 + "." + dyeColor.getName();
        } else {
            return super.getTranslationKey(stack);
        }
    }

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        BannerItem.appendBannerTooltip(stack, tooltip);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    public EquipmentSlot getSlotType() {
        return EquipmentSlot.OFFHAND;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        if (!(entity instanceof PlayerEntity player)) return;

        boolean isOffhand = player.getOffHandStack() == stack;

        int modelData = isOffhand ? 2 : 1;

        CustomModelDataComponent current = stack.get(DataComponentTypes.CUSTOM_MODEL_DATA);

        if (current == null || current.value() != modelData) {
            stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(modelData));
        }
    }
}
