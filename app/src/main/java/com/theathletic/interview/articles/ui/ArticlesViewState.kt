package com.theathletic.interview.articles.ui

data class ArticlesViewState(
    val isLoading: Boolean = true,
    val articleModels: List<ArticleUiModel> = emptyList()
)

sealed interface ArticleEvent {
    object ShowSomeMessage : ArticleEvent
}