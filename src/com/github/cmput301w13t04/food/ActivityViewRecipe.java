package com.github.cmput301w13t04.food;

import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/* A class for viewing a recipe in our UI */
public class ActivityViewRecipe extends FragmentActivity implements
		ActionBar.TabListener {

	public static Recipe recipe;

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load Cache
		Cache cache = new Cache();
		cache.load(this);

		cache.printRecipeList();

		// Load Recipe from ID
		long recipeID = this.getIntent().getLongExtra("RECIPE_ID", -1);

		Log.d("Initial Value", String.valueOf(recipeID));
		
		if (recipeID == -1) {
			/* Check to see if a link was passed */
			try {
				Log.d("Test", "Started");
				Uri data = getIntent().getData();
				Log.d("URI", data.toString());
				recipeID = Long.valueOf(data.getHost()); // Recipe ID
				Log.d("recipeID", String.valueOf(recipeID));
			} catch (Exception e) {
				/* Recipe not found, Abort! */
				noRecipeFound();
			}
		}

		recipe = cache.getRecipe(recipeID);
		if (recipe == null) {
			// TODO: Get recipe from database

			noRecipeFound();
		}

		// ELSE: We've got our recipe!

		// Setup View
		setContentView(R.layout.activity_view_recipe);

		// Setup ActionBar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.shrimpdumpling); // TODO: Get actual
														// picture from recipe
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

	public void noRecipeFound() {
		// TODO: Toast "No recipe found"
		finish();
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
		public RecipeDescriptionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_recipe_description, container, false);

			TextView recipe_description = (TextView) rootView
					.findViewById(R.id.recipe_description);
			recipe_description.setText(recipe.getDescription());

			TextView recipe_time = (TextView) rootView
					.findViewById(R.id.recipe_time);
			recipe_time.setText(String.valueOf(recipe.getTime()));

			return rootView;
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

			ListView list = (ListView) rootView
					.findViewById(R.id.ingredient_list);
			list.setAdapter(new IngredientAdapter(list.getContext(),
					R.layout.item_ingredient, recipe.getIngredients()));

			return rootView;
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

			ListView list = (ListView) rootView.findViewById(R.id.step_list);
			list.setAdapter(new StepAdapter(list.getContext(),
					R.layout.item_step, recipe.getSteps()));

			return rootView;
		}
	}
}