package com.theendercore.overmapped.utils.map.samplers

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.MapColor

object CaveSampler {
    fun colorOverride(sampler: MapSampler, level: Level, color: MapColor, pos: BlockPos): MapColor? {
        if (sampler.getHeight(pos.y) != pos.y) return null

        for (dir in Direction.Plane.HORIZONTAL) {
            val pos2 = pos.relative(dir)
            val state = level.getBlockState(pos2)
            if (!state.canOcclude()) {
                return null
            }
        }
        return MapColor.NONE
    }
}