package com.example.storiesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storiesapp.data.DemoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoriesViewModel : ViewModel(){
    private val repo = DemoRepo()
    private val _images = MutableLiveData<List<String>>()
    val images = _images

    fun fetchImages() = viewModelScope.launch(Dispatchers.IO) {
        _images.postValue(repo.getImages())
    }
}