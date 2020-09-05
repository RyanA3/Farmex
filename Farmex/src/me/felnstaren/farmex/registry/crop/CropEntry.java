package me.felnstaren.farmex.registry.crop;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class CropEntry implements ICropEntry {

	private Material material;
	
	public CropEntry(Material material) {
		this.material = material;
	}
	
	public boolean matches(Block check) {
		return check.getType() == material;
	}

	public CropType getType() {
		return CropType.VANILLA;
	}
	
}
