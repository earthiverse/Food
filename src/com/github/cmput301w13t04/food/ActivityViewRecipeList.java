package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

/*
 * Activity for viewing our Recipe Book
 */
public class ActivityViewRecipeList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_recipe_list);
		// Show the Up button in the action bar.
		setupActionBar();

		Cache cache = new Cache();
		cache.load(this);

		// Populate Recipe List
		ListView list = (ListView) findViewById(R.id.recipe_list);
		list.setAdapter(new RecipeAdapter(list.getContext(),
				R.layout.item_recipe, cache.getRecipes()));

		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Intent intent = new Intent(ActivityViewRecipeList.this, ActivityViewRecipe.class);
				intent.putExtra("RECIPE_ID", id);
				startActivity(intent);

			}

		});
	}

	/*
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_recipe_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void export(MenuItem menuItem){
		Cache cache = new Cache();
		cache.load(this);
		int res = cache.exportRecipes(this);
		Toast toast;

		if(res == -1)
			toast = Toast.makeText(this, "Export Failed!", Toast.LENGTH_SHORT);
		else
			toast = Toast.makeText(this, "Exported to sdcard/Download!", Toast.LENGTH_SHORT);

		toast.show();
		return;
	}

}
