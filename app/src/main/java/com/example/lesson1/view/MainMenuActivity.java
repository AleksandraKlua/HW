package com.example.lesson1.view;

import com.example.lesson1.R;
import com.example.lesson1.model.User;
import com.example.lesson1.view.adapter.RvAdapter;
import com.example.lesson1.viewmodel.MainMenuViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private MainMenuViewModel mainMenuViewModel;
    private EditText entryMessageField;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mainMenuViewModel = ViewModelProviders.of(this).get(MainMenuViewModel.class);
        //mainMenuViewModel.setChatArray();
        getUserInfo();

        RecyclerView recyclerView = findViewById(R.id.chat);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        adapter = new RvAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        if(mainMenuViewModel.getChatArray().size() != 0){
            ArrayList<String> chatArray = mainMenuViewModel.getChatArray();
            ((RvAdapter) adapter).setData(chatArray);
        }

        findViewById(R.id.btn_enter).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        entryMessageField = findViewById(R.id.entry_message);

        if(v.getId()== R.id.btn_enter){
           sendUserMessage();

           if(mainMenuViewModel.getChatArray().size() == 1){
               sendBotMessage(v);
           }
           linearLayoutManager.scrollToPosition(linearLayoutManager.getItemCount()-1);
        }
    }

    private void sendUserMessage(){
        String messageText = entryMessageField.getText().toString();
        mainMenuViewModel.getChatLiveData().observe(this, chatArray -> {
            if (!messageText.trim().isEmpty()) {
                mainMenuViewModel.chatList(messageText);
                ((RvAdapter) adapter).setData(chatArray);
            }
        });
        entryMessageField.getText().clear();
    }

    private void sendBotMessage(View v){
        mainMenuViewModel.getChatLiveData().observe(this, chatArray -> {
            v.postDelayed(() -> {
                mainMenuViewModel.chatList("Привет!");
                ((RvAdapter) adapter).setData(chatArray);
                }, 1000);
            v.postDelayed(() -> {
                mainMenuViewModel.chatList("Я твой собеседник. Ты меня не знаешь, но ты прекрасный человек. "
                           + "Улыбнись :)");
                ((RvAdapter) adapter).setData(chatArray);
                }, 5000);
        });
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
            inscription.setText(mainMenuViewModel.getRandomNickname());
        }else{
            userInfo.setText("Имя Фамилия");
            inscription.setText("Человек");
        }
    }
}