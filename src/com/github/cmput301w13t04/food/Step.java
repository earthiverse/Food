package com.github.cmput301w13t04.food;

import java.util.ArrayList;

/**
 * A step that is executed during the execution of a recipe
 * @author W13T04
 *
 */
public class Step {

	// The name of the the step
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	// The description of the step
	/**
	 * @uml.property  name="description"
	 */
	private String description;
	
	// The list of photos associated with the step
	/**
	 * @uml.property  name="photos"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.github.cmput301w13t04.food.Photo"
	 */
	private ArrayList<Photo> photos;

	/**
	 * Create a step object without photos
	 * @param name The name of the step
	 * @param description The description of the step
	 */
	public Step(String name, String description) {
		this.name = name;
		this.description = description;
		this.photos = new ArrayList<Photo>();
	}
	
	/**
	 * Create a step object with photos
	 * @param name The name of the step
	 * @param description The description of the step
	 * @param photos The photos to attach to the step
	 */
	public Step(String name, String description, ArrayList<Photo> photos) {
		this.name = name;
		this.description = description;
		
		if(photos == null)
			this.photos = new ArrayList<Photo>();
		else
			this.photos = photos;
	}

	/**
	 * Get the description of the step
	 * @return  The description of the step
	 * @uml.property  name="description"
	 */
	public String getDescription() 
	
	
	
	{
		return description;
	}

	/**
	 * Change the description of the step
	 * @param description  The new description of the step
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) 
	
	
	
	{
		this.description = description;
	}

	/**
	 * Get the name of the step 
	 * @return  The name of the step
	 * @uml.property  name="name"
	 */
	public String getName() 
	
	
	
	{
		return name;
	}

	/**
	 * Change the name of the step
	 * @param name  the new name of the step
	 * @uml.property  name="name"
	 */
	public void setName(String name) 
	
	
	
	{
		this.name = name;
	}
	
	/**
	 * Add a photo to the step
	 * @param photo The photo to be added
	 */
	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}
	
	/**
	 * Remove a photo from the step
	 * @param index The position of the photo that will be removed
	 */
	public void removePhoto(int index) {
		this.photos.remove(index);
	}

	/** 
	 * @uml.property name="recipe"
	 * @uml.associationEnd aggregation="shared" inverse="step:com.github.cmput301w13t04.food.Recipe"
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
	 * @uml.property  name="recipe1"
	 * @uml.associationEnd  inverse="step1:com.github.cmput301w13t04.food.Recipe"
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

}
