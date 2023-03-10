package com.bernardosan.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bernardosan.newsapp.R
import com.bernardosan.newsapp.mock.MockNewsModel
import com.bernardosan.newsapp.mock.MockNewsModel.getTimeAgo
import com.bernardosan.newsapp.models.Article
import com.bernardosan.newsapp.models.SourceObject
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun TopNews(navController: NavController, articles: List<Article>){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn{
            items(articles.size){
                index ->
                TopNewsItem(article = articles[index] ,
                    onClick = {
                        navController.navigate("Detail/$index")
                    }
                )
            }
        }
    }
}

@Composable
fun TopNewsItem(article: Article, onClick: () -> Unit = {}){
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onClick()
        }){
        CoilImage(imageModel = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(id = R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(id = R.drawable.breaking_news)
        )
        Column(modifier = Modifier
            .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = MockNewsModel.stringToDate(article.publishedAt!!).getTimeAgo(), color = Color.White, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
            Spacer(modifier = Modifier.fillMaxHeight(0.7f))
            Surface(modifier = Modifier
                .fillMaxSize()
                .alpha(0.7f), color = Color.White) {
                Text(text = article.title!!,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Justify,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 15.sp,
                    modifier = Modifier
                        .padding(top = 6.dp, start = 12.dp, end = 12.dp, bottom = 6.dp)
                        .alpha(1.5f))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview(){
    TopNewsItem(
        Article(
            SourceObject(null, "Tiger King"),
            author = "CBSBoston.com Staff",
            title = "Principal Beaten Unconscious At Dorchester School; Classes Canceled Thursday - CBS Boston",
            description = "Principal Patricia Lampron and another employee were assaulted at Henderson Upper Campus during dismissal on Wednesday.",
            publishedAt = "2021-11-04T01:55:00Z"
        )
    )
}