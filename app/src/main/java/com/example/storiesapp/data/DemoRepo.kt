package com.example.storiesapp.data

import com.example.storiesapp.utils.DemoData

class DemoRepo {
    fun getImages() : List<String>{
        return DemoData.images;
    }

    fun getAImage(position: Int): String {
        return DemoData.images[position]
    }
}