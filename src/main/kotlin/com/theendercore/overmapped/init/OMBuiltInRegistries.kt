package com.theendercore.overmapped.init

import com.theendercore.overmapped.item.component.map_variant.MapVariantType
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.core.MappedRegistry

object OMBuiltInRegistries {
    val MAP_VARIANT_TYPE: MappedRegistry<MapVariantType<*>> =
        FabricRegistryBuilder.createSimple(OMRegistries.MAP_VARIANT_TYPE).buildAndRegister()

    fun init() {
    }
}