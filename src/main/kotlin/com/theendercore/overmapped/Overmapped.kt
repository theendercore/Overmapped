package com.theendercore.overmapped

import com.theendercore.overmapped.config.OvermappedConfig
import com.theendercore.overmapped.init.OMBuiltInRegistries
import com.theendercore.overmapped.init.OMCommands
import com.theendercore.overmapped.init.OMComponents
import com.theendercore.overmapped.item.component.map_variant.MapVariantType
import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("unused")
object Overmapped {
    const val MODID = "overmapped"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Overmapped::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::OvermappedConfig)

    fun init() {
        OMComponents.init()
        OMBuiltInRegistries.init()
        MapVariantType.init()
        OMCommands.init()
    }

    fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, path)
}
