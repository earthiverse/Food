package com.github.cmput301w13t04.food.test;

import android.test.AndroidTestCase;

import com.github.cmput301w13t04.food.model.Step;

public class StepTest extends AndroidTestCase{

	
	public void testGetDescription() throws Throwable{
		Step step = new Step("Add onions", "Add onions to pan");
		assertEquals("Add onions to pan", step.getDescription());
		step.setDescription("Add water");
		assertEquals("Add water", step.getDescription());
	}

	
	public void testGetName() throws Throwable{
		Step step = new Step("Add onions", "Add onions to pan");
		assertEquals("Add onions", step.getName());
		step.setName("Add water");
		assertEquals("Add water", step.getName());
	}

}
