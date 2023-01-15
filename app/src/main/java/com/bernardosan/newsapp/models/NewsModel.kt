package com.bernardosan.newsapp.models

import android.net.Uri
import com.bernardosan.newsapp.R

data class NewsModel(
    val id: Int,
    val image: Int = R.drawable.breaking_news,
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String
)
