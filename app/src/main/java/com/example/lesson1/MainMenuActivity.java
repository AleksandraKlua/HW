package com.example.lesson1;

import com.example.lesson1.adapter.RvAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainMenuActivity extends Activity implements View.OnClickListener {

    private final ArrayList<String> chatArray = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getUserInfo();

        RecyclerView recyclerView = findViewById(R.id.chat);
        recyclerView.setHasFixedSize(true);
        adapter = new RvAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_enter).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        EditText entryMessageField = findViewById(R.id.entry_message);

        if(v.getId()== R.id.btn_enter){
            String messageText = entryMessageField.getText().toString();

            if(!messageText.trim().isEmpty()){
                chatArray.add(messageText);
            }

            ((RvAdapter) adapter).setData(chatArray);

            if(chatArray.size()==1){
                v.postDelayed(() -> {
                    chatArray.add("Привет!");
                    ((RvAdapter) adapter).setData(chatArray);
                }, 1000);
                v.postDelayed(() -> {
                    chatArray.add("Я твой собеседник. Ты меня не знаешь, но ты прекрасный человек. "
                            + "Улыбнись :)");
                    ((RvAdapter) adapter).setData(chatArray);
                }, 5000);
            }

            linearLayoutManager.scrollToPosition(linearLayoutManager.getItemCount()-1);
            entryMessageField.setText("");
        }
    }

    private void getUserInfo(){
        Intent intent = getIntent();
        TextView userInfo = findViewById(R.id.user_info);
        TextView inscription = findViewById(R.id.inscription);
        Parcelable user = intent.getParcelableExtra("User");
        String firstName = ((User) user).getFirstName();
        String lastName = ((User) user).getLastName();
        String fullName = firstName + " " + lastName;

        if(user != null){
            userInfo.setText(fullName);
            inscription.setText(getRandomNickname());
        }else{
            userInfo.setText("Имя Фамилия");
            inscription.setText("Человек");
        }
    }

    private String getRandomNickname(){
        String[] nicknames = new String[]{"Властитель мира", "Пушистик", "Рептилоид", "Император",
                "Друид", "Воин", "Шпион", "Шаман", "Паладин", "Жрец"};
        Random random = new Random();

        return nicknames[random.nextInt(nicknames.length)];
    }
}