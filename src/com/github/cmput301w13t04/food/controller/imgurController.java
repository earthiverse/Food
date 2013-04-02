package com.github.cmput301w13t04.food.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

/**
 * An adapter that allows our project to use the IMGUR API to upload and
 * download photos for publishing or downloading recipes.
 * 
 * @author W13T04
 * 
 */

/*
 * NOTE: A lot of the code for imgur posting is taken from:
 * http://stackoverflow.
 * com/questions/7124484/android-uploading-a-photo-to-host-on
 * -imgur-programatically
 * 
 * Code for fetch:
 * http://stackoverflow.com/questions/4875114/android-save-image-
 * from-url-onto-sd-card
 */
/*
 * To implement, do something like this:
 * 
 * Log.d("REALP",path); imgurController ic = new imgurController(); String
 * result = null; try { //To post an image result = ic.post(path);
 * Log.d("YATTA", result); } catch (IOException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } //To fetch an image. String fpath =
 * ic.fetch(result); Log.d("imgur fetch result", fpath); Where "path" is the
 * string path of the photo.
 */
public class imgurController {
	/*
	 * NOTE: This method implements the 2nd version of the imgur API, which
	 * unfortunately is no longer supported, so this API Key is actually from a
	 * demo app I found online but there doesn't seem to be a way to register a
	 * new key for the 2nd version.
	 */
	private String DEV_KEY = "5a5141ca9354bf7929b98a1d7a4c26ae";

	// private String DEV_KEY = "3c45563c813ad29";

	/**
	 * Unused because the controller has a hard-coded dev key instead of any
	 * state objects
	 */
	public imgurController() {
	}

	/**
	 * Post a photo to IMGUR
	 * 
	 * @param path The path in the device of the photo to be posted
	 * @return The URL of the posted photo
	 */
	public String post(String path) {
		Log.d("Testing", "Started post...");
		Bitmap bitmap = BitmapFactory.decodeFile(Uri.parse(path).getPath());
		Log.d("Testing", "Decoded File...");
		String result = null;
		try {
			Log.d("Testing", "Started Execution...");

			// Your download code here; work with the url parameter and then
			// return the result
			// which if I remember correctly from your code, is a string.
			// This gets called and runs ON ANOTHER thread

			// Creates Byte Array from picture
			Log.d("Testing", "Here.");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

			String data = null;
			data = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

			Log.d("Creating Connection", "yes");
			HttpPost hpost = new HttpPost("http://api.imgur.com/2/upload");
			// HttpPost hpost = new
			// HttpPost("http://api.imgur.com/2/account/images");

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("image", data));
			nameValuePairs.add(new BasicNameValuePair("type", "base64"));
			nameValuePairs.add(new BasicNameValuePair("key", DEV_KEY));

			try {
				hpost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e) {
				e.toString();
				return null;
			}

			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse resp = null;
			try {
				resp = client.execute(hpost);
			} catch (ClientProtocolException e) {
				Log.e("Testing", e.toString());
				return null;
			} catch (IOException e) {
				Log.e("Testing", e.toString());
				return null;
			}
			Log.d("Executed", "yes");

			try {
				result = EntityUtils.toString(resp.getEntity());
				Log.d("YOYO", result);
			} catch (ParseException e) {
				e.toString();
				return null;
			} catch (IOException e) {
				e.toString();
				return null;
			}
			if (result.indexOf("original") >= 0)
				result = result.substring(result.indexOf("original")
						+ "original".length() + 1,
						result.lastIndexOf("original") - 2);
			Log.d("Destination", result);
		} catch (Exception e) {
			// Something went terribly wrong, oh noes.
			return null;
		}
		return result;
	}

	/**
	 * Fetch a photo from IMGUR using the API
	 * @param remoteURL The URL of the IMGUR photo
	 * @return The path on the device where the photo has been saved
	 */
	public String fetch(String remoteURL) {
		String path = null;
		try {
			try {
				Log.d("Creating Connection", "yes");
				URL url = new URL(remoteURL);
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);
				urlConnection.connect();
				Log.d("Connected", "yes");
				File SDCardRoot = Environment.getExternalStorageDirectory()
						.getAbsoluteFile();
				String filename = remoteURL.substring(19);
				Log.i("Local filename:", "" + filename);
				File file = new File(SDCardRoot, filename);
				if (file.createNewFile()) {
					file.createNewFile();
				}
				FileOutputStream fileOutput = new FileOutputStream(file);
				InputStream inputStream = urlConnection.getInputStream();
				int totalSize = urlConnection.getContentLength();
				int downloadedSize = 0;
				byte[] buffer = new byte[1024];
				int bufferLength = 0;
				while ((bufferLength = inputStream.read(buffer)) > 0) {
					fileOutput.write(buffer, 0, bufferLength);
					downloadedSize += bufferLength;
					Log.i("Progress:", "downloadedSize:" + downloadedSize
							+ "totalSize:" + totalSize);
				}
				fileOutput.close();
				if (downloadedSize == totalSize)
					path = file.getAbsolutePath();
			} catch (MalformedURLException e) {
				Log.d("Testing", "Malformed");
				e.printStackTrace();
				path = null;
			} catch (IOException e) {
				Log.d("Testing", "IOException");
				path = null;
				e.printStackTrace();
			}
			Log.i("filepath:", " " + path);
		} catch (Exception e) {
			// Something went wrong
		}
		return path;
	}
}