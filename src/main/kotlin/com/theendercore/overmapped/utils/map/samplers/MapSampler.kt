package com.theendercore.overmapped.utils.map.samplers

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.MapColor

interface MapSampler {
    fun getHeight(original: Int): Int = original
    fun sliceOnly(): Boolean
    fun colorOverride(level: Level, originalColor: MapColor, pos: BlockPos): MapColor? = null
}