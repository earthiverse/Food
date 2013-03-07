package com.github.cmput301w13t04.food;

import com.google.gson.Gson;

import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        doStuff();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* TESTING: This function does random stuff. Nothing important. */
    public void doStuff() {
    	//LinearLayout imageView = (LinearLayout) findViewById(R.id.image_scroll);
    	
    	//ImageView image = new ImageView(this);
    	//image.setImageResource(R.drawable.ic_action_emo_wink);
    	//imageView.addView(image);
    	
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
    
    /* TESTING: Manually create and show recipe */
    public void showRecipe(View view) {
    	// TODO: Create Recipe
    	
    	// TODO: Parcel Recipe
    	
    	// Start Viewing Recipe
		Intent intent = new Intent(MainActivity.this, ViewRecipe.class);
		startActivity(intent);
    }
}