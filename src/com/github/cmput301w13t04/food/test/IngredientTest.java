package com.github.cmput301w13t04.food.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.cmput301w13t04.food.Ingredient;

public class IngredientTest {

	@Test
	public void testGetName() {
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

	@Test
	public void testGetDescription() {
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

	@Test
	public void testGetQuantity() {

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
