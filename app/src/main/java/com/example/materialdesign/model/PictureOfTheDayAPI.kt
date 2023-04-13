package com.example.materialdesign.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query ("api_key") apiKey: String): Call<PictureOfTheDayData>
    @GET("planetary/apod")
    fun getPictureOfTheDayByDate(@Query("api_key") apiKey:String,@Query("date") date:String):Call<PictureOfTheDayData>
}
