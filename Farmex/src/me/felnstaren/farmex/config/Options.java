package me.felnstaren.farmex.config;

import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.farmex.logger.Level;
import me.felnstaren.farmex.logger.Logger;

public class Options {
	
	public static boolean USE_PERMISSION = false;

	public static void load(YamlConfiguration config) {
		Logger.log(Level.INFO, "Loading config options from file; " + config.getCurrentPath());
		
		Logger.getInstance().setPriority(Level.valueOf(config.getString("logger-priority")));
		USE_PERMISSION = config.getBoolean("use-permission");
	}
	
}
