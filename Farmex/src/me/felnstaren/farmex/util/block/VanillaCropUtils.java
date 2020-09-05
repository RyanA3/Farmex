package me.felnstaren.farmex.util.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;

public class VanillaCropUtils {

	public static boolean isMature(Block plant) {
		Ageable crop = (Ageable) plant.getBlockData();
		return crop.getAge() == crop.getMaximumAge();
	}
	
	public static boolean isPlantable(Block land) {
		if(land == null || land.getType() != Material.FARMLAND) return false;
		if(land.getRelative(BlockFace.UP).getType() != Material.AIR) return false;
		return true;
	}
	
	public static boolean harvest(Block plant) {
		Ageable crop = (Ageable) plant.getBlockData();
		if(crop.getAge() != crop.getMaximumAge()) return false;
		
		crop.setAge(0);
		for(ItemStack drop : plant.getDrops()) 
			plant.getWorld().dropItemNaturally(plant.getLocation().add(0.5, 0.5, 0.5), drop);
		
		plant.setBlockData(crop);
		return true;
	}
	
}
