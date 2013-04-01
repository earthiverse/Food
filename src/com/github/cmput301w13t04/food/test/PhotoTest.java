package com.github.cmput301w13t04.food.test;


import junit.framework.TestCase;

import org.junit.Test;

import android.os.Parcel;

import com.github.cmput301w13t04.food.model.Photo;
import com.github.cmput301w13t04.food.model.User;

public class PhotoTest extends TestCase {


	@Test
	public void testGetPhotographer() {
		User Photographer = new User("Photographer");
		Photo p1 = new Photo("Path", "Description", Photographer);
		assertEquals(p1.getPhotographer(), Photographer);

	}
	@Test
	public void testValidPhotographer(){
		Photo p2 = new Photo(null, null, null);
		assertNull(p2.getPhotographer());
	}


	@Test
	public void testGetDescription() {
		User Photographer = new User("Photographer");
		Photo p1 = new Photo("Path", "Description", Photographer);
		assertEquals(p1.getDescription(), "Description");
		
	}
	@Test
	public void testVaildDescription(){
		Photo p2 = new Photo(null, null, null);
		assertNull(p2.getDescription());
	}
	@Test
	public void testSetDescription() {
		User Photographer = new User("Photographer");
		Photo p1 = new Photo("Path", "Description", Photographer);
		p1.setDescription("New Description");
		assertEquals(p1.getDescription(), "New Description");
	}

	@Test
	public void testGetPath() {
		User Photographer = new User("Photographer");
		Photo p1 = new Photo("Path", "Description", Photographer);
		assertEquals(p1.getPath(), "Path");
	}
	@Test
	public void testValidPath(){
		Photo p2 = new Photo(null, null, null);
		assertNull(p2.getPath());
	}
	@Test
	public void testSetPath() {
		User Photographer = new User("Photographer");
		Photo p1 = new Photo("Path", "Description", Photographer);
		p1.setPath("New Path");
		assertEquals(p1.getPath(), "New Path");
	}
	
	@Test
	public void testGetAbsolutePath(){
		User Photographer = new User("Photographer");
		Photo p1 = new Photo("Path", "Description", Photographer);
		String resultPath = null;
		resultPath = p1.getAbsolutePath();
		assertNotNull(resultPath);
		assertEquals(p1.getPath(), "Path");
		
		p1.setPath("file://Path%20");
		resultPath = p1.getAbsolutePath();
		assertNotNull(resultPath);
		assertEquals(p1.getPath(), "Path ");
	}
	@Test
	public void testParcel(){
		User Photographer = new User("Photographer");
		Photo p1 = new Photo("Path", "Description", Photographer);
		
		Parcel parcel = Parcel.obtain();
		p1.writeToParcel(parcel, 0);
		parcel.setDataPosition(0);
		Photo resultP = Photo.CREATOR.createFromParcel(parcel);
		assertEquals(p1, resultP);
		parcel.recycle();
	}
}
