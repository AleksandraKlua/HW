package com.example.lesson1.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lesson1.model.User;
import com.example.lesson1.view.MainMenuActivity;
import com.example.lesson1.R;
import com.example.lesson1.viewmodel.FragmentViewModel;
import com.example.lesson1.viewmodel.SigninViewModel;

public class SigninFragment extends Fragment implements View.OnClickListener{
    private TextView email;
    private TextView password;
    private SigninViewModel signinViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        signinViewModel = ViewModelProviders.of(requireActivity()).get(SigninViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceBundle){
        super.onViewCreated(view, savedInstanceBundle);

        view.findViewById(R.id.btnSignin).setOnClickListener(this);
        view.findViewById(R.id.register).setOnClickListener(this);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
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
                getFragment();
                break;
        }
    }

    private void getFragment(){
        Fragment registerFragment = new RegisterFragment();
        FragmentTransaction ft;
        assert getFragmentManager() != null;

        ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(R.id.container, registerFragment).addToBackStack(null);
        ft.commit();
    }

    private void onBtnClicked(){
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        signinViewModel.getUserLiveData().observe(this, user -> {
            if(signinViewModel.checkFields(emailStr, passwordStr)) {
                user = new User(emailStr, passwordStr, "Фамилия", "Имя");
                Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                intent.putExtra("User", user);

                startActivity(intent);
            }else{
                showDialog();
            }
        });
    }

    private void showDialog(){
        DialogFragment dialog = new CustomDialogFragment();
        FragmentViewModel fragmentViewModel = ViewModelProviders.of(requireActivity())
                .get(FragmentViewModel.class);
        fragmentViewModel.setMessageToFragment(getResources().getString(R.string.empty_fields)
                .concat(signinViewModel.messageText()));
        dialog.show(getFragmentManager(), "dialog");
    }
}