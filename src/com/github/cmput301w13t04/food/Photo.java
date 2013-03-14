package com.github.cmput301w13t04.food;

import android.graphics.Bitmap;

/**
 * The object used to store photos for Ingredients and Recipes
 * @author W13T04
 *
 */
public class Photo {
	// The image data for the bitmap photo
	private Bitmap ImageData;
	
	// The description of the photo added by the photographer
	private String description;
	
	// The photographer of this photo
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
	 */
	public String getPhotographer() {
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
	 * @return The description of the photo
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Change the description of the photo
	 * @param description The new description of the phto
	 */
	public void setDescription(String description) {
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

}
