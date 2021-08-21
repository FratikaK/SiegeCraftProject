package com.github.kamunyan.siegecraft.listeners

import com.github.kamunyan.siegecraft.MatchManager
import com.github.kamunyan.siegecraft.SiegeCraft
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinQuitListener : Listener {

    private val plugin = SiegeCraft.instance
    private val manager = MatchManager

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        event.joinMessage = "${ChatColor.AQUA}${player.displayName}がサーバーに参加しました"
        player.teleport(manager.lobbySpawnLocation)
    }
}