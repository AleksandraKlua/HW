package com.example.lesson1;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private final String email;
    private final String password;

    User (String email, String password){
        this.email = email;
        this.password = password;
    }


    protected User(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
