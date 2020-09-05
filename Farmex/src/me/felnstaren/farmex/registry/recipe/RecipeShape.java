package me.felnstaren.farmex.registry.recipe;

import java.util.HashMap;

import org.bukkit.Material;

public class RecipeShape {

	private String[] shape;
	private HashMap<Character, Material> matmap = new HashMap<Character, Material>();
	
	public RecipeShape(String line1, String line2, String line3) {
		shape = new String[3];
		shape[0] = line1;
		shape[1] = line2;
		shape[2] = line3;
	}
	
	public RecipeShape(String line1, String line2) {
		shape = new String[2];
		shape[0] = line1;
		shape[1] = line2;
	}
	
	public RecipeShape(String line1) {
		shape = new String[1];
		shape[0] = line1;
	}
	
	
	
	public RecipeShape material(Character key, Material mat) {
		matmap.put(key, mat);
		return this;
	}
	
	
	
	public String[] getShape() {
		return this.shape;
	}
	
	public HashMap<Character, Material> getMatMap() {
		return this.matmap;
	}
	
}
