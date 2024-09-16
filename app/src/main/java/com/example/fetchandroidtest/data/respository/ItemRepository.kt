package com.example.fetchandroidtest.data.respository

import com.example.fetchandroidtest.data.models.Item
import com.example.fetchandroidtest.data.network.RetrofitClient

object ItemRepository {

    suspend fun getItems(): List<Item> {
        return RetrofitClient.getRetrofitClient().getItems()
    }
}
