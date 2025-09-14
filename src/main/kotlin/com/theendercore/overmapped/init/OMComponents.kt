package com.theendercore.overmapped.init

import com.theendercore.overmapped.Overmapped.id
import net.minecraft.component.DataComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object OMComponents {
//    val SPELL = register("spell") { it.codec(SpellComponent.CODEC).build() }


    fun init() {}
    fun <T> register(
        name: String,
        build: (DataComponentType.Builder<T>) -> DataComponentType<T>,
    ): DataComponentType<T> =
        Registry.register(Registries.DATA_COMPONENT_TYPE, id(name), build(DataComponentType.builder()))
}