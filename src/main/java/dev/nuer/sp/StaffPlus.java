package dev.nuer.sp;

import dev.nuer.sp.cmd.FreezeCmd;
import dev.nuer.sp.cmd.UnfreezeCmd;
import dev.nuer.sp.listeners.PlayerListener;
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
        getCommand("freeze").setExecutor(new FreezeCmd());
        getCommand("unfreeze").setExecutor(new UnfreezeCmd());
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
        pm.registerEvents(new PlayerListener(), this);
    }
}
