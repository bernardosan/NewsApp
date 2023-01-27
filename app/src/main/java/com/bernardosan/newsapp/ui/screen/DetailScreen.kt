package com.bernardosan.newsapp.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bernardosan.newsapp.R
import com.bernardosan.newsapp.mock.MockNewsModel
import com.bernardosan.newsapp.mock.MockNewsModel.getTimeAgo
import com.bernardosan.newsapp.models.Article
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun DetailScreen(navController: NavController, article: Article, scrollState: ScrollState){

    Scaffold(
        topBar = {DetailTopAppBar(onBackPressed = {navController.popBackStack()})}
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(it)
        ) {
            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                error = ImageBitmap.imageResource(id = R.drawable.breaking_news),
                placeHolder = ImageBitmap.imageResource(id = R.drawable.breaking_news),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = article.author.toString())
                InfoWithIcon(Icons.Default.DateRange, info = MockNewsModel.stringToDate(article.publishedAt?:"Not Available").getTimeAgo())
            }

            Text(
                text = article.title?:"Not Available",
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )

            Text(text = article.description?:"Not Available",
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}

@Composable 
fun DetailTopAppBar(onBackPressed: () -> Unit = {}){
    TopAppBar(
        title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)},
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
    )
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
        Article(
            author = "Raja Razek, CNN",
            title = "'Tiger King' Joe Exotic says he has been diagnosed with aggressive form of prostate cancer - CNN",
            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix docuseries \\\"Tiger King: Murder, Mayhem and Madness,\\\" has been diagnosed with an aggressive form of prostate cancer, according to a letter written by Maldonado.",
            publishedAt = "2021-11-04T05:35:21Z"
        ),
        rememberScrollState())
}