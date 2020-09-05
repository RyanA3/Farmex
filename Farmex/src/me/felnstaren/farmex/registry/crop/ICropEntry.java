package me.felnstaren.farmex.registry.crop;

import org.bukkit.block.Block;

public interface ICropEntry {

	public boolean matches(Block check);
	public CropType getType();
	
}
