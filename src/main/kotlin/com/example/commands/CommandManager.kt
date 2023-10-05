package com.example.commands

import com.example.ExampleMod
import com.example.commands.SimpleCommand.ProcessCommandRunnable
import com.example.utils.ChatUtils
import net.minecraft.command.ICommandSender
import net.minecraftforge.client.ClientCommandHandler

class CommandManager {

    init {
        registerCommand("testcommand") {
            ChatUtils.messageToChat("Test successful.")
        }
        registerCommand("openconfig") {
            ExampleMod.configManager.openConfigGui()
        }
    }

    private fun registerCommand(
        name: String,
        function: (Array<String>) -> Unit
    ) = registerCommand0(name, function)

    private fun registerCommand0(
        name: String,
        function: (Array<String>) -> Unit,
        autoComplete: ((Array<String>) -> List<String>) = { listOf() }
    ) {
        ClientCommandHandler.instance.registerCommand(
            SimpleCommand(
                name,
                createCommand(function)
            ) { _, b, _ -> autoComplete(b) }
        )
    }

    private fun createCommand(function: (Array<String>) -> Unit) =
        object : ProcessCommandRunnable() {
            override fun processCommand(sender: ICommandSender?, args: Array<out String>) {
                function(args.asList().toTypedArray())
            }
        }
}