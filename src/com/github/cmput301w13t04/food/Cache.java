package com.github.cmput301w13t04.food;


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
	private Context context;

	private final String recipeFile = "recipeData.gson";
	private final String ingredientFile = "recipeData.gson";

	public Cache(Context context) {
		Recipes = new ArrayList<Recipe>();
		Ingredients = new ArrayList<Ingredient>();
		this.context = context;

		this.load();
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
	public void save() {

		Gson gson = new Gson();
		String ingList = gson.toJson(Ingredients);
		String recipeList = gson.toJson(Recipes);

		Log.d("Testing", ingList + "inSave");
		Log.d("Testing", recipeList);

		try {
			FileOutputStream rOut = context.openFileOutput(recipeFile, Context.MODE_PRIVATE);
			FileOutputStream ingOut = context.openFileOutput(ingredientFile, Context.MODE_PRIVATE);
			OutputStreamWriter iWriter, rWriter; 


			if(this.hasIngredients()){
				iWriter = new OutputStreamWriter(ingOut);
				iWriter.write(ingList);
				iWriter.flush();
				iWriter.close();
			}

			if(this.hasRecipes()){
				rWriter = new OutputStreamWriter(rOut);
				rWriter.write(recipeList);
				rWriter.flush();
				rWriter.close();
			}
			
			rOut.close();
			ingOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the cache from file
	 */
	public void load() {

		Gson gson = new Gson();
		Type ingredientList = new TypeToken<ArrayList<Ingredient>>(){}.getType();
		Type rList = new TypeToken<ArrayList<Recipe>>(){}.getType();

		StringBuffer fileContent;
		byte[] buffer;
		
		File fileR = new File(context.getFilesDir(), recipeFile);
		File fileI = new File(context.getFilesDir(), ingredientFile);
		
		if(!fileR.exists()){
			try {
				fileR.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(!fileI.exists()){
			try {
				fileI.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			FileInputStream rIn = context.openFileInput(recipeFile);
			FileInputStream ingIn = context.openFileInput(ingredientFile);
			
			fileContent = new StringBuffer("");
			buffer = new byte[1];
			while(ingIn.read(buffer) != -1)
				fileContent.append(new String(buffer));
			
			//Log.d("Testing", fileContent.toString());
			
			if(fileI.length() != 0){
				Ingredients = gson.fromJson(fileContent.toString(), ingredientList);
				Log.d("Testing", Ingredients.get(0).getName() + " inLoad");
			}
			
			fileContent = new StringBuffer("");
			buffer = new byte[1];
			while(rIn.read(buffer) != -1)
				fileContent.append(new String(buffer));

			Log.d("Testing", fileContent.toString());
			
			if(fileR.length() != 0)
				Recipes = gson.fromJson(fileContent.toString(), rList);

		

		} catch (IOException e) {
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

}
