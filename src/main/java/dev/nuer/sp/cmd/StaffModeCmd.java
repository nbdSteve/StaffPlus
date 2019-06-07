package dev.nuer.sp.cmd;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.managers.StaffModeManager;
import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class that handles the /staff-mode command
 */
public class StaffModeCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("staff+.mode")) {
            if (sender instanceof Player) {
                if (StaffModeManager.playersInStaffMode.get(sender) != null) {
                    StaffModeManager.remove((Player) sender);
                } else {
                    StaffModeManager.add((Player) sender);
                }
            } else {
                StaffPlus.LOGGER.info("Only players can use staff mode.");
            }
        } else {
            if (sender instanceof Player) {
                MessageUtil.message("messages", "no-permission", (Player) sender);
            }
        }
        return true;
    }
}
