package com.bernardosan.newsapp.models

import com.bernardosan.newsapp.models.ArticleCategory.*

enum class ArticleCategory(val categoryName: String) {
    BUSINESS("Business"),
    ENTERTAINMENT("Entertainment"),
    GENERAL("General"),
    HEALTH("Health"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    TECHNOLOGY("Technology")
}

fun getAllCategory(): List<ArticleCategory>{
    return listOf(
        GENERAL,
        TECHNOLOGY,
        BUSINESS,
        ENTERTAINMENT,
        HEALTH,
        SCIENCE,
        SPORTS
    )
}

fun getCategory(categoryName: String): ArticleCategory?{
    val map = values().associateBy(ArticleCategory::categoryName)
    return map[categoryName]
}