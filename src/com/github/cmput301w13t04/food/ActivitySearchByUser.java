package com.github.cmput301w13t04.food;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActivitySearchByUser extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_by_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search_by_user, menu);
		return true;
	}
	public void searchByUser() throws IOException{
		Recipe[] recipe;
		RecipeSearch search = new RecipeSearch();
		recipe = search.query("1337");
		
	}

}
