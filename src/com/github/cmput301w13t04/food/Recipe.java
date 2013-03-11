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
	/**
	 * @uml.property  name="title"
	 */
	private String title;

	// the author of the recipe
	/**
	 * @uml.property  name="author"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private User author;

	// the description of the Recipe
	/**
	 * @uml.property  name="description"
	 */
	private String description;

	// The time the recipe takes to make
	/**
	 * @uml.property  name="time"
	 */
	private int time;

	// Photos of the recipe
	/**
	 * @uml.property  name="photos"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.github.cmput301w13t04.food.Photo"
	 */
	private ArrayList<Photo> photos;

	// The ingredients needed in the recipe
	/**
	 * @uml.property  name="ingredients"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.github.cmput301w13t04.food.Ingredient"
	 */
	private ArrayList<Ingredient> ingredients;

	// The list of steps needed to complete the recipe
	/**
	 * @uml.property  name="steps"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.github.cmput301w13t04.food.Step"
	 */
	private ArrayList<Step> steps;

	// The unique recipe id
	/**
	 * @uml.property  name="id"
	 */
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
	 * @return  a string containing the title
	 * @uml.property  name="title"
	 */
	public String getTitle() 
	
	
	
	
	
	
	
	
	
	
	{
		return title;
	}

	/**
	 * Set a new title for the Recipe object
	 * @param title  The new title of the Recipe object
	 * @uml.property  name="title"
	 */
	public void setTitle(String title) 
	
	
	
	
	
	
	
	
	
	
	{
		this.title = title;
	}

	/**
	 * Get the description of the recipe
	 * @return  The descrition of the recipe
	 * @uml.property  name="description"
	 */
	public String getDescription() 
	
	
	
	
	
	
	
	
	
	
	{
		return description;
	}

	/**
	 * Change the description of the recipe
	 * @param description  The new description of the recipe
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) 
	
	
	
	
	
	
	
	
	
	
	{
		this.description = description;
	}

	/**
	 * Get the time it takes to complete the recipe
	 * @return  The time it to complete the recipe
	 * @uml.property  name="time"
	 */
	public int getTime() 
	
	
	
	
	
	
	
	
	
	
	{
		return time;
	}

	/**
	 * Change the time it takes to complete the recipe
	 * @param time  The revised time that it takes to complete the recipe
	 * @uml.property  name="time"
	 */
	public void setTime(int time) 
	
	
	
	
	
	
	
	
	
	
	{
		this.time = time;
	}

	/**
	 * Get the user who created the recipe
	 * @return  The user who created the recipe
	 * @uml.property  name="author"
	 */
	public User getAuthor() 
	
	
	
	
	
	
	
	
	
	
	{
		return author;
	}

	/**
	 * Change the user who created the recipe
	 * @param author  The new user who appears as author of the recipe
	 * @uml.property  name="author"
	 */
	public void setAuthor(User author) 
	
	
	
	
	
	
	
	
	
	
	{
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
	 * @return  The Id of the recipe
	 * @uml.property  name="id"
	 */
	public long getId() 
	
	
	
	
	
	
	
	
	
	
	{
		return this.id;
	}

	/**
	 * Get the list of steps in the recipe
	 * @return The list of steps
	 */
	public ArrayList<Step> getSteps() {
		return this.steps;
	}

	/** 
	 * @uml.property name="step"
	 * @uml.associationEnd inverse="recipe:com.github.cmput301w13t04.food.Step"
	 */
	private Step step;

	/** 
	 * Getter of the property <tt>step</tt>
	 * @return  Returns the step.
	 * @uml.property  name="step"
	 */
	public Step getStep()
	
	
	
	
	
	
	
	
	
	{
		return step;
	}

	/** 
	 * Setter of the property <tt>step</tt>
	 * @param step  The step to set.
	 * @uml.property  name="step"
	 */
	public void setStep(Step step)
	
	
	
	
	
	
	
	
	
	{
		this.step = step;
	}

	/**
	 * @uml.property  name="step1"
	 * @uml.associationEnd  aggregation="shared" inverse="recipe1:com.github.cmput301w13t04.food.Step"
	 */
	private Step step1;

	/**
	 * Getter of the property <tt>step1</tt>
	 * @return  Returns the step1.
	 * @uml.property  name="step1"
	 */
	public Step getStep1()
	
	
	
	
	
	
	
	{
	
		return step1;
	}

	/**
	 * Setter of the property <tt>step1</tt>
	 * @param step1  The step1 to set.
	 * @uml.property  name="step1"
	 */
	public void setStep1(Step step1)
	
	
	
	
	
	
	
	{
	
		this.step1 = step1;
	}

	/**
	 * @uml.property  name="ingredient"
	 * @uml.associationEnd  aggregation="shared" inverse="recipe:com.github.cmput301w13t04.food.Ingredient"
	 */
	private Ingredient ingredient;

	/**
	 * Getter of the property <tt>ingredient</tt>
	 * @return  Returns the ingredient.
	 * @uml.property  name="ingredient"
	 */
	public Ingredient getIngredient()
	
	
	
	
	
	
	{
	
		return ingredient;
	}

	/**
	 * Setter of the property <tt>ingredient</tt>
	 * @param ingredient  The ingredient to set.
	 * @uml.property  name="ingredient"
	 */
	public void setIngredient(Ingredient ingredient)
	
	
	
	
	
	
	{
	
		this.ingredient = ingredient;
	}

	/**
	 * @uml.property  name="user"
	 * @uml.associationEnd  aggregation="shared" inverse="recipe:com.github.cmput301w13t04.food.User"
	 */
	private User user;

	/**
	 * Getter of the property <tt>user</tt>
	 * @return  Returns the user.
	 * @uml.property  name="user"
	 */
	public User getUser()
	
	
	
	
	
	{
	
		return user;
	}

	/**
	 * Setter of the property <tt>user</tt>
	 * @param user  The user to set.
	 * @uml.property  name="user"
	 */
	public void setUser(User user)
	
	
	
	
	
	{
	
		this.user = user;
	}

	/**
	 * @uml.property  name="photo"
	 * @uml.associationEnd  aggregation="shared" inverse="recipe:com.github.cmput301w13t04.food.Photo"
	 */
	private Photo photo;

	/**
	 * Getter of the property <tt>photo</tt>
	 * @return  Returns the photo.
	 * @uml.property  name="photo"
	 */
	public Photo getPhoto()
	
	
	
	
	{
	
		return photo;
	}

	/**
	 * Setter of the property <tt>photo</tt>
	 * @param photo  The photo to set.
	 * @uml.property  name="photo"
	 */
	public void setPhoto(Photo photo)
	
	
	
	
	{
	
		this.photo = photo;
	}

	/**
	 * @uml.property  name="cache"
	 * @uml.associationEnd  inverse="recipe1:com.github.cmput301w13t04.food.Cache"
	 */
	private Cache cache;

	/**
	 * Getter of the property <tt>cache</tt>
	 * @return  Returns the cache.
	 * @uml.property  name="cache"
	 */
	public Cache getCache()
	
	
	
	{
	
		return cache;
	}

	/**
	 * Setter of the property <tt>cache</tt>
	 * @param cache  The cache to set.
	 * @uml.property  name="cache"
	 */
	public void setCache(Cache cache)
	
	
	
	{
	
		this.cache = cache;
	}

	/**
	 * @uml.property  name="database"
	 * @uml.associationEnd  aggregation="shared" inverse="recipe:com.github.cmput301w13t04.food.Database"
	 */
	private Database database;

	/**
	 * Getter of the property <tt>database</tt>
	 * @return  Returns the database.
	 * @uml.property  name="database"
	 */
	public Database getDatabase()
	
	
	{
	
		return database;
	}

	/**
	 * Setter of the property <tt>database</tt>
	 * @param database  The database to set.
	 * @uml.property  name="database"
	 */
	public void setDatabase(Database database)
	
	
	{
	
		this.database = database;
	}

	/** 
	 * @uml.property name="database1"
	 * @uml.associationEnd inverse="recipe1:com.github.cmput301w13t04.food.Database"
	 */
	private Database database1;

	/** 
	 * Getter of the property <tt>database1</tt>
	 * @return  Returns the database1.
	 * @uml.property  name="database1"
	 */
	public Database getDatabase1()
	
	{
		return database1;
	}

	/** 
	 * Setter of the property <tt>database1</tt>
	 * @param database1  The database1 to set.
	 * @uml.property  name="database1"
	 */
	public void setDatabase1(Database database1)
	
	{
		this.database1 = database1;
	}
}
