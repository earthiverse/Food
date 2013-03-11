
package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ListView;

/*
 * An activity for adding ingredients to our pantry
 */
public class Activity_ViewIngredientList extends Activity
{
	/**
	 * @uml.property  name="listIngredients"
	 * @uml.associationEnd  
	 */
	private ListView listIngredients;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_recipe_ingredients);

		Cache cache = new Cache();
		cache.load(this);

		ArrayList<Ingredient> ingredients = cache.getIngredients();


		 listIngredients= ( ListView ) findViewById( R.id.ingredient_list);
		 listIngredients.setAdapter(new IngredientAdapter(this, R.layout.item_ingredient, ingredients));
    
	}
	/**
	 * @uml.property  name="activity_AddIngredient"
	 * @uml.associationEnd  inverse="activity_ViewIngredientList:com.github.cmput301w13t04.food.Activity_AddIngredient"
	 */
	private Activity_AddIngredient activity_AddIngredient;
	/**
	 * Getter of the property <tt>activity_AddIngredient</tt>
	 * @return  Returns the activity_AddIngredient.
	 * @uml.property  name="activity_AddIngredient"
	 */
	public Activity_AddIngredient getActivity_AddIngredient()
	
	
	{
	
		return activity_AddIngredient;
	}
	/**
	 * Setter of the property <tt>activity_AddIngredient</tt>
	 * @param activity_AddIngredient  The activity_AddIngredient to set.
	 * @uml.property  name="activity_AddIngredient"
	 */
	public void setActivity_AddIngredient(
			Activity_AddIngredient activity_AddIngredient)
	
	
	{
	
		this.activity_AddIngredient = activity_AddIngredient;
	}
	/**
	 * @uml.property  name="activity_ViewIngredient"
	 * @uml.associationEnd  inverse="activity_ViewIngredientList:com.github.cmput301w13t04.food.Activity_ViewIngredient"
	 */
	private Activity_ViewIngredient activity_ViewIngredient;
	/**
	 * Getter of the property <tt>activity_ViewIngredient</tt>
	 * @return  Returns the activity_ViewIngredient.
	 * @uml.property  name="activity_ViewIngredient"
	 */
	public Activity_ViewIngredient getActivity_ViewIngredient()
	
	{
	
		return activity_ViewIngredient;
	}
	/**
	 * Setter of the property <tt>activity_ViewIngredient</tt>
	 * @param activity_ViewIngredient  The activity_ViewIngredient to set.
	 * @uml.property  name="activity_ViewIngredient"
	 */
	public void setActivity_ViewIngredient(
			Activity_ViewIngredient activity_ViewIngredient)
	
	{
	
		this.activity_ViewIngredient = activity_ViewIngredient;
	}
	/**
	 * @uml.property  name="cache"
	 * @uml.associationEnd  inverse="activity_ViewIngredientList:com.github.cmput301w13t04.food.Cache"
	 */
	private Cache cache;
	/**
	 * Getter of the property <tt>cache</tt>
	 * @return  Returns the cache.
	 * @uml.property  name="cache"
	 */
	public Cache getCache()
	{

		return cache;
	}
	/**
	 * Setter of the property <tt>cache</tt>
	 * @param cache  The cache to set.
	 * @uml.property  name="cache"
	 */
	public void setCache(Cache cache)
	{

		this.cache = cache;
	}

        //TODO: Do something when a list item is clicked
 


}