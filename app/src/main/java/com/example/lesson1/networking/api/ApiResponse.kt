package com.example.lesson1.networking.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiResponse {
    private const val base_url = "http://jsonplaceholder.typicode.com/"

    fun makeRetrofitRequest(): ApiService {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(ApiService::class.java)
    }

}