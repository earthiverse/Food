package com.github.cmput301w13t04.food;

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
}
