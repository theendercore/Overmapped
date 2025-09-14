package com.theendercore.overmapped

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.theendercore.overmapped.config.OvermappedConfig
import com.theendercore.overmapped.init.OMComponents

@Suppress("unused")
object Overmapped {
    const val MODID = "overmapped"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Overmapped::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::OvermappedConfig)

    fun init() {
        OMComponents.init()
    }

    fun id(path: String) = Identifier.of(MODID, path)
}
