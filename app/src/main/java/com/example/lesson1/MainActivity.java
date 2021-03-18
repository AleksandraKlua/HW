package com.example.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onClick(@NonNull View v){
        final int idBtnRegistration = R.id.btnRegistration;
        final int idBtnSignin = R.id.btnSignin;

        switch(v.getId()){
            case idBtnRegistration:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case idBtnSignin:
                startActivity(new Intent(this, SigninActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRegistration).setOnClickListener(this);
        findViewById(R.id.btnSignin).setOnClickListener(this);
    }

}
