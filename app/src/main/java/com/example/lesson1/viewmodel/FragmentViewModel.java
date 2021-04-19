package com.example.lesson1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentViewModel extends ViewModel {
    public final MutableLiveData<String> message = new MutableLiveData<>();

    public FragmentViewModel(){
        message.setValue("");
    }

    public String formMessageText(Boolean fieldNotEmpty, String field, String title){
        if(!fieldNotEmpty){
            field = field.concat("\n- ").concat(title);
        }
        return field;
    }

    public void setMessageToFragment(String s){
        message.setValue(s);
    }

    public LiveData<String> getMessage(){
        return message;
    }

    public String getStringMessage(){
        return String.valueOf(message.getValue());
    }

}
