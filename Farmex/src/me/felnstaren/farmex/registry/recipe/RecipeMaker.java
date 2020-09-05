package me.felnstaren.farmex.registry.recipe;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.farmex.util.Messenger;
import me.felnstaren.farmex.util.item.ItemEditor;

public class RecipeMaker {
	
	private JavaPlugin plugin;
	
	public RecipeMaker(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public Recipe toRecipe(RecipeShape shape, ItemStack result) {
		NamespacedKey key = new NamespacedKey(plugin, Messenger.uncolor(ItemEditor.getName(result)).replace(' ', '_'));
		HashMap<Character, Material> matmap = shape.getMatMap();
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		
		recipe.shape(shape.getShape());
		
		for(Character matkey : matmap.keySet()) 
			recipe.setIngredient(matkey, matmap.get(matkey));
		
		return recipe;
	}
	
}
