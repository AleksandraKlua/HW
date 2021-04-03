package com.example.lesson1;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private final String email;
    private final String password;
    private final String lastName;
    private final String firstName;

    public User(String email, String password, String lastName, String firstName){
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }


    protected User(Parcel in) {
        email = in.readString();
        password = in.readString();
        lastName = in.readString();
        firstName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(lastName);
        dest.writeString(firstName);
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

    public String getLastName(){
        return this.lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }
}
