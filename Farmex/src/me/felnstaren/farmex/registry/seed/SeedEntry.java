package me.felnstaren.farmex.registry.seed;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SeedEntry implements ISeedEntry {

	protected Material item;
	protected Material block;
	
	public SeedEntry(Material item, Material block) {
		this.item = item;
		this.block = block;
	}
	
	public boolean matches(ItemStack check) {
		return check.getType() == item;
	}

	public SeedType getType() {
		return SeedType.VANILLA;
	}
	
	public Material getItemMaterial() {
		return item;
	}
	
	public Material getBlockMaterial() {
		return block;
	}
	
}
