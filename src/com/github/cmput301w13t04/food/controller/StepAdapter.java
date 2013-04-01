package com.github.cmput301w13t04.food.controller;

import java.util.ArrayList;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.model.Step;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Allows for the proper display of Steps in a ListView
 * @author W13T04
 */
public class StepAdapter extends ArrayAdapter<Step> {
	private ArrayList<Step> steps;
	private int layout;
	
	/**
	 * Create the Step adapter
	 * @param context the context of the application
	 * @param textViewResourceId the resource ID of the target TextView
	 * @param ingredients  The list of Steps to be adapted
	 */
	public StepAdapter(Context context, int textViewResourceId,
			ArrayList<Step> steps) {
		super(context, textViewResourceId, steps);

		this.steps = steps;

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

		// Grab ingredient from list
		Step step = steps.get(position);

		// Display ingredient
		if (step != null) {

			// TODO: Populate Step Image

			TextView name = (TextView) view.findViewById(R.id.step_title);
			if (name != null) {
				name.setText(step.getName());
			}

			TextView description = (TextView) view
					.findViewById(R.id.step_description);
			if (description != null) {
				description.setText(step.getDescription());
			}

			TextView number = (TextView) view.findViewById(R.id.step_number);
			if (number != null) {
				number.setText(String.valueOf(position + 1) + ". ");
			}
		}

		return view;
	}
}
