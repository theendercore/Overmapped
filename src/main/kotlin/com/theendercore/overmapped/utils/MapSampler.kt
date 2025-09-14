package com.theendercore.overmapped.utils

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.MapColor

interface MapSampler {
    fun getHeight(original: Int): Int = original
    fun lockLayer(): Boolean = false
    fun colorOverride(level: Level, color: MapColor, pos: BlockPos): MapColor? = null
}