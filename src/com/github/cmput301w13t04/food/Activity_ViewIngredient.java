package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/* The class that allows us to view ingredients in our UI */
public class Activity_ViewIngredient extends Activity {
	private int id;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_ingredient);
    
    populate();


  }
  public void populate (){
	    Intent intent = getIntent();
	    this.id = intent.getIntExtra("index", 0);
	    
		Cache cache = new Cache();
		cache.load(this);

		ArrayList<Ingredient> ingredients = cache.getIngredients();
		
		Ingredient ingredient = ingredients.get(id);
		
		// Display ingredient
				if (ingredient != null) {

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
		Intent intent = new Intent(this,
				Activity_EditIngredient.class);
		intent.putExtra("index", this.id);
		intent.putExtra("New", false);
		startActivity(intent);
	}
	public void deleteIngredient(View view) {
		Cache cache = new Cache ();
		cache.load(this);
		cache.removeIngredient(id);
		cache.save(this);
		finish();
		Toast.makeText(getApplicationContext(), "Ingredient entry deleted!",
				Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onResume() {
		super.onResume();
		
		// Refresh log
		populate();
	}
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.view_ingredient, menu);
    return true;
  }

}
