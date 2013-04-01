package com.github.cmput301w13t04.food.test;


import android.test.AndroidTestCase;

import com.github.cmput301w13t04.food.model.Ingredient;

public class IngredientTest extends AndroidTestCase{


	public void testGetName() throws Throwable{
		Ingredient i = new Ingredient("Onion", "1", "A tasty vegetable", null);
		
		String name, newName;
		
		name = i.getName();
		newName = "Apple";
		
		assertEquals(name, "Onion");
		assertFalse(name.equals(newName));
		
		i.setName(newName);
		name = i.getName();
		
		assertEquals(name, newName);
		assertFalse(name.equals("Onion"));
	}

	
	public void testGetDescription() throws Throwable{
		Ingredient i = new Ingredient("Onion", "1", "A tasty vegetable", null);
		
		String desc, newDesc;
		
		desc = i.getDescription();
		newDesc = "A rotten vegetable";
		
		assertEquals(desc, "A tasty vegetable");
		assertFalse(desc.equals(newDesc));
		
		i.setDescription(newDesc);
		desc = i.getDescription();
		
		assertEquals(desc, newDesc);
		assertFalse(desc.equals("A tasty vegetable"));
		
	}

	
	public void testGetQuantity() throws Throwable{

		Ingredient i = new Ingredient("Onion", "1", "A tasty vegetable", null);
		
		String q, newQ;
		
		q = i.getQuantity();
		newQ = "2";
		
		assertEquals(q, "1");
		assertFalse(q.equals(newQ));
		
		i.setQuantity(newQ);
		q = i.getQuantity();
		
		assertEquals(q, newQ);
		assertFalse(q.equals("1"));
	}

}
