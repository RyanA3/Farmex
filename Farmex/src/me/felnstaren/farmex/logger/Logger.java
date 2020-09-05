package me.felnstaren.farmex.logger;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.farmex.util.Messenger;
import net.md_5.bungee.api.ChatColor;

public class Logger {
	
	private static Logger LOGGER;
	
	public static void init(JavaPlugin plugin) {
		if(LOGGER != null) return;
		LOGGER = new Logger(plugin.getServer().getConsoleSender(), plugin.getName());
	}
	
	public static Logger getInstance() {
		return LOGGER;
	}
	
	public static void log(Level level, String message) {
		Logger.getInstance().loga(level, message);
	}
	
	

	private ConsoleCommandSender console;
	private String label;
	private Level priority = Level.INFO;
	
	private Logger(ConsoleCommandSender console, String label) {
		this.console = console;
		this.label = label;
	}
	
	public void loga(Level level, String message) {
		if(level.weight < priority.weight) return;
		
		String prefix = ChatColor.AQUA + label + "." + level.color + level.name();
		String divider = ChatColor.AQUA + " >> " + ChatColor.GRAY;
		
		console.sendMessage(prefix + divider + Messenger.color(message));
	}
	
	public void setPriority(Level priority) {
		this.priority = priority;
	}
	
}
