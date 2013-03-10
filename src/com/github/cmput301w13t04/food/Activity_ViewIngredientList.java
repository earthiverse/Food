
package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ListView;

public class Activity_ViewIngredientList extends Activity
{
	private ListView listIngredients;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_recipe_ingredients);

		Cache cache = new Cache();
		cache.load(this);

		ArrayList<Ingredient> ingredients = cache.getIngredients();


		 listIngredients= ( ListView ) findViewById( R.id.ingredient_list);
		 listIngredients.setAdapter(new IngredientAdapter(this, R.layout.item_ingredient, ingredients));
    
	}

        //TODO: Do something when a list item is clicked
 


}