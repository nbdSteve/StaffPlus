package dev.nuer.sp.cmd;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.managers.FileManager;
import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class that handles the main /staff-plus command
 */
public class StaffPlusCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h")) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("staff+.help")) {
                        MessageUtil.message("messages", "help", (Player) sender);
                    } else {
                        MessageUtil.message("messages", "no-permission", (Player) sender);
                    }
                } else {
                    StaffPlus.LOGGER.info("The help message can only be view in game.");
                }
            } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r")) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("omnitools.admin")) {
                        //Reload and instantiate all configuration sections
                        FileManager.reload();
                        MessageUtil.message("messages", "reload", (Player) sender);
                    } else {
                        MessageUtil.message("messages", "no-permission", (Player) sender);
                    }
                } else {
                    //Reload and instantiate all configuration sections
                    FileManager.reload();
                    StaffPlus.LOGGER.info("Reloaded all configuration files.");
                }
            }
        }
        return true;
    }
}