package com.theendercore.overmapped.init

import com.mojang.brigadier.context.CommandContext
import com.mojang.serialization.JsonOps
import com.theendercore.overmapped.Overmapped.log
import com.theendercore.overmapped.item.component.MapVariantComponent
import com.theendercore.overmapped.item.component.map_variant.CaveVariant
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands.literal
import net.minecraft.network.chat.Component

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

        try {

            val lookup = world.registryAccess()
            val ops = lookup.createSerializationContext(JsonOps.INSTANCE)


            val json =
                MapVariantComponent.CODEC.encodeStart(ops, MapVariantComponent(CaveVariant(45))).getOrThrow().toString()


            player.sendSystemMessage(Component.literal(json), false)
        } catch (e: Exception) {
            log.error("Error: ", e)
            return 0
        }
        return 1
    }

}