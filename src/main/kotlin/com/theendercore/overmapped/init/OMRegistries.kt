package com.theendercore.overmapped.init

import com.theendercore.overmapped.Overmapped.id
import com.theendercore.overmapped.item.component.map_variant.MapVariantType
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

object OMRegistries {
    val MAP_VARIANT_TYPE = createRegistryKey<MapVariantType<*>>("map_variant_type")

    private fun <T> createRegistryKey(id: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey(id(id))
}