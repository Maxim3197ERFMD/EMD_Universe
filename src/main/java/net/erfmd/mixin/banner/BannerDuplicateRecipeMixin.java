package net.erfmd.mixin.banner;

import net.minecraft.recipe.BannerDuplicateRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BannerDuplicateRecipe.class)
public class BannerDuplicateRecipeMixin {
    @ModifyConstant(method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z", constant = @Constant(intValue = 6))
    private int getOriginalLimit(int original_limit) {
        return 16;
    }

    @ModifyConstant(method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;", constant = @Constant(intValue = 6))
    private int getOriginalCraftingLimit(int original_limit) {
        return 16;
    }
}

