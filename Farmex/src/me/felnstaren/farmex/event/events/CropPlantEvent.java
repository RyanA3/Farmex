package me.felnstaren.farmex.event.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.farmex.event.IFarmexEvent;

public class CropPlantEvent extends IFarmexEvent {

	private Player player;
	private Block crop;
	private ItemStack seed;
	
	public CropPlantEvent(Player player, Block crop, ItemStack seed) {
		this.player = player;
		this.crop = crop;
		this.seed = seed;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getCrop() {
		return crop;
	}
	
	public ItemStack getSeed() {
		return seed;
	}
	
}
