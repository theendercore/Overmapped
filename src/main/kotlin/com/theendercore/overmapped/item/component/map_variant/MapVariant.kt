package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.Codec
import com.theendercore.overmapped.Overmapped.MODID
import com.theendercore.overmapped.init.OMBuiltInRegistries.MAP_VARIANT_TYPE
import com.theendercore.overmapped.init.OMComponents.MAP_VARIANT
import com.theendercore.overmapped.item.component.MapVariantComponent
import com.theendercore.overmapped.utils.HeightSampler
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

interface MapVariant {
    fun getType(): MapVariantType<*>
    fun addData(player: Player, openMap: ItemStack)
    fun createHeightSampler(player: Player, level: Level, map: ItemStack): HeightSampler?
    fun nameOverride(type: MapType): Component?
    fun appendTooltip(
        stack: ItemStack, ctx: Item.TooltipContext, list: MutableList<Component>, tooltipFlag: TooltipFlag,
    )

    companion object {
        val CODEC: Codec<MapVariant> = MAP_VARIANT_TYPE.byNameCodec().dispatch(MapVariant::getType) { it.codec() }
        fun ItemStack.setMapData(data: MapVariant) = set(MAP_VARIANT, MapVariantComponent(data))

        @JvmStatic
        fun ItemStack.getMapData() = get(MAP_VARIANT)?.variant
        fun ItemStack.createHeightSampler(player: Player, level: Level): HeightSampler? =
            getMapData()?.createHeightSampler(player, level, this)

        enum class MapType {
            EMPTY,
            FULL;

            fun lang(variant: String) = "item.$MODID.$variant.${mapName()}"
            fun mapName() = if (this == FULL) "filled_map" else "map"
        }
    }
}