package com.github.cmput301w13t04.food;

/**
 * A simple user object that is useful for adding a signature when creating 
 * something like a recipe.
 * @author W13T04
 *
 */
public class User {
	private String email;

	/**
	 * Create a new user
	 * @param email The email of the new User
	 */
	public User(String email) {
		this.email = email;
	}

	/**
	 * Get the new Username of the User
	 */
	public String getUsername() {
		return email;
	}
}
