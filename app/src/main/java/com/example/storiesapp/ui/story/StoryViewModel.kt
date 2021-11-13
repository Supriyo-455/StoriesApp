package com.example.storiesapp.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storiesapp.data.DemoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel(){
    private val repo = DemoRepo()

    private val _images = MutableLiveData<List<String>>()
    val images : LiveData<List<String>> = _images

    private val _image = MutableLiveData<String>()
    val image : LiveData<String> = _image

    fun getImages() = viewModelScope.launch(Dispatchers.IO) {
        _images.postValue(repo.getImages())
    }

    fun getAImage(position: Int) = viewModelScope.launch(Dispatchers.IO) {
        _image.postValue(repo.getAImage(position))
    }
}