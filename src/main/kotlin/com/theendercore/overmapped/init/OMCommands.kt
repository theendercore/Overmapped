package com.theendercore.overmapped.init

import com.mojang.brigadier.context.CommandContext
import com.theendercore.overmapped.item.component.map_variant.CaveVariant
import com.theendercore.overmapped.item.component.map_variant.DebugVariant
import com.theendercore.overmapped.item.component.map_variant.NoYLockVariant
import com.theendercore.overmapped.item.component.map_variant.SliceVariant
import com.theendercore.overmapped.utils.map.MapVariantProcessing.setMapData
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands.literal
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.Items

object OMCommands {
    fun init() = CommandRegistrationCallback.EVENT.register { dispatcher, ctx, _ ->
        if (!FabricLoader.getInstance().isDevelopmentEnvironment) return@register

        val test = literal("om_test").executes { spell(it) }.build()
        dispatcher.root.addChild(test)

    }


    fun spell(cx: CommandContext<CommandSourceStack>): Int {
        val src = cx.source ?: return 0
        val world = src.level ?: return 0
        val player = src.player ?: return 0

        val list = listOf(CaveVariant(0), SliceVariant(0), NoYLockVariant, DebugVariant)
        for (variant in list) {
            val item = Items.MAP.defaultInstance
            item.setMapData(variant)
            world.addFreshEntity(ItemEntity(world, player.x, player.y, player.z, item))
        }

        player.sendSystemMessage(Component.literal("Spawned test map items!"))

        return 1
    }

}