package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		doStuff();
		testCache();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void doStuff() {
		// LinearLayout imageView = (LinearLayout)
		// findViewById(R.id.image_scroll);

		// ImageView image = new ImageView(this);
		// image.setImageResource(R.drawable.ic_action_emo_wink);
		// imageView.addView(image);
		//
		// Recipe arecipe = new Recipe("Recipe Title", new
		// User("test@test.test"),
		// "I'm just testing this recipe, please ignore it.", 30, null);
		// Log.d("Testing", new Gson().toJson(arecipe));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		// Log.d("Testing", String.valueOf(System.nanoTime()));
		// Log.d("Testing", String.valueOf(System.currentTimeMillis()));

		AccountManager am = AccountManager.get(this);
		Account[] accounts = am.getAccountsByType("com.google");
		Log.d("Testing", accounts[0].name);
	}

	public void testCache() {
		Cache cache = new Cache();
		cache.load(this);
		Ingredient i, i2;
		i = new Ingredient("Onion", "1", "A tasty vegetable", null);
		i2 = new Ingredient("Apple", "2", "A tasty fruit", null);
		Log.d("Testing", i.getName());
		String success = "The cache has ingredients";
		String failure = "add ingredient failed";
		cache.addIngredient(i);
		if (cache.hasIngredients())
			Log.d("Testing", success);
		else
			Log.d("Testing", failure);
		cache.save(this);
		cache.printIngredientList();
		cache.printRecipeList();

		Cache cache2 = new Cache();
		cache2.load(this);

		if (cache2.hasIngredients())
			Log.d("Testing", "cache has been saved");
		else
			Log.d("Testing", failure);

		cache2.addIngredient(i2);
		cache2.save(this);
		cache2.printIngredientList();
		cache2.printRecipeList();
	}

	/* TESTING: Manually create and show recipe */
	public void showRecipe(View view) {
		Cache cache = new Cache();
		
		//Testing: Create fake recipe
		Recipe recipe = new Recipe("Shrimp Shau Mai (Dim Sum Dumpling)", new User("MarraMamba@food.com"), "This is yummy, and pretty easy to put together, the wrapping wll be a little wrinkly. It not only has a shrimp filling, but a whole shrimp in it as well. Came from a recipe book i have that is written in half chinese and half english. Updated as per chef flower's comments in directions.", 38, null, null, null, 1337);
		
		Ingredient shrimp = new Ingredient("Shrimp (Raw, Peeled)", "1/2 lb", null, null);
		Ingredient cookingwine = new Ingredient("Cooking Wine", "1/2 tablespoon", null, null);
		Ingredient sesameoil = new Ingredient("Sesame Oil", "1/2 tablespoon", null, null);
		Ingredient cornstarch = new Ingredient("Cornstarch", "1 teaspoon", null, null);
		Ingredient sugar = new Ingredient("Sugar", "1 teaspoon", null, null);
		Ingredient salt = new Ingredient("Salt", "1/2 teaspoon", null, null);
		Ingredient pepper = new Ingredient("Pepper", "1/4 teaspoon", null, null);
		Ingredient waterchestnut = new Ingredient("Water Chestnut", "1/2 cup chopped", null, null);
		Ingredient coriander = new Ingredient("Coriander", "3 tablespoons", null, null);
		Ingredient groundporkfat = new Ingredient("Ground Pork Fat", "2 tablespoons", null, null);
		Ingredient moreshrimp = new Ingredient("Shrimp (Raw, Peeled, Whole w/ Tail)", "24", null, null);
		Ingredient wontonskins = new Ingredient("Wonton Skins", "48", null, null);
		
		recipe.addIngredient(shrimp);
		recipe.addIngredient(cookingwine);
		recipe.addIngredient(sesameoil);
		recipe.addIngredient(cornstarch);
		recipe.addIngredient(sugar);
		recipe.addIngredient(salt);
		recipe.addIngredient(pepper);
		recipe.addIngredient(waterchestnut);
		recipe.addIngredient(coriander);
		recipe.addIngredient(groundporkfat);
		recipe.addIngredient(moreshrimp);
		recipe.addIngredient(wontonskins);
		
		Step one = new Step("Chop!", "Chop shrimp coarsely.");
		Step two = new Step("Combine!", "Combine wine,sesame oil, cornstarch, sugar, salt and pepper.");
		Step three = new Step("Chop More!", "Chop coriander and combine with chestnuts and lard/pork fat.");
		Step four = new Step("Mix!", "Mix all together with the chopped shrimp.");
		Step five = new Step("Place!", "Place a tablespoon of the filling on top of a wonton skin. Place a shrimp on top of the filling (tail up to the sky).");
		Step six = new Step("Wet, Cover, Wrap!", "Wet the edges of the wonton skin, then cover shrimp with another wonton skin letting the tail stick out between the two skins. Wrap around the filling, making sure the edges are sealed (use little more water if edge is to dry. Do this til all are done.");
		Step seven = new Step("Steam!", "Place the finished shau mai in a steamer for 8 minutes over high heat, serve alone or with your favorite dipping sauce.");
		
		recipe.addStep(one);
		recipe.addStep(two);
		recipe.addStep(three);
		recipe.addStep(four);
		recipe.addStep(five);
		recipe.addStep(six);
		recipe.addStep(seven);
		
		cache.addRecipe(recipe);
		cache.save(view.getContext());

		// Start Viewing Recipe
		Intent intent = new Intent(MainActivity.this, ViewRecipe.class);
		intent.putExtra("RECIPE_ID", (long) 1337);
		startActivity(intent);
	}
}