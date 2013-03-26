package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Gets input from the user and saves it to the cache.
 * 
 * @author T04
 * 
 */
public class ActivityManageIngredient extends Activity {

	int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_ingredient);

		populate();
	}

	public void populate() {
		// Get Ingredient ID
		this.id = getIntent().getIntExtra("INGREDIENT_ID", -1);

		if (this.id != -1) {
			// Grab Ingredient from Cache
			Cache cache = new Cache();
			cache.load(this);
			Ingredient ingredient = cache.getIngredient(this.id);

			// Populate Current Ingredient Values
			if (ingredient == null) {

				// TODO: Something went wrong, there is no ingredient with that
				// ID.
				finish();

			} else {

				// TODO: Populate Ingredient Image

				TextView quantity = (TextView) findViewById(R.id.add_quantity);
				if (quantity != null) {
					quantity.setText(String.valueOf(ingredient.getQuantity()));
				}

				TextView name = (TextView) findViewById(R.id.add_name);
				if (name != null) {
					name.setText(ingredient.getName());
				}

				TextView description = (TextView) findViewById(R.id.add_description);
				if (name != null) {
					description.setText(ingredient.getDescription());
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_ingredient, menu);
		return true;
	}

	public void updateIngredient(View view) {
		// Get Name
		EditText name = (EditText) findViewById(R.id.add_name);
		String nameIngredient = name.getText().toString();
		
		if(nameIngredient.isEmpty()) {
			Toast.makeText(view.getContext(),
					"Missing Ingredient Name!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		
		// Get Quantity
		EditText quantity = (EditText) findViewById(R.id.add_quantity);
		String quantityIngredient = quantity.getText().toString();
		
		if(quantityIngredient.isEmpty()) {
			Toast.makeText(view.getContext(),
					"Missing Ingredient Quantity!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		
		// Get Description
		EditText description = (EditText) findViewById(R.id.add_description);
		String descriptionIngredient = description.getText().toString();
		
		// Construct ingredient
		Ingredient ingredient = new Ingredient(nameIngredient,
				quantityIngredient, descriptionIngredient, null);
		
		// TODO: Add photo changes
		
		Cache cache = new Cache();
		cache.load(view.getContext());
		
		if(getIntent().getBooleanExtra("INGREDIENT_EDIT", false)) {
			// Update ingredient
			cache.updateIngredient(ingredient, this.id);
		} else {
			// Add new ingredient
			cache.addIngredient(ingredient);
		}

		cache.save(view.getContext());
		
		Toast.makeText(view.getContext(),
				"Added " + nameIngredient + "!", Toast.LENGTH_SHORT)
				.show();
		
		finish();
	}

}
