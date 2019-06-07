package dev.nuer.sp.managers;

import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Class that handles adding and removing players from the staff chat channel
 */
public class StaffChatManager {
    //Store a list of players who are using the staff chat
    public static ArrayList<Player> playersInStaffChat = new ArrayList<>();

    /**
     * Adds the specified player to the staff chat channel
     *
     * @param player Player, the player to add
     */
    public static void add(Player player) {
        if (playersInStaffChat.contains(player)) return;
        playersInStaffChat.add(player);
        MessageUtil.message("messages", "staff-chat", player, "{status}", "enabled");
    }

    /**
     * Removes the specified player from the staff chat channel
     *
     * @param player Player, the player to remove
     */
    public static void remove(Player player) {
        if (!playersInStaffChat.contains(player)) return;
        playersInStaffChat.remove(player);
        MessageUtil.message("messages", "staff-chat", player, "{status}", "disabled");
    }
}
