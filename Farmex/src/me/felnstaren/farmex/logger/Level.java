package me.felnstaren.farmex.logger;

import org.bukkit.ChatColor;

public enum Level {

	FATAL(ChatColor.RED, 3),
	WARN(ChatColor.YELLOW, 2),
	INFO(ChatColor.WHITE, 1),
	STREAM(ChatColor.GRAY, 0);
	
	public final ChatColor color;
	public final int weight;
	
	private Level(ChatColor color, int weight) {
		this.color = color;
		this.weight = weight;
	}
	
}
