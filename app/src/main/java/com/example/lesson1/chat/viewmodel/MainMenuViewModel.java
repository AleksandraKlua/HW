package com.example.lesson1.chat.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lesson1.chat.model.Message;

import java.util.ArrayList;
import java.util.Random;

public class MainMenuViewModel extends ViewModel {
    private final ArrayList<Message> chatArray = new ArrayList<>();
    private final MutableLiveData<ArrayList<Message>> chatLiveData  = new MutableLiveData<>();

    public MainMenuViewModel(){
        chatLiveData.setValue(chatArray);
    }

    public MutableLiveData<ArrayList<Message>> getChatLiveData(){
        return chatLiveData;
    }

    public void setChatArray(){
        chatLiveData.setValue(chatArray);
    }

    public void chatList(Message message){
        chatArray.add(message);
    }

    public ArrayList<Message> getChatArray(){
        return chatArray;
    }

    public String getRandomNickname(){
        String[] nicknames = new String[]{"Властитель мира", "Пушистик", "Рептилоид", "Император",
                "Друид", "Воин", "Шпион", "Шаман", "Паладин", "Жрец"};
        Random random = new Random();

        return nicknames[random.nextInt(nicknames.length)];
    }
}
