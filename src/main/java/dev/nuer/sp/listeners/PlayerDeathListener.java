package dev.nuer.sp.listeners;

import dev.nuer.sp.managers.InventoryRestoreManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Class that handles adding the dead players inventory to the map of inventories
 */
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        if (InventoryRestoreManager.deadPlayerInventories.containsKey(event.getEntity())) {
            InventoryRestoreManager.deadPlayerInventories.remove(event.getEntity());
        }
        InventoryRestoreManager.add(event.getEntity());
    }
}
