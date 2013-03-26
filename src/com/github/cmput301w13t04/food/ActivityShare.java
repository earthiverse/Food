package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.Menu;

public class ActivityShare extends Activity {
	public static Recipe recipe;
	public static ArrayList<Step> steps;
	public static Step step;
	public static Ingredient ingredients;
	public static ArrayList<Ingredient> ingredient;
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
			body.append("Title:  " + recipe.getTitle()+"<br>");
			body.append("Author:  " + recipe.getAuthor()+ "<br>");
			
			body.append("Time:  " + recipe.getTime() + "mins <br>");
			ingredient = recipe.getIngredients();
			int size = ingredient.size();
			steps = recipe.getSteps();
			body.append("Required Ingredients: ");
			for(int i = 0; i<size; i++){
				ingredients = ingredient.get(i);
				body.append(ingredients.getName()+", ");
				
				
			}
			size = steps.size();
			for(int i = 0; i<size; i++){
				step = steps.get(i);
				
				body.append(i+1 + ". "+step.getName()+"<br>");
				body.append(step.getDescription()+"<br>");
				
			}
		}
		String link_val = "food://com.github.cmput301w13t04.food.ActivityViewRecipe?X=1337";
		
		String link = "<a href=\"" + link_val + "\">" + "LINK TO APP ReasonableRecipe"+ "</a>";
		body.append(link);
		intent.setType("text/html");
	    intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "gongal@ualberta.ca" });
	    intent.putExtra(Intent.EXTRA_SUBJECT, "ReasonableRecipe: " + recipe.getTitle());
	    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body.toString()));  
	    startActivity(Intent.createChooser(intent, "Share Via:"));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_share, menu);
		return true;
	}

}
