package com.github.cmput301w13t04.food.controller;

import java.util.ArrayList;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.model.Photo;
import com.github.cmput301w13t04.food.model.Recipe;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Allows for the proper display of Recipes in a ListView
 * 
 * @author W13T04
 */
public class RecipeAdapter extends ArrayAdapter<Recipe> {
	private ArrayList<Recipe> recipes;
	private int layout;

	/**
	 * Create the Recipe adapter
	 * 
	 * @param context
	 *            the context of the application
	 * @param textViewResourceId
	 *            the resource ID of the target TextView
	 * @param ingredients
	 *            The list of Recipes to be adapted
	 */
	public RecipeAdapter(Context context, int textViewResourceId,
			ArrayList<Recipe> recipes) {
		super(context, textViewResourceId, recipes);

		this.recipes = recipes;

		this.layout = textViewResourceId;
	}

	@Override
	public long getItemId(int position) {
		return recipes.get(position).getId();
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

		// Grab ingredient from list
		Recipe recipe = recipes.get(position);

		// Display recipe
		if (recipe != null) {
			Photo photo = recipe.getPhoto(0);
			if (photo != null) {
				ImageView image = (ImageView) view
						.findViewById(R.id.recipe_image);
				image.setImageURI(Uri.parse(photo.getPath()));

				if (image != null) {
					recipes.get(position).getPhoto(0);
				}
			}

			TextView name = (TextView) view.findViewById(R.id.recipe_name);
			if (name != null) {
				name.setText(recipe.getTitle());
			}

			TextView description = (TextView) view
					.findViewById(R.id.recipe_description);
			if (description != null) {
				description.setText(recipe.getDescription());
			}
		}

		return view;
	}
}
