package dev.nuer.sp;

import dev.nuer.sp.cmd.*;
import dev.nuer.sp.listeners.*;
import dev.nuer.sp.managers.FileManager;
import dev.nuer.sp.managers.StaffModeManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Main class for the plugin
 */
public final class StaffPlus extends JavaPlugin {
    //Store the plugin instance
    public static StaffPlus instance;
    //Store the plugin logger
    public static Logger LOGGER;

    @Override
    public void onEnable() {
        instance = this;
        LOGGER = instance.getLogger();
        FileManager fm = new FileManager(this);
        fileHandler(fm);
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        for (Player player : StaffModeManager.playersInStaffMode.keySet()) {
            StaffModeManager.remove(player);
        }
    }

    /**
     * Void method to create the files for the plugin
     * @param fileManager FileManager, the file manager class
     */
    public void fileHandler(FileManager fileManager) {
        fileManager.add("config", "staff+.yml");
        fileManager.add("messages", "messages.yml");
    }

    /**
     * Registers all of the plugin events
     */
    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerFreezeListener(), this);
        pm.registerEvents(new StaffChatListener(), this);
        pm.registerEvents(new StaffModeInventoryListener(), this);
        pm.registerEvents(new StaffToolListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
    }

    /**
     * Registers all of the plugin commands
     */
    public void registerCommands() {
        getCommand("freeze").setExecutor(new FreezeCmd());
        getCommand("unfreeze").setExecutor(new UnfreezeCmd());
        getCommand("staff-chat").setExecutor(new StaffChatCmd());
        getCommand("staff-mode").setExecutor(new StaffModeCmd());
        getCommand("restore").setExecutor(new RestoreCmd());
    }
}
