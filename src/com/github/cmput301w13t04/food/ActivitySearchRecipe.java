package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ActivitySearchRecipe extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_search_recipe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search_recipe, menu);
		return true;
	}
	public void searchByUser(View view) {
		Intent intent = new Intent(ActivitySearchRecipe.this,
				ActivitySearchByUser.class);
		startActivity(intent);
	}
	public void searchByIngredient(View view) {
		Intent intent = new Intent(ActivitySearchRecipe.this,
				ActivitySearchByIngredient.class);
		startActivity(intent);
	}

}
