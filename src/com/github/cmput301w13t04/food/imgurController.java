package com.github.cmput301w13t04.food;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;


public class imgurController
{
	private String DEV_KEY = "3c45563c813ad29";
	private String path;
	
	public imgurController(String path){
		this.path = path;
	}
	
	public void post() throws IOException{
		Log.d("MADIT","yo");
		Bitmap bitmap = BitmapFactory.decodeFile(path);

		// Creates Byte Array from picture
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // Not sure whether this should be jpeg or png, try both and see which works best
		URL url = new URL("http://api.imgur.com/2/upload");

		//encodes picture with Base64 and inserts ape key
		String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encode(baos.toByteArray(), Base64.DEFAULT).toString(), "UTF-8");
		data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(DEV_KEY, "UTF-8");

		// opens connection and sends data
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();
		
		BufferedReader in = new BufferedReader(
                new InputStreamReader(
                conn.getInputStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null) 
		    Log.d("RETURND", inputLine);
		in.close();
	}
	
}