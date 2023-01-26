package com.example.weatherapp.network

import com.bernardosan.savedinstancebundledemo.network.NewsResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    ): Call<NewsResponse>
}
// END