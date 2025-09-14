package com.theendercore.overmapped.config

import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.config.ConfigGroup
import com.theendercore.overmapped.Overmapped.MODID
import com.theendercore.overmapped.Overmapped.id

@Suppress("unused")
class OvermappedConfig : Config(id(MODID)) {
    var groupName = ConfigGroup("group_id", false)
    @ConfigGroup.Pop
    var clientEntry = true
}