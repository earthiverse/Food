package com.github.cmput301w13t04.food;

import java.util.ArrayList;

public class Recipe {
	private String title; // the title of the recipe
	private User author; // the author of the recipe
	private String description;
	private int time;
	private ArrayList<Photo> photos;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Step> steps;
	private long id;

	public Recipe(String title, User author, String description, int time) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.time = time;
		this.id = System.nanoTime();

		this.steps = new ArrayList<Step>();
		this.ingredients = new ArrayList<Ingredient>();
		this.photos = new ArrayList<Photo>();
	}

	public Recipe(String title, User author, String description, int time,
			ArrayList<Step> steps, ArrayList<Ingredient> ingredients,
			ArrayList<Photo> photos, long id) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.time = time;
		this.id = id;

		if (steps == null)
			this.steps = new ArrayList<Step>();
		else
			this.steps = steps;

		if (ingredients == null)
			this.ingredients = new ArrayList<Ingredient>();
		else
			this.ingredients = ingredients;

		if (photos == null)
			this.photos = new ArrayList<Photo>();
		else
			this.photos = photos;
	}

	/**
	 * Get the title of the Recipe object
	 * 
	 * @return a string containing the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set a new title for the Recipe object
	 * 
	 * @param title
	 *            The new title of the Recipe object
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void addPicture(Photo photo) {
		photos.add(photo);
	}

	public void removePicture(int index) {
		photos.remove(index);
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	public void addStep(Step step) {
		steps.add(step);
	}

	public int stepCount() {
		return steps.size();
	}

	public void removeStep(Step step) {
		steps.remove(step);
	}

	public long getId() {
		return this.id;
	}

	public ArrayList<Step> getSteps() {
		return this.steps;
	}
}
