package com.github.cmput301w13t04.food.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A step that is executed during the execution of a recipe
 * @author W13T04
 *
 */
public class Step implements Parcelable {

	// The name of the the step
	private String name;
	
	// The description of the step
	private String description;
	
	// The list of photos associated with the step
//	private ArrayList<Photo> photos;

	/**
	 * Create a step object without photos
	 * @param name The name of the step
	 * @param description The description of the step
	 */
	public Step(String name, String description) {
		this.name = name;
		this.description = description;
//		this.photos = new ArrayList<Photo>();
	}
	
//	/**
//	 * Create a step object with photos
//	 * @param name The name of the step
//	 * @param description The description of the step
//	 * @param photos The photos to attach to the step
//	 */
//	public Step(String name, String description, ArrayList<Photo> photos) {
//		this.name = name;
//		this.description = description;
//		
//		if(photos == null)
//			this.photos = new ArrayList<Photo>();
//		else
//			this.photos = photos;
//	}

	/**
	 * Create an empty step object
	 */
	public Step() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the description of the step
	 * @return The description of the step
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Change the description of the step
	 * @param description The new description of the step
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the name of the step 
	 * @return The name of the step
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change the name of the step
	 * @param name the new name of the step
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * For parcelable
	 */
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(name);
		out.writeString(description);
	}
	
	private Step(Parcel in) {
		name = in.readString();
		description = in.readString();
	}
	
	public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
		public Step createFromParcel(Parcel in) {
			return new Step(in);
		}

		public Step[] newArray(int size) {
			return new Step[size];
		}
	};
	
//	/**
//	 * Add a photo to the step
//	 * @param photo The photo to be added
//	 */
//	public void addPhoto(Photo photo) {
//		this.photos.add(photo);
//	}
//	
//	/**
//	 * Remove a photo from the step
//	 * @param index The position of the photo that will be removed
//	 */
//	public void removePhoto(int index) {
//		this.photos.remove(index);
//	}

}