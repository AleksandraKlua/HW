package com.example.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;

public class SigninActivity extends Activity implements View.OnClickListener{
    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        final View btnSignin;

        messageText = findViewById(R.id.message);
        btnSignin = findViewById(R.id.btn_signin);
        btnSignin.setOnClickListener(this);

        messageText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(@NonNull View v){
        if (v.getId() == R.id.btn_signin){
            onBtnClicked();
        }else if (v.getId() == R.id.register){
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private void onBtnClicked(){
        if(fieldsNotEmpty())
            messageText.setVisibility(View.INVISIBLE);
        else
            messageText.setVisibility(View.VISIBLE);
    }

    private boolean fieldsNotEmpty(){
        TextView email =  findViewById(R.id.email);
        TextView password = findViewById(R.id.password);

        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }

}
