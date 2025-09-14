package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.MapCodec
import com.theendercore.overmapped.utils.map.MapType
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Component.translatable
import net.minecraft.world.entity.player.Player

object DebugVariant : MapVariant {
    override fun getType(): MapVariantType<DebugVariant> = MapVariantType.DEBUG
    override fun createVariant(player: Player) = DebugVariant
    override fun nameOverride(type: MapType): Component? = translatable(type.lang("debug"))

    val CODEC: MapCodec<DebugVariant> = MapCodec.unit { DebugVariant }
}
