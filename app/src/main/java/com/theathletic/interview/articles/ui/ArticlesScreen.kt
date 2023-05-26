package com.theathletic.interview.articles.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.theathletic.interview.core.collectWithLifecycle
import com.theathletic.interview.main.MainActivity
import com.theathletic.interview.main.navigateSingleTopTo
import com.theathletic.interview.ui.theme.Black
import com.theathletic.interview.ui.theme.White
import org.koin.androidx.compose.getViewModel

class ArticleUiModel(
    val title: String,
    val author: String? = null,
    val displayAuthor: Boolean = false,
    val imageUrl: String?,
    var authorImageUrl: String? = null,
    val updatedAt: String?
)

@Composable
fun ArticlesScreen(viewModel: ArticlesViewModel = getViewModel(), navController: NavHostController) {

    val state by viewModel.viewState.collectAsState(initial = ArticlesViewState(true, emptyList()))

    viewModel.viewEvent.collectWithLifecycle { //event->
//        when (event){
//          here you can handle one-off events
//        }
    }

    ArticlesList(showLoading = state.isLoading, models = state.articleModels, navController)
}

@Composable
fun ArticlesList(
    showLoading: Boolean,
    models: List<ArticleUiModel>,
    navController: NavHostController
) {
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
            items(models) { ArticleItem(it, navController) }
        }
    }
}

@Composable
fun ArticleItem(model: ArticleUiModel, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
            .height(200.dp)
            .clickable { navController.navigateSingleTopTo(MainActivity.Screen.SingleArticle.route) },
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
            Row {
                AsyncImage(
                    model = model.authorImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = model.author ?: "",
                    style = MaterialTheme.typography.caption,
                    color = White
                )
            }
            Text(
                text = model.updatedAt ?: "",
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
            author = "Sample Author Name",
            imageUrl = null,
            authorImageUrl = "https://cdn.theathletic.com/app/uploads/2019/09/27193448/JH_Pic.jpg",
            updatedAt = "May 24"
        ),
        rememberNavController()
    )
}