package com.github.cmput301w13t04.food.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.controller.Cache;
import com.github.cmput301w13t04.food.controller.IngredientAdapter;
import com.github.cmput301w13t04.food.model.Ingredient;

/**
 * A class used for searching ingredients off of the online database
 * @author solinas
 *
 */

public class ActivitySearchIngredient extends Activity {

	private Cache cache;
	private ArrayList<Ingredient> ingredients;
	private ListView ingredientList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_ingredient);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	@Override
	public void onResume() {
		super.onResume();

		// Refresh log
		cache = new Cache();
		cache.load(this);

		ingredients = cache.getIngredients();

		ingredientList = (ListView) findViewById(R.id.ingredient_list);
		ingredientList.setAdapter(new IngredientAdapter(this,
				R.layout.item_ingredient, ingredients));

		ingredientList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// Uncheck everything
		for (int i = 0; i < ingredientList.getAdapter().getCount(); i++) {
			ingredientList.setItemChecked(i, true);
			Log.d("Testing", String.valueOf(i) + " set to false.");
		}

		ingredientList.setOnItemClickListener(new OnItemClickListener() {

			// Change selection based on ingredients
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (ingredientList.isItemChecked(arg2)) {
					Log.d("Testing", String.valueOf(arg2)
							+ ": Checked->Unchecked");
					arg1.setBackgroundColor(getResources().getColor(
							R.color.transparent));
				} else {
					Log.d("Testing", String.valueOf(arg2)
							+ ": Unchecked->Checked");
					arg1.setBackgroundColor(getResources().getColor(
							R.color.translucent_blue));
				}
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
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

	/**
	 * Button handler for searching
	 * @param view
	 */
	public void searchIngredients(View view) {
		Intent intent = new Intent(ActivitySearchIngredient.this,
				ActivityViewRecipeListDatabase.class);

		SparseBooleanArray positions = ingredientList.getCheckedItemPositions();
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

		for (int i = 0; i < positions.size(); i++) {
			if (!positions.get(i)) {
				Log.d("Testing", "selected " + String.valueOf(i));
				ingredients.add(this.ingredients.get(i));
			}
		}
		intent.putExtra("INGREDIENTS", ingredients);
		startActivity(intent);
	}
}
