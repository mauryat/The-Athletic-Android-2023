package com.theathletic.interview.articles.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SingleArticleScreen(body: String?, authorName: String?, authorImageUrl: String?) {
    Text(text = "Single Article Screen for params: $body $authorName $authorImageUrl")
}