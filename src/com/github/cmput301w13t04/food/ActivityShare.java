package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class ActivityShare extends Activity {
	public static Recipe recipe;
	public static ArrayList<Step> steps;
	public static Step step;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		Intent intent = new Intent(Intent.ACTION_SEND);
		Cache cache = new Cache();
		cache.load(this);
		StringBuffer body = new StringBuffer("");
		
		
		recipe = cache.getRecipe(1337);
		if(recipe == null){
			Log.d("No recipe found", "recipe null");
			finish();
		}
		else{
			steps = recipe.getSteps();
			int size = steps.size();
			for(int i = 0; i<size; i++){
				step = steps.get(i);
				body.append(step.getName()+":\n");
				body.append(step.getDescription()+"\n");
			}
		}
		
	    intent.setType("message/rfc822");
	    intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "address@example.com" });
	    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the mail");
	    intent.putExtra(Intent.EXTRA_TEXT, body.toString());
	    startActivity(Intent.createChooser(intent, "Share Via:"));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_share, menu);
		return true;
	}

}
