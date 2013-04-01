package com.github.cmput301w13t04.food.model.query;

import java.util.ArrayList;

import com.github.cmput301w13t04.food.model.Recipe;

public class RecipeResult {
	
	public Hits hits;
	
	public ArrayList<Recipe> getRecipes() {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for(int i = 0; i < hits.hits2.size(); i++) {
			result.add(hits.hits2.get(i)._source);
		}
		return result;
	}
	
	public class Hits {
		public ArrayList<Hits2> hits2;
		
		public class Hits2 {
			public Recipe _source;
		}
	}
}
