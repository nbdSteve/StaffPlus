package dev.nuer.sp.cmd;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.managers.FreezeManager;
import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("staff+.freeze")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (!Bukkit.getOnlinePlayers().contains(target)) {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "invalid-command", (Player) sender, "{reason}",
                                "The player you are trying to freeze to is not online");
                    } else {
                        StaffPlus.LOGGER.info("Invalid command, the player is not online.");
                    }
                    return true;
                }
                FreezeManager.freeze((Player) sender, target);
            } else {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "invalid-command", (Player) sender, "{reason}", "please specify a player to freeze");
                } else {
                    StaffPlus.LOGGER.info("Invalid command, please specify a player.");
                }
            }
        } else {
            if (sender instanceof Player) {
                MessageUtil.message("messages", "no-permission", (Player) sender);
            }
        }
        return true;
    }
}