package com.bernardosan.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bernardosan.newsapp.models.NewsModel

@Composable
fun DetailScreen(navController: NavController, newsModel: NewsModel){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)
        Image(painter = painterResource(id = newsModel.image),
        contentDescription = "")
        
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go Back")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    DetailScreen(rememberNavController(),
        NewsModel(
            1,
            author = "Raja Razek, CNN",
            title = "'Tiger King' Joe Exotic says he has been diagnosed with aggressive form of prostate cancer - CNN",
            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix docuseries \\\"Tiger King: Murder, Mayhem and Madness,\\\" has been diagnosed with an aggressive form of prostate cancer, according to a letter written by Maldonado.",
            publishedAt = "2021-11-04T05:35:21Z"
        )
    )
}