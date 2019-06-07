package dev.nuer.sp.managers;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.utils.MessageUtil;
import dev.nuer.sp.utils.StaffModeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class StaffModeManager {
    public static HashMap<Player, ItemStack[]> playersInStaffMode = new HashMap<>();

    public static void add(Player player) {
        if (playersInStaffMode.get(player) != null) return;
        StaffModeUtil.setStaffMode(player);
        try {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "v " + player.getName());
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

    public static void remove(Player player) {
        if (playersInStaffMode.get(player) == null) return;
        StaffModeUtil.removeStaffMode(player);
        try {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "v " + player.getName());
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
