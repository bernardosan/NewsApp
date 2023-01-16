package com.bernardosan.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bernardosan.newsapp.models.NewsModel

@Composable
fun DetailScreen(navController: NavController, newsModel: NewsModel, scrollState: ScrollState){

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)
        .padding(8.dp)){
        Text(text = "Read More", fontWeight = FontWeight.SemiBold,  textAlign = TextAlign.Justify)
        Image(painter = painterResource(id = newsModel.image),
        contentDescription = "")

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            InfoWithIcon(Icons.Default.Edit, info = newsModel.author)
            InfoWithIcon(Icons.Default.DateRange, info = newsModel.publishedAt)
        }

        Text(text = newsModel.title, textAlign = TextAlign.Justify, modifier = Modifier.padding(bottom = 8.dp), fontWeight = FontWeight.Bold)

        Text(text = newsModel.description, textAlign = TextAlign.Justify)
        
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go Back")
        }
    }
}

@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = "Author", modifier = Modifier.padding(end = 8.dp),
            colorResource(id = com.bernardosan.newsapp.R.color.purple_200)
        )

        Text(text = info, fontSize = 12.sp)
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
        ),
        rememberScrollState())
}