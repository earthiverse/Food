package com.github.cmput301w13t04.food.controller;

import java.util.ArrayList;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.model.Photo;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class RecipePhotoAdapter extends ArrayAdapter<Photo> {
	private ArrayList<Photo> photos;
	private int layout;

	public RecipePhotoAdapter(Context context, int textViewResourceId,
			ArrayList<Photo> photos) {
		super(context, textViewResourceId, photos);

		this.photos = photos;

		this.layout = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		// Inflate view if no view exists
		if (view == null) {
			final LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.layout, null);
		}

		Photo photo = photos.get(position);

		// Display photo
		if (photo != null) {
			ImageView photoView = (ImageView) view
					.findViewById(R.id.recipe_picture);
			photoView.setImageURI(Uri.parse(photo.getPath()));
		}

		return view;
	}
}