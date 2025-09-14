package com.theendercore.overmapped.item.component.map_variant

import com.mojang.serialization.MapCodec
import com.theendercore.overmapped.Overmapped.id
import com.theendercore.overmapped.init.OMBuiltInRegistries
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation

fun interface MapVariantType<P : MapVariant> {
    fun codec(): MapCodec<P>

    companion object {
        val CAVE = register("cave", CaveVariant.CODEC)

        fun init() = Unit
        fun <P : MapVariant> register(id: String, mapCodec: MapCodec<P>): MapVariantType<P> = register(id(id), mapCodec)
        fun <P : MapVariant> register(id: ResourceLocation, mapCodec: MapCodec<P>): MapVariantType<P> =
            Registry.register(OMBuiltInRegistries.MAP_VARIANT_TYPE, id, MapVariantType { mapCodec })
    }
}