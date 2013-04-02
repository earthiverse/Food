package com.github.cmput301w13t04.food.controller;

import java.io.File;

import android.os.Environment;
import android.util.Log;
import com.github.cmput301w13t04.food.R;
import com.github.cmput301w13t04.food.view.ActivityTakePhoto;

public final class AlbumDirFactory{

	// Standard storage location for digital camera files
	private static final String CAMERA_DIR = "/dcim/";

	/**
	 * Get the Album's absolute path for when we want to strore photos
	 * @param albumName The desired name of the album
	 * @return The File containing the album
	 */
	public File getAlbumStorageDir(String albumName) {
		return new File (
				Environment.getExternalStorageDirectory()
				+ CAMERA_DIR
				+ albumName
		);
	}

	public File getAlbumDir(ActivityTakePhoto activityTakePhoto) {
		File storageDir = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			storageDir = getAlbumStorageDir(activityTakePhoto.getAlbumName());
			if (storageDir != null) {
				if (!storageDir.mkdirs()) {
					if (!storageDir.exists()) {
						Log.d("CameraSample", "failed to create directory");
						return null;
					}
				}
			}
		} else {
			Log.v(activityTakePhoto.getString(R.string.app_name),
					"External storage is not mounted READ/WRITE.");
		}
		return storageDir;
	}
}
