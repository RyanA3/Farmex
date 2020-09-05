package me.felnstaren.farmex.registry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.farmex.logger.Level;
import me.felnstaren.farmex.logger.Logger;
import me.felnstaren.farmex.registry.recipe.RecipeMaker;
import me.felnstaren.farmex.registry.recipe.RecipeShape;
import me.felnstaren.farmex.util.item.ItemEditor;
import other.bananapuncher714.nbt.NBTEditor;

public enum CustomMaterial {

	WOODEN_SCYTHE(Material.WOODEN_HOE, "Wooden Scythe", 1, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.OAK_PLANKS)),
	STONE_SCYTHE(Material.STONE_HOE, "Stone Scythe", 2, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.COBBLESTONE)),
	IRON_SCYTHE(Material.IRON_HOE, "Iron Scythe", 3, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.IRON_INGOT)),
	GOLDEN_SCYTHE(Material.GOLDEN_HOE, "Golden Scythe", 4, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.GOLD_INGOT)),
	DIAMOND_SCYTHE(Material.DIAMOND_HOE, "Diamond Scythe", 5, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.DIAMOND));
	
	private int id;
	private String name;
	private Material material;
	private RecipeShape shape;
	
	private CustomMaterial(Material material, String name, int id, RecipeShape shape) {
		this.material = material;
		this.name = name;
		this.id = id;
		this.shape = shape;
	}
	
	public Material getTrueMaterial() {
		return this.material;
	}
	
	public int getID() {
		return this.id;
	}
	
	public RecipeShape getRecipeShape() {
		return shape;
	}
	
	
	
	public boolean matches(ItemStack item) {
		if(item.getType() != material) return false;
		if(!NBTEditor.contains(item, "FarmexID")) return false;
		if(NBTEditor.getInt(item, "FarmexID") != id) return false;
		
		return true;
	}
	
	public ItemStack itemize() {
		ItemStack item = new ItemStack(material);
		item = NBTEditor.set(item, id, "FarmexID");
		ItemEditor.setName(item, "&f" + name);
		ItemEditor.setCustomModelData(item, id);
		
		return item;
	}
	
	
	
	public static CustomMaterial getAsCustomMaterial(ItemStack item) {
		int id = NBTEditor.getInt(item, "FarmexID");

		for(CustomMaterial material : CustomMaterial.values()) 
			if(id == material.id) return material;
		return null;
	}
	
	public static boolean isCustomMaterial(ItemStack item) {
		return NBTEditor.contains(item, "FarmexID");
	}
	
	public static void registerRecipes(RecipeMaker recipe_maker) {
		Logger.log(Level.INFO, "Registering custom item recipes...");
		for(CustomMaterial material : CustomMaterial.values())
			Bukkit.addRecipe(recipe_maker.toRecipe(material.getRecipeShape(), material.itemize()));
	}
	
}
