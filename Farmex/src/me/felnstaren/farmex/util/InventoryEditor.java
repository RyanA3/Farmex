package me.felnstaren.farmex.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryEditor {

	public static int getTotalItems(Inventory inventory, ItemStack search) {
		int count = 0;
		
		ItemStack[] contents = inventory.getContents();
		for(int i = 0; i < contents.length; i++) {
			if(contents[i] == null) continue;
			if(contents[i].isSimilar(search)) count += contents[i].getAmount();
		}
		
		return count;
	}
	
	public static boolean removeItems(Inventory inventory, ItemStack search, int amount) {
		ItemStack[] contents = inventory.getContents();
		for(int i = 0; i < contents.length; i++) {
			if(amount == 0) return true;
			if(contents[i] == null) continue;
			if(!contents[i].isSimilar(search)) continue;
			
			int stack = contents[i].getAmount();
			if(stack <= amount) {
				amount -= stack;
				stack = 0;
			} else {
				stack -= amount;
				amount = 0;
			}
			
			contents[i].setAmount(stack);
		}
		
		return amount == 0;
	}
	
}
