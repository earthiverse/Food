package com.github.cmput301w13t04.food.controller;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.github.cmput301w13t04.food.model.Ingredient;
import com.github.cmput301w13t04.food.model.Photo;
import com.github.cmput301w13t04.food.model.Recipe;
import com.google.gson.Gson;

/**
 * An object used for communication with the online database
 * 
 * @author W13T04
 * 
 */
public class Database {

	public Database() {
		// TODO: Figure out how we're doing things.
	}

	/**
	 * Publish a recipe to the online database
	 * 
	 * @param recipe
	 */
	public void publishRecipe(Recipe recipe) {
		new PublishRecipeTask().execute(recipe);
		// TODO: Post this JSON to the server
	}

	private class PublishRecipeTask extends AsyncTask<Recipe, Void, Boolean> {
		protected Boolean doInBackground(Recipe... recipes) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				imgurController imageHost = new imgurController();
				for (int i = 0; i < recipes.length; i++) {
					Log.d("Starting publish", "Haihai");
					Recipe recipe = recipes[i];

					HttpPost httpPost = new HttpPost(
							"http://www.earthiverse.ath.cx:9200/food/recipe/"
									+ String.valueOf(recipe.getId()));

					// Post recipe photos
					ArrayList<Photo> photos = recipe.getPhotos();
					ArrayList<String> oldRecipePhotoPaths = new ArrayList<String>();
					Log.d("Photo size", String.valueOf(photos.size()));
					for (int j = 0; j < photos.size(); j++) {
						// Post image to server, get new path
						oldRecipePhotoPaths.add(photos.get(j).getPath());

						String remotePath = imageHost.post(photos.get(j)
								.getPath());

						// Assign new path
						photos.get(j).setPath(remotePath);
					}
					Log.d("We get here?", "Yes");

					// TODO: Post ingredient photos
					ArrayList<Ingredient> ingredients = recipe.getIngredients();
					ArrayList<String> oldIngredientPhotoPaths = new ArrayList<String>();
					for (int j = 0; j < ingredients.size(); j++) {
						// Post image to server, get new path
						try {
							// Get local path
							oldIngredientPhotoPaths.add(ingredients.get(j)
									.getPhoto().getPath());

							String remotePath = imageHost.post(ingredients
									.get(j).getPhoto().getPath());

							// Assign new path
							ingredients.get(j).getPhoto().setPath(remotePath);
						} catch (Exception e) {
							// No photo on ingredient
						}
					}

					// Send converted recipe to ElasticSearch
					StringEntity post = new StringEntity(
							new Gson().toJson(recipe));
					httpPost.setEntity(post);

					HttpResponse response = httpClient.execute(httpPost);
					String responseBody = EntityUtils.toString(response
							.getEntity());

					// Restore Photo Paths
					// Recipe Images
					for (int j = 0; j < photos.size(); j++) {
						photos.get(j).setPath(oldRecipePhotoPaths.get(j));
					}
					// Ingredient Images
					for (int j = 0; j < ingredients.size(); j++) {
						try {
							ingredients.get(j).getPhoto()
									.setPath(oldIngredientPhotoPaths.get(j));
						} catch (Exception e) {
							// No photo on ingredient
						}
					}

					Log.d("Result", responseBody);
				}
			} catch (Exception e) {
				// Something went wrong
				e.printStackTrace();
				return false;
			}
			Log.d("What?", "what.");

			return true;
		}

		protected void onPostExecute(Boolean result) {
			Log.d("Result onPostExecute", String.valueOf(result));
			// This gets called on the interface (main) thread!
			//
		}

	}
}
