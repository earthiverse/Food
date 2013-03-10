package com.github.cmput301w13t04.food;

import java.util.ArrayList;

/**
 * An object that represents a recipe that can be followed to make 
 * a meal.
 * @author W13T04
 *
 */
public class Recipe {

	// the title of the recipe
	private String title;

	// the author of the recipe
	private User author;

	// the description of the Recipe
	private String description;

	// The time the recipe takes to make
	private int time;

	// Photos of the recipe
	private ArrayList<Photo> photos;

	// The ingredients needed in the recipe
	private ArrayList<Ingredient> ingredients;

	// The list of steps needed to complete the recipe
	private ArrayList<Step> steps;

	// The unique recipe id
	private long id;

	/**
	 * Create a new recipe object with, id will be generated
	 * 
	 * @param title
	 *            The title of the recipe
	 * @param author
	 *            The user who created the recipe
	 * @param description
	 *            The description of the recipe
	 * @param time
	 *            The time the the recipe takes to execute
	 */
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

	/**
	 * Create a new recipe object with, id is predefined
	 * 
	 * @param title
	 *            The title of the recipe
	 * @param author
	 *            The user who created the recipe
	 * @param description
	 *            The description of the recipe
	 * @param time
	 *            The time the the recipe takes to execute
	 */
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
	 * @return a string containing the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set a new title for the Recipe object
	 * @param title The new title of the Recipe object
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the description of the recipe
	 * @return The descrition of the recipe
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Change the description of the recipe
	 * @param description The new description of the recipe
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the time it takes to complete the recipe
	 * @return The time it to complete the recipe
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Change the time it takes to complete the recipe
	 * @param time The revised time that it takes to complete the recipe
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * Get the user who created the recipe
	 * @return The user who created the recipe
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Change the user who created the recipe
	 * @param author The new user who appears as author of the recipe 
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * Add a picture to the recipe
	 * @param photo The picture to be added to the recipe
	 */
	public void addPicture(Photo photo) {
		photos.add(photo);
	}

	/**
	 * Remove a picture from the recipe
	 * @param index The position in the ArrayList of the photo to be removed
	 */
	public void removePicture(int index) {
		photos.remove(index);
	}

	/**
	 * Get the list of ingredients used in the recipe
	 * @return The list of ingredients used in the recipe
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Add an ingredient to the recipe's ingredient list
	 * @param ingredient The ingredient to be added
	 */
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	/**
	 * Add a step to the recipe
	 * @param step The step to be added
	 */
	public void addStep(Step step) {
		steps.add(step);
	}

	/**
	 * Get the number of steps in the recipe
	 * @return The number of steps in the recipe
	 */
	public int stepCount() {
		return steps.size();
	}

	/**
	 * Remove a step from the recipe
	 * @param step The step to be removed
	 */
	public void removeStep(Step step) {
		steps.remove(step);
	}

	/**
	 * Get the id of the recipe
	 * @return The Id of the recipe
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Get the list of steps in the recipe
	 * @return The list of steps
	 */
	public ArrayList<Step> getSteps() {
		return this.steps;
	}
}
