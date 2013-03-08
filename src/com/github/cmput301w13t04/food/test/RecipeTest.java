package com.github.cmput301w13t04.food.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.cmput301w13t04.food.Recipe;
import com.github.cmput301w13t04.food.Step;
import com.github.cmput301w13t04.food.User;

public class RecipeTest {

	@Test
	public void testGetTitle() {
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8, null);
		
		String title = r.getTitle();
		assertEquals(title, "Onion soup");
		
		r.setTitle("Apple soup");
		title = r.getTitle();
		assertEquals(title, "Apple soup");
	}

	@Test
	public void testGetDescription() {
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8, null);
		
		String desc = r.getDescription();
		assertEquals(desc, "A tasty, zesty soup");
		
		r.setDescription("A rotten soup");
		desc = r.getDescription();
		assertEquals(desc, "A rotten soup");
	}

	@Test
	public void testGetTime() {
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8, null);
		
		int time = r.getTime();
		assertEquals(time, 8);
		
		r.setTime(1);
		time = r.getTime();
		assertEquals(time, 1);
	}

	@Test
	public void testGetAuthor() {
		
		User u1, u2;

		u1 = new User("dud@ualberta.ca");
		u2 = new User("bobloblaw@ualberta.ca");
		
		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8, null);
		
		User author = r.getAuthor();
		assertEquals(author, u1);
		
		r.setAuthor(u2);
		author = r.getAuthor();
		assertEquals(author, u2);
	}

	@Test
	public void testAddStep() {
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8, null);
		Step step = new Step("Add onions", "Add onions to pan", null);
		
		r.addStep(step);
		
		assertEquals(r.stepCount(), 1);
		
		r.removeStep(step);
		
		assertEquals(r.stepCount(), 0);
	}

	@Test
	public void testGetId() {
		
		User u1;

		u1 = new User("dud@ualberta.ca");
		
		long id = 1;

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8, null, id);
		
		assertEquals(r.getId(), id);
	}

}
