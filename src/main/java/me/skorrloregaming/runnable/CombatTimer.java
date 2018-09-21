package me.skorrloregaming.runnable;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.skorrloregaming.Server;
import me.skorrloregaming.impl.Switches.SwitchIntDouble;

public class CombatTimer extends BukkitRunnable {
	private final Player player;
	private String prefix;
	private int originalSeconds;
	private int passes = 0;

	public CombatTimer(Player player, Player targetPlayer, int seconds, String prefix) {
		this.player = player;
		this.prefix = prefix;
		player.sendMessage(prefix + ChatColor.GRAY + "You have engaged in combat with " + ChatColor.RED + targetPlayer.getName());
		this.originalSeconds = seconds;
	}

	@Override
	public void run() {
		if (passes == 0) {
			Server.getPlayersInCombat().put(player.getUniqueId(), new SwitchIntDouble(this.getTaskId(), originalSeconds));
		}
		passes = passes + 1;
		if (!Server.getPlayersInCombat().containsKey(player.getUniqueId())) {
			cancel();
			return;
		}
		SwitchIntDouble existingExtreme = Server.getPlayersInCombat().get(player.getUniqueId());
		existingExtreme.setArg1(existingExtreme.getArg1() - 0.20);
		Server.getPlayersInCombat().put(player.getUniqueId(), existingExtreme);
		if (existingExtreme.getArg1() <= 0) {
			player.sendMessage(prefix + ChatColor.GRAY + "You are no longer engaged in combat.");
			Server.getPlayersInCombat().remove(player.getUniqueId());
			cancel();
			return;
		}
	}
}
