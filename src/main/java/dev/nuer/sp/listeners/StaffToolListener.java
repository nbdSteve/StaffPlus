package dev.nuer.sp.listeners;

import dev.nuer.sp.managers.FreezeManager;
import dev.nuer.sp.managers.InventoryRestoreManager;
import dev.nuer.sp.managers.StaffModeManager;
import dev.nuer.sp.nbtapi.NBTItem;
import dev.nuer.sp.utils.RandomTeleportUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class StaffToolListener implements Listener {

    @EventHandler
    public void playerClick(PlayerInteractEntityEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getRightClicked() instanceof Player)) return;
        if (StaffModeManager.playersInStaffMode.containsKey(event.getPlayer())) {
            NBTItem item = new NBTItem(event.getPlayer().getItemInHand());
            try {
                if (item.getBoolean("staff+.tool.freeze")) {
                    FreezeManager.unfreeze(event.getPlayer(), (Player) event.getRightClicked());
                }
            } catch (NullPointerException notATool) {
                //Do nothing
            }
        }
    }

    @EventHandler
    public void playerInteract(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;
        if (StaffModeManager.playersInStaffMode.containsKey(event.getDamager())) {
            NBTItem item = new NBTItem(((Player) event.getDamager()).getInventory().getItemInHand());
            try {
                if (item.getBoolean("staff+.tool.freeze")) {
                    FreezeManager.freeze((Player) event.getDamager(), (Player) event.getEntity());
                }
            } catch (NullPointerException notATool) {
                //Do nothing
            }
            try {
                if (item.getBoolean("staff+.tool.restore")) {
                    InventoryRestoreManager.restore((Player) event.getDamager(), (Player) event.getEntity());
                }
            } catch (NullPointerException notATool) {
                //Do nothing
            }
            try {
                if (item.getBoolean("staff+.tool.invsee")) {
                    //Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "invsee " + event.getEntity().getName());
                    ((Player) event.getDamager()).openInventory(((Player) event.getEntity()).getInventory());
                }
            } catch (NullPointerException notATool) {
                //Do nothing
            }
            try {
                if (item.getBoolean("staff+.tool.randtp")) {
                    RandomTeleportUtil.teleport((Player) event.getDamager());
                }
            } catch (NullPointerException notATool) {
                //Do nothing
            }
        }
    }

    @EventHandler
    public void click(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.LEFT_CLICK_AIR)) return;
        if (StaffModeManager.playersInStaffMode.containsKey(event.getPlayer())) {
            NBTItem item = new NBTItem(event.getPlayer().getItemInHand());
            try {
                if (item.getBoolean("staff+.tool.randtp")) {
                    RandomTeleportUtil.teleport(event.getPlayer());
                }
            } catch (NullPointerException notATool) {
                //Do nothing
            }
        }
    }
}