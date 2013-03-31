package com.github.cmput301w13t04.food.view;

import java.util.ArrayList;
import java.util.Locale;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.controller.Cache;
import com.github.cmput301w13t04.food.controller.IngredientAdapter;
import com.github.cmput301w13t04.food.controller.StepAdapter;
import com.github.cmput301w13t04.food.model.Photo;
import com.github.cmput301w13t04.food.model.Recipe;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * View a Recipe and all of its properties
 * 
 * @author W13T04
 * 
 */
public class ActivityViewRecipe extends FragmentActivity implements
		ActionBar.TabListener {

	public static Recipe recipe;

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
		recipe = cache.getRecipe(recipeID);
		if (recipe == null) {
			// TODO: Get recipe from database

			// ELSE ERROR:
			// TODO: Toast 'Recipe not found!'
			finish();
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

	public void manageRecipe(MenuItem menuitem) {
		Intent intent = new Intent(ActivityViewRecipe.this,
				ActivityManageRecipe.class);
		intent.putExtra("RECIPE_ID", recipe.getId());
		startActivity(intent);
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