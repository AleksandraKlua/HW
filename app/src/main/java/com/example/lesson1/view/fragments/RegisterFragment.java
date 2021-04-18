package com.example.lesson1.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lesson1.view.MainMenuActivity;
import com.example.lesson1.R;
import com.example.lesson1.model.User;
import com.example.lesson1.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment implements View.OnClickListener{
    private EditText password;
    private EditText repeatPassword;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private RegisterViewModel registerViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnRegistration).setOnClickListener(this);
        view.findViewById(R.id.signin).setOnClickListener(this);

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
                getFragment();
                break;
        }
    }

    private void getFragment(){
        Fragment signinFragment = new SigninFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(R.id.container, signinFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void onSubmitBtnClicked(){
        checkPasswords();
    }

    private String editTextToString(EditText editText){
        return editText.getText().toString();
    }

    private void checkPasswords(){
        String emailStr = editTextToString(email);
        String passwordStr = editTextToString(password);
        String repeatPasswordStr = editTextToString(repeatPassword);
        String lastNameStr = editTextToString(lastName);
        String firstNameStr = editTextToString(firstName);

        if(registerViewModel.fieldsNotEmpty(emailStr, firstNameStr, lastNameStr, passwordStr,
                repeatPasswordStr)) {
            if(registerViewModel.checkPasswordLength(passwordStr)){
                showDialogFragment(R.string.wrong_password_length);
            }else{
                registerViewModel.getUserLiveData().observe(this, user -> {
                    if (passwordStr.equals(repeatPasswordStr)) {
                        user = new User(emailStr, passwordStr, lastNameStr, firstNameStr);
                        Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                        intent.putExtra("User", user);

                        startActivity(intent);
                    } else {
                        showDialogFragment(R.string.not_match_passwords);
                    }
                });
            }
        }else{
            showDialogFragment(R.string.empty_fields);
        }
    }

    private void showDialogFragment(int id){

        DialogFragment dialog = new CustomDialogFragment();
        Bundle sendMessage = new Bundle();
        String field = registerViewModel.messageText();
        sendMessage.putString("message", getString(id).concat(field));
        dialog.setArguments(sendMessage);
        dialog.show(getFragmentManager(), "dialog");
    }

}