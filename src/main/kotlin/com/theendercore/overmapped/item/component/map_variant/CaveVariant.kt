package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.theendercore.overmapped.item.component.map_variant.MapVariant.Companion.setMapData
import com.theendercore.overmapped.utils.HeightSampler
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

open class CaveVariant(val yHeight: Int) : MapVariant {
    override fun getType(): MapVariantType<CaveVariant> = MapVariantType.CAVE
    override fun addData(player: Player, openMap: ItemStack) {
        openMap.setMapData(CaveVariant(player.y.toInt()))
    }

    override fun createHeightSampler(player: Player, level: Level, map: ItemStack): HeightSampler? =
        HeightSampler { yHeight }

    override fun nameOverride(type: MapVariant.Companion.MapType): Component? =
        Component.translatable(type.lang("cave"))

    override fun appendTooltip(
        stack: ItemStack,
        ctx: Item.TooltipContext,
        list: MutableList<Component>,
        tooltipFlag: TooltipFlag,
    ) {
        if (!tooltipFlag.isAdvanced) return

        list.add(Component.translatable("Y level: $yHeight", yHeight))
    }

    override fun equals(other: Any?): Boolean {
        return other is CaveVariant && other.yHeight == yHeight
    }

    companion object {
        val CODEC: MapCodec<CaveVariant> = RecordCodecBuilder.mapCodec {
            it.group(Codec.INT.fieldOf("y_height").forGetter(CaveVariant::yHeight)).apply(it, ::CaveVariant)
        }
    }
}
