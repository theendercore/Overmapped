package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.theendercore.overmapped.utils.map.MapType
import com.theendercore.overmapped.utils.map.samplers.CaveSampler
import com.theendercore.overmapped.utils.map.samplers.MapSampler
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Component.translatable
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.MapColor
import org.teamvoided.creative_works.util.trash.TextOps.Companion.colorFormat

open class CaveVariant(val yHeight: Int) : MapVariant {
    override fun getType(): MapVariantType<CaveVariant> = MapVariantType.CAVE
    override fun createVariant(player: Player) = CaveVariant(player.y.toInt())
    override fun createMapSampler(player: Player, level: Level, map: ItemStack): MapSampler? = object : MapSampler {
        override fun getHeight(original: Int): Int = yHeight
        override fun sliceOnly(): Boolean = false
        override fun colorOverride(level: Level, originalColor: MapColor, pos: BlockPos): MapColor? =
            CaveSampler.colorOverride(this, level, originalColor, pos)
    }

    override fun nameOverride(type: MapType): Component? = translatable(type.lang("cave"))
    override fun appendTooltip(
        stack: ItemStack, ctx: Item.TooltipContext, list: MutableList<Component>, flags: TooltipFlag,
    ) {
        if (!flags.isAdvanced) return
        list.add(Component.translatable("Y level: $yHeight", yHeight).colorFormat(true, ChatFormatting.GRAY))
    }

    override fun hashCode(): Int = yHeight
    override fun equals(other: Any?): Boolean = other is CaveVariant && other.yHeight == yHeight

    companion object {
        val CODEC: MapCodec<CaveVariant> = RecordCodecBuilder.mapCodec {
            it.group(Codec.INT.fieldOf("y_height").forGetter(CaveVariant::yHeight)).apply(it, ::CaveVariant)
        }
    }
}
