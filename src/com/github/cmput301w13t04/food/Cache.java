package com.github.cmput301w13t04.food;

import java.util.*;

public class Cache {

	private ArrayList<Recipe> Recipes;
	private ArrayList<Ingredient> Ingredients;
	
	public Cache(){
		Recipes = new ArrayList<Recipe>();
		Ingredients = new ArrayList<Ingredient>();
		//load the recipes into Recipes
		//load the ing into Ing
	}
	
	public ArrayList<Recipe> getRecipes(){
		return this.Recipes;
	}
	
	public ArrayList<Ingredient> getIngredients(){
			return this.Ingredients;
	}
	
	public void save(){
		
	}
	
	public void load(){
		
	}
	
	public void removeRecipe(Recipe r){
		Recipes.remove(r);
	}
	
	public void removeIngredient(Ingredient ing){
		Recipes.remove(ing);
	}
	
	public boolean hasIngredients(){
		return Ingredients.isEmpty();
	}
	
	public boolean hasRecipes(){
		return Recipes.isEmpty();
	}
	
	public int recipeCount(){
		return Recipes.size();
	}
	
	public int ingredientCount(){
		return Ingredients.size();
	}
 }
