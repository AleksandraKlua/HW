package com.example.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private TextView messageText;
    private EditText password;
    private EditText repeatPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        final View btnRegister;

        btnRegister = findViewById(R.id.btn_registration);
        btnRegister.setOnClickListener(this);
        messageText = findViewById(R.id.message);

        messageText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(@NonNull View v){
        if (v.getId() == R.id.btn_registration){
            onSubmitBtnClicked();
        }else if (v.getId() == R.id.signin){
            startActivity(new Intent(this, SigninActivity.class));
        }
    }

    private void onSubmitBtnClicked(){
        checkPasswords();
    }

    private boolean fieldsNotEmpty(){
        EditText email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        EditText firstName = findViewById(R.id.first_name);
        EditText lastName = findViewById(R.id.last_name);
        repeatPassword = findViewById(R.id.repeat_password);

        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !firstName.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty() && !repeatPassword.getText().toString().isEmpty();
    }

    private void checkPasswords(){
        if(fieldsNotEmpty()) {
            if (password.getText().toString().equals(repeatPassword.getText().toString())) {
                messageText.setVisibility(View.INVISIBLE);
            } else {
                messageText.setVisibility(View.VISIBLE);
                messageText.setText(R.string.not_match_passwords);
            }
        }else{
            messageText.setVisibility(View.VISIBLE);
            messageText.setText(R.string.message);
        }
    }

}
