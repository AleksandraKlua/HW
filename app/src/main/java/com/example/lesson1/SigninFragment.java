package com.example.lesson1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.DialogFragment;

public class SigninFragment extends Fragment implements View.OnClickListener{
    private TextView email;
    private TextView password;
    private boolean emailNotEmpty;
    private boolean passwordNotEmpty;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceBundle){
        super.onViewCreated(view, savedInstanceBundle);

        View btnSignin = view.findViewById(R.id.btnSignin);
        btnSignin.setOnClickListener(this);

        TextView register = view.findViewById(R.id.register);
        register.setOnClickListener(this);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onClick(@NonNull View v){
        final int idBtnSignin = R.id.btnSignin;
        final int idRegister = R.id.register;

        switch(v.getId()){
            case idBtnSignin:
                onBtnClicked();
                break;
            case idRegister:
                Fragment registerFragment = new RegisterFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.replace(R.id.container, registerFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }

    private void onBtnClicked(){
        DialogFragment dialog = new CustomDialogFragment();
        Bundle sendMessage = new Bundle();
        if(fieldsNotEmpty()){
            String hello = "hello";
        }
        else {
            String field = "";
            if (!emailNotEmpty) {
                field = field.concat("\n- ").concat(getResources().getString(R.string.email));
            }
            if (!passwordNotEmpty) {
                field = field.concat("\n- ").concat(getResources().getString(R.string.password));
            }
            sendMessage.putString("message", getResources().getString(R.string.empty_fields)
                    .concat(field));
            dialog.setArguments(sendMessage);
            dialog.show(getFragmentManager(), "dialog");
        }
    }

    private boolean fieldsNotEmpty(){
        emailNotEmpty = !email.getText().toString().isEmpty();
        passwordNotEmpty = !password.getText().toString().isEmpty();

        return emailNotEmpty && passwordNotEmpty;
    }

}
