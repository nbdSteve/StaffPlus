package dev.nuer.sp.managers;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.utils.MessageUtil;
import dev.nuer.sp.utils.staffmode.StaffModeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Class that handles all of the staff mode features
 */
public class StaffModeManager {
    //Store a static map of all the players that are in staff mode, and their previous inventory
    public static HashMap<Player, ItemStack[]> playersInStaffMode = new HashMap<>();

    /**
     * Adds the specified player to the staff mode map
     *
     * @param player Player, the player to set in staff mode
     */
    public static void add(Player player) {
        if (playersInStaffMode.get(player) != null) return;
        StaffModeUtil.setStaffMode(player);
        for (String command : FileManager.get("config").getStringList("staff-mode-commands")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
        }
        MessageUtil.message("messages", "enter-staff-mode", player);
    }

    /**
     * Disables staff mode for the specified player
     *
     * @param player Player, the player to remove from staff mode
     */
    public static void remove(Player player) {
        if (playersInStaffMode.get(player) == null) return;
        StaffModeUtil.removeStaffMode(player);
        for (String command : FileManager.get("config").getStringList("staff-mode-commands")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
        }
        MessageUtil.message("messages", "exit-staff-mode", player);
    }
}
