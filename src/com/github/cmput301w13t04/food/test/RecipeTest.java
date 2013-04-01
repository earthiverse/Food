package com.github.cmput301w13t04.food.test;


import android.test.AndroidTestCase;

import com.github.cmput301w13t04.food.model.Recipe;
import com.github.cmput301w13t04.food.model.Step;
import com.github.cmput301w13t04.food.model.User;

public class RecipeTest extends AndroidTestCase{

	public void testGetTitle() throws Throwable{
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		
		String title = r.getTitle();
		assertEquals(title, "Onion soup");
		
		r.setTitle("Apple soup");
		title = r.getTitle();
		assertEquals(title, "Apple soup");
	}

	public void testGetDescription() throws Throwable {
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		
		String desc = r.getDescription();
		assertEquals(desc, "A tasty, zesty soup");
		
		r.setDescription("A rotten soup");
		desc = r.getDescription();
		assertEquals(desc, "A rotten soup");
	}

	public void testGetTime() throws Throwable{
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		
		int time = r.getTime();
		assertEquals(time, 8);
		
		r.setTime(1);
		time = r.getTime();
		assertEquals(time, 1);
	}

	public void testGetAuthor() throws Throwable{
		
		User u1, u2;

		u1 = new User("dud@ualberta.ca");
		u2 = new User("bobloblaw@ualberta.ca");
		
		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		
		User author = r.getAuthor();
		assertEquals(author, u1);
		
		r.setAuthor(u2);
		author = r.getAuthor();
		assertEquals(author, u2);
	}

	public void testAddStep() throws Throwable{
		
		User u1;

		u1 = new User("dud@ualberta.ca");

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8);
		Step step = new Step("Add onions", "Add onions to pan");
		
		r.addStep(step);
		
		assertEquals(r.stepCount(), 1);
		
		r.removeStep(step);
		
		assertEquals(r.stepCount(), 0);
	}

	public void testGetId() throws Throwable{
		
		User u1;

		u1 = new User("dud@ualberta.ca");
		
		long id = 1;

		Recipe r = new Recipe("Onion soup", u1, "A tasty, zesty soup", 8, null, null, null, id);
		
		assertEquals(r.getId(), id);
	}

}
