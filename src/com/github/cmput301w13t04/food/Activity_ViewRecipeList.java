package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

/*
 * Activity for viewing our Recipe Book
 */
public class Activity_ViewRecipeList extends Activity {

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
				Intent intent = new Intent(Activity_ViewRecipeList.this, Activity_ViewRecipe.class);
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

	/**
	 * @uml.property  name="activity_SearchRecipe"
	 * @uml.associationEnd  inverse="activity_ViewRecipeList:com.github.cmput301w13t04.food.Activity_SearchRecipe"
	 */
	private Activity_SearchRecipe activity_SearchRecipe;

	/**
	 * Getter of the property <tt>activity_SearchRecipe</tt>
	 * @return  Returns the activity_SearchRecipe.
	 * @uml.property  name="activity_SearchRecipe"
	 */
	public Activity_SearchRecipe getActivity_SearchRecipe()
	
	
	
	{
	
		return activity_SearchRecipe;
	}

	/**
	 * Setter of the property <tt>activity_SearchRecipe</tt>
	 * @param activity_SearchRecipe  The activity_SearchRecipe to set.
	 * @uml.property  name="activity_SearchRecipe"
	 */
	public void setActivity_SearchRecipe(
			Activity_SearchRecipe activity_SearchRecipe)
	
	
	
	{
	
		this.activity_SearchRecipe = activity_SearchRecipe;
	}

	/**
	 * @uml.property  name="activity_ViewRecipe"
	 * @uml.associationEnd  inverse="activity_ViewRecipeList:com.github.cmput301w13t04.food.Activity_ViewRecipe"
	 */
	private Activity_ViewRecipe activity_ViewRecipe;

	/**
	 * Getter of the property <tt>activity_ViewRecipe</tt>
	 * @return  Returns the activity_ViewRecipe.
	 * @uml.property  name="activity_ViewRecipe"
	 */
	public Activity_ViewRecipe getActivity_ViewRecipe()
	
	
	{
	
		return activity_ViewRecipe;
	}

	/**
	 * Setter of the property <tt>activity_ViewRecipe</tt>
	 * @param activity_ViewRecipe  The activity_ViewRecipe to set.
	 * @uml.property  name="activity_ViewRecipe"
	 */
	public void setActivity_ViewRecipe(Activity_ViewRecipe activity_ViewRecipe)
	
	
	{
	
		this.activity_ViewRecipe = activity_ViewRecipe;
	}

	/**
	 * @uml.property  name="activity_AddRecipe"
	 * @uml.associationEnd  inverse="activity_ViewRecipeList:com.github.cmput301w13t04.food.Activity_AddRecipe"
	 */
	private Activity_AddRecipe activity_AddRecipe;

	/**
	 * Getter of the property <tt>activity_AddRecipe</tt>
	 * @return  Returns the activity_AddRecipe.
	 * @uml.property  name="activity_AddRecipe"
	 */
	public Activity_AddRecipe getActivity_AddRecipe()
	
	{
	
		return activity_AddRecipe;
	}

	/**
	 * Setter of the property <tt>activity_AddRecipe</tt>
	 * @param activity_AddRecipe  The activity_AddRecipe to set.
	 * @uml.property  name="activity_AddRecipe"
	 */
	public void setActivity_AddRecipe(Activity_AddRecipe activity_AddRecipe)
	
	{
	
		this.activity_AddRecipe = activity_AddRecipe;
	}

	/**
	 * @uml.property  name="cache"
	 * @uml.associationEnd  inverse="activity_ViewRecipeList:com.github.cmput301w13t04.food.Cache"
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

}
