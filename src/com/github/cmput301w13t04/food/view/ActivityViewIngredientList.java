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
 * 
 * @author W13T04
 * 
 */
public class ActivityViewIngredientList extends Activity {

	private static final int ACTION_ADD_INGREDIENT = 1;

	private Cache cache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ingredient_list);
	}

	public void onResume() {
		super.onResume();

		// Refresh log
		cache = new Cache();
		cache.load(this);

		ArrayList<Ingredient> ingredients = cache.getIngredients();

		ListView listIngredients = (ListView) findViewById(R.id.ingredient_list);
		listIngredients.setAdapter(new IngredientAdapter(this,
				R.layout.item_ingredient, ingredients));

		listIngredients.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// View ingredient on item click.
				Intent intent = new Intent(ActivityViewIngredientList.this,
						ActivityViewIngredient.class);

				intent.putExtra("INGREDIENT", cache.getIngredient(position));
				intent.putExtra("POSITION", position);
				startActivity(intent);
			}
		});
	}

	/**
	 * Launches Add Ingredient (Called on Button Press)
	 * 
	 * @param view
	 */
	public void addIngredient(View view) {
		Intent intent = new Intent(ActivityViewIngredientList.this,
				ActivityManageIngredient.class);
		startActivityForResult(intent, ACTION_ADD_INGREDIENT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ACTION_ADD_INGREDIENT: {
			if (resultCode == RESULT_OK) {
				Cache cache = new Cache();
				cache.load(this);

				// Add ingredient
				Ingredient ingredient = data.getParcelableExtra("INGREDIENT");
				cache.addIngredient(ingredient);

				cache.save(this);
			}
		}
		default: break;
		}
	}
}