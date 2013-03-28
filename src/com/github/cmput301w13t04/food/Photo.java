package com.github.cmput301w13t04.food;

import java.io.IOException;

import android.util.Log;


/**
 * The object used to store photos for Ingredients and Recipes
 * @author W13T04
 *
 */

public class Photo {
	// The image data for the bitmap photo
	private String path;
	
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
	public Photo(String path, String description, String photographer) {
		this.path = path;
		//TODO:REMOVE YO SHIT
		Log.d("REALP",path);
		imgurController ic = new imgurController(path);
		try
		{
			ic.post();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * @param description The new description of the photo
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the image's path 
	 * @return the path of the photo
	 */
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
