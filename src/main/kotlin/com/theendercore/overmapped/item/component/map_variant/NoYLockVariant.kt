package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.MapCodec
import com.theendercore.overmapped.utils.MapSampler
import com.theendercore.overmapped.utils.MapType
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Component.translatable
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

object NoYLockVariant : MapVariant {
    override fun getType(): MapVariantType<NoYLockVariant> = MapVariantType.NO_Y_LOCK
    override fun createVariant(player: Player) = NoYLockVariant
    override fun nameOverride(type: MapType): Component? = translatable(type.lang("no_y_lock"))
    override fun createMapSampler(player: Player, level: Level, map: ItemStack): MapSampler? =
        MapSampler { player.y.toInt() }

    val CODEC: MapCodec<NoYLockVariant> = MapCodec.unit { NoYLockVariant }
}
