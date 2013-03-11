package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * Allows for the proper display of an ingredient in a list on the UI
 */
public class IngredientAdapter extends ArrayAdapter<Ingredient> {
	/**
	 * @uml.property  name="ingredients"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.github.cmput301w13t04.food.Ingredient"
	 */
	private ArrayList<Ingredient> ingredients;
	/**
	 * @uml.property  name="layout"
	 */
	private int layout;

	public IngredientAdapter(Context context, int textViewResourceId,
			ArrayList<Ingredient> ingredients) {
		super(context, textViewResourceId, ingredients);
		
		this.ingredients = ingredients;
		
		this.layout = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		// Inflate view if no view exists
		if (view == null) {
			final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.layout, null);
		}

		// Grab ingredient from list
		Ingredient ingredient = ingredients.get(position);

		// Display ingredient
		if (ingredient != null) {

			// TODO: Populate Ingredient Image

			TextView quantity = (TextView) view
					.findViewById(R.id.ingredient_quantity);
			if (quantity != null) {
				quantity.setText(String.valueOf(ingredient.getQuantity()));
			}
			
			TextView name = (TextView) view.findViewById(R.id.ingredient_name);
			if (name != null) {
				name.setText(ingredient.getName());
			}
		}

		return view;
	}

}
