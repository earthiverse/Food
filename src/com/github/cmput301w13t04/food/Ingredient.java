package com.github.cmput301w13t04.food;

/**
 * An object used to represent an ingredient that can be used in a recipe
 * @author W13T04
 *
 */
public class Ingredient {
	
	// The name of the ingredient
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	// The quantity of ingredients in the desired measurement unit
	/**
	 * @uml.property  name="quantity"
	 */
	private String quantity;
	
	// A brief description of the ingredient (optional)
	/**
	 * @uml.property  name="description"
	 */
	private String description;
	
	// The photo attached to the ingredient (optional)
	/**
	 * @uml.property  name="photo"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Photo photo;

	/**
	 * Create an Ingredient object with the following parameters
	 * @param name The name of the ingredient
	 * @param quantity The quantity in desired unit
	 * @param description A  brief description of the ingredient
	 * @param photo A photo that is attached t the ingredient
	 */
	public Ingredient(String name, String quantity, String description, Photo photo) {
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.photo = photo;
	}

	/**
	 * Get the name of the ingredient
	 * @return  The name of the ingredient
	 * @uml.property  name="name"
	 */
	public String getName() 
	
	{
		return name;
	}

	/**
	 * Change the name of an ingredient
	 * @param Name  the new name of the ingredient
	 * @uml.property  name="name"
	 */
	public void setName(String Name) 
	
	{
		this.name = Name;
	}

	/**
	 * Get a description of the ingredient
	 * @return  The description of the ingredient
	 * @uml.property  name="description"
	 */
	public String getDescription() 
	
	{
		return description;
	}

	/**
	 * Change the description of the ingredient
	 * @param description  The new description of the ingredient
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) 
	
	{
		this.description = description;
	}

	/**
	 * Get the quantity of the ingredient in a string that also contains the unit of measurement
	 * @return  A string containing the quantity of the ingredient
	 * @uml.property  name="quantity"
	 */
	public String getQuantity() 
	
	{
		return quantity;
	}

	/**
	 * Change the quantity of the ingredient
	 * @param Quantity  The  new desired quantity of the ingredient
	 * @uml.property  name="quantity"
	 */
	public void setQuantity(String Quantity) 
	
	{
		this.quantity = Quantity;
	}

	/**
	 * Get the photo that is attached to the ingredient
	 * @return  The photo that is attached to the ingredient
	 * @uml.property  name="photo"
	 */
	public Photo getPhoto() 
	
	{
		return photo;
	}

	/**
	 * Change the photo that is attached to the ingredient
	 * @param photo  The new photo that will be attached to the ingredient
	 * @uml.property  name="photo"
	 */
	public void setPhoto(Photo photo) 
	
	{
		this.photo = photo;
	}

	/**
	 * @uml.property  name="recipe"
	 * @uml.associationEnd  inverse="ingredient:com.github.cmput301w13t04.food.Recipe"
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