package com.github.cmput301w13t04.food.view;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.controller.Database;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FragmentSearchRecipeUser extends DialogFragment {

	private EditText searchUser;

	static FragmentSearchRecipeUser newInstance() {
		return new FragmentSearchRecipeUser();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Search by Username");
		builder.setNegativeButton("Cancel", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Do Stuff
			}
		});
		builder.setPositiveButton("Search", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Database database = new Database();

				database.searchRecipe(searchUser.getText().toString());
			}
		});

		View v = getActivity().getLayoutInflater().inflate(
				R.layout.fragment_search_recipe_user, null);

		searchUser = (EditText) v
				.findViewById(R.id.search_username);
		builder.setView(v);

		return builder.create();
	}
}
