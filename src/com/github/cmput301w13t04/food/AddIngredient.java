package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Gets input from the user and saves it to the cache.
 * 
 * @author T04
 * 
 */
public class AddIngredient extends Activity {

	private static final int TAKE_PHOTO = 1;
	private Photo p;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ingredient);
		p = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_ingredient, menu);
		return true;
	}

	/**
	 * Gets input from user, checks if user entered both name and quantity.
	 * @return True if the user has entered valid data
	 */
	public boolean addIngredientToCache() {
		EditText name = (EditText) findViewById(R.id.add_name);
		EditText quantity = (EditText) findViewById(R.id.add_quantity);
		EditText description = (EditText) findViewById(R.id.add_description);

		String nameIngredient = name.getText().toString();
		String descriptionIngredient = description.getText().toString();
		String quantityIngredient = quantity.getText().toString();

		

		if (!nameIngredient.isEmpty() && !quantityIngredient.isEmpty()) {
			
			Ingredient ingredient = new Ingredient(nameIngredient,
					quantityIngredient, descriptionIngredient, p);

			Cache cache = new Cache();
			cache.load(this);
			cache.addIngredient(ingredient);
			cache.save(this);

			return true;
		} else
			return false;

	}
	
	public void takePhoto(View view){
		Intent intent = new Intent(this, PhotoIntentActivity.class);
		startActivityForResult(intent, TAKE_PHOTO);
	}

	public void finishActivity(View view) {
		if (addIngredientToCache()) {
			this.finish();
			Toast.makeText(getApplicationContext(), "New entry added! ",
					Toast.LENGTH_SHORT).show();
		}

		else
			Toast.makeText(getApplicationContext(),
					"Please enter a name and quantity!", Toast.LENGTH_SHORT)
					.show();

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode == TAKE_PHOTO){
			if(resultCode == RESULT_OK){
				String path = data.getStringExtra("path");
				String desc = data.getStringExtra("desc");
				//get User instead of null
				p = new Photo(path, desc, null);
			}
		}
	}

}
