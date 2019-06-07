package dev.nuer.sp.listeners;

import dev.nuer.sp.nbtapi.NBTItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class StaffModeInventoryListener implements Listener {

    @EventHandler
    public void playerInventory(InventoryMoveItemEvent event) {
        if (event.isCancelled()) return;
        NBTItem item = new NBTItem(event.getItem());
        try {
            if (item.getBoolean("staff+.tool.freeze")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.restore")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.invsee")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.randtp")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
    }

    @EventHandler
    public void playerInventory(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        if (event.getCurrentItem() == null) return;
        NBTItem item = new NBTItem(event.getCurrentItem());
        try {
            if (item.getBoolean("staff+.tool.freeze")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.restore")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.invsee")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.randtp")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
    }

    @EventHandler
    public void playerInventory(InventoryDragEvent event) {
        if (event.isCancelled()) return;
        if (event.getCursor() == null) return;
        NBTItem item = new NBTItem(event.getCursor());
        try {
            if (item.getBoolean("staff+.tool.freeze")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.restore")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.invsee")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.randtp")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
    }

    @EventHandler
    public void playerInventory(PlayerDropItemEvent event) {
        if (event.isCancelled()) return;
        NBTItem item = new NBTItem(event.getItemDrop().getItemStack());
        try {
            if (item.getBoolean("staff+.tool.freeze")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.restore")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.invsee")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
        try {
            if (item.getBoolean("staff+.tool.randtp")) {
                event.setCancelled(true);
            }
        } catch (NullPointerException notATool) {
            //Do nothing
        }
    }
}
