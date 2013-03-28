package com.github.cmput301w13t04.food;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;


public class imgurController
{
	private String DEV_KEY = "3c45563c813ad29";
	private String path;
	
	public imgurController(String path){
		this.path = path;

	}
	private class PublishImageTask extends AsyncTask<Bitmap, Void, Boolean> {
	     protected Boolean doInBackground(Bitmap... bitmap) {
	        //Your download code here; work with the url parameter and then return the result
	        //which if I remember correctly from your code, is a string.
	        //This gets called and runs ON ANOTHER thread
	 		
	 		// Creates Byte Array from picture
	 		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 		bitmap[0].compress(Bitmap.CompressFormat.JPEG, 100, baos); // Not sure whether this should be jpeg or png, try both and see which works best
	 		URL url = null;
			try
			{
				//url = new URL("imgur.com/api/upload.xml");
				url = new URL("http://api.imgur.com/2/upload");
			} catch (MalformedURLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	 		//encodes picture with Base64 and inserts ape key
	 		String data = null;
			try
			{
				data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encode(baos.toByteArray(), Base64.DEFAULT).toString(), "UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	 		try
			{
				data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(DEV_KEY, "UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	 		// opens connection and sends data
	 		URLConnection conn = null;
			try
			{
				conn = url.openConnection();
				Log.d("Connection Status","Open");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("Connection Status","Closed");
			}
			
	 		conn.setDoOutput(true);
	 		OutputStreamWriter wr = null;
			try
			{
				wr = new OutputStreamWriter(conn.getOutputStream());
				Log.d("WR","wr obtained");
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	 		try
			{
				wr.write(data);
				Log.d("WR","wr written");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try
			{
				wr.flush();
				Log.d("WR","wr flushed");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	 		BufferedReader in = null;
	 		
			try
			{
				in = new BufferedReader(
				         new InputStreamReader(
				         conn.getInputStream()));
				Log.d("Reader","Connected hopefully");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 		String inputLine;
	 		try
			{
				while ((inputLine = in.readLine()) != null) 
				    Log.d("RETURND", inputLine);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 		try
			{
				in.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	    	 
	    	return true;
	     }
	}
	public void post() throws IOException{
		Log.d("MADIT","yo");

		Bitmap bitmap = BitmapFactory.decodeFile(Uri.parse(path).getPath());
		PublishImageTask runner = new PublishImageTask();
		runner.execute(bitmap);
	
	}
	
}