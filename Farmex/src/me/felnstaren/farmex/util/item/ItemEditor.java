package me.felnstaren.farmex.util.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemEditor {

	public static ItemStack setName(ItemStack item, String str) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(str.replace('&', '§'));
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static String getName(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		return meta.getDisplayName();
	}	
	
	
	
	public static ItemStack setUnbreakable(ItemStack item, boolean unb) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(unb);
		item.setItemMeta(meta);
		
		return item;
	}
	
	
	
	public static ItemStack setCustomModelData(ItemStack item, int data) {
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(data);
		item.setItemMeta(meta);
		
		return item;
	}
	
}
