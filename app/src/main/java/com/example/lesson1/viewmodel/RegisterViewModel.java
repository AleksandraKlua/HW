package com.example.lesson1.viewmodel;

public class RegisterViewModel extends FragmentViewModel {
    private boolean emailNotEmpty;
    private boolean passwordNotEmpty;
    private boolean repeatPasswordNotEmpty;
    private boolean firstNameNotEmpty;
    private boolean lastNameNotEmpty;

    public RegisterViewModel(){
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