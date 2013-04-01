package com.github.cmput301w13t04.food.view;

import java.util.ArrayList;
import java.util.Locale;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.controller.Cache;
import com.github.cmput301w13t04.food.controller.Database;
import com.github.cmput301w13t04.food.controller.IngredientAdapter;
import com.github.cmput301w13t04.food.controller.StepAdapter;
import com.github.cmput301w13t04.food.model.Ingredient;
import com.github.cmput301w13t04.food.model.Photo;
import com.github.cmput301w13t04.food.model.Recipe;
import com.github.cmput301w13t04.food.model.Step;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * View a Recipe and all of its properties
 * 
 * @author W13T04
 * 
 */
public class ActivityViewRecipe extends FragmentActivity implements
		ActionBar.TabListener {

	private static Recipe recipe;
	private boolean fromRemote;

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setup View
		setContentView(R.layout.activity_view_recipe);
	}

	@Override
	public void onResume() {
		super.onResume();

		// Load Cache
		Cache cache = new Cache();
		cache.load(this);

		cache.printRecipeList();

		// Load Recipe from ID
		long recipeID = this.getIntent().getLongExtra("RECIPE_ID", -1);

		if (recipeID == -1) {
			/* Check to see if a link was passed */
			try {
				Uri data = getIntent().getData();
				recipeID = Long.valueOf(data.getLastPathSegment());
				Log.d("recipeID", data.getLastPathSegment());
			} catch (Exception e) {
				/* No recipe found, abort! */
				noRecipeFound();
			}
		}

		recipe = cache.getRecipe(recipeID);
		if (recipe == null) {
			Database database = new Database();
			recipe = database.searchByID(recipeID);
			if(recipe == null) {
				// TODO: Toast 'Recipe not found!'
				finish();
			} else {
				// WOO, WE GOT IT!
				fromRemote = true;
			}
		}

		// ELSE: We've got our recipe!

		// Setup ActionBar
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setTitle(recipe.getTitle());
		actionBar.setSubtitle(recipe.getAuthor().getUsername());

		// Setup Tab Navigation
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// Add Tab Names
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if(fromRemote) {
			// Replace edit with save when we're viewing remote recipes
			menu.getItem(0).setIcon(R.drawable.content_save);
			menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
				public boolean onMenuItemClick(MenuItem item) {
					// Save to cache on click
					Cache cache = new Cache();
					cache.load(getBaseContext());
					
					cache.addRecipe(recipe);
					
					cache.save(getBaseContext());
					
					// Show confirmation to user
					Toast toast = Toast.makeText(getApplicationContext(), "Saved " + recipe.getTitle() + "!", Toast.LENGTH_SHORT);
					toast.show();
					
					// Revert Our Changes
					item.setIcon(R.drawable.content_edit);
					item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
						public boolean onMenuItemClick(MenuItem item) {
							manageRecipe(item);
							fromRemote = false;
							return true;
						}
					});
					return true;
				}
			});
			
		}
		return true;
	}

	@Override
	public void onPause() {
		super.onPause();

		getActionBar().removeAllTabs();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_recipe, menu);
		return true;
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// Not used.
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// Not used.
	}

	/* Handle Page Switching */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment;

			switch (position) {
			// First Tab: Description
			case 0:
				fragment = new RecipeDescriptionFragment();

				// Pass Current Recipe

				return fragment;

				// Second Tab: Ingredients
			case 1:
				fragment = new RecipeIngredientsFragment();

				// Pass Current Recipe

				return fragment;

				// Third Tab: Steps
			case 2:
				fragment = new RecipeStepFragment();

				// Pass Current Recipe

				return fragment;
			}

			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.recipe_title_description)
						.toUpperCase(l);
			case 1:
				return getString(R.string.recipe_title_ingredients)
						.toUpperCase(l);
			case 2:
				return getString(R.string.recipe_title_steps).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * Called when no recipe is found with given parameters
	 */
	private void noRecipeFound() {
		// TODO: Show error no recipe found
		finish();
	}

	/**
	 * Menu button handler for editing recipe
	 * 
	 * @param menuitem
	 */
	public void manageRecipe(MenuItem menuitem) {
		Intent intent = new Intent(ActivityViewRecipe.this,
				ActivityManageRecipe.class);
		intent.putExtra("RECIPE_ID", recipe.getId());
		startActivity(intent);
	}

	/*
	 * TODO: If this were a real app, it would not hard-code English words.
	 */
	/**
	 * Menu button handler for sharing a recipe via. email
	 */
	public void shareRecipe(MenuItem menuitem) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");

		intent.putExtra(Intent.EXTRA_SUBJECT, recipe.getTitle()
				+ " [ReasonableRecipes]");

		// Title & Author
		StringBuffer body = new StringBuffer("<a href=\"http://reasonablerecipes.com/"
				+ String.valueOf(recipe.getId()) + "\">" + "<strong>"
				+ recipe.getTitle() + "</strong></a><br />by "
				+ recipe.getAuthor().getUsername() + "<br /><br />");

		// Time
		body.append("<strong>Time Required:</strong> " + String.valueOf(recipe.getTime() + " minutes")
				+ "<br /><br />");

		// Ingredients
		body.append("<strong>Ingredient List:</strong><br />");
		ArrayList<Ingredient> ingredients = recipe.getIngredients();
		for (int i = 0; i < ingredients.size(); i++) {
			Ingredient ingredient = ingredients.get(i);
			body.append(ingredient.getQuantity() + " " + ingredient.getName() + "<br />");
		}
		body.append("<br />");
		
		// Steps
		body.append("<strong>Directions:</strong><br />");
		ArrayList<Step> steps = recipe.getSteps();
		for (int i = 0; i < steps.size() ; i++) {
			Step step = steps.get(i);
			body.append(String.valueOf(i + 1) + ". " + step.getName() + "<br />");
			body.append(step.getDescription() + "<br /><br />");
		}
		
		// Footer
		body.append("<a href=\"http://reasonablerecipes.com/"
				+ String.valueOf(recipe.getId()) + "\">" + "http://reasonablerecipes.com/"
				+ String.valueOf(recipe.getId()) + "</a>");

		intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body.toString()));
		startActivity(Intent.createChooser(intent, "Share Recipe"));
	}

	/* Description Fragment */
	public static class RecipeDescriptionFragment extends Fragment {

		private ImageView image;

		private ArrayList<Photo> photos;
		private int position = 0;

		public RecipeDescriptionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_recipe_description, container, false);

			// Image Button Handlers
			ImageButton nextPhoto = (ImageButton) rootView
					.findViewById(R.id.button_next_photo);
			nextPhoto.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Photo photo;
					try {
						// Next Photo
						photo = photos.get(position + 1);
						position += 1;
						image.setImageURI(Uri.parse(photo.getPath()));
					} catch (Exception e1) {
						// First Photo
						try {
							photo = photos.get(0);
							position = 0;
							image.setImageURI(Uri.parse(photo.getPath()));
						} catch (Exception e2) {
							// No photos attached to recipe
						}
					}
				}
			});
			ImageButton prevPhoto = (ImageButton) rootView
					.findViewById(R.id.button_prev_photo);
			prevPhoto.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Photo photo;
					try {
						// Previous Photo
						photo = photos.get(position - 1);
						position -= 1;
						image.setImageURI(Uri.parse(photo.getPath()));
					} catch (Exception e1) {
						// Last Photo
						try {
							photo = photos.get(photos.size() - 1);
							position = photos.size() - 1;
							image.setImageURI(Uri.parse(photo.getPath()));
						} catch (Exception e2) {
							// No photos attached to recipe
						}
					}
				}
			});

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();

			View v = this.getView();

			TextView recipe_description = (TextView) v
					.findViewById(R.id.recipe_description);
			recipe_description.setText(recipe.getDescription());

			TextView recipe_time = (TextView) v.findViewById(R.id.recipe_time);
			recipe_time.setText(String.valueOf(recipe.getTime()));

			photos = recipe.getPhotos();

			// Recipe Photos
			try {
				Photo photo = photos.get(position);
				image = (ImageView) v.findViewById(R.id.recipe_image);
				image.setImageURI(Uri.parse(photo.getPath()));
			} catch (Exception e) {
				// No photos :c
			}
		}
	}

	/* Ingredient Fragment */
	public static class RecipeIngredientsFragment extends Fragment {
		public RecipeIngredientsFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_recipe_ingredients, container, false);

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();

			View v = this.getView();

			ListView list = (ListView) v.findViewById(R.id.ingredient_list);
			list.setAdapter(new IngredientAdapter(list.getContext(),
					R.layout.item_ingredient, recipe.getIngredients()));
		}
	}

	/* Step Fragment */
	public static class RecipeStepFragment extends Fragment {
		public RecipeStepFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_recipe_steps,
					container, false);

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();

			View v = this.getView();

			ListView list = (ListView) v.findViewById(R.id.step_list);
			list.setAdapter(new StepAdapter(list.getContext(),
					R.layout.item_step, recipe.getSteps()));
		}
	}
}