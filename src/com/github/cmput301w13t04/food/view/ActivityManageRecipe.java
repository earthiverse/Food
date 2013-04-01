package com.github.cmput301w13t04.food.view;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.R.layout;
import com.github.cmput301w13t04.food.R.menu;
import com.github.cmput301w13t04.food.controller.Cache;
import com.github.cmput301w13t04.food.controller.Database;
import com.github.cmput301w13t04.food.controller.IngredientAdapter;
import com.github.cmput301w13t04.food.controller.StepAdapter;
import com.github.cmput301w13t04.food.model.Ingredient;
import com.github.cmput301w13t04.food.model.Photo;
import com.github.cmput301w13t04.food.model.Recipe;
import com.github.cmput301w13t04.food.model.Step;
import com.github.cmput301w13t04.food.model.User;

import java.util.ArrayList;
import java.util.Locale;

import android.accounts.Account;
import android.accounts.AccountManager;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/* A class for viewing a recipe in our UI */
public class ActivityManageRecipe extends FragmentActivity implements
		ActionBar.TabListener {

	private static final int ACTION_MANAGE_INGREDIENT = 1;
	private static final int ACTION_ADD_INGREDIENT = 2;
	private static final int ACTION_MANAGE_STEP = 3;
	private static final int ACTION_ADD_STEP = 4;
	private static final int TAKE_PHOTO = 5;

	public static Cache cache = new Cache();
	public static Recipe recipe;
	public static boolean isNewRecipe;

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load Cache
		cache.load(this);

		cache.printRecipeList();

		// Load Recipe from ID
		long recipeID = this.getIntent().getLongExtra("RECIPE_ID", -1);

		if (recipeID == -1) {
			isNewRecipe = true;

			// Get current user as the author
			AccountManager am = AccountManager.get(this);
			Account[] accounts = am.getAccountsByType("com.google");
			User author = new User(accounts[0].name);

			// Create blank recipe to fill out
			recipe = new Recipe(null, author, null, 0);
		} else {
			isNewRecipe = false;

			recipe = cache.getRecipe(recipeID);
		}

		// Setup View
		setContentView(R.layout.activity_view_recipe);

		// Setup ActionBar
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_manage_recipe, menu);
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

	public void updateRecipe(MenuItem menuitem) {
		if (isNewRecipe) {
			cache.addRecipe(recipe);
		}

		cache.save(this);

		finish();
	}
	
	public void publishRecipe(MenuItem menuitem) {
		Database database = new Database();
		database.publishRecipe(recipe);
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

		private EditText recipe_description;
		private EditText recipe_time;
		private EditText recipe_name;
		private ImageView image;

		private ArrayList<Photo> photos;
		private int position = 0;

		public RecipeDescriptionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(
					R.layout.fragment_manage_recipe_description, container,
					false);

			// Recipe Name
			recipe_name = (EditText) rootView.findViewById(R.id.recipe_name);
			recipe_name.setText(recipe.getTitle());
			recipe_name.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
					recipe.setTitle(s.toString());
				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}
			});

			recipe_description = (EditText) rootView
					.findViewById(R.id.recipe_description);
			recipe_description.setText(recipe.getDescription());
			recipe_description.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
					recipe.setDescription(s.toString());
				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}
			});

			recipe_time = (EditText) rootView.findViewById(R.id.recipe_time);

			// Fill in time if it's defined in the recipe
			if (recipe.getTime() > 0) {
				recipe_time.setText(String.valueOf(recipe.getTime()));
			}

			recipe_time.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
					try {
						recipe.setTime(Integer.valueOf(s.toString()));
					} catch (Exception e) {
						// Not a valid integer
					}
					// recipe.setTime(Integer.valueOf(recipe_description.getText()
					// .toString()));
				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}
			});

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

			// Take a photo
			Button addPhoto = (Button) rootView
					.findViewById(R.id.button_add_photo);
			addPhoto.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getActivity().getBaseContext(),
							ActivityTakePhoto.class);
					startActivityForResult(intent, TAKE_PHOTO);
				}
			});

			// Remove photo
			Button removePhoto = (Button) rootView
					.findViewById(R.id.button_delete_photo);
			removePhoto.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					try {
						recipe.removePhoto(position);
						Photo photo = recipe.getPhoto(0);
						if(photo != null) {
							// Still has a photo, display it
							image.setImageURI(Uri.parse(photo.getPath()));
						} else {
							// No photos in recipe, display placeholder
							image.setImageResource(R.drawable.photo);
						}
					} catch(Exception e) {
						// No photos left on recipe to remove
					}
					position = 0;
				}
			});

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();

			View view = getView();

			photos = recipe.getPhotos();

			// Recipe Photos
			try {
				Photo photo = photos.get(position);
				image = (ImageView) view.findViewById(R.id.recipe_image);
				image.setImageURI(Uri.parse(photo.getPath()));
			} catch (Exception e) {
				// No photos :c
			}

		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if (requestCode == TAKE_PHOTO) {
				if (resultCode == RESULT_OK) {
					String path = data.getStringExtra("path");
					String desc = data.getStringExtra("desc");
					// get User instead of null
					Photo p = new Photo(path, desc, null);

					recipe.addPhoto(p);

					if (p != null) {
						ImageView photoView = (ImageView) getView()
								.findViewById(R.id.recipe_image);
						photoView.setImageURI(Uri.parse(p.getPath()));
						position = recipe.getPhotos().size() - 1;
					}
				}
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
					R.layout.fragment_manage_recipe_ingredients, container,
					false);

			Button addIngredient = (Button) rootView
					.findViewById(R.id.button_add_ingredient);
			addIngredient.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getActivity().getBaseContext(),
							ActivityManageIngredient.class);
					startActivityForResult(intent, ACTION_ADD_INGREDIENT);
				}
			});

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();

			View view = this.getView();
			ListView list = (ListView) view.findViewById(R.id.ingredient_list);
			list.setAdapter(new IngredientAdapter(list.getContext(),
					R.layout.item_ingredient, recipe.getIngredients()));

			list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// View ingredient on item click.
					Intent intent = new Intent(getActivity().getBaseContext(),
							ActivityManageIngredient.class);

					intent.putExtra("INGREDIENT",
							recipe.getIngredient(position));
					intent.putExtra("POSITION", position);
					startActivityForResult(intent, ACTION_MANAGE_INGREDIENT);
				}
			});
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			switch (requestCode) {
			case ACTION_MANAGE_INGREDIENT: {
				if (resultCode == RESULT_OK) {
					// Update Ingredient
					int position = data.getIntExtra("POSITION", -1);
					Ingredient ingredient = data
							.getParcelableExtra("INGREDIENT");

					if (position != -1) {
						recipe.updateIngredient(ingredient, position);
					} else {
						// TODO: SOMETHING WENT WRONG.
					}
				} else if (resultCode == ActivityManageIngredient.RESULT_DELETE) {
					// Remove Ingredient
					int position = data.getIntExtra("POSITION", -1);

					if (position != -1) {
						recipe.removeIngredient(position);
					}
				}
				break;
			}
			case ACTION_ADD_INGREDIENT: {
				if (resultCode == RESULT_OK) {
					// Add ingredient
					Ingredient ingredient = data
							.getParcelableExtra("INGREDIENT");
					recipe.addIngredient(ingredient);
				}
				break;
			}
			}
			;
		}
	}

	/* Step Fragment */
	public static class RecipeStepFragment extends Fragment {
		public RecipeStepFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_manage_recipe_steps, container, false);

			Button addStep = (Button) rootView
					.findViewById(R.id.button_add_step);
			addStep.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getActivity().getBaseContext(),
							ActivityManageStep.class);
					intent.putExtra("NEW_POSITION", recipe.stepCount());
					startActivityForResult(intent, ACTION_ADD_STEP);
				}
			});

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();

			View view = getView();

			ListView list = (ListView) view.findViewById(R.id.step_list);
			list.setAdapter(new StepAdapter(list.getContext(),
					R.layout.item_step, recipe.getSteps()));
			list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// View ingredient on item click.
					Intent intent = new Intent(getActivity().getBaseContext(),
							ActivityManageStep.class);

					intent.putExtra("STEP", recipe.getStep(position));
					intent.putExtra("POSITION", position);
					startActivityForResult(intent, ACTION_MANAGE_STEP);
				}
			});
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			switch (requestCode) {
			case ACTION_MANAGE_STEP: {
				if (resultCode == RESULT_OK) {
					// Update Step
					int position = data.getIntExtra("POSITION", -1);
					Step step = data.getParcelableExtra("STEP");

					if (position != -1) {
						recipe.updateStep(step, position);
					} else {
						// TODO: SOMETHING WENT WRONG.
					}
				} else if (resultCode == ActivityManageStep.RESULT_DELETE) {
					// Remove Step
					int position = data.getIntExtra("POSITION", -1);

					if (position != -1) {
						recipe.removeStep(position);
					}
				}
				break;
			}
			case ACTION_ADD_STEP: {
				if (resultCode == RESULT_OK) {
					// Add step

					Step step = data.getParcelableExtra("STEP");
					recipe.addStep(step);

				}
				break;
			}
			}
			;
		}
	}
}