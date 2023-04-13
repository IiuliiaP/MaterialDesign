package com.example.materialdesign.model


import com.example.materialdesign.utils.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RepositoryImpl: Repository {
    private val baseUrl = BASE_URL

    fun getPictureOfTheDayAPI(): PictureOfTheDayAPI{
        return retrofit.create(PictureOfTheDayAPI::class.java)
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

}