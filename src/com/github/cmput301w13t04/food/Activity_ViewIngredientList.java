
package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * An activity for adding ingredients to our pantry
 */
public class Activity_ViewIngredientList extends Activity
{
	private ListView listIngredients;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_recipe_ingredients);

		populate();
    
	}

	public void onResume() {
		super.onResume();
		
		// Refresh log
		populate();
	}
	
	  public void populate (){
			Cache cache = new Cache();
			cache.load(this);

			ArrayList<Ingredient> ingredients = cache.getIngredients();


			 listIngredients= ( ListView ) findViewById( R.id.ingredient_list);
			 listIngredients.setAdapter(new IngredientAdapter(this, R.layout.item_ingredient, ingredients));
			 
			 listIngredients.setOnItemClickListener(new OnItemClickListener() {
			      public void onItemClick(AdapterView<?> parent, View view,
			          int position, long id) {

			    	 Intent intent = new Intent(Activity_ViewIngredientList.this, Activity_ViewIngredient.class);
			    	 intent.putExtra("index", position);
			 		 startActivity(intent);

			      }
			    });
	  }
        //TODO: Do something when a list item is clicked
 


}