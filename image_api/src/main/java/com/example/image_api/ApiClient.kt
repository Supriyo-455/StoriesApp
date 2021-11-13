package com.example.image_api

import com.example.image_api.api.ImageApi
import com.example.image_api.custom_converter.EnumConverter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ImgurClient {
    private const val API_KEY = "fde231b7642d73d" //TODO: ideally should be in app not in lib

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", "Client-ID $API_KEY")
                    .build()
                it.proceed(request)
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(EnumConverter())
            .baseUrl("https://api.imgur.com/3/")
            .build()
    }

    val api : ImageApi by lazy {
        retrofit.create(ImageApi::class.java)
    }
}