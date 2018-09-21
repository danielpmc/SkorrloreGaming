package me.skorrloregaming.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.skorrloregaming.$;
import me.skorrloregaming.Server;

public class StaffChatCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = ((Player) sender);
		if (!($.getRankId(player) > -1)) {
			$.playLackPermissionMessage(player);
			return true;
		}
		if (Server.getStaffChatPlayers().contains(player.getUniqueId())) {
			Server.getStaffChatPlayers().remove(player.getUniqueId());
			sender.sendMessage($.Legacy.tag + ChatColor.RED + "Success. " + ChatColor.GRAY + "You have left the staff chat.");
		} else {
			Server.getStaffChatPlayers().add(player.getUniqueId());
			sender.sendMessage($.Legacy.tag + ChatColor.RED + "Success. " + ChatColor.GRAY + "You have entered the staff chat.");
		}
		return true;
	}

}
