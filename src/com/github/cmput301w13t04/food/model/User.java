package com.github.cmput301w13t04.food.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A simple user object that is useful for adding a signature when creating 
 * something like a recipe.
 * @author W13T04
 *
 */
public class User implements Parcelable {
	private String email;

	/**
	 * Create a new user
	 * @param email The email of the new User
	 */
	public User(String email) {
		this.email = email;
	}

	/**
	 * Get the new Username of the User
	 */
	public String getUsername() {
		return email;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(email);
	}
	
    private User(Parcel in) {
        email = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}