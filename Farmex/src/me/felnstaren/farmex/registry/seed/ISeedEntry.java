package me.felnstaren.farmex.registry.seed;

import org.bukkit.inventory.ItemStack;

public interface ISeedEntry {

	public boolean matches(ItemStack check);
	public SeedType getType();
	
}
