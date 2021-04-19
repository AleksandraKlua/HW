package com.example.lesson1.viewmodel;
import java.lang.String;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lesson1.model.User;

public class SigninViewModel extends FragmentViewModel {
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private boolean emailNotEmpty;
    private boolean passwordNotEmpty;

    public SigninViewModel(){
        userLiveData.setValue(null);
    }

    public LiveData<User> getUserLiveData(){
        return userLiveData;
    }

    public void sendUserData(User user){

    }

    public boolean checkFields(String email, String password){
        passwordNotEmpty = !password.isEmpty();
        emailNotEmpty = !email.isEmpty();

        return passwordNotEmpty && emailNotEmpty;
    }

    public String messageText(){
        String field = "";
        field = formMessageText(emailNotEmpty, field, "email");
        field = formMessageText(passwordNotEmpty, field, "Пароль");

        return field;
    }
}
