package com.theendercore.overmapped.utils

import com.theendercore.overmapped.Overmapped

enum class MapType {
    EMPTY,
    FULL;

    fun lang(variant: String) = "item.${Overmapped.MODID}.$variant.${mapName()}"
    fun mapName() = if (this == FULL) "filled_map" else "map"
}