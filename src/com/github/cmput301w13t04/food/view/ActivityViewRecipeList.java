package com.github.cmput301w13t04.food.view;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.controller.Cache;
import com.github.cmput301w13t04.food.controller.RecipeAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

/**
 * An activity designed for viewing our list of Ingredients
 * 
 * @author W13T04
 * 
 */
public class ActivityViewRecipeList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_recipe_list);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	@Override
	protected void onResume() {
		super.onResume();

		Cache cache = new Cache();
		cache.load(this);

		// Populate Recipe List
		ListView list = (ListView) findViewById(R.id.recipe_list);
		list.setAdapter(new RecipeAdapter(list.getContext(),
				R.layout.item_recipe, cache.getRecipes()));

		list.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getApplication());

				builder.setMessage("Remove Recipe?");
				builder.setPositiveButton("Remove",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								
								dialog.dismiss();
							}
						});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
					
				});
				
				builder.create();
				builder.show();

				// TODO Auto-generated method stub
				return false;
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Intent intent = new Intent(ActivityViewRecipeList.this,
						ActivityViewRecipe.class);
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

	public void addRecipe(View view) {
		Intent intent = new Intent(ActivityViewRecipeList.this,
				ActivityManageRecipe.class);
		startActivity(intent);
	}

	/**
	 * Export the current Recipe list to .json format for use in other
	 * applications
	 */
	public void export(MenuItem menuItem) {
		Cache cache = new Cache();
		cache.load(this);
		int res = cache.exportRecipes(this);
		Toast toast;

		if (res == -1)
			toast = Toast.makeText(this, "Export Failed!", Toast.LENGTH_SHORT);
		else
			toast = Toast.makeText(this, "Exported to sdcard/Download!",
					Toast.LENGTH_SHORT);

		toast.show();
		return;
	}

	public void showRecipeSearch(MenuItem menuItem) {
		// Create the fragment and show it as a dialog.
		DialogFragment newFragment = FragmentSearchRecipe.newInstance();
		newFragment.show(getFragmentManager(), "SearchRecipe");
	}

}
