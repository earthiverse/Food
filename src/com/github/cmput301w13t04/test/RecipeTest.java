package com.github.cmput301w13t04.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.github.cmput301w13t04.food.Recipe;
import com.github.cmput301w13t04.food.User;

public class RecipeTest extends TestCase {
	protected Recipe recipe;
	
	@Override
	protected void setUp() {
		String name = "Test Recipe";
		int time = 30;
		User author = new User("hyprkookeez@gmail.com");
		
		//recipe = new Recipe(name, time, author);
	}
	
	@Override
	protected void runTest() {
		test();
		test2();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test2() {
		fail("Not yet implemented");
	}

}
