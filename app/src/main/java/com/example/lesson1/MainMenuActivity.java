package com.example.lesson1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        findViewById(R.id.signin_message);
        findViewById(R.id.user_info);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        TextView message = findViewById(R.id.signin_message);
        TextView userInfo = findViewById(R.id.user_info);
        Parcelable user = intent.getParcelableExtra("User");
        String email = ((User) user).getEmail();
        String password = ((User) user).getPassword();

        if(extras != null){
            message.setText(extras.getString("Message", "Привет!"));
        }else{
            message.setText("Привет");
        }

        if(user != null){
            userInfo.setText("Email: " + email +"\nПароль: " + password);
        }else{
            userInfo.setText("Данные");
        }
    }
}
