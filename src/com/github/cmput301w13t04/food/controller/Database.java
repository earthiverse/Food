package com.github.cmput301w13t04.food.controller;

import android.os.AsyncTask;

import com.github.cmput301w13t04.food.model.Recipe;
import com.google.gson.Gson;

/**
 * An object used for communication with the online database
 * @author W13T04
 *
 */
public class Database {

	public Database() {
		// TODO: Figure out how we're doing things.
	}

	/**
	 * Publish a recipe to the online database
	 * @param recipe
	 */
	void publishRecipe(Recipe recipe) {
		String post = new Gson().toJson(recipe);
		// TODO: Post this JSON to the server
	}
	
	private class PublishRecipeTask extends AsyncTask<Recipe, Void, Boolean> {
	     protected Boolean doInBackground(Recipe... recipes) {
	        //Your download code here; work with the url parameter and then return the result
	        //which if I remember correctly from your code, is a string.
	        //This gets called and runs ON ANOTHER thread
	    	return true;
	     }

	     protected void onPostExecute(String result) {
	         // This gets called on the interface (main) thread!
	         // 
	     }

	 }
}
