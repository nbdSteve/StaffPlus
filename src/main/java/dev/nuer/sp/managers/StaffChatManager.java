package dev.nuer.sp.managers;

import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StaffChatManager {
    public static ArrayList<Player> playersInStaffChat = new ArrayList<>();

    public static void add(Player player) {
        if (playersInStaffChat.contains(player)) return;
        playersInStaffChat.add(player);
        MessageUtil.message("messages", "staff-chat", player, "{status}", "enabled");
    }

    public static void remove(Player player) {
        if (!playersInStaffChat.contains(player)) return;
        playersInStaffChat.remove(player);
        MessageUtil.message("messages", "staff-chat", player, "{status}", "disabled");
    }
}
