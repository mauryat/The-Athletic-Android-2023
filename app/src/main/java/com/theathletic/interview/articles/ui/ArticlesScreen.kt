package com.theathletic.interview.articles.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.theathletic.interview.core.collectWithLifecycle
import com.theathletic.interview.ui.theme.Black
import com.theathletic.interview.ui.theme.White
import org.koin.androidx.compose.getViewModel

class ArticleUiModel(
    val title: String,
    val author: String? = null,
    val displayAuthor: Boolean = false,
    val imageUrl: String?
)

@Composable
fun ArticlesScreen(viewModel: ArticlesViewModel = getViewModel()) {

    val state by viewModel.viewState.collectAsState(initial = ArticlesViewState(true, emptyList()))

    viewModel.viewEvent.collectWithLifecycle { //event->
//        when (event){
//          here you can handle one-off events
//        }
    }

    ArticlesList(showLoading = state.isLoading, models = state.articleModels)
}

@Composable
fun ArticlesList(showLoading: Boolean, models: List<ArticleUiModel>) {
    Box {
        if (showLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            items(models) { ArticleItem(it) }
        }
    }
}

@Composable
fun ArticleItem(model: ArticleUiModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
            .height(200.dp)
    ) {
        AsyncImage(
            alpha = 0.5f,
            modifier = Modifier.fillMaxWidth(),
            model = model.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = model.title,
                style = MaterialTheme.typography.body1,
                color = White
            )
            Text(
                text = model.author ?: "",
                style = MaterialTheme.typography.caption,
                color = White
            )
        }
    }
}

@Preview(backgroundColor = 0xFFffffff, showBackground = true, name = "Article")
@Composable
fun ArticleItemPreview() {
    ArticleItem(
        ArticleUiModel(
            "Sample Title",
            imageUrl = null,
            author = "Sample Author Name"
        )
    )
}