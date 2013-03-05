package com.github.cmput301w13t04.food;

import java.util.ArrayList;

public class Step {
	private int index;
	private String name;
	private String description;
	private ArrayList<Photo> photo;
	
	public Step(int index, String name, String description, ArrayList<Photo> photo) {
		this.index = index;
		this.name = name;
		this.description = description;
		this.photo = photo;
	}
	
}
