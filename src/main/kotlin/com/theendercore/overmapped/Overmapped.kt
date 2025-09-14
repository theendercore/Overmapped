package com.theendercore.overmapped

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.theendercore.overmapped.config.OvermappedConfig

@Suppress("unused")
object Overmapped {
    const val MODID = "overmapped"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Overmapped::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::OvermappedConfig)

    fun init() {
        log.info("Hello from Common")
    }

    fun id(path: String) = Identifier.of(MODID, path)
}
