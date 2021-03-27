package com.example.lesson1;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //final int idRegister = R.id.register;
    //final int idBtnSignin = R.id.btnSignin;

    @Override
    public void onClick(@NonNull View v){
        //final int idBtnRegistration = R.id.btnRegistration;


        //switch(v.getId()){
            //case idBtnRegistration:
                //startActivity(new Intent(this, RegisterFragment.class));
                //break;
            //case idBtnSignin:
                //startActivity(new Intent(this, SigninFragment.class));
                //break;
        //}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment signinFragment = new SigninFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, signinFragment).commit();
    }
}
