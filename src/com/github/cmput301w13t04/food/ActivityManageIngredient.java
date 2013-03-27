package com.github.cmput301w13t04.food;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Gets input from the user and saves it to the cache.
 * 
 * @author T04
 * 
 */
public class ActivityManageIngredient extends Activity {

	private static final int TAKE_PHOTO = 1;
	private Photo p;
	int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_ingredient);
		
		p = null;

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

				ImageView photoView = (ImageView) findViewById(R.id.ingredient_image);
				if(photoView == null)
					Log.d("Path", "NULL");
				
				if(ingredient.getPhoto() != null) {
					Log.d("Photo_URI", ingredient.getPhoto().getPath().toString());
					photoView.setImageURI(Uri.parse(ingredient.getPhoto().getPath()));
				}

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

		if (nameIngredient.isEmpty()) {
			Toast.makeText(view.getContext(), "Missing Ingredient Name!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		// Get Quantity
		EditText quantity = (EditText) findViewById(R.id.add_quantity);
		String quantityIngredient = quantity.getText().toString();

		if (quantityIngredient.isEmpty()) {
			Toast.makeText(view.getContext(), "Missing Ingredient Quantity!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		// Get Description
		EditText description = (EditText) findViewById(R.id.add_description);
		String descriptionIngredient = description.getText().toString();

		// Construct ingredient
		Ingredient ingredient = new Ingredient(nameIngredient,
				quantityIngredient, descriptionIngredient, p);

		
		// TODO: Add photo changes

		Cache cache = new Cache();
		cache.load(view.getContext());

		if (getIntent().getBooleanExtra("INGREDIENT_EDIT", false)) {
			// Update ingredient
			cache.updateIngredient(ingredient, this.id);
		} else {
			// Add new ingredient
			cache.addIngredient(ingredient);
		}

		cache.save(view.getContext());

		Toast.makeText(view.getContext(), "Added " + nameIngredient + "!",
				Toast.LENGTH_SHORT).show();

		finish();
	}

	public void takePhoto(View view) {
		Intent intent = new Intent(this, PhotoIntentActivity.class);
		startActivityForResult(intent, TAKE_PHOTO);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == TAKE_PHOTO) {
			if (resultCode == RESULT_OK) {
				String path = data.getStringExtra("path");
				String desc = data.getStringExtra("desc");
				// get User instead of null
				p = new Photo(path, desc, null);
				
				ImageView photoView = (ImageView) findViewById(R.id.ingredient_image);
				if(photoView == null)
					Log.d("Path", "NULL");
				
				if(p != null) {
					Log.d("Photo_URI", p.getPath().toString());
					photoView.setImageURI(Uri.parse(p.getPath()));
				}
			}
		}
	}

}
