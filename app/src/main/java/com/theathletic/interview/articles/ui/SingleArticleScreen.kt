package com.theathletic.interview.articles.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SingleArticleScreen(articleId: String?) {
    Text(text = "Single Article Screen for article: $articleId")
}