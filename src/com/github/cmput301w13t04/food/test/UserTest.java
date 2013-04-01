package com.github.cmput301w13t04.food.test;

import android.test.AndroidTestCase;

import com.github.cmput301w13t04.food.model.User;

public class UserTest extends AndroidTestCase{

	
	public void testGetUsername() throws Throwable{
		User u = new User("dud@ualberta.ca");
		String uName = u.getUsername();
		
		assertFalse(uName.equals(null));
		assertFalse(uName.equals("dude@ualberta.ca"));
		assertEquals(uName, "dud@ualberta.ca");
	}

}
