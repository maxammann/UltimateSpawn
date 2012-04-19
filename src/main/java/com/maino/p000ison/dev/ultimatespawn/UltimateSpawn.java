package com.maino.p000ison.dev.ultimatespawn;

import com.maino.p000ison.dev.ultimatespawn.events.USSpawnEvents;
import com.maino.p000ison.dev.ultimatespawn.handlers.CommandHandler;
import com.maino.p000ison.dev.ultimatespawn.handlers.SettingsHandler;
import com.maino.p000ison.dev.ultimatespawn.handlers.StorageHandler;
import com.maino.p000ison.dev.ultimatespawn.handlers.commands.*;
import com.maino.p000ison.dev.ultimatespawn.util.Util;
import java.util.logging.Logger;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author p00ison
 * @author maino
 */
public class UltimateSpawn extends JavaPlugin {

    private Util util;
    private static final Logger log = Logger.getLogger("Minecraft");
    private SettingsHandler settingsHandler;
    private StorageHandler storageHandler;
    private static Permission perms = null;
    private CommandHandler commandHandler = new CommandHandler();

    @Override
    public void onEnable() {
        settingsHandler = new SettingsHandler(this);
        storageHandler = new StorageHandler(this);
        util = new Util();
        registerCommands();
        
        if (getServer().getPluginManager().getPlugin("Vault") != null) {
            setupPermissions();
            log.info("Hooked Vault for Permission-Support!");
        }
        
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new USSpawnEvents(this), this);
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        commandHandler = new CommandHandler();
        
        getCommandHandler().addCommand(new HelpCommand(this));
        getCommandHandler().addCommand(new ReloadCommand(this));
        getCommandHandler().addCommand(new SetCommand(this));
        getCommandHandler().addCommand(new SpawnCommand(this));
        getCommandHandler().addCommand(new LocalSpawnCommand(this));
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return getCommandHandler().dispatch(sender, cmd, commandLabel, args);
    }

    public static boolean hasPermission(Player player, String permission) {
        if (perms != null) {
            return perms.has(player, permission);
        }
        return player.hasPermission(permission);
    }
    
    public static String getPrimaryGroup(Player player) {
        if (perms != null) {
            return perms.getPrimaryGroup(player);
        }
        return "null";
    }

    /**
     * @return the util
     */
    public Util getUtil() {
        return util;
    }

    /**
     * @return the settingsHandler
     */
    public SettingsHandler getSettingsHandler() {
        return settingsHandler;
    }

    /**
     * @return the commandHandler
     */
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    /**
     * @return the storageHandler
     */
    public StorageHandler getStorageHandler() {
        return storageHandler;
    }
}
