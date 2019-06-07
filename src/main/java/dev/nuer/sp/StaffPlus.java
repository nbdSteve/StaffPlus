package dev.nuer.sp;

import dev.nuer.sp.cmd.FreezeCmd;
import dev.nuer.sp.cmd.StaffChatCmd;
import dev.nuer.sp.cmd.StaffModeCmd;
import dev.nuer.sp.cmd.UnfreezeCmd;
import dev.nuer.sp.listeners.*;
import dev.nuer.sp.managers.FileManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class StaffPlus extends JavaPlugin {
    public static StaffPlus instance;
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
        // Plugin shutdown logic
    }

    public void fileHandler(FileManager fileManager) {
        fileManager.add("config", "staff+.yml");
        fileManager.add("messages", "messages.yml");
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerFreezeListener(), this);
        pm.registerEvents(new StaffChatListener(), this);
        pm.registerEvents(new StaffModeInventoryListener(), this);
        pm.registerEvents(new StaffToolListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
    }

    public void registerCommands() {
        getCommand("freeze").setExecutor(new FreezeCmd());
        getCommand("unfreeze").setExecutor(new UnfreezeCmd());
        getCommand("staff-chat").setExecutor(new StaffChatCmd());
        getCommand("staff-mode").setExecutor(new StaffModeCmd());
    }
}
