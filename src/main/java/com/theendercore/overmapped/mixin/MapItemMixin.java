package com.theendercore.overmapped.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.theendercore.overmapped.utils.MapSampler;
import com.theendercore.overmapped.utils.MapVariantProcessing;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(MapItem.class)
public abstract class MapItemMixin {

   /* // (ender) TODO, Myb don't do this?
    @Redirect(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/dimension/DimensionType;hasCeiling()Z"))
    private boolean hasCeiling(DimensionType instance) {
        return false;
    }*/

    @Inject(method = "appendHoverText", at = @At("TAIL"))
    void x(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag flags, CallbackInfo ci) {
        var data = MapVariantProcessing.getMapData(stack);
        if (data != null) data.appendTooltip(stack, ctx, tooltip, flags);
    }

    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void customMapLogic(Level level, Entity entity, MapItemSavedData mapData, CallbackInfo ci) {
        var override = MapVariantProcessing.getOverride(level, entity, mapData);
        if (override != null) {
            override.customMapUpdate(level, entity, mapData);
            ci.cancel();
        }
    }

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData;getHoldingPlayer(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData$HoldingPlayer;"))
    private void createHeightSampler(Level level, Entity entity, MapItemSavedData mapData, CallbackInfo ci, @Share("map_sampler") LocalRef<@Nullable MapSampler> heightSampler) {
        heightSampler.set(MapVariantProcessing.getMapSampler(level, entity, mapData));
    }

    @ModifyExpressionValue(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/LevelChunk;getHeight(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I"))
    public int sampleHeightmap(int value, @Share("map_sampler") LocalRef<MapSampler> heightSampler) {
        var sampler = heightSampler.get();
        if (sampler != null) return sampler.getHeight(value);
        return value;
    }
}
