package com.github.cmput301w13t04.food.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The object used to store photos for Ingredients and Recipes
 * @author W13T04
 *
 */

public class Photo implements Parcelable {
	// The image data for the bitmap photo
	private String path;
	
	// The description of the photo added by the photographer
	private String description;
	
	// The photographer of this photo
	private User photographer;

	/**
	 * Create an Photo object for use in a recipe of ingredient
	 * @param ImageData The image data for the photo
	 * @param description The description added to the photo by the photographer
	 * @param photographer The user who took the photo
	 */
	public Photo(String path, String description, User photographer) {
		this.path = path;
		this.description = description;
		this.photographer = photographer;
	}

	/**
	 * Get the photographer of the photo
	 * @return
	 */
	public User getPhotographer() {
		return photographer;
	}

	/**
	 * Set the name of the photographer 
	 * @param Photographer The new name to be set
	 */
	public void setPhotographer(User Photographer) {
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
	
	/**
	 * For parcelable
	 */
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(path);
		out.writeString(description);
		out.writeParcelable(photographer, 0);
	}
	
    private Photo(Parcel in) {
        path = in.readString();
        description = in.readString();
        photographer = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<Photo> CREATOR
            = new Parcelable.Creator<Photo>() {
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

}