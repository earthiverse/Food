package com.github.cmput301w13t04.food.controller;

import java.io.File;

import android.os.Environment;

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
}
