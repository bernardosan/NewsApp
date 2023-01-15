package com.bernardosan.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bernardosan.newsapp.mock.MockNewsModel
import com.bernardosan.newsapp.ui.screen.DetailScreen
import com.bernardosan.newsapp.ui.screen.TopNews

@Composable
fun NewsApp(){
    Navigation()
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "TopNews"){
        composable("TopNews"){
            TopNews(navController = navController)
        }
        composable("Detail/{newsId}",
            arguments = listOf(navArgument("newsId"){type = NavType.IntType})
        ){
            val id = it.arguments!!.getInt("newsId")
            val newsData = MockNewsModel.getNews(id)
            DetailScreen(navController = navController, newsModel =  newsData)
        }
    }
}