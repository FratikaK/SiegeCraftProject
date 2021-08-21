package com.github.kamunyan.siegecraft

import com.github.kamunyan.siegecraft.configs.SpawnConfig
import com.github.kamunyan.siegecraft.listeners.JoinQuitListener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.server.ServerLoadEvent
import org.bukkit.plugin.java.JavaPlugin

class SiegeCraft : JavaPlugin() {

    companion object {
        lateinit var instance: SiegeCraft
    }

    init {
        instance = this
    }

    override fun onEnable() {
        SpawnConfig.loadConfig()

        //Register events
        registerEvents(JoinQuitListener())

        sendLog(MatchManager.lobbySpawnLocation.toString())

        Bukkit.getLogger().info("${ChatColor.AQUA}SiegeCraft Start!")
    }

    override fun onDisable() {
    }

    fun sendLog(log: String) {
        this.logger.info("${ChatColor.AQUA}${log}")
    }

    fun sendWarnLog(log: String) {
        this.logger.info("${ChatColor.RED}${log}")
    }

    private fun registerEvents(listener: Listener) {
        this.server.pluginManager.registerEvents(listener, this)
    }
}