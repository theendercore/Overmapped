package com.theendercore.overmapped.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.theendercore.overmapped.item.component.map_variant.MapVariantProcessing;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EmptyMapItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(EmptyMapItem.class)
public class EmptyMapItemMixin {

    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/MapItem;create(Lnet/minecraft/world/level/Level;IIBZZ)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack addHeightComponent(ItemStack openMap, @Local(argsOnly = true) Player entity, @Local ItemStack closedMap) {
        MapVariantProcessing.onMapOpen(closedMap, entity, openMap);
        return openMap;
    }
}
