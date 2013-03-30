package com.github.cmput301w13t04.food.view;

import java.util.ArrayList;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.controller.Cache;
import com.github.cmput301w13t04.food.controller.IngredientAdapter;
import com.github.cmput301w13t04.food.model.Ingredient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * An activity designed for viewing our list of Ingredients
 * @author W13T04
 *
 */
public class ActivityViewIngredientList extends Activity {
	private ListView listIngredients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_recipe_ingredients);

		// Populate log
		populate();
	}

	public void onResume() {
		super.onResume();

		// Refresh log
		populate();
	}

	/**
	 * Load the ingredients data and populate the views field with this data
	 */
	public void populate() {
		Cache cache = new Cache();
		cache.load(this);

		ArrayList<Ingredient> ingredients = cache.getIngredients();

		listIngredients = (ListView) findViewById(R.id.ingredient_list);
		listIngredients.setAdapter(new IngredientAdapter(this,
				R.layout.item_ingredient, ingredients));

		listIngredients.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// View ingredient on item click.
				Intent intent = new Intent(ActivityViewIngredientList.this,
						ActivityViewIngredient.class);
				intent.putExtra("INGREDIENT_ID", position);
				startActivity(intent);
			}
		});
	}
}