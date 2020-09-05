package me.felnstaren.farmex;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.farmex.config.Loader;
import me.felnstaren.farmex.config.Options;
import me.felnstaren.farmex.event.FarmexEventMaster;
import me.felnstaren.farmex.listener.HoeListener;
import me.felnstaren.farmex.logger.Level;
import me.felnstaren.farmex.logger.Logger;
import me.felnstaren.farmex.registry.CustomMaterial;
import me.felnstaren.farmex.registry.crop.CropBlockRegistry;
import me.felnstaren.farmex.registry.recipe.RecipeMaker;
import me.felnstaren.farmex.registry.seed.SeedItemRegistry;

public class Farmex extends JavaPlugin {

	private SeedItemRegistry seed_registry;
	private CropBlockRegistry crop_registry;
	private FarmexEventMaster event_master;
	private RecipeMaker recipe_maker;
	
	public void onEnable() {
		Logger.init(this);
		
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		
		this.recipe_maker = new RecipeMaker(this);
		CustomMaterial.registerRecipes(recipe_maker);
		
		this.seed_registry = new SeedItemRegistry();
		seed_registry.register(Material.WHEAT_SEEDS, Material.WHEAT);
		seed_registry.register(Material.CARROT, Material.CARROTS);
		seed_registry.register(Material.POTATO, Material.POTATOES);
		seed_registry.register(Material.BEETROOT_SEEDS, Material.BEETROOTS);
		
		this.crop_registry = new CropBlockRegistry();
		crop_registry.register(Material.WHEAT);
		crop_registry.register(Material.CARROTS);
		crop_registry.register(Material.POTATOES);
		crop_registry.register(Material.BEETROOTS);
		
		this.event_master = new FarmexEventMaster();
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new HoeListener(event_master, seed_registry, crop_registry), this);
		
		Logger.log(Level.INFO, "&aFarmex initialization complete");
	}
	
	public void onDisable() {
		
	}
	
	
	
	public SeedItemRegistry getSeedRegistry() {
		return seed_registry;
	}
	
	public CropBlockRegistry getCropRegistry() {
		return crop_registry;
	}
	
	public FarmexEventMaster getEventMaster() {
		return event_master;
	}
	
}
