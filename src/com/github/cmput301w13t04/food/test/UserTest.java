package com.github.cmput301w13t04.food.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.cmput301w13t04.food.User;

public class UserTest {

	@Test
	public void testGetUsername() {
		User u = new User("dud@ualberta.ca");
		String uName = u.getUsername();
		
		assertFalse(uName.equals(null));
		assertFalse(uName.equals("dude@ualberta.ca"));
		assertEquals(uName, "dud@ualberta.ca");
	}

}
