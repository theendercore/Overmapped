package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.theendercore.overmapped.utils.map.MapType
import com.theendercore.overmapped.utils.map.samplers.MapSampler
import me.fzzyhmstrs.fzzy_config.util.FcText.translatable
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import org.teamvoided.creative_works.util.trash.TextOps.Companion.colorFormat

open class SliceVariant(val yHeight: Int) : MapVariant {
    override fun getType(): MapVariantType<SliceVariant> = MapVariantType.SLICE
    override fun createVariant(player: Player) = SliceVariant(player.y.toInt())
    override fun createMapSampler(player: Player, level: Level, map: ItemStack): MapSampler? = object : MapSampler {
        override fun getHeight(original: Int): Int = yHeight
        override fun sliceOnly(): Boolean = true
    }

    override fun nameOverride(type: MapType): Component? = translatable(type.lang("slice"))

    override fun appendTooltip(
        stack: ItemStack, ctx: Item.TooltipContext, list: MutableList<Component>, flags: TooltipFlag,
    ) {
        if (!flags.isAdvanced) return
        list.add(Component.translatable("Slice y level: $yHeight", yHeight).colorFormat(true, ChatFormatting.GRAY))
    }

    override fun hashCode(): Int = yHeight
    override fun equals(other: Any?): Boolean = other is SliceVariant && other.yHeight == yHeight

    companion object {
        val CODEC: MapCodec<SliceVariant> = RecordCodecBuilder.mapCodec {
            it.group(Codec.INT.fieldOf("y_height").forGetter(SliceVariant::yHeight)).apply(it, ::SliceVariant)
        }
    }
}
