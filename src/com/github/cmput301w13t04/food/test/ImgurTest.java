package com.github.cmput301w13t04.food.test;

import java.io.IOException;

import com.github.cmput301w13t04.food.controller.imgurController;

import junit.framework.TestCase;

public class ImgurTest extends TestCase {
	public void testPost() {
		imgurController ic1 = new imgurController();
		String dummy = "dummy";
		try
		{
			ic1.post(dummy);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(dummy);
	}

	public void testFetch() {
		imgurController ic1 = new imgurController();
		String dummy = "dummy";
		ic1.fetch(dummy);	
		assertNull(dummy);
	}
}
