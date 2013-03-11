package com.github.cmput301w13t04.food;

import android.graphics.Bitmap;

/**
 * The object used to store photos for Ingredients and Recipes
 * @author W13T04
 *
 */
public class Photo {
	
	// The image data for the bitmap photo
	/**
	 * @uml.property  name="imageData"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Bitmap ImageData;
	
	// The description of the photo added by the photographer
	/**
	 * @uml.property  name="description"
	 */
	private String description;
	
	// The photographer of this photo
	/**
	 * @uml.property  name="photographer"
	 */
	private String photographer;

	/**
	 * Create an Photo object for use in a recipe of ingredient
	 * @param ImageData The image data for the photo
	 * @param description The description added to the photo by the photographer
	 * @param photographer The user who took the photo
	 */
	public Photo(Bitmap ImageData, String description, String photographer) {
		this.ImageData = ImageData;
		this.description = description;
		this.photographer = photographer;
	}

	/**
	 * Get the photographer of the photo
	 * @return
	 * @uml.property  name="photographer"
	 */
	public String getPhotographer() 
	{
		return photographer;
	}

	/**
	 * Set the name of the photographer 
	 * @param Photographer The new name to be set
	 */
	public void setTitle(String Photographer) {
		this.photographer = Photographer;
	}

	/**
	 * Get the description of the photo
	 * @return  The description of the photo
	 * @uml.property  name="description"
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * Change the description of the photo
	 * @param description  The new description of the phto
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	/**
	 * Get the bitmap image data for the photo
	 * @return he bitmap image data for the photo
	 */
	public Bitmap getPhoto() {
		return this.ImageData;
	}

	/**
	 * Change the photo object
	 * @param img The new bitmap image data
	 */
	public void setPhoto(Bitmap img) {
		this.ImageData = img;
	}

	/**
	 * @uml.property  name="recipe"
	 * @uml.associationEnd  inverse="photo:com.github.cmput301w13t04.food.Recipe"
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
