package com.github.cmput301w13t04.food.test;

import java.io.IOException;

import android.test.AndroidTestCase;

import com.github.cmput301w13t04.food.controller.imgurController;

public class ImgurTest extends AndroidTestCase {
	
	public void testPost() throws Throwable{
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

	public void testFetch() throws Throwable{
		imgurController ic1 = new imgurController();
		String dummy = "dummy";
		ic1.fetch(dummy);	
		assertNull(dummy);
	}
}
