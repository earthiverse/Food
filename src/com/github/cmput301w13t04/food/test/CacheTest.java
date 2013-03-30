package com.github.cmput301w13t04.food.test;

import java.util.ArrayList;

import org.junit.*;

import com.github.cmput301w13t04.food.controller.Cache;
import com.github.cmput301w13t04.food.model.Ingredient;
import com.github.cmput301w13t04.food.model.Recipe;
import com.github.cmput301w13t04.food.model.User;

import junit.framework.TestCase;

public class CacheTest extends TestCase {

	@Test
	public void testAddIngredient() {

		Cache cache = new Cache();
		Ingredient i, i2;
		i = new Ingredient("Onion", "1", "A tasty vegetable", null);
		cache.addIngredient(i);

		assertTrue(cache.hasIngredients());

		i2 = new Ingredient("Apple", "1", "A tasty fruit", null);
		cache.addIngredient(i2);

		assertEquals(2, cache.ingredientCount());

	}

	@Test
	public void testRemoveIngredient() {

		Cache cache = new Cache();
		Ingredient i, i2;
		i = new Ingredient("Onion", "1", "A tasty vegetable", null);
		i2 = new Ingredient("Apple", "1", "A tasty fruit", null);

		cache.addIngredient(i);
		cache.addIngredient(i2);

		cache.removeIngredient(i);

		assertTrue(cache.hasIngredients());
		assertEquals(1, cache.ingredientCount());
		assertEquals("Apple", cache.getIngredient(0).getName());

		cache.removeIngredient(i2);

		assertFalse(cache.hasIngredients());

	}

	@Test
	public void testGetIngredients() {

		Cache cache = new Cache();
		Ingredient i, i2;
		i = new Ingredient("Onion", "1", "A tasty vegetable", null);
		i2 = new Ingredient("Apple", "1", "A tasty fruit", null);

		cache.addIngredient(i);
		cache.addIngredient(i2);

		ArrayList<Ingredient> ingList = cache.getIngredients();

		Ingredient iTest = ingList.get(0);

		assertEquals(iTest, i);

		cache.removeIngredient(i);
		ingList = cache.getIngredients();
		iTest = ingList.get(0);

		assertEquals(iTest, i2);

	}

	@Test
	public void testAddRecipe() {

		Cache cache = new Cache();
		Recipe r, r2;

		User u1, u2;

		u1 = new User("dud@ualberta.ca");
		u2 = new User("bobloblaw@ualberta.ca");

		r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		cache.addRecipe(r);

		assertTrue(cache.hasRecipes());

		r2 = new Recipe("Apple soup", u2, "A tasty fruit soup", 3);
		cache.addRecipe(r2);

		assertEquals(2, cache.recipeCount());

	}

	@Test
	public void testRemoveRecipe() {

		Cache cache = new Cache();
		Recipe r, r2;

		User u1, u2;

		u1 = new User("dud@ualberta.ca");
		u2 = new User("bobloblaw@ualberta.ca");

		r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		r2 = new Recipe("Apple soup", u2, "A tasty fruit soup", 3);

		long id = r.getId();

		cache.addRecipe(r);
		cache.addRecipe(r2);

		cache.removeRecipe(r2);

		assertTrue(cache.hasRecipes());
		assertEquals(1, cache.recipeCount());
		assertEquals("Onion soup", cache.getRecipe(id).getTitle());

		cache.removeRecipe(r);

		assertFalse(cache.hasRecipes());

	}

	@Test
	public void testGetRecipes() {

		Cache cache = new Cache();
		Recipe r, r2;
		User u1, u2;

		u1 = new User("dud@ualberta.ca");
		u2 = new User("bobloblaw@ualberta.ca");

		r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		r2 = new Recipe("Apple soup", u2, "A tasty fruit soup", 3);

		cache.addRecipe(r);
		cache.addRecipe(r2);

		ArrayList<Recipe> rList = cache.getRecipes();

		Recipe rTest = rList.get(0);

		assertEquals(rTest, r);

		cache.removeRecipe(r);
		rList = cache.getRecipes();
		rTest = rList.get(0);

		assertEquals(rTest, r2);

	}

}
