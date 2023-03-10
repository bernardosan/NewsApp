package com.bernardosan.newsapp

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bernardosan.newsapp.models.Article
import com.bernardosan.newsapp.models.BottomMenuScreen
import com.bernardosan.newsapp.ui.screen.Categories
import com.bernardosan.newsapp.ui.screen.DetailScreen
import com.bernardosan.newsapp.ui.screen.Sources
import com.bernardosan.newsapp.ui.screen.TopNews
import com.bernardosan.newsapp.network.NewsManager
import eu.tutorials.newsapp.components.BottomMenu

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController = navController,scrollState)
}

@Composable
fun MainScreen(navController: NavHostController,scrollState: ScrollState) {
    Scaffold(bottomBar ={
        BottomMenu(navController = navController)
    }) {
        Navigation(navController =navController , scrollState =scrollState,paddingValues = it)
    }
}


@Composable
fun Navigation(navController:NavHostController, scrollState: ScrollState,
               paddingValues: PaddingValues, newsManager: NewsManager = NewsManager()
) {
    val articles = newsManager.newsResponse.value.articles

    articles?.let {

        NavHost(
            navController = navController,
            startDestination = BottomMenuScreen.TopNews.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            bottomNavigation(navController = navController, articles, newsManager = newsManager)
            composable("Detail/{index}",
                arguments = listOf(
                    navArgument("index") { type = NavType.IntType }
                )) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let{
                    val article = articles[index]
                    DetailScreen(navController = navController, article = article, scrollState = scrollState )
                }
                if (index != null) {
                    DetailScreen(
                        scrollState = scrollState,
                        navController = navController,
                        article = articles[index]
                    )
                }
            }
        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController,
                                     articles: List<Article>,
                                     newsManager: NewsManager
) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController, articles = articles)
    }
    composable(BottomMenuScreen.Categories.route) {
        Categories(newsManager = newsManager,
            onFetchCategory = {
                newsManager.onSelectedCategoryChanged(it)
                newsManager.getArticlesByCategory(it)
            },
        )
    }
    composable(BottomMenuScreen.Sources.route) {
        Sources(newsManager = newsManager)
    }
}