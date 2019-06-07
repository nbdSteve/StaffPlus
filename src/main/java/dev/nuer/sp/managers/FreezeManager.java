package dev.nuer.sp.managers;

import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Class that handles toggling if a player is frozen or not
 */
public class FreezeManager {
    //Store a list of the players that are frozen
    public static ArrayList<Player> frozenPlayers = new ArrayList<>();

    /**
     * Sets the player to freeze to being frozen
     *
     * @param executor       Player, the player who from the target
     * @param playerToFreeze Player, the player to freeze
     */
    public static void freeze(Player executor, Player playerToFreeze) {
        if (!frozenPlayers.contains(playerToFreeze)) {
            frozenPlayers.add(playerToFreeze);
            MessageUtil.message("messages", "executor-player-frozen", executor, "{player}", playerToFreeze.getName());
            MessageUtil.message("messages", "receiver-player-frozen", playerToFreeze);
        } else {
            MessageUtil.message("messages", "executor-player-already-frozen", executor, "{player}", playerToFreeze.getName());
        }
    }

    /**
     * Unfreezes the player who was frozen
     *
     * @param executor       Player, the player who from the target
     * @param playerToFreeze Player, the player to unfreeze
     */
    public static void unfreeze(Player executor, Player playerToFreeze) {
        if (frozenPlayers.contains(playerToFreeze)) {
            frozenPlayers.remove(playerToFreeze);
            MessageUtil.message("messages", "executor-player-unfrozen", executor, "{player}", playerToFreeze.getName());
            MessageUtil.message("messages", "receiver-player-unfrozen", playerToFreeze);
        } else {
            MessageUtil.message("messages", "executor-player-not-frozen", executor, "{player}", playerToFreeze.getName());
        }
    }
}
