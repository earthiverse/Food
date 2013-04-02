package com.github.cmput301w13t04.food.view;

import com.github.cmput301w13t04.food.R;

import android.os.Bundle;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A fragment created for searching recipes by ingredient
 * @author W13T04
 *
 */

public class FragmentSearchRecipe extends DialogFragment {
	static FragmentSearchRecipe newInstance() {
		return new FragmentSearchRecipe();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_search_recipe, container,
				false);

		getDialog().setTitle("Search for Recipe");
		getDialog().setCancelable(true);

		Button byIngredient = (Button) v
				.findViewById(R.id.button_search_by_ingredient);
		byIngredient.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						ActivitySearchIngredient.class);
				startActivity(intent);
				dismiss();
			}
		});

		Button byUser = (Button) v.findViewById(R.id.button_search_by_user);
		byUser.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DialogFragment newFragment = FragmentSearchRecipeUser.newInstance();
				newFragment.show(getFragmentManager(), "SearchByUser");
				dismiss();
			}
		});

		// TODO: Populate View (If needed)?

		return v;
	}
}
