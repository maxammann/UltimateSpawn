package com.maino.p000ison.dev.ultimatespawn.handlers.commands;

import com.maino.p000ison.dev.ultimatespawn.UltimateSpawn;
import org.bukkit.command.CommandSender;

/**
 *
 * @author p000ison
 * @author maino
 */
public class ReloadCommand extends BasicCommand {

    private UltimateSpawn plugin = null;

    public ReloadCommand(UltimateSpawn plugin) {
        super("Reload");
        this.plugin = plugin;
        setDescription("Reloads the Config.");
        setUsage("/ulspawn reload");
        setArgumentRange(0, 0);
        setIdentifiers("reload");
        setPermission("ulspawn.command.reload");
    }

    @Override
    public boolean execute(CommandSender sender, String identifier, String[] args) {
        final long startTime = System.nanoTime();
        plugin.getSettingsHandler().load();
        final long endTime = System.nanoTime();
        //sender.sendMessage(Util.color(String.format(plugin.getSettingsHandler().getMessageReload(), ((double) (endTime - startTime)) / 1000000000.0)));
        return true;
    }
}
