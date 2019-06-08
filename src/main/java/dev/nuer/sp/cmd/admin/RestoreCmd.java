package dev.nuer.sp.cmd.admin;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.managers.InventoryRestoreManager;
import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class that handles the /restore command for the plugin
 */
public class RestoreCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("staff+.restore")) {
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
                InventoryRestoreManager.restore((Player) sender, target);
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
