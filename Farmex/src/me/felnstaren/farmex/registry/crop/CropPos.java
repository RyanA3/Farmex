package me.felnstaren.farmex.registry.crop;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class CropPos {

	public int x;
	public int y;
	public int z;
	public String world;
	
	public CropPos(int x, int y, int z, String world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}
	
	public CropPos(Block block) {
		this.x = block.getLocation().getBlockX();
		this.y = block.getLocation().getBlockY();
		this.z = block.getLocation().getBlockZ();
		this.world = block.getWorld().getName();
	}
	
	public boolean matches(Location location) {
		return 
				world.equals(location.getWorld().getName()) &&
				x == location.getBlockX() &&
				y == location.getBlockY() &&
				z == location.getBlockZ();
	}
	
}
