package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.MapCodec
import com.theendercore.overmapped.utils.map.MapType
import com.theendercore.overmapped.utils.map.samplers.CaveSampler
import com.theendercore.overmapped.utils.map.samplers.MapSampler
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Component.translatable
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.MapColor

object NoYLockVariant : MapVariant {
    override fun getType(): MapVariantType<NoYLockVariant> = MapVariantType.NO_Y_LOCK
    override fun createVariant(player: Player) = NoYLockVariant
    override fun nameOverride(type: MapType): Component? = translatable(type.lang("no_y_lock"))
    override fun createMapSampler(player: Player, level: Level, map: ItemStack): MapSampler? = object : MapSampler {
        override fun getHeight(original: Int): Int = player.y.toInt()
        override fun sliceOnly(): Boolean = false
        override fun colorOverride(level: Level, originalColor: MapColor, pos: BlockPos): MapColor? =
            CaveSampler.colorOverride(this, level, originalColor, pos)
    }

    val CODEC: MapCodec<NoYLockVariant> = MapCodec.unit { NoYLockVariant }
}
