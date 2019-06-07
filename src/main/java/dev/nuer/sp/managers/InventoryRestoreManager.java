package dev.nuer.sp.managers;

import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Class that handles restoring a players inventory
 */
public class InventoryRestoreManager {
    //Store the player of players who just died
    public static HashMap<Player, ItemStack[]> deadPlayerInventories = new HashMap<>();

    /**
     * Restores the specified players inventory from just before they died
     *
     * @param executor        Player, the staff member restoring the inventory
     * @param playerToRestore Player, the player who's inventory is being restored
     */
    public static void restore(Player executor, Player playerToRestore) {
        playerToRestore.getInventory().clear();
        for (ItemStack item : deadPlayerInventories.get(playerToRestore)) {
            if (item != null) {
                playerToRestore.getInventory().addItem(item);
            }
        }
        MessageUtil.message("messages", "executor-restore-inventory", executor, "{player}", playerToRestore.getName());
        MessageUtil.message("messages", "receiver-restore-inventory", playerToRestore);
    }

    /**
     * Adds the specified player to the map of players who recently died
     *
     * @param player Player, the player who died
     */
    public static void add(Player player) {
        if (deadPlayerInventories.containsKey(player)) deadPlayerInventories.remove(player);
        deadPlayerInventories.put(player, player.getInventory().getContents());
    }
}
