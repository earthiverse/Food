package com.github.cmput301w13t04.food;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonObject;


public class RecipeSearch extends AsyncTask<String, Void, Recipe[]> {
	
	
	private final String RecipeURL = "http://earthiverse.ath.cx:9200/food/recipe/";
	private HttpClient httpClient;
	private Gson gson;
	private String searchUrlParam;
	
	@Override
	protected Recipe[] doInBackground(String... queryStrings) {
		// TODO Auto-generated method stub
		String searchTerms = queryStrings[0];
		return this.query(searchTerms);
	}
	public Recipe[] query(String searchTerms){
		
		try {
			searchUrlParam = URLEncoder.encode(new QueryRequest(searchTerms).toJSON(gson), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpGet getRequest = new HttpGet(RecipeURL + "_search?pretty=" + searchUrlParam);

		HttpResponse response;
		String resultBody;
		try {
			response = httpClient.execute(getRequest);
			resultBody = readInputStreamToString(response.getEntity().getContent());
		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

		Recipe[] results = gson.fromJson(resultBody, QueryResult.class).getResults();
		if (results == null) {
			results = new Recipe[0];
		}
		return results;
		
		
	}
	
	
	public RecipeSearch(){
		httpClient = new DefaultHttpClient();
		gson = new Gson();
	}
	
	private static class QueryRequest {
		private static class Query {
			Map<String, String> query_string;

			Query(String query) {
				query_string = new HashMap<String, String>();
				query_string.put("query", query);
			}
		}

		Query query;

		public QueryRequest(String query) {
			this.query = new Query(query);
		}

		public String toJSON(Gson gson) {
			return gson.toJson(this);
		}
	}
	
	private static class QueryResult {
		public static class QueryHitData {
			int total;
			double max_score;
			QueryHit[] hits;
		}

		public static class QueryHit {
			Recipe _source;
		}

		public QueryHitData hits;

		public Recipe[] getResults() {
			if (hits == null) {
				return null;
			}

			ArrayList<Recipe> results = new ArrayList<Recipe>();
			for (QueryHit hit: hits.hits) {
				results.add(hit._source);
			}

			Recipe asArray[] = new Recipe[results.size()];
			results.toArray(asArray);
			return asArray;
		}
	}
	
	
	private String readInputStreamToString(InputStream input) {
		java.util.Scanner s = new java.util.Scanner(input).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	


	

	
	
	
	

}
