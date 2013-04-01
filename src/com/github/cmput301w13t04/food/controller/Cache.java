package com.github.cmput301w13t04.food.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.github.cmput301w13t04.food.model.Ingredient;
import com.github.cmput301w13t04.food.model.Recipe;
import com.google.gson.Gson;

/**
 * The object that allows for storage of local data on the device
 * 
 * @author W13T04
 */
public class Cache {

	// Our local list of recipes( also called recipe book)
	private ArrayList<Recipe> Recipes;

	// Our local list of ingredients (also called pantry)
	private ArrayList<Ingredient> Ingredients;

	private final String jsonFilename = "cache.json";

	/**
	 * Create a new Cache object with empty Recipe and Ingredient lists
	 */
	public Cache() {
		Recipes = new ArrayList<Recipe>();
		Ingredients = new ArrayList<Ingredient>();
	}

	/**
	 * Get the recipes that are stored locally
	 * 
	 * @return an ArrayList of type Recipes
	 */
	public ArrayList<Recipe> getRecipes() {
		return this.Recipes;
	}

	/**
	 * Get the ingredients that are stored locally
	 * 
	 * @return an ArrayList of type Ingredient
	 */
	public ArrayList<Ingredient> getIngredients() {
		return this.Ingredients;
	}

	/**
	 * Set new ingredient list
	 */
	public void setIngredients(ArrayList<Ingredient> newIng) {
		this.Ingredients = newIng;
	}

	/**
	 * Set new ingredient list
	 */
	public void updateIngredient(Ingredient ingredient, int id) {
		Ingredients.remove(id);
		Ingredients.add(id, ingredient);
	}

	/**
	 * Save the cache to file in order to make changes persist
	 */
	public void save(Context context) {
		Gson gson = new Gson();

		try {
			// Save Cache to File
			DataOutputStream dataOut = new DataOutputStream(
					context.openFileOutput(this.jsonFilename,
							Context.MODE_PRIVATE));
			dataOut.writeUTF(gson.toJson(this));
			dataOut.flush();
			dataOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load the cache from file
	 */
	public void load(Context context) {
		Gson gson = new Gson();

		try {
			// Load Cache from File
			StringBuffer cacheData = new StringBuffer("");
			DataInputStream dataIn = new DataInputStream(
					context.openFileInput(this.jsonFilename));
			try {

				for (;;)
					cacheData.append(dataIn.readUTF());

			} catch (EOFException e) {
				// Create temporary cache
				Cache tempCache = new Cache();
				tempCache = gson.fromJson(cacheData.toString(), Cache.class);

				// Copy important info from temporary cache
				this.Ingredients = tempCache.Ingredients;
				this.Recipes = tempCache.Recipes;

			}
			dataIn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove a Recipe object from the cache
	 * 
	 * @param r
	 *            the Recipe object to be removed
	 */
	public void removeRecipe(Recipe r) {
		Recipes.remove(r);
	}

	/**
	 * Remove an Ingredient
	 * 
	 * @param ing
	 *            the Ingredient object to be removed
	 */
	public void removeIngredient(Ingredient ing) {
		Ingredients.remove(ing);
	}

	/**
	 * Remove an Ingredient
	 * 
	 * @param ing
	 *            the Ingredient object to be removed
	 */
	public void removeIngredient(int id) {
		Ingredients.remove(id);
	}

	/**
	 * Checks if the cache has Ingredients
	 * 
	 * @return True is the cache has ingredients
	 */
	public boolean hasIngredients() {
		return !Ingredients.isEmpty();
	}

	/**
	 * Checks if the cache has any Recipes
	 * 
	 * @return True if the cache has Recipes
	 */
	public boolean hasRecipes() {
		return !Recipes.isEmpty();
	}

	/**
	 * Get the number of Recipes that are currently in the cache
	 * 
	 * @return The number of Recipes in the cache
	 */
	public int recipeCount() {
		return Recipes.size();
	}

	/**
	 * Get the number of Ingredients that are currently in he cache
	 * 
	 * @return The number of Ingredients that are in the cache
	 */
	public int ingredientCount() {
		return Ingredients.size();
	}

	/**
	 * Add an Ingredient object to the cache
	 * 
	 * @param i
	 *            The Ingredient to be added
	 */
	public void addIngredient(Ingredient i) {
		this.Ingredients.add(i);
	}

	/**
	 * Add a Recipe object to the cache
	 * 
	 * @param r
	 *            The Recipe to be added
	 */
	public void addRecipe(Recipe r) {
		// If recipe being added exists, old recipe is overwritten
		for (int i = 0; i < this.recipeCount(); i++){
			if (Recipes.get(i).getId() == r.getId()) {
				Recipes.set(i, r);
				return;
			}
		}
		this.Recipes.add(r);
	}

	/**
	 * Get a Recipe from the cache by it's id
	 * 
	 * @param id
	 *            the id of the Recipe object you would like
	 * @return The Recipe object that the caller requested, or null if it
	 *         doesn't exist
	 */
	public Recipe getRecipe(long id) {

		for (int i = 0; i < this.recipeCount(); i++) {
			if (Recipes.get(i).getId() == id) {
				return Recipes.get(i);
			}
		}

		return null;
	}

	/**
	 * Get an ingredient based on position in the cache
	 * 
	 * @param pos
	 *            The position of the Ingredient object wanted
	 * @return The Ingredient object from the position that was requested
	 */
	public Ingredient getIngredient(int pos) {
		Ingredient ingredient = null;
		try {
			ingredient = this.Ingredients.get(pos);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ingredient;
	}
	
	/**
	 * Export the current Recipe list to .json format for use in other apps
	 * @return 0 on success, -1 of there are no recipes or an exception is raised
	 */
	public int exportRecipes(Context context){
		
		if(this.Recipes.isEmpty())
			return -1;
		
		Gson gson = new Gson();

		try {
			String jsonRecipes = gson.toJson(this.Recipes);
			File file = new File(Environment.getExternalStorageDirectory(), "MyRecipes.json");
			FileOutputStream fout = new FileOutputStream(file);
			fout.write(jsonRecipes.getBytes(Charset.forName("UTF-8")));
			fout.flush();
			fout.close();
			
		} catch (Exception e) {
			return -1;
		}

		
		return 0;
	}

	/*
	 * For debug.
	 */
	public void printRecipeList() {
		Log.i("Recipe", "# of Recipes: " + String.valueOf(this.recipeCount()));

		for (int i = 0; i < this.recipeCount(); i++) {
			Log.i("Recipe", "Name: " + Recipes.get(i).getTitle());
		}
	}

	/*
	 * For debug.
	 */
	public void printIngredientList() {
		Log.i("Ingredient",
				"# of Ingredients: " + String.valueOf(this.ingredientCount()));

		for (int i = 0; i < this.ingredientCount(); i++) {
			Log.i("Ingredient", "Name: " + Ingredients.get(i).getName()
					+ "\nDescription:" + Ingredients.get(i).getDescription());
		}
	}

}
