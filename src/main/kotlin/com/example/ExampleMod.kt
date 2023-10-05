package com.example

import com.example.commands.CommandManager
import com.example.config.ConfigManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = "examplemod", useMetadata = true)
class ExampleMod {

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        println(" ")
        println("starting the example mod!")
        println("loading config")
        configManager = ConfigManager()
        MinecraftForge.EVENT_BUS.register(configManager)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        println(" ")
        println("loading features")
        CommandManager()
    }

    companion object {
        lateinit var configManager: ConfigManager
        private const val MOD_ID = "examplemod"

        @JvmStatic
        val version: String
            get() = Loader.instance().indexedModList[MOD_ID]!!.version
    }
}
