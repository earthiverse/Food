package com.github.cmput301w13t04.food;

import com.google.gson.Gson;

/**
 * An object used for communication with the online database
 * @author W13T04
 *
 */
public class Database {

	public Database() {
		// TODO: Figure out how we're doing things.
	}

	/**
	 * Publish a recipe to the online database
	 * @param recipe
	 */
	void publishRecipe(Recipe recipe) {
		String post = new Gson().toJson(recipe);
		// TODO: Post this JSON to the server
	}

	/**
	 * @uml.property  name="recipe"
	 * @uml.associationEnd  inverse="database:com.github.cmput301w13t04.food.Recipe"
	 */
	private Recipe recipe;

	/**
	 * Getter of the property <tt>recipe</tt>
	 * @return  Returns the recipe.
	 * @uml.property  name="recipe"
	 */
	public Recipe getRecipe()
	
	
	
	
	{
	
		return recipe;
	}

	/**
	 * Setter of the property <tt>recipe</tt>
	 * @param recipe  The recipe to set.
	 * @uml.property  name="recipe"
	 */
	public void setRecipe(Recipe recipe)
	
	
	
	
	{
	
		this.recipe = recipe;
	}

	/** 
	 * @uml.property name="recipe1"
	 * @uml.associationEnd aggregation="shared" inverse="database1:com.github.cmput301w13t04.food.Recipe"
	 */
	private Recipe recipe1;

	/** 
	 * Getter of the property <tt>recipe1</tt>
	 * @return  Returns the recipe1.
	 * @uml.property  name="recipe1"
	 */
	public Recipe getRecipe1()
	
	
	
	{
		return recipe1;
	}

	/** 
	 * Setter of the property <tt>recipe1</tt>
	 * @param recipe1  The recipe1 to set.
	 * @uml.property  name="recipe1"
	 */
	public void setRecipe1(Recipe recipe1)
	
	
	
	{
		this.recipe1 = recipe1;
	}

	/**
	 * @uml.property  name="activity_SearchRecipe"
	 * @uml.associationEnd  inverse="database:com.github.cmput301w13t04.food.Activity_SearchRecipe"
	 */
	private Activity_SearchRecipe activity_SearchRecipe;

	/**
	 * Getter of the property <tt>activity_SearchRecipe</tt>
	 * @return  Returns the activity_SearchRecipe.
	 * @uml.property  name="activity_SearchRecipe"
	 */
	public Activity_SearchRecipe getActivity_SearchRecipe()
	
	{
	
		return activity_SearchRecipe;
	}

	/**
	 * Setter of the property <tt>activity_SearchRecipe</tt>
	 * @param activity_SearchRecipe  The activity_SearchRecipe to set.
	 * @uml.property  name="activity_SearchRecipe"
	 */
	public void setActivity_SearchRecipe(
			Activity_SearchRecipe activity_SearchRecipe)
	
	{
	
		this.activity_SearchRecipe = activity_SearchRecipe;
	}

	/**
	 * @uml.property  name="activity_ViewRecipe"
	 * @uml.associationEnd  inverse="database:com.github.cmput301w13t04.food.Activity_ViewRecipe"
	 */
	private Activity_ViewRecipe activity_ViewRecipe;

	/**
	 * Getter of the property <tt>activity_ViewRecipe</tt>
	 * @return  Returns the activity_ViewRecipe.
	 * @uml.property  name="activity_ViewRecipe"
	 */
	public Activity_ViewRecipe getActivity_ViewRecipe()
	{

		return activity_ViewRecipe;
	}

	/**
	 * Setter of the property <tt>activity_ViewRecipe</tt>
	 * @param activity_ViewRecipe  The activity_ViewRecipe to set.
	 * @uml.property  name="activity_ViewRecipe"
	 */
	public void setActivity_ViewRecipe(Activity_ViewRecipe activity_ViewRecipe)
	{

		this.activity_ViewRecipe = activity_ViewRecipe;
	}
}
