package dev.nuer.sp.listeners;

import dev.nuer.sp.managers.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerListener implements Listener {

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
    public void playerInteract(PlayerDropItemEvent event) {
        if (event.isCancelled()) return;
        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void playerInteract(PlayerPickupItemEvent event) {
        if (event.isCancelled()) return;
        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
    }

//    @EventHandler
//    public void playerInteract(PlayerBucketEvent event) {
//        if (event.isCancelled()) return;
//        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
//    }
//
//    @EventHandler
//    public void playerInteract(PlayerFishEvent event) {
//        if (event.isCancelled()) return;
//        if (FreezeManager.frozenPlayers.contains(event.getPlayer())) event.setCancelled(true);
//    }

    @EventHandler
    public void playerInteract(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player)) return;
        if (FreezeManager.frozenPlayers.contains(event.getEntity())) event.setCancelled(true);
    }
}
