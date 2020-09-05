package me.felnstaren.farmex.registry.seed;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import other.bananapuncher714.nbt.NBTEditor;

public class CustomSeedEntry implements ISeedEntry {

	protected String nbt_tag;
	protected Material item;
	
	public CustomSeedEntry(Material item, String nbt_tag) {
		this.item = item;
		this.nbt_tag = nbt_tag;
	}
	
	public boolean matches(ItemStack check) {
		return 
				check.getType() == item &&
				NBTEditor.contains(check, nbt_tag);
	}

	public SeedType getType() {
		return SeedType.CUSTOM;
	}

}
