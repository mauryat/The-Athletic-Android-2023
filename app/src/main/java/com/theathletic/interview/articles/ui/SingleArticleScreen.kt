package com.theathletic.interview.articles.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun SingleArticleScreen(
    body: String?,
    authorName: String?,
    authorImageUrl: String?,
    articleImageUrl: String?
) {

    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = articleImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(text = "Single Article Screen for params: $body $authorName $authorImageUrl")
    }
}