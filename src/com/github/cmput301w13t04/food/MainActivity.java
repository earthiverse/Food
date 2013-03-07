package com.github.cmput301w13t04.food;

import com.google.gson.Gson;

import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		testCache();
		//doStuff();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void doStuff() {
		LinearLayout imageView = (LinearLayout) findViewById(R.id.image_scroll);

		ImageView image = new ImageView(this);
		image.setImageResource(R.drawable.ic_action_emo_wink);
		imageView.addView(image);

		Recipe arecipe = new Recipe("Recipe Title", new User("test@test.test"), "I'm just testing this recipe, please ignore it.", 30, null);
		Log.d("Testing", new Gson().toJson(arecipe));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));
		Log.d("Testing", String.valueOf(System.nanoTime()));
		Log.d("Testing", String.valueOf(System.currentTimeMillis()));

		AccountManager am = AccountManager.get(this);
		Account[] accounts = am.getAccountsByType("com.google");
		Log.d("Testing", accounts[0].name);
	}

	public void testCache(){
		Cache cache = new Cache(this);
		Ingredient i;
		i = new Ingredient("Onion", 1 , "A tasty, savoury vegetable", null);
		Log.d("Testing", i.getName());
		String success = "The cache has ingredients";
		String failure = "add inredient failed";
		cache.addIngredient(i);
		if(cache.hasIngredients())
			Log.d("Testing", success);
		else
			Log.d("Testing", failure);
		cache.save();
		//cache = new Cache(this);
		if(cache.hasIngredients())
			Log.d("Testing", success);
		else
			Log.d("Testing", failure);
	}


}