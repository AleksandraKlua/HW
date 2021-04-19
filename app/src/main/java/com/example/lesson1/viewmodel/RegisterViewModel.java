package com.example.lesson1.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lesson1.R;
import com.example.lesson1.model.User;

public class RegisterViewModel extends ViewModel {
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> userLastName = new MutableLiveData<>();
    private final MutableLiveData<String> userFirstName = new MutableLiveData<>();
    public final MutableLiveData<String> message = new MutableLiveData<>();
    private boolean emailNotEmpty;
    private boolean passwordNotEmpty;
    private boolean repeatPasswordNotEmpty;
    private boolean firstNameNotEmpty;
    private boolean lastNameNotEmpty;

    public RegisterViewModel(){
        userLiveData.setValue(null);
        message.setValue("");
    }

    public LiveData<User> getUserLiveData(){
        return userLiveData;
    }


    public void setUserLastName(String str){
        userLastName.setValue(str);
    }
    public LiveData<String> getUserLastName(){

        return this.userLastName;
    }
    public String getUserLastNameString(){
        return this.userLastName.getValue();
    }

    public void setUserFirstName(String str){
        this.userFirstName.setValue(str);
    }
    public LiveData<String> getUserFirstName(){
        return this.userFirstName;
    }
    public String getUserFirstNameString(){
        return this.userFirstName.getValue();
    }


    public boolean fieldsNotEmpty(String email, String firstName, String lastName, String password,
                                   String repeatPassword){
        emailNotEmpty = !email.isEmpty();
        firstNameNotEmpty = !firstName.isEmpty();
        lastNameNotEmpty = !lastName.isEmpty();
        passwordNotEmpty = !password.isEmpty();
        repeatPasswordNotEmpty = !repeatPassword.isEmpty();

        return emailNotEmpty && firstNameNotEmpty && lastNameNotEmpty && passwordNotEmpty
                && repeatPasswordNotEmpty;
    }

    public boolean checkPasswordLength(String password){
        return password.length() < 8;
    }

    public String messageText(){
        String field = "";
        if (!emailNotEmpty){
            field = field.concat("\n- ").concat("email");
        }
        if(!firstNameNotEmpty){
            field = field.concat("\n- ").concat("Имя");
        }
        if(!lastNameNotEmpty){
            field = field.concat("\n- ").concat("Фамилия");
        }
        if (!passwordNotEmpty){
            field = field.concat("\n- ").concat("Пароль");
        }
        if(!repeatPasswordNotEmpty){
            field = field.concat("\n- ").concat("Повторите пароль");
        }
        return field;
    }

    public void setMessageToFragment(String s){
        message.setValue(s);
    }

    public LiveData<String> getMessage(){
        return message;
    }

    public String getStringMessage(){
        return String.valueOf(message.getValue());
    }

}
