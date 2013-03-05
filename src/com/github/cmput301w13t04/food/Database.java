package com.github.cmput301w13t04.food;

import com.google.gson.Gson;

public class Database {
	
	public Database() {	}

	void publishRecipe(Recipe recipe) {
		String post = new Gson().toJson(recipe);
		//TODO: Post this JSON to the server
	}
}
