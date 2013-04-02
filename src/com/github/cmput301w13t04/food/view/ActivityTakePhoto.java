package com.github.cmput301w13t04.food.view;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.controller.AlbumDirFactory;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * An intent that allows user to take a photo and then crop it, so that it can be attached to 
 * an ingredient or a recipe
 * @author W13T04
 *
 */
public class ActivityTakePhoto extends Activity {

	private static final int ACTION_TAKE_PHOTO_B = 1;
	private static final int ACTION_DO_CROP = 2;

	private String mCurrentPhotoPath;
	private String description;
	private Uri mURI;

	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";

	private AlbumDirFactory mAlbumStorageDirFactory = null;

	/* Photo album for this application */
	public String getAlbumName() {
		return getString(R.string.app_name);
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
		.format(new Date());
		String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
		File albumF = mAlbumStorageDirFactory.getAlbumDir(this);
		File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX,
				albumF);
		return imageF;
	}

	private File setUpPhotoFile() throws IOException {

		File f = createImageFile();
		mCurrentPhotoPath = f.getAbsolutePath();

		return f;
	}

	private void galleryAddPic() {

		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		doCrop(contentUri);
	}

	/**
	 * Load all of the relevant parameters and start the crop intent
	 * @param picURI The URI of the recently taken photo
	 */
	public void doCrop(Uri picURI) {

		try {
			// call the standard crop action intent (the user device may not
			// support it)
			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			// indicate image type and Uri
			cropIntent.setDataAndType(picURI, "image/*");
			// set crop properties
			cropIntent.putExtra("crop", "true");
			// indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 1);
			cropIntent.putExtra("aspectY", 1);
			// indicate output X and Y
			cropIntent.putExtra("outputX", 360);
			cropIntent.putExtra("outputY", 360);
			// retrieve data on return
			cropIntent.putExtra("output", picURI);
			cropIntent.putExtra("return-data", true);
			mURI = picURI;
			// start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, ACTION_DO_CROP);
		}

		catch (ActivityNotFoundException anfe) {
			// display an error message
			String errorMessage = "Whoops - your device doesn't support the crop action!";
			Toast toast = Toast
					.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	private void dispatchTakePictureIntent(int actionCode) {

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		switch (actionCode) {
		case ACTION_TAKE_PHOTO_B: {
			File f = null;

			try {
				f = setUpPhotoFile();
				mCurrentPhotoPath = f.getAbsolutePath();
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(f));
			} catch (IOException e) {
				e.printStackTrace();
				f = null;
				mCurrentPhotoPath = null;
			}
		}
		break;
		default:
			break;
		} // switch

		startActivityForResult(takePictureIntent, actionCode);
	}

	private void handleBigCameraPhoto() {

		if (mCurrentPhotoPath != null) {
			galleryAddPic();
		} else
			Log.d("Picture", "Invalid Path");

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_intent);
		this.description = null;
		mAlbumStorageDirFactory = new AlbumDirFactory();
		dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast toast = Toast
				.makeText(this, "No photo added", Toast.LENGTH_SHORT);
		
		switch (requestCode) {
		case ACTION_TAKE_PHOTO_B: {
			if (resultCode == RESULT_OK) {
				handleBigCameraPhoto();
			}
			else{
				toast.show();
				finish();
			}	
			break;
		} // ACTION_TAKE_PHOTO_B
		case ACTION_DO_CROP: {
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				// get the cropped bitmap
				Bitmap pic = extras.getParcelable("data");
				// retrieve a reference to the ImageView
				ImageView picView = (ImageView) findViewById(R.id.IngPhotoView);
				// display the returned cropped image
				picView.setImageBitmap(pic);

			}
			else{
				toast.show();
				finish();
			}
			break;
		}
		} // switch
	}

	/**
	 * The button listener to confirm adding the photo
	 */
	public void done(View view){
		Intent result = new Intent();
		result.putExtra("path", mURI.toString());
		result.putExtra("desc", description);
		setResult(Activity.RESULT_OK, result);
		finish();
	}

	/**
	 * Indicates whether the specified action can be used as an intent. This
	 * method queries the package manager for installed packages that can
	 * respond to an intent with the specified action. If no suitable package is
	 * found, this method returns false.
	 * http://android-developers.blogspot.com/2009/01/can-i-use-this-intent.html
	 * 
	 * @param context
	 *            The application's environment.
	 * @param action
	 *            The Intent action to check for availability.
	 * 
	 * @return True if an Intent with the specified action can be sent and
	 *         responded to, false otherwise.
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

}