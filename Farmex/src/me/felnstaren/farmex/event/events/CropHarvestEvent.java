package me.felnstaren.farmex.event.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.felnstaren.farmex.event.IFarmexEvent;

public class CropHarvestEvent extends IFarmexEvent {

	private Player player;
	private Block crop;
	
	public CropHarvestEvent(Player player, Block crop) {
		this.player = player;
		this.crop = crop;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getCrop() {
		return crop;
	}
	
}
