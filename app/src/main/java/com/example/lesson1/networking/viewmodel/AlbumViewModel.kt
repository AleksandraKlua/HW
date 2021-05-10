package com.example.lesson1.networking.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson1.networking.model.Album
import java.util.*

class AlbumViewModel : ViewModel() {
    var albumMutableLiveData =
        MutableLiveData<ArrayList<Album>>()
    var albumArrayList = ArrayList<Album>()

    init {
        albumMutableLiveData.value = albumArrayList
    }
}