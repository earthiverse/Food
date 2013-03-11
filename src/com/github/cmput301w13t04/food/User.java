package com.github.cmput301w13t04.food;

/**
 * A simple user object that is useful for adding a signature when creating 
 * something like a recipe.
 * @author W13T04
 *
 */
public class User {
	/**
	 * @uml.property  name="email"
	 */
	private String email;

	/**
	 * Create a new user
	 * @param email The email of the new User
	 */
	public User(String email) {
		this.email = email;
	}

	/**
	 * Get the new Username of the User
	 */
	public String getUsername() {
		return email;
	}

	/**
	 * @uml.property  name="recipe"
	 * @uml.associationEnd  inverse="user:com.github.cmput301w13t04.food.Recipe"
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
}
