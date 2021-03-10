package com.example.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btn_hello){
            startActivity(new Intent(this, SurpriseActivity.class));
        }else if (v.getId() == R.id.btn_hello_user){
            startActivity(new Intent(this, HelloUserActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViewById(R.id.btn_hello).setOnClickListener(this);
        findViewById(R.id.btn_hello_user).setOnClickListener(this);
    }

}
