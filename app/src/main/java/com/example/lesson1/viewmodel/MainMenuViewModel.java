package com.example.lesson1.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class MainMenuViewModel extends ViewModel {
    private final ArrayList<String> chatArray = new ArrayList<>();
    private final MutableLiveData<ArrayList<String>> chatLiveData  = new MutableLiveData<>();

    public MainMenuViewModel(){
        chatLiveData.setValue(chatArray);
    }

    public MutableLiveData<ArrayList<String>> getChatLiveData(){
        return chatLiveData;
    }

    public void setChatArray(){
        chatLiveData.setValue(chatArray);
    }

    public void chatList(String message){
        chatArray.add(message);
    }

    public ArrayList<String> getChatArray(){
        return chatArray;
    }

    public String getRandomNickname(){
        String[] nicknames = new String[]{"Властитель мира", "Пушистик", "Рептилоид", "Император",
                "Друид", "Воин", "Шпион", "Шаман", "Паладин", "Жрец"};
        Random random = new Random();

        return nicknames[random.nextInt(nicknames.length)];
    }
}
