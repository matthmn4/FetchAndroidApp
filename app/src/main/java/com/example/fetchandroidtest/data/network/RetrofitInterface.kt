package com.example.fetchandroidtest.data.network

import com.example.fetchandroidtest.data.models.Item
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/hiring.json")
    suspend fun getItems(): List<Item>
}
