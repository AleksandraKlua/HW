package com.example.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onClick(@NonNull View v){
        if(v.getId() == R.id.btn_registration){
            startActivity(new Intent(this, RegisterActivity.class));
        }else if (v.getId() == R.id.btn_signin){
            startActivity(new Intent(this, SigninActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_registration).setOnClickListener(this);
        findViewById(R.id.btn_signin).setOnClickListener(this);
    }

}
