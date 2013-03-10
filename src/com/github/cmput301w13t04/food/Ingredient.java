package com.github.cmput301w13t04.food;

/**
 * An object used to represent an ingredient that can be used in a recipe
 * @author W13T04
 *
 */
public class Ingredient {
	
	// The name of the ingredient
	private String name;
	
	// The quantity of ingredients in the desired measurement unit
	private String quantity;
	
	// A brief description of the ingredient (optional)
	private String description;
	
	// The photo attached to the ingredient (optional)
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
	 * @return The name of the ingredient
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change the name of an ingredient
	 * @param Name the new name of the ingredient
	 */
	public void setName(String Name) {
		this.name = Name;
	}

	/**
	 * Get a description of the ingredient
	 * @return The description of the ingredient
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *  Change the description of the ingredient
	 * @param description The new description of the ingredient
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the quantity of the ingredient in a string that also contains
	 * the unit of measurement
	 * @return A string containing the quantity of the ingredient
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Change the quantity of the ingredient
	 * @param Quantity The  new desired quantity of the ingredient
	 */
	public void setQuantity(String Quantity) {
		this.quantity = Quantity;
	}

	/**
	 * Get the photo that is attached to the ingredient
	 * @return The photo that is attached to the ingredient
	 */
	public Photo getPhoto() {
		return photo;
	}

	/**
	 * Change the photo that is attached to the ingredient
	 * @param photo The new photo that will be attached to the ingredient
	 */
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}