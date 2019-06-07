package dev.nuer.sp.managers;

import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryRestoreManager {
    public static HashMap<Player, ItemStack[]> deadPlayerInventories = new HashMap<>();

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

    public static void add(Player player) {
        if (deadPlayerInventories.containsKey(player)) deadPlayerInventories.remove(player);
        deadPlayerInventories.put(player, player.getInventory().getContents());
    }
}
