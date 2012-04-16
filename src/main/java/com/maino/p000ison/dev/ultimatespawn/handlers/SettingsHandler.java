package com.maino.p000ison.dev.ultimatespawn.handlers;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author p00ison
 * @author maino
 */
public final class SettingsHandler {

    private UltimateSpawn plugin;
    private File main;
    private FileConfiguration config;

    /**
     *
     */
    public SettingsHandler(UltimateSpawn plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        main = new File(getPlugin().getDataFolder() + File.separator + "config.yml");
        load();
    }

    /**
     * Load the configuration
     */
    public void load() {
        boolean exists = (main).exists();

        if (exists) {
            try {
                getConfig().options().copyDefaults(true);
                getConfig().load(main);
            } catch (Exception ex) {
                plugin.getLogger().log(Level.WARNING, "Loading the config failed!:{0}", ex.getMessage());
            }
        } else {
            getConfig().options().copyDefaults(true);

        }

        save();
    }

    public void save() {
        try {
            getConfig().save(main);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.WARNING, "Saving the config failed!:{0}", ex.getMessage());
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