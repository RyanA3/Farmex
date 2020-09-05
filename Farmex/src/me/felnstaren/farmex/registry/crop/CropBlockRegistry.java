package me.felnstaren.farmex.registry.crop;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

import me.felnstaren.farmex.logger.Level;
import me.felnstaren.farmex.logger.Logger;

public class CropBlockRegistry {

	private ICropEntry[] registry = new ICropEntry[256];
	
	public CropBlockRegistry() {
		
	}
	
	public boolean isCrop(Block block) {
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null) continue;
			else if(registry[i].matches(block)) return true;
		} return false;
	}
	
	public CropType getCropType(Block block) {
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null) continue;
			else if(registry[i].matches(block))
				return registry[i].getType();
		} return CropType.NONE;
	}
	
	public ICropEntry getEntry(Block block) {
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null) continue;
			else if(registry[i].matches(block)) return registry[i];
		} return null;
	}
	
	
	
	public void register(ICropEntry entry) {
		Logger.log(Level.STREAM, "Adding block to crop registry...");
		for(int i = registry.length - 1; i > 0; i--)
			if(registry[i] == null) { registry[i] = entry; return; }
	}
	
	public void register(Material material) {
		register(new CropEntry(material));
	}

	
	/*
	 * Used for unregistering vanilla CropEntries (Entry strictly by Materials)
	 */
	public void unregister(CropEntry entry) {
		for(int i = 0; i < registry.length; i++) {
			if(!(registry[i] instanceof CropEntry)) continue;
			else if(((CropEntry) registry[i]).equals(entry)) { registry[i] = null; return; }
		}
	}
	
	/*
	 * Used for unregistering custom crop location entries, 
	 * automatically queries all CropBlockLocationEntries and CropBlockLocationChunkEntries
	 */
	public void unregister(Block crop) {
		Logger.log(Level.STREAM, "Unregistering custom crop block from crop registry...");
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null || registry[i].getType() == CropType.VANILLA) continue;
			if(registry[i].matches(crop)) { ((CropBlockLocationChunkEntry) registry[i]).unregister(crop); return; }
		}
	}
	
	/*
	 * Used for unregistering whole chunks of crops, use this if taking an approach to loading
	 * custom crops where they are loaded into memory as chunks are loaded, and unloaded as chunks
	 * are unloaded.
	 */
	public void unregister(Chunk chunk) {
		Logger.log(Level.STREAM, "Unregistering custom crop chunk from crop registry...");
		for(int i = 0; i < registry.length; i++) {
			if(registry[i] == null || registry[i].getType() == CropType.VANILLA) continue;
			if(((CropBlockLocationChunkEntry) registry[i]).getChunk().equals(chunk)) { registry[i] = null; return; }
		}
	}
	
}
