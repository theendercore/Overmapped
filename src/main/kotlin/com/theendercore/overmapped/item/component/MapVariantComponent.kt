package com.theendercore.overmapped.item.component

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.theendercore.overmapped.item.component.map_variant.MapVariant

data class MapVariantComponent(val variant: MapVariant) {

    companion object {
        val CODEC: Codec<MapVariantComponent> = RecordCodecBuilder.create {
            it.group(MapVariant.CODEC.fieldOf("variant").forGetter(MapVariantComponent::variant))
                .apply(it, ::MapVariantComponent)
        }
    }
}


