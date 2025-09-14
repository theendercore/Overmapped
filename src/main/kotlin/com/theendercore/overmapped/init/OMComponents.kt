package com.theendercore.overmapped.init

import com.theendercore.overmapped.Overmapped.id
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries

object OMComponents {
//    val SPELL = register("spell") { it.codec(SpellComponent.CODEC).build() }


    fun init() {}
    fun <T> register(
        name: String,
        build: (DataComponentType.Builder<T>) -> DataComponentType<T>,
    ): DataComponentType<T> =
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id(name), build(DataComponentType.builder()))
}