package dev.nuer.sp.utils;

import dev.nuer.sp.managers.StaffModeManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Class that has staff mode util methods
 */
public class StaffModeUtil {

    /**
     * Sets the specified player into staff mode
     *
     * @param player Player, the player to set in staff mode
     */
    public static void setStaffMode(Player player) {
        StaffModeManager.playersInStaffMode.put(player, player.getInventory().getContents());
        player.getInventory().clear();
        //Add the staff items here
        ItemUtil.create("freeze", player);
        ItemUtil.create("restore", player);
        ItemUtil.create("invsee", player);
        ItemUtil.create("randtp", player);
    }

    /**
     * Removes the specified player from staff mode
     *
     * @param player Player, the player to remove from staff mode
     */
    public static void removeStaffMode(Player player) {
        player.getInventory().clear();
        for (ItemStack item : StaffModeManager.playersInStaffMode.get(player)) {
            if (item != null) {
                player.getInventory().addItem(item);
            }
        }
        StaffModeManager.playersInStaffMode.remove(player);
    }
}