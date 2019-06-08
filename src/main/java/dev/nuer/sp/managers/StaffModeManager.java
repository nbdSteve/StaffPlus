package dev.nuer.sp.managers;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.utils.MessageUtil;
import dev.nuer.sp.utils.StaffModeUtil;
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
        try {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    FileManager.get("config").getString("vanish-command").replace("{player}", player.getName()));
        } catch (Exception e) {
            StaffPlus.LOGGER.severe("Error while entering staff mode! Unable to vanish the player: " + player.getName());
        }
        try {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "god " + player.getName());
        } catch (Exception e) {
            StaffPlus.LOGGER.severe("Error while entering staff mode! Unable to god mode the player: " + player.getName());
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
        try {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    FileManager.get("config").getString("vanish-command").replace("{player}", player.getName()));
        } catch (Exception e) {
            StaffPlus.LOGGER.severe("Error while exiting staff mode! Unable to show the player: " + player.getName());
        }
        try {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "god " + player.getName());
        } catch (Exception e) {
            StaffPlus.LOGGER.severe("Error while exiting staff mode! Unable to remove god mode from the player: " + player.getName());
        }
        MessageUtil.message("messages", "exit-staff-mode", player);
    }
}
