package com.example.lesson1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class HelloUserActivity extends Activity implements View.OnClickListener{
    private EditText editText;
    private TextView messageText;

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.button){
            onSubmitBtnClicked();
        }
    }

    private void onSubmitBtnClicked(){
        String name = editText.getText().toString();
        String message = getString(R.string.hello_username, name);
        messageText.setText(message);
        messageText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_user_activity);

        final View btnSubmit;

        editText = findViewById(R.id.edit_text);
        messageText = findViewById(R.id.output);
        btnSubmit = findViewById(R.id.button);
        btnSubmit.setOnClickListener(this);

        messageText.setVisibility(View.INVISIBLE);
    }

}
