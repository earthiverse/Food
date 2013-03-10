package com.github.cmput301w13t04.food;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StepAdapter extends ArrayAdapter<Step> {
	private ArrayList<Step> steps;
	private int layout;

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

			// TODO:

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
