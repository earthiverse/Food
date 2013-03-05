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
}