package com.theendercore.overmapped.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.theendercore.overmapped.utils.MapVariantProcessing;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public class ItemMixin {

    @ModifyReturnValue(method = "getName", at = @At("RETURN"))
    Component changeMapName(Component original, ItemStack stack) {
        var name = MapVariantProcessing.getMapName(stack);
        return name != null ? name : original;
    }
}
