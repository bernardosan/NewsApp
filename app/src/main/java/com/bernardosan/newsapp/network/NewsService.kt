package com.example.weatherapp.network

import com.bernardosan.newsapp.network.NewsResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
    ): Call<NewsResponse>


    @GET("top-headlines")
    fun getArticlesByCategory(
        @Query("category") category: String,
        @Query("country") country: String = "pt"
    ): Call<NewsResponse>
}
// END