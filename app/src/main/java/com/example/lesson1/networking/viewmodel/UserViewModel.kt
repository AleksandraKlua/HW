package com.example.lesson1.networking.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson1.networking.model.UserNet
import java.util.ArrayList

class UserViewModel : ViewModel() {
    var userMutableLiveData =
        MutableLiveData<ArrayList<UserNet>>()
    var userArrayList = ArrayList<UserNet>()

    init {
        userMutableLiveData.value = userArrayList
    }
}