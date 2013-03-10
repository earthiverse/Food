package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
	private ArrayList<Recipe> recipes;
	private int layout;

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
			
			// TODO: Populate Recipe Image

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
