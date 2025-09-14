package com.theendercore.overmapped.config

import com.theendercore.overmapped.Overmapped.MODID
import com.theendercore.overmapped.Overmapped.id
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.config.ConfigGroup

@Suppress("unused")
class OvermappedConfig : Config(id(MODID)) {
    var groupName = ConfigGroup("group_id", false)

    @ConfigGroup.Pop
    var clientEntry = true
}