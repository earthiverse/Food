package com.github.cmput301w13t04.food;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;

/**
 * The object that allows for storage of local data on the device
 * @author W13T04
 */
public class Cache {

	// Our local list of recipes( also called recipe book)
	/**
	 * @uml.property  name="recipes"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.github.cmput301w13t04.food.Recipe"
	 */
	private ArrayList<Recipe> Recipes;
	
	//Our local list of ingredients (also called pantry)
	/**
	 * @uml.property  name="ingredients"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.github.cmput301w13t04.food.Ingredient"
	 */
	private ArrayList<Ingredient> Ingredients;
	
	/**
	 * @uml.property  name="jsonFilename"
	 */
	private String jsonFilename = "cache.json";

	/**
	 * Create a new Cache object with empty Recipe and Ingredient lists
	 */
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

		try {
			// Save Cache to File
			DataOutputStream dataOut = new DataOutputStream(context.openFileOutput(this.jsonFilename, Context.MODE_PRIVATE));
			Log.d("Write", "Wrote: " + gson.toJson(this));
			dataOut.writeUTF(gson.toJson(this));
			dataOut.flush();
			dataOut.close();
			
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
			// Load Cache from File
			StringBuffer cacheData = new StringBuffer("");
			DataInputStream dataIn = new DataInputStream(context.openFileInput(this.jsonFilename));
			try {
				
				for (;;)
					cacheData.append(dataIn.readUTF());
				
			} catch (EOFException e) {

				// Finished Reading File, Convert from JSON to Object
				Log.d("File", "Read Ingredients: " + cacheData.toString());
				
				// Create temporary cache
				Cache tempCache = new Cache();
				tempCache = gson.fromJson(cacheData.toString(), Cache.class);
				
				// Copy important info from temporary cache
				this.Ingredients = tempCache.Ingredients;
				this.Recipes = tempCache.Recipes;
				
		    }
			dataIn.close();
			
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
		Ingredients.remove(ing);
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
	public Recipe getRecipe(long id){

		for(int i = 0; i < this.recipeCount(); i++){
			if(Recipes.get(i).getId() == id){
				return Recipes.get(i);
			}
		}

		return null;
	}
	
	/**
	 * Get an ingredient based on position in the cache
	 * @param pos The position of the Ingredient object wanted
	 * @return The Ingredient object from the position that was requested
	 */
	public Ingredient getIngredient(int pos){
		return this.Ingredients.get(pos);
	}
	
	/*
	 * For debug.
	 */
	public void printRecipeList() {
		Log.i("Recipe", "# of Recipes: " + String.valueOf(this.recipeCount()));
		
		for(int i = 0; i < this.recipeCount(); i++){
			Log.i("Recipe", "Name: " + Recipes.get(i).getTitle());
		}
	}
	
	/*
	 * For debug.
	 */
	public void printIngredientList() {
		Log.i("Ingredient", "# of Ingredients: " + String.valueOf(this.ingredientCount()));
		
		for(int i = 0; i < this.ingredientCount(); i++){
			Log.i("Ingredient", "Name: " + Ingredients.get(i).getName() + "\nDescription:" + Ingredients.get(i).getDescription());
		}
	}

	/**
	 * @uml.property  name="recipe1"
	 * @uml.associationEnd  aggregation="shared" inverse="cache:com.github.cmput301w13t04.food.Recipe"
	 */
	private Recipe recipe1;

	/**
	 * Getter of the property <tt>recipe1</tt>
	 * @return  Returns the recipe1.
	 * @uml.property  name="recipe1"
	 */
	public Recipe getRecipe1()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		return recipe1;
	}

	/**
	 * Setter of the property <tt>recipe1</tt>
	 * @param recipe1  The recipe1 to set.
	 * @uml.property  name="recipe1"
	 */
	public void setRecipe1(Recipe recipe1)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		this.recipe1 = recipe1;
	}

	/**
	 * @uml.property  name="activity_ViewRecipeList"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_ViewRecipeList"
	 */
	private Activity_ViewRecipeList activity_ViewRecipeList;

	/**
	 * Getter of the property <tt>activity_ViewRecipeList</tt>
	 * @return  Returns the activity_ViewRecipeList.
	 * @uml.property  name="activity_ViewRecipeList"
	 */
	public Activity_ViewRecipeList getActivity_ViewRecipeList()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		return activity_ViewRecipeList;
	}

	/**
	 * Setter of the property <tt>activity_ViewRecipeList</tt>
	 * @param activity_ViewRecipeList  The activity_ViewRecipeList to set.
	 * @uml.property  name="activity_ViewRecipeList"
	 */
	public void setActivity_ViewRecipeList(
			Activity_ViewRecipeList activity_ViewRecipeList)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		this.activity_ViewRecipeList = activity_ViewRecipeList;
	}

	/**
	 * @uml.property  name="activity_AddRecipe"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_AddRecipe"
	 */
	private Activity_AddRecipe activity_AddRecipe;

	/**
	 * Getter of the property <tt>activity_AddRecipe</tt>
	 * @return  Returns the activity_AddRecipe.
	 * @uml.property  name="activity_AddRecipe"
	 */
	public Activity_AddRecipe getActivity_AddRecipe()
	
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		return activity_AddRecipe;
	}

	/**
	 * Setter of the property <tt>activity_AddRecipe</tt>
	 * @param activity_AddRecipe  The activity_AddRecipe to set.
	 * @uml.property  name="activity_AddRecipe"
	 */
	public void setActivity_AddRecipe(Activity_AddRecipe activity_AddRecipe)
	
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		this.activity_AddRecipe = activity_AddRecipe;
	}

	/**
	 * @uml.property  name="activity_SearchRecipe"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_SearchRecipe"
	 */
	private Activity_SearchRecipe activity_SearchRecipe;

	/**
	 * Getter of the property <tt>activity_SearchRecipe</tt>
	 * @return  Returns the activity_SearchRecipe.
	 * @uml.property  name="activity_SearchRecipe"
	 */
	public Activity_SearchRecipe getActivity_SearchRecipe()
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		return activity_SearchRecipe;
	}

	/**
	 * Setter of the property <tt>activity_SearchRecipe</tt>
	 * @param activity_SearchRecipe  The activity_SearchRecipe to set.
	 * @uml.property  name="activity_SearchRecipe"
	 */
	public void setActivity_SearchRecipe(
			Activity_SearchRecipe activity_SearchRecipe)
	
	
	
	
	
	
	
	
	
	
	
	
	{
	
		this.activity_SearchRecipe = activity_SearchRecipe;
	}

	/**
	 * @uml.property  name="activity_ViewRecipe"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_ViewRecipe"
	 */
	private Activity_ViewRecipe activity_ViewRecipe;

	/**
	 * Getter of the property <tt>activity_ViewRecipe</tt>
	 * @return  Returns the activity_ViewRecipe.
	 * @uml.property  name="activity_ViewRecipe"
	 */
	public Activity_ViewRecipe getActivity_ViewRecipe()
	
	
	
	
	
	
	
	
	
	
	
	{
	
		return activity_ViewRecipe;
	}

	/**
	 * Setter of the property <tt>activity_ViewRecipe</tt>
	 * @param activity_ViewRecipe  The activity_ViewRecipe to set.
	 * @uml.property  name="activity_ViewRecipe"
	 */
	public void setActivity_ViewRecipe(Activity_ViewRecipe activity_ViewRecipe)
	
	
	
	
	
	
	
	
	
	
	
	{
	
		this.activity_ViewRecipe = activity_ViewRecipe;
	}

	/**
	 * @uml.property  name="activity_EditRecipe"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_EditRecipe"
	 */
	private Activity_EditRecipe activity_EditRecipe;

	/**
	 * Getter of the property <tt>activity_EditRecipe</tt>
	 * @return  Returns the activity_EditRecipe.
	 * @uml.property  name="activity_EditRecipe"
	 */
	public Activity_EditRecipe getActivity_EditRecipe()
	
	
	
	
	
	
	
	
	
	
	{
	
		return activity_EditRecipe;
	}

	/**
	 * Setter of the property <tt>activity_EditRecipe</tt>
	 * @param activity_EditRecipe  The activity_EditRecipe to set.
	 * @uml.property  name="activity_EditRecipe"
	 */
	public void setActivity_EditRecipe(Activity_EditRecipe activity_EditRecipe)
	
	
	
	
	
	
	
	
	
	
	{
	
		this.activity_EditRecipe = activity_EditRecipe;
	}

	/** 
	 * @uml.property name="activity_AddIngredient"
	 * @uml.associationEnd inverse="cache:com.github.cmput301w13t04.food.Activity_AddIngredient"
	 */
	private Activity_AddIngredient activity_AddIngredient;

	/** 
	 * Getter of the property <tt>activity_AddIngredient</tt>
	 * @return  Returns the activity_AddIngredient.
	 * @uml.property  name="activity_AddIngredient"
	 */
	public Activity_AddIngredient getActivity_AddIngredient()
	
	
	
	
	
	
	
	
	
	{
		return activity_AddIngredient;
	}

	/**
	 * @uml.property  name="activity_ViewIngredientList"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_ViewIngredientList"
	 */
	private Activity_ViewIngredientList activity_ViewIngredientList;

	/**
	 * Getter of the property <tt>activity_ViewIngredientList</tt>
	 * @return  Returns the activity_ViewIngredientList.
	 * @uml.property  name="activity_ViewIngredientList"
	 */
	public Activity_ViewIngredientList getActivity_ViewIngredientList()
	
	
	
	
	
	
	
	
	{
	
		return activity_ViewIngredientList;
	}

	/**
	 * Setter of the property <tt>activity_ViewIngredientList</tt>
	 * @param activity_ViewIngredientList  The activity_ViewIngredientList to set.
	 * @uml.property  name="activity_ViewIngredientList"
	 */
	public void setActivity_ViewIngredientList(
			Activity_ViewIngredientList activity_ViewIngredientList)
	
	
	
	
	
	
	
	
	{
	
		this.activity_ViewIngredientList = activity_ViewIngredientList;
	}

	/**
	 * @uml.property  name="activity_ViewIngredient"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_ViewIngredient"
	 */
	private Activity_ViewIngredient activity_ViewIngredient;

	/**
	 * Getter of the property <tt>activity_ViewIngredient</tt>
	 * @return  Returns the activity_ViewIngredient.
	 * @uml.property  name="activity_ViewIngredient"
	 */
	public Activity_ViewIngredient getActivity_ViewIngredient()
	
	
	
	
	
	
	
	{
	
		return activity_ViewIngredient;
	}

	/**
	 * Setter of the property <tt>activity_ViewIngredient</tt>
	 * @param activity_ViewIngredient  The activity_ViewIngredient to set.
	 * @uml.property  name="activity_ViewIngredient"
	 */
	public void setActivity_ViewIngredient(
			Activity_ViewIngredient activity_ViewIngredient)
	
	
	
	
	
	
	
	{
	
		this.activity_ViewIngredient = activity_ViewIngredient;
	}

	/**
	 * @uml.property  name="activity_EditIngredient"
	 * @uml.associationEnd  inverse="cache:com.github.cmput301w13t04.food.Activity_EditIngredient"
	 */
	private Activity_EditIngredient activity_EditIngredient;

	/**
	 * Getter of the property <tt>activity_EditIngredient</tt>
	 * @return  Returns the activity_EditIngredient.
	 * @uml.property  name="activity_EditIngredient"
	 */
	public Activity_EditIngredient getActivity_EditIngredient()
	
	
	
	
	
	
	{
	
		return activity_EditIngredient;
	}

	/**
	 * Setter of the property <tt>activity_EditIngredient</tt>
	 * @param activity_EditIngredient  The activity_EditIngredient to set.
	 * @uml.property  name="activity_EditIngredient"
	 */
	public void setActivity_EditIngredient(
			Activity_EditIngredient activity_EditIngredient)
	
	
	
	
	
	
	{
	
		this.activity_EditIngredient = activity_EditIngredient;
	}

	/** 
	 * Setter of the property <tt>activity_AddIngredient</tt>
	 * @param activity_AddIngredient  The activity_AddIngredient to set.
	 * @uml.property  name="activity_AddIngredient"
	 */
	public void setActivity_AddIngredient(
			Activity_AddIngredient activity_AddIngredient)
	
	
	{
		this.activity_AddIngredient = activity_AddIngredient;
	}

	/**
	 * @uml.property  name="activity_AddIngredient1"
	 * @uml.associationEnd  inverse="cache1:com.github.cmput301w13t04.food.Activity_AddIngredient"
	 */
	private Activity_AddIngredient activity_AddIngredient1;

	/**
	 * Getter of the property <tt>activity_AddIngredient1</tt>
	 * @return  Returns the activity_AddIngredient1.
	 * @uml.property  name="activity_AddIngredient1"
	 */
	public Activity_AddIngredient getActivity_AddIngredient1()
	{

		return activity_AddIngredient1;
	}

	/**
	 * Setter of the property <tt>activity_AddIngredient1</tt>
	 * @param activity_AddIngredient1  The activity_AddIngredient1 to set.
	 * @uml.property  name="activity_AddIngredient1"
	 */
	public void setActivity_AddIngredient1(
			Activity_AddIngredient activity_AddIngredient1)
	{

		this.activity_AddIngredient1 = activity_AddIngredient1;
	}

}
