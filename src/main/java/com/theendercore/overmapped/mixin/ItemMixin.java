package com.theendercore.overmapped.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.theendercore.overmapped.item.component.map_variant.MapVariant.Companion;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.EmptyMapItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static com.theendercore.overmapped.item.component.map_variant.MapVariant.getMapData;

@Mixin(Item.class)
public class ItemMixin {

    @ModifyReturnValue(method = "getName", at = @At("RETURN"))
    Component x(Component original, ItemStack stack) {
        Companion.MapType type = null;
        if (stack.getItem() instanceof EmptyMapItem) type = Companion.MapType.EMPTY;
        if (stack.getItem() instanceof MapItem) type = Companion.MapType.FULL;
        if (type != null) {
            var data = getMapData(stack);
            if (data != null) {
                var name = data.nameOverride(type);
                if (name != null) return name;
            }
        }

        return original;
    }
}
