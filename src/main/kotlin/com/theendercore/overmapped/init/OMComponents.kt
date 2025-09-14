package com.theendercore.overmapped.init

import com.theendercore.overmapped.Overmapped.id
import com.theendercore.overmapped.item.component.map_variant.MapVariant
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries

object OMComponents {
    val MAP_VARIANT = register("map_variant") { it.persistent(MapVariant.CODEC).build() }


    fun init() {}
    fun <T> register(
        name: String,
        build: (DataComponentType.Builder<T>) -> DataComponentType<T>,
    ): DataComponentType<T> =
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id(name), build(DataComponentType.builder()))
}