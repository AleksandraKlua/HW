package com.example.lesson1.chat.viewmodel;
import java.lang.String;

public class SigninViewModel extends FragmentViewModel {
    private boolean emailNotEmpty;
    private boolean passwordNotEmpty;

    public SigninViewModel(){
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
