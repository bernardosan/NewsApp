package com.bernardosan.newsapp.ui.screen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.bernardosan.newsapp.network.NewsManager

val items = listOf(
    "TechCrunch" to "techcrunch",
    "TalkSport"  to "talksport",
    "Bussiness Insider" to "bussiness-insider",
    "Reuters" to "reuters",
    "Politico" to "politico",
    "TheVerge" to "the-verge"
)

@Composable
fun Sources(newsManager: NewsManager){
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "${newsManager.sourceName.value} Source")},
        actions = {
            var menuExpanded by remember { mutableStateOf(false) }
            IconButton(onClick = {menuExpanded = true}) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp))){
                DropdownMenu(expanded = menuExpanded,
                    onDismissRequest = {menuExpanded = false}) {
                    items.forEach {
                        DropdownMenuItem(onClick = {
                            newsManager.sourceName.value = it.second
                            menuExpanded = false
                        }) {
                            Text(it.first)
                        }
                    }
                }
            }


        })
    }) {
    }
}