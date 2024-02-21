package com.example.secondtryrestapidata

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ServiceInterface {


    @GET("/quotes")
    fun getAllProducts(): Call<ApiResponse>

}