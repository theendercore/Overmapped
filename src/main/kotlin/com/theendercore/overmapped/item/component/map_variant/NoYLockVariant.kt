package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.MapCodec
import com.theendercore.overmapped.utils.MapSampler
import com.theendercore.overmapped.utils.MapType
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
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
        override fun colorOverride(level: Level, color: MapColor, pos: BlockPos): MapColor? {
            if (getHeight(pos.y) != pos.y) return null

            for (dir in Direction.Plane.HORIZONTAL) {
                val pos2 = pos.relative(dir)
                val state = level.getBlockState(pos2)
                if (!state.canOcclude()) {
                    return null
                }
            }

            return MapColor.NONE
        }
    }

    val CODEC: MapCodec<NoYLockVariant> = MapCodec.unit { NoYLockVariant }
}
