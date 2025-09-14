package com.theendercore.overmapped.item.component.map_variant

import com.theendercore.overmapped.init.OMComponents
import com.theendercore.overmapped.init.OMComponents.MAP_VARIANT
import com.theendercore.overmapped.item.component.map_variant.MapVariant.Companion.createHeightSampler
import com.theendercore.overmapped.utils.HeightSampler
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.saveddata.maps.MapItemSavedData

object MapVariantProcessing {
    @JvmStatic
    fun onMapOpen(closedMap: ItemStack, player: Player, openMap: ItemStack) {
        closedMap.get(MAP_VARIANT)?.variant?.addData(player, openMap)
    }

    @JvmStatic
    fun getHeightSampler(level: Level, entity: Entity, mapData: MapItemSavedData): HeightSampler? {
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
}