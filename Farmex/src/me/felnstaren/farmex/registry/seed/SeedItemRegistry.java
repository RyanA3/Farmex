package me.felnstaren.farmex.registry.seed;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.farmex.logger.Level;
import me.felnstaren.farmex.logger.Logger;

public class SeedItemRegistry {

	private ISeedEntry[] registry = new ISeedEntry[32];
	
	public SeedItemRegistry() {
		
	}
	
	public boolean isSeed(ItemStack check) {
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null) continue;
			else if(registry[i].matches(check)) return true;
		} return false;
	}
	
	public SeedType getSeedType(ItemStack check) {
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null) continue;
			else if(registry[i].matches(check))
				return registry[i].getType();
		} return SeedType.NONE;
	}
	
	public ISeedEntry getEntry(ItemStack check) {
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null) continue;
			else if(registry[i].matches(check)) return registry[i];
		} return null;
	}
	
	
	
	public void register(ISeedEntry entry) {
		Logger.log(Level.DEBUG, "Adding item to seed registry...");
		for(int i = registry.length - 1; i > 0; i--)
			if(registry[i] == null) { registry[i] = entry; return; }
	}
	
	public void register(Material item, Material block) {
		register(new SeedEntry(item, block));
	}
	
}
