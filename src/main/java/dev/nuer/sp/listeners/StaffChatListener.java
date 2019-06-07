package dev.nuer.sp.listeners;

import dev.nuer.sp.managers.FileManager;
import dev.nuer.sp.managers.StaffChatManager;
import dev.nuer.sp.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatListener implements Listener {

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event) {
        if (StaffChatManager.playersInStaffChat.contains(event.getPlayer())) {
            event.setCancelled(true);
        } else {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("staff+.chat")) {
                String prefix = FileManager.get("config").getString("staff-chat-prefix").replace("{sender}", event.getPlayer().getName());
                player.sendMessage(ColorUtil.colorize(prefix + event.getMessage()));
            }
        }
    }
}
