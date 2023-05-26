package com.theathletic.interview.leagues.ui

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
import com.theathletic.interview.articles.ui.ArticleUiModel
import com.theathletic.interview.core.collectWithLifecycle
import com.theathletic.interview.leagues.LeaguesViewModel
import com.theathletic.interview.ui.theme.Black
import com.theathletic.interview.ui.theme.White
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class LeagueUiModel(
    val title: String,
    val imageUrl: String?,
    val updatedAt: String?,
)

@Composable
fun LeaguesScreen(
    viewModel: LeaguesViewModel = getViewModel(),
    navController: NavHostController,
    onSingleLeagueClick: (String, String) -> Unit
) {

    val state by viewModel.viewState.collectAsState(initial = LeaguesViewState(true, emptyList()))

    viewModel.viewEvent.collectWithLifecycle { //event->
//        when (event){
//          here you can handle one-off events
//        }
    }

    com.theathletic.interview.leagues.ui.LeaguesList(
        showLoading = state.isLoading,
        models = state.leagueModels,
        navController,
        onSingleLeagueClick
    )
}

@Composable
fun LeaguesList(
    showLoading: Boolean,
    models: List<LeagueUiModel>,
    navController: NavHostController,
    onSingleLeagueClick: (String, String) -> Unit
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
            items(models) { LeagueItem(it, navController, onSingleLeagueClick) }
        }
    }
}

@Composable
fun LeagueItem(
    model: LeagueUiModel,
    navController: NavHostController,
    onSingleLeagueClick: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
            .height(200.dp)
            .clickable { onSingleLeagueClick("url", model.title) },
    ) {
        AsyncImage(
            alpha = 0.5f,
            modifier = Modifier.fillMaxWidth(),
            model = model.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = model.title,
            style = MaterialTheme.typography.body1,
            color = White
        )
    }
}

@Preview(backgroundColor = 0xFFffffff, showBackground = true, name = "League")
@Composable
fun LeagueItemPreview() {
    LeagueItem(
        LeagueUiModel(
            "Sample Title",
            imageUrl = null,
            updatedAt = "May 24",
        ),
        rememberNavController(),
        onSingleLeagueClick = { articleImageUrl, title -> {}}
    )
}