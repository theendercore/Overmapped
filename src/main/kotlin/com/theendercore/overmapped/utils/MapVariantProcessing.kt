package com.theendercore.overmapped.utils

import com.theendercore.overmapped.init.OMComponents.MAP_VARIANT
import com.theendercore.overmapped.item.component.map_variant.MapVariant
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.EmptyMapItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.MapItem
import net.minecraft.world.level.Level
import net.minecraft.world.level.saveddata.maps.MapItemSavedData

object MapVariantProcessing {
    @JvmStatic
    fun onMapOpen(closedMap: ItemStack, player: Player, openMap: ItemStack) {
        val data = closedMap.get(MAP_VARIANT)?.createVariant(player) ?: return
        openMap.setMapData(data)
    }

    @JvmStatic
    fun getMapName(stack: ItemStack): Component? {
        val type = when (stack.item) {
            is EmptyMapItem -> MapType.EMPTY
            is MapItem -> MapType.EMPTY
            else -> return null
        }

        return stack.getMapData()?.nameOverride(type)
    }

    @JvmStatic
    fun getMapSampler(level: Level, entity: Entity, mapData: MapItemSavedData): MapSampler? {
        if (entity !is Player) return null

        var stack = entity.mainHandItem
        var mapId = stack.get(DataComponents.MAP_ID)
        if (mapId != null && level.getMapData(mapId) === mapData) {
            return stack.createHeightSampler(entity, level)
        }

        stack = entity.offhandItem
        mapId = stack.get(DataComponents.MAP_ID)
        if (mapId != null && level.getMapData(mapId) === mapData) {
            return stack.createHeightSampler(entity, level)
        }

        return null
    }

    @JvmStatic
    fun getOverride(level: Level, entity: Entity, mapData: MapItemSavedData): MapVariant? {
        if (entity !is Player) return null

        var stack = entity.mainHandItem
        var mapId = stack.get(DataComponents.MAP_ID)
        var mapVariant = stack.getMapData()
        if (mapId != null && mapVariant?.hasCustomMapUpdates() == true && level.getMapData(mapId) === mapData) {
            return mapVariant
        }

        stack = entity.offhandItem
        mapId = stack.get(DataComponents.MAP_ID)
        mapVariant = stack.getMapData()

        if (mapId != null && mapVariant?.hasCustomMapUpdates() == true && level.getMapData(mapId) === mapData) {
            return mapVariant
        }

        return null
    }

    @JvmStatic
    fun ItemStack.getMapData() = get(MAP_VARIANT)
    fun ItemStack.setMapData(data: MapVariant) = set(MAP_VARIANT, data)
    fun ItemStack.createHeightSampler(player: Player, level: Level): MapSampler? =
        getMapData()?.createMapSampler(player, level, this)
}