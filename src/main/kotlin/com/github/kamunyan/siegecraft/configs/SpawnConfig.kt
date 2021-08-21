package com.github.kamunyan.siegecraft.configs

import com.github.kamunyan.siegecraft.MatchManager
import com.github.kamunyan.siegecraft.SiegeCraft
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object SpawnConfig {
    private val plugin = SiegeCraft.instance
    private val spawns = HashMap<String, Location>()

    fun loadConfig() {
        plugin.logger.info("Lobby map loading...")
        val fileDir = File("plugins/SiegeCraft/spawns")
        val lobbyFile = File("plugins/SiegeCraft/spawns/lobby.yml")

        fileDir.parentFile.mkdir()
        fileDir.mkdir()

        val files = fileDir.listFiles()
        if (!files.contains(lobbyFile)) {
            lobbyFile.createNewFile()
            plugin.saveResource("spawns/lobby.yml", false)
        }

        val yml = YamlConfiguration.loadConfiguration(lobbyFile)
        if (!yml.contains("lobby")) {
            MatchManager.lobbySpawnLocation = plugin.server.getWorld("world")!!.spawnLocation
            plugin.sendWarnLog("[lobby.yml]spawn is not found!")
        } else {
            for (spawnName in yml.getValues(false).keys) {
                val spawn = Location(
                    yml.getString("${spawnName}.world")?.let { plugin.server.getWorld(it) },
                    yml.getDouble("${spawnName}.x"),
                    yml.getDouble("${spawnName}.y"),
                    yml.getDouble("${spawnName}.z"),
                    yml.getDouble("${spawnName}.yaw").toFloat(),
                    yml.getDouble("${spawnName}.pitch").toFloat()
                )
                spawns[spawnName] = spawn
                plugin.sendLog("[SpawnConfig] $spawnName location was loaded.")
            }
            MatchManager.lobbySpawnLocation = spawns["lobby"]!!
            plugin.sendLog("[SpawnConfig] lobby.yml file was successfully loaded!")
        }
    }
}