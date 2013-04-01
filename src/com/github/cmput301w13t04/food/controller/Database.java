package com.github.cmput301w13t04.food.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
import com.github.cmput301w13t04.food.model.query.RecipeResult;
import com.github.cmput301w13t04.food.model.query.UserQuery;
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

	public ArrayList<Recipe> searchRecipe(String email) {
		UserQuery query = new UserQuery(email.trim());
		try {
			return new SearchRecipeTask().execute(query).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private class SearchRecipeTask extends AsyncTask<UserQuery, Void, ArrayList<Recipe>> {

		@Override
		protected ArrayList<Recipe> doInBackground(UserQuery... queries) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(
						"http://earthiverse.ath.cx:9200/food/recipe/_search");

				for (int i = 0; i < queries.length; i++) {
					UserQuery query = queries[i];

					StringEntity get = new StringEntity(
							new Gson().toJson(query));
					httpPost.setEntity(get);

					HttpResponse response = httpClient.execute(httpPost);
					String responseBody = EntityUtils.toString(response
							.getEntity());
					Log.d("responseBody", responseBody);
					
					RecipeResult result = new Gson().fromJson(responseBody, RecipeResult.class);
					
					ArrayList<Recipe> recipes = result.getRecipes();
					
					// Download the first recipe pictures to device
					imgurController imageHost = new imgurController();
					for (int j = 0; j < recipes.size(); j++) {
						Recipe recipe = recipes.get(i);
						try {
							Photo photo = recipe.getPhoto(0);
							
							// Save to device
							String localPath = imageHost.fetch(photo.getPath());
							// Set new path
							photo.setPath(localPath);
						} catch(Exception e) {
							// No first photo
						}
					}
					
					return recipes;
				}
			} catch (Exception e) {
				// Something went wrong...
			}
			return null;
		}

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
					Recipe recipe = recipes[i];

					HttpPost httpPost = new HttpPost(
							"http://www.earthiverse.ath.cx:9200/food/recipe/"
									+ String.valueOf(recipe.getId()));

					// Post recipe photos
					ArrayList<Photo> photos = recipe.getPhotos();
					ArrayList<String> oldRecipePhotoPaths = new ArrayList<String>();
					for (int j = 0; j < photos.size(); j++) {
						// Post image to server, get new path
						oldRecipePhotoPaths.add(photos.get(j).getPath());

						String remotePath = imageHost.post(photos.get(j)
								.getPath());

						// Assign new path
						photos.get(j).setPath(remotePath);
					}

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
					
					httpClient.execute(httpPost);
					
					// TODO: Verify response

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
				}
			} catch (Exception e) {
				// Something went wrong
				e.printStackTrace();
				return false;
			}

			return true;
		}

		protected void onPostExecute(Boolean result) {
			// TODO: Confirmation of success
		}

	}
}
