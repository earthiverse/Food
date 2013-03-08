package com.github.cmput301w13t04.food;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Cache {

	private ArrayList<Recipe> Recipes;
	private ArrayList<Ingredient> Ingredients;

	private final String recipeFilename = "recipeData.gson";
	private final String ingredientFilename = "recipeData.gson";

	public Cache() {
		Recipes = new ArrayList<Recipe>();
		Ingredients = new ArrayList<Ingredient>();
	}

	/**
	 * Get the recipes that are stored locally
	 * @return an ArrayList of type Recipes
	 */
	public ArrayList<Recipe> getRecipes() {
		return this.Recipes;
	}

	/**
	 * Get the ingredients that are stored locally
	 * @return an ArrayList of type Ingredient
	 */
	public ArrayList<Ingredient> getIngredients() {
		return this.Ingredients;
	}

	/**
	 * Save the cache to file in order to make changes persist
	 */
	public void save(Context context) {
		Gson gson = new Gson();
		
		Log.d("Write", "Testing Everything2JSON: " + gson.toJson(this));

		try {
			// Save Ingredient List to File
			DataOutputStream ingredientOut = new DataOutputStream(context.openFileOutput(this.ingredientFilename, Context.MODE_PRIVATE));
			Log.d("Write", "Wrote: " + gson.toJson(this.Ingredients));
			ingredientOut.writeUTF(gson.toJson(this.Ingredients));
			ingredientOut.flush();
			ingredientOut.close();

			// Save Recipe List to File
//			DataOutputStream recipeOut = new DataOutputStream(context.openFileOutput(this.recipeFilename, Context.MODE_PRIVATE));
//			Log.d("Write", "Wrote: " + gson.toJson(this.Recipes));
//			recipeOut.writeUTF(gson.toJson(this.Recipes));
//			recipeOut.flush();
//			recipeOut.close();
			
		} catch(Exception e) {
			Log.d("Testing", "Error in Cache Save!");
			Log.d("Testing", e.getMessage());
		}

	}

	/**
	 * Load the cache from file
	 */
	public void load(Context context) {
		Gson gson = new Gson();
		
		try {
			// Load Ingredient List from File
			StringBuffer ingredientData = new StringBuffer("");
			DataInputStream ingredientIn = new DataInputStream(context.openFileInput(this.ingredientFilename));
			try {
				
				for (;;)
					ingredientData.append(ingredientIn.readUTF());
				
			} catch (EOFException e) {

				// Finished Reading File, Convert from JSON to Object
				Log.d("File", "Read Ingredients: " + ingredientData.toString());
				this.Ingredients = gson.fromJson(ingredientData.toString(), new TypeToken<ArrayList<Ingredient>>(){}.getType());
				
		    }
			ingredientIn.close();
			
			// Load Recipe List from File
//			StringBuffer recipeData = new StringBuffer("");
//			DataInputStream recipeIn = new DataInputStream(context.openFileInput(this.recipeFilename));
//			try {
//				
//				for (;;)
//					recipeData.append(recipeIn.readUTF());
//				
//			} catch (EOFException e) {
//
//				// Finished Reading File, Convert from JSON to Object
//				Log.d("File", "Read Recipe: " + recipeData.toString());
//				this.Recipes = gson.fromJson(recipeData.toString(), new TypeToken<ArrayList<Recipe>>(){}.getType());
//				
//		    }
//			recipeIn.close();
			
		} catch(Exception e) {
			Log.d("Testing", "Error in Cache Load!");
			Log.d("Testing", e.getMessage());
		}
	}

	/**
	 * Remove a Recipe object from the cache
	 * @param r the Recipe object to be removed
	 */
	public void removeRecipe(Recipe r) {
		Recipes.remove(r);
	}

	/**
	 * Remove an Ingredient
	 * @param ing the Ingredient object to be removed
	 */
	public void removeIngredient(Ingredient ing) {
		Recipes.remove(ing);
	}

	/**
	 * Checks if the cache has Ingredients
	 * @return True is the cache has ingredients
	 */
	public boolean hasIngredients() {
		return !Ingredients.isEmpty();
	}

	/**
	 * Checks if the cache has any Recipes
	 * @return True if the cache has Recipes
	 */
	public boolean hasRecipes() {
		return !Recipes.isEmpty();
	}

	/**
	 * Get the number of Recipes that are currently in the cache
	 * @return The number of Recipes in the cache
	 */
	public int recipeCount() {
		return Recipes.size();
	}

	/**
	 * Get the number of Ingredients that are currently in he cache
	 * @return The number of Ingredients that are in the cache
	 */
	public int ingredientCount() {
		return Ingredients.size();
	}

	/**
	 * Add an Ingredient object to the cache
	 * @param i The Ingredient to be added
	 */
	public void addIngredient(Ingredient i){
		this.Ingredients.add(i);
	}

	/**
	 * Add a Recipe object to the cache
	 * @param r The Recipe to be added
	 */
	public void addRecipe(Recipe r){
		this.Recipes.add(r);
	}

	/**
	 * Get a Recipe from the cache by it's id
	 * @param id the id of the Recipe object you would like
	 * @return The Recipe object that the caller requested, or null if it doesn't exist
	 */
	public Recipe getRecipe(int id){

		for(int i = 0; i < this.recipeCount(); i++){
			if(Recipes.get(i).getId() == id){
				return Recipes.get(i);
			}
		}

		return null;
	}
	
	/**
	 * For debug.
	 */
	public void printRecipeList() {
		Log.i("Recipe", "# of Recipes: " + String.valueOf(this.recipeCount()));
		
		for(int i = 0; i < this.recipeCount(); i++){
			Log.i("Recipe", "Name: " + Recipes.get(i).getTitle());
		}
	}
	
	/**
	 * For debug.
	 */
	public void printIngredientList() {
		Log.i("Ingredient", "# of Ingredients: " + String.valueOf(this.ingredientCount()));
		
		for(int i = 0; i < this.ingredientCount(); i++){
			Log.i("Ingredient", "Name: " + Ingredients.get(i).getName() + "\nDescription:" + Ingredients.get(i).getDescription());
		}
	}

}
