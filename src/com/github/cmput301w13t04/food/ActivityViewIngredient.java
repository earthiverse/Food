package com.github.cmput301w13t04.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/* The class that allows us to view ingredients in our UI */
public class ActivityViewIngredient extends Activity {
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ingredient);

		// Populate Ingredient
		populate();
	}

	@Override
	public void onResume() {
		super.onResume();

		// Repopulate Ingredient List
		populate();
	}

	public void populate() {
		// Get Ingredient ID
		this.id = getIntent().getIntExtra("INGREDIENT_ID", -1);

		Cache cache = new Cache();
		cache.load(this);

		Ingredient ingredient = cache.getIngredient(this.id);

		// Populate Current Ingredient
		if (ingredient == null) {
			
			// TODO: Something went wrong, there is no ingredient with that ID.
			finish();
			
		} else {
			
			// TODO: Populate Ingredient Image

			TextView quantity = (TextView) findViewById(R.id.ingQuantity);
			if (quantity != null) {
				quantity.setText(String.valueOf(ingredient.getQuantity()));
			}

			TextView name = (TextView) findViewById(R.id.ingName);
			if (name != null) {
				name.setText(ingredient.getName());
			}

			TextView description = (TextView) findViewById(R.id.ingDescription);
			if (name != null) {
				description.setText(ingredient.getDescription());
			}
		}
	}

	public void editIngredient(View view) {
		Intent intent = new Intent(ActivityViewIngredient.this,
				ActivityManageIngredient.class);
		intent.putExtra("INGREDIENT_ID", this.id);
		intent.putExtra("INGREDIENT_EDIT", true);
		startActivity(intent);
	}

	public void deleteIngredient(View view) {
		
		Cache cache = new Cache();
		cache.load(this);
		cache.removeIngredient(id);
		cache.save(this);
		
		finish();
		Toast.makeText(getApplicationContext(), "Ingredient entry deleted!",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_ingredient, menu);
		return true;
	}

}
