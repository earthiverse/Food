package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Gets input from the user and saves it to the cache.
 * 
 * @author T04
 * 
 */
public class Activity_AddIngredient extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ingredient);
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

		/* Photo class not yet implemented */
		Photo photo = new Photo(null, null, null);

		if (!nameIngredient.isEmpty() && !quantityIngredient.isEmpty()) {
			
			Ingredient ingredient = new Ingredient(nameIngredient,
					quantityIngredient, descriptionIngredient, photo);

			Cache cache = new Cache();
			cache.load(this);
			cache.addIngredient(ingredient);
			cache.save(this);

			return true;
		} else
			return false;

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

	/** 
	 * @uml.property name="cache"
	 * @uml.associationEnd inverse="activity_AddIngredient:com.github.cmput301w13t04.food.Cache"
	 */
	private Cache cache;

	/** 
	 * Getter of the property <tt>cache</tt>
	 * @return  Returns the cache.
	 * @uml.property  name="cache"
	 */
	public Cache getCache()
	
	
	
	
	
	
	{
		return cache;
	}

	/** 
	 * Setter of the property <tt>cache</tt>
	 * @param cache  The cache to set.
	 * @uml.property  name="cache"
	 */
	public void setCache(Cache cache)
	
	
	
	{
		this.cache = cache;
	}

	/**
	 * @uml.property  name="cache1"
	 * @uml.associationEnd  inverse="activity_AddIngredient1:com.github.cmput301w13t04.food.Cache"
	 */
	private Cache cache1;

	/**
	 * Getter of the property <tt>cache1</tt>
	 * @return  Returns the cache1.
	 * @uml.property  name="cache1"
	 */
	public Cache getCache1()
	{

		return cache1;
	}

	/**
	 * Setter of the property <tt>cache1</tt>
	 * @param cache1  The cache1 to set.
	 * @uml.property  name="cache1"
	 */
	public void setCache1(Cache cache1)
	{

		this.cache1 = cache1;
	}

}
