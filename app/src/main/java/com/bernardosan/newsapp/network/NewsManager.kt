package com.bernardosan.savedinstancebundledemo.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsManager {
    private val _newsResponse =  mutableStateOf(NewsResponse())
    val newsResponse: State<NewsResponse>
        @Composable get() = remember{
            _newsResponse
        }


    init {
        getArticles()
    }

    private fun getArticles(){
        val service = API.service.getNews("pt", API.API_KEY)
        service.enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful){
                    _newsResponse.value = response.body()!!
                } else{
                    Log.d("error", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}