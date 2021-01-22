package com.example.itunes_searching_app.api

import com.example.itunes_searching_app.models.RetrofitModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Interface {
    @GET("search")
    fun getRetrofitModel(@Query("term") term: String): Call<RetrofitModel>
}