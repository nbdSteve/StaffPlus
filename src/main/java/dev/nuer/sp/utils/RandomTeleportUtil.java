package dev.nuer.sp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that handles teleporting the staff member to a random player
 */
public class RandomTeleportUtil {

    /**
     * Teleports the player to a random player
     *
     * @param player Player, the player to teleport
     */
    public static void teleport(Player player) {
        int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        player.teleport(players.get(random).getLocation());
    }
}
