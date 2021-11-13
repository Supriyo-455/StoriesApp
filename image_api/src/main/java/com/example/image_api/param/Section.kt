package com.example.image_api.param

import com.squareup.moshi.Json

enum class Section {
    @Json(name = "hot")
    HOT,

    @Json(name = "top")
    TOP
}