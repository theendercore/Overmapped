package com.theendercore.overmapped.utils

fun interface HeightSampler {
    fun getHeight(original: Int): Int
}