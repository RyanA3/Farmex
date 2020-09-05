package me.felnstaren.farmex.registry.crop;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class CropBlockLocationChunkEntry implements ICropEntry {

	private Chunk chunk;
	private CropPos[] chunk_crops = new CropPos[128];
	
	public CropBlockLocationChunkEntry(Chunk chunk, CropPos[] chunk_crops, Material material) {
		this.chunk_crops = chunk_crops;
		this.chunk = chunk;
	}
	
	public boolean matches(Block check) {
		if(!check.getChunk().equals(chunk)) return false;
		for(int i = 0; i < chunk_crops.length; i++) {
			if(chunk_crops[i] == null) continue;
			else if(chunk_crops[i].matches(check.getLocation())) return true;
		} return false;
	}
	
	public CropType getType() {
		return CropType.CUSTOM;
	}
	
	public Chunk getChunk() {
		return chunk;
	}
	
	
	
	public void register(Block block) {
		if(matches(block)) return;
		else if(!block.getChunk().equals(chunk)) return;
		for(int i = chunk_crops.length - 1; i > 0; i--) {
			if(chunk_crops[i] != null) continue;
			else { chunk_crops[i] = new CropPos(block); return; }
		}
	}
	
	public void unregister(Block block) {
		if(!block.getChunk().equals(chunk)) return;
		for(int i = 0; i < chunk_crops.length; i++) {
			if(chunk_crops[i] == null) continue;
			else if(chunk_crops[i].matches(block.getLocation())) chunk_crops[i] = null;
		}
	}

}
