package com.maino.p000ison.dev.ultimatespawn.handlers;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author p00ison
 * @author maino
 */
public final class StorageHandler {

    private UltimateSpawn plugin;
    private File file;
    private FileConfiguration config;

    /**
     *
     */
    public StorageHandler(UltimateSpawn plugin) {
        this.plugin = plugin;
        file = new File(getPlugin().getDataFolder() + File.separator + "spawns.yml");
        load();
    }

    /**
     * Load the configuration
     */
    public void load() {
        try {
            config = YamlConfiguration.loadConfiguration(file);
        } catch (Exception ex) {
            plugin.getLogger().log(Level.WARNING, "Loading spawns.yml failed!:{0}", ex.getMessage());
        }
        save();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.WARNING, "Saving spawns.yml failed!:{0}", ex.getMessage());
        }
    }

    /**
     * @return the plugin
     */
    public UltimateSpawn getPlugin() {
        return plugin;
    }

    /**
     * @return the config
     */
    public FileConfiguration getConfig() {
        return config;
    }
}