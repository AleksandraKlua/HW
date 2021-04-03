package com.example.lesson1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.DialogFragment;

import com.example.lesson1.MainMenuActivity;
import com.example.lesson1.R;
import com.example.lesson1.User;

public class RegisterFragment extends Fragment implements View.OnClickListener{
    private EditText password;
    private EditText repeatPassword;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private boolean passwordNotEmpty;
    private boolean repeatPasswordNotEmpty;
    private boolean emailNotEmpty;
    private boolean firstNameNotEmpty;
    private boolean lastNameNotEmpty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        View btnRegister = view.findViewById(R.id.btnRegistration);
        btnRegister.setOnClickListener(this);

        TextView signin = view.findViewById(R.id.signin);
        signin.setOnClickListener(this);

        email = view.findViewById(R.id.email);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        password = view.findViewById(R.id.password);
        repeatPassword = view.findViewById(R.id.repeatPassword);
    }

    @Override
    public void onClick(@NonNull View v){
        final int idBtnRegistration = R.id.btnRegistration;
        final int idSignin = R.id.signin;

        switch(v.getId()){
            case idBtnRegistration:
                onSubmitBtnClicked();
                break;
            case idSignin:
                Fragment signinFragment = new SigninFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.replace(R.id.container, signinFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }

    private void onSubmitBtnClicked(){
        checkPasswords();
    }

    private boolean fieldsNotEmpty(){
        emailNotEmpty = !email.getText().toString().isEmpty();
        firstNameNotEmpty = !firstName.getText().toString().isEmpty();
        lastNameNotEmpty = !lastName.getText().toString().isEmpty();
        passwordNotEmpty = !password.getText().toString().isEmpty();
        repeatPasswordNotEmpty = !repeatPassword.getText().toString().isEmpty();

        return emailNotEmpty && firstNameNotEmpty && lastNameNotEmpty && passwordNotEmpty
                && repeatPasswordNotEmpty;
    }

    private boolean checkPasswordLength(){
        return password.getText().toString().length() < 8;
    }

    private void checkPasswords(){

        if(fieldsNotEmpty()) {
            if(checkPasswordLength()){
                showDialogFragment(R.string.wrong_password_length);
            }else{
                if (password.getText().toString().equals(repeatPassword.getText().toString())) {
                    User user = new User (email.getText().toString(), password.getText().toString(),
                            lastName.getText().toString(), firstName.getText().toString());
                    Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                    intent.putExtra("User", user);

                    startActivity(intent);
                } else {
                    showDialogFragment(R.string.not_match_passwords);
                }
            }
        }else{
            showDialogFragment(R.string.empty_fields);
        }
    }

    private String messageText(){
        String field = "";
        if (!emailNotEmpty){
            field = field.concat("\n- ").concat(getResources().getString(R.string.email));
        }
        if(!firstNameNotEmpty){
            field = field.concat("\n- ").concat(getResources().getString(R.string.first_name));
        }
        if(!lastNameNotEmpty){
            field = field.concat("\n- ").concat(getResources().getString(R.string.last_name));
        }
        if (!passwordNotEmpty){
            field = field.concat("\n- ").concat(getResources().getString(R.string.password));
        }
        if(!repeatPasswordNotEmpty){
            field = field.concat("\n- ").concat(getResources().getString(R.string.repeat_password));
        }
        return field;
    }

    private void showDialogFragment(int id){
        DialogFragment dialog = new CustomDialogFragment();
        Bundle sendMessage = new Bundle();
        String field = messageText();

        sendMessage.putString("message", getResources().getString(id).concat(field));
        dialog.setArguments(sendMessage);
        dialog.show(getFragmentManager(), "dialog");
    }

}
