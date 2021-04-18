package com.example.lesson1.viewmodel;
import java.lang.String;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lesson1.R;
import com.example.lesson1.model.User;

public class SigninViewModel extends ViewModel {
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

    public String messageText(){
        String field = "";
        if (!emailNotEmpty) {
            field = field.concat("\n- ").concat("email");
        }
        if (!passwordNotEmpty) {
            field = field.concat("\n- ").concat("пароль");
        }
        return field;
    }

    public boolean fieldsNotEmpty(String email, String password){
        emailNotEmpty = !email.isEmpty();
        passwordNotEmpty = !password.isEmpty();

        return emailNotEmpty && passwordNotEmpty;
    }
}
