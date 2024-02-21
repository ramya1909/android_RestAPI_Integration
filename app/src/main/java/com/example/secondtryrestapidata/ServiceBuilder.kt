package com.example.secondtryrestapidata

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://quotable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    // Change the function to suspend
    suspend fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
