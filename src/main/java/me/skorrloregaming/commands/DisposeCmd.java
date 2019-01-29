package me.skorrloregaming.commands;

import me.skorrloregaming.$;
import me.skorrloregaming.Server;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DisposeCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = ((Player) sender);
		if (Server.getPlayersInCombat().containsKey(player.getUniqueId())) {
			player.sendMessage($.getMinigameTag(player) + ChatColor.RED + "You cannot use this command during combat.");
			return true;
		}
		Inventory inv = Bukkit.createInventory(null, 27);
		player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
		player.openInventory(inv);
		return true;
	}

}
