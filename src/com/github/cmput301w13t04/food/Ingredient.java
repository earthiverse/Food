package com.github.cmput301w13t04.food;

public class Ingredient {
	private String name;
	private int quantity;
	private String description;
	private Photo photo;
	
	public Ingredient(String name, int quantity, String description, Photo photo) {
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.photo = photo;
	}
	 public String getName() {
	    return name;
	  }

	  public void setName(String Name) {
	    this.name = Name;
	  }
	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }
	  public int getQuantity(){
	    return quantity;
	  }
	  public void setQuantity(int Quantity){
	    this.quantity = Quantity;
	  }
	  
	  public Photo getPhoto(){
	    return photo;
	  }
	  public void setPhoto(Photo photo){
	    this.photo = photo;
	  }

}