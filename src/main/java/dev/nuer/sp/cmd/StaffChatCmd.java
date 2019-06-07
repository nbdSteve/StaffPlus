package dev.nuer.sp.cmd;

import dev.nuer.sp.StaffPlus;
import dev.nuer.sp.managers.StaffChatManager;
import dev.nuer.sp.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("staff+.chat")) {
            if (sender instanceof Player) {
                if (StaffChatManager.playersInStaffChat.contains(sender)) {
                    StaffChatManager.remove((Player) sender);
                } else {
                    StaffChatManager.add((Player) sender);
                }
            } else {
                StaffPlus.LOGGER.info("Only players can use staff chat.");
            }
        } else {
            if (sender instanceof Player) {
                MessageUtil.message("messages", "no-permission", (Player) sender);
            }
        }
        return true;
    }
}
