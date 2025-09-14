package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.Codec
import com.theendercore.overmapped.init.OMBuiltInRegistries.MAP_VARIANT_TYPE
import com.theendercore.overmapped.utils.MapSampler
import com.theendercore.overmapped.utils.MapType
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraft.world.level.saveddata.maps.MapItemSavedData

interface MapVariant {
    fun getType(): MapVariantType<*>
    fun createVariant(player: Player): MapVariant

    fun createMapSampler(player: Player, level: Level, map: ItemStack): MapSampler? = null
    fun nameOverride(type: MapType): Component? = null
    fun appendTooltip(stack: ItemStack, ctx: Item.TooltipContext, list: MutableList<Component>, flags: TooltipFlag) =
        Unit

    fun hasCustomMapUpdates(): Boolean = false
    fun customMapUpdate(level: Level, entity: Entity, mapData: MapItemSavedData) = Unit

    companion object {
        val CODEC: Codec<MapVariant> = MAP_VARIANT_TYPE.byNameCodec().dispatch(MapVariant::getType) { it.codec() }
    }
}