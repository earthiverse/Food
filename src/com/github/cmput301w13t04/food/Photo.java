package com.github.cmput301w13t04.food;

import android.graphics.Bitmap;

public class Photo {
	private Bitmap ImageData;
	private String description;
	private String photographer;
	/*Constructor*/
	public Photo(Bitmap ImageData, String, description, String photographer){
	  this.ImageData = ImageData;
	  this.description = description;
	  this.photographer = photographer;
	}
	/*Acessor Methods*/
	public String getPhotographer() {
    return photographer;
  }

	
  public void setTitle(String Photographer) {
    this.photographer = Photographer;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public Bitmap getPhoto(){
    return photo;
  }
  public void setPhoto(Bitmap img){
    this.ImageData = img;
  }
  
 
	
	
	
}
	