package dev.nuer.sp.listeners;

import dev.nuer.sp.managers.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Class that cancels all player events while they are frozen
 */
public class PlayerFreezeListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if (event.isCancelled()) return;
        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        if (event.isCancelled()) return;
        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void playerItemDrop(PlayerDropItemEvent event) {
        if (event.isCancelled()) return;
        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void playerItemPickup(PlayerPickupItemEvent event) {
        if (event.isCancelled()) return;
        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void inventoryInteract(InventoryOpenEvent event) {
        if (event.isCancelled()) return;
        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void playerReceiveDamage(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player)) return;
        if (FreezeManager.frozenPlayers.contains(event.getEntity())) event.setCancelled(true);
    }

    @EventHandler
    public void playerAdministerDamage(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getDamager() instanceof Player)) return;
        if (FreezeManager.frozenPlayers.contains(event.getDamager())) event.setCancelled(true);
    }
}
