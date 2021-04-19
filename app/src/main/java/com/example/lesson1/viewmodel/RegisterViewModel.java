package com.example.lesson1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lesson1.model.User;

public class RegisterViewModel extends FragmentViewModel {
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> userLastName = new MutableLiveData<>();
    private final MutableLiveData<String> userFirstName = new MutableLiveData<>();
    private boolean emailNotEmpty;
    private boolean passwordNotEmpty;
    private boolean repeatPasswordNotEmpty;
    private boolean firstNameNotEmpty;
    private boolean lastNameNotEmpty;

    public RegisterViewModel(){
        userLiveData.setValue(null);
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

    public boolean checkRegistrationFields(String email, String firstName, String lastName, String password,
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

        field = formMessageText(emailNotEmpty, field, "email");
        field = formMessageText(firstNameNotEmpty, field, "Имя");
        field = formMessageText(lastNameNotEmpty, field, "Фамилия");
        field = formMessageText(passwordNotEmpty, field, "Пароль");
        field = formMessageText(repeatPasswordNotEmpty, field, "Повторите пароль");

        return field;
    }
}