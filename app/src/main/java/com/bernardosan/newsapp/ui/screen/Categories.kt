package com.bernardosan.newsapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bernardosan.newsapp.R
import com.bernardosan.newsapp.mock.MockNewsModel
import com.bernardosan.newsapp.mock.MockNewsModel.getTimeAgo
import com.bernardosan.newsapp.models.Article
import com.bernardosan.newsapp.models.ArticleCategory
import com.bernardosan.newsapp.models.getAllCategory
import com.bernardosan.newsapp.network.NewsManager
import com.bernardosan.newsapp.ui.theme.Purple200
import com.bernardosan.newsapp.ui.theme.Purple500
import com.bernardosan.newsapp.ui.theme.Purple700
import com.skydoves.landscapist.coil.CoilImage
import okhttp3.internal.wait

@Composable
fun Categories(onFetchCategory: (String) -> Unit, newsManager: NewsManager){
    val tabsItems = getAllCategory()
    Column {
        LazyRow(
            Modifier
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = 4.dp)){
            items(tabsItems.size){
                val category = tabsItems[it]
                CategoryTab(category = category.categoryName,
                    onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }
        if(newsManager.getArticleCategory.value.articles != null){
            ArticleContent(articles = newsManager.getArticleCategory.value.articles!!)
        } else {
            onFetchCategory(ArticleCategory.GENERAL.categoryName)
        }
    }
}
@Composable
fun CategoryTab(category: String, isSelected: Boolean = false,
                onFetchCategory: (String) -> Unit){
    val background =
        if(isSelected) Purple200
        else Purple700

    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 12.dp)
            .clickable {
                onFetchCategory(category)
            },
        shape = MaterialTheme.shapes.small,
        color = background
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }


}

@Composable
fun ArticleContent(articles: List<Article>, modifier: Modifier = Modifier){
    LazyColumn{
        items(articles){
            article ->
            Card(modifier = Modifier.padding(8.dp),
                border = BorderStroke(width = 2.dp, color = Purple500),
            ) {

                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center){

                    CoilImage(imageModel = article.urlToImage,
                        modifier = Modifier
                            .size(100.dp),
                        placeHolder = painterResource(id = R.drawable.breaking_news),
                    )

                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        Text(text = article.title ?: "Not Available",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            maxLines = 3, overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Justify
                        )
                        Row(modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween)
                        {
                            Text(text= article.author?:"Not Available",
                                overflow = TextOverflow.Clip, maxLines = 1,
                                fontSize = 16.sp)
                        }


                    }

                }

                Surface(color = Color.Black, modifier = Modifier.alpha(0.7f), ) {
                    Text(text= if(article.publishedAt != null) MockNewsModel.stringToDate(article.publishedAt).getTimeAgo() else "Not Available", overflow = TextOverflow.Clip,
                        modifier = Modifier.padding(4.dp), fontSize = 12.sp, color = Color.White)
                }

            }
        }
    }
}