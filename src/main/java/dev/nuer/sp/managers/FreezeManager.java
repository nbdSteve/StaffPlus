package dev.nuer.sp.managers;

import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FreezeManager {
    public static ArrayList<Player> frozenPlayers = new ArrayList<>();

    public static void freeze(Player executor, Player playerToFreeze) {
        if (!frozenPlayers.contains(playerToFreeze)) {
            frozenPlayers.add(playerToFreeze);
            MessageUtil.message("messages", "executor-player-frozen", executor, "{player}", playerToFreeze.getName());
            MessageUtil.message("messages", "receiver-player-frozen", playerToFreeze);
        } else {
            MessageUtil.message("messages", "executor-player-already-frozen", executor, "{player}", playerToFreeze.getName());
        }
    }

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
