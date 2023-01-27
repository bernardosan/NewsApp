package com.bernardosan.newsapp.network

import android.util.Log
import androidx.compose.runtime.*
import com.bernardosan.newsapp.models.ArticleCategory
import com.bernardosan.newsapp.models.getCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsManager {
    private val _newsResponse =  mutableStateOf(NewsResponse())
    val newsResponse: State<NewsResponse>
        @Composable get() = remember{
            _newsResponse
        }

    private val _getArticleByCategory = mutableStateOf(NewsResponse())
    val getArticleCategory: State<NewsResponse>
    @Composable get() = remember {
        _getArticleByCategory
    }

    private val _getArticleBySource = mutableStateOf(NewsResponse())
    val getArticleSource: State<NewsResponse>
        @Composable get() = remember {
            _getArticleBySource
        }

    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(ArticleCategory.GENERAL)

    val sourceName = mutableStateOf("abc-news")

    init {
        getArticles()
    }

    private fun getArticles(){
        val service = API.service.getNews("pt")
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

    fun getArticlesByCategory(categoryName: String){
        val service = API.service.getArticlesByCategory(category = categoryName)
        service.enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful){
                    _getArticleByCategory.value = response.body()!!
                } else{
                    Log.d("error", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun getArticlesBySource(sourceName: String){
        val service = API.service.getArticlesByCategory(sourceName)
        service.enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful){
                    _getArticleBySource.value = response.body()!!
                } else{
                    Log.d("error", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun onSelectedCategoryChanged(categoryName: String){
        val newCategory = getCategory(categoryName)
        selectedCategory.value = newCategory

    }
}