package com.theathletic.interview.articles.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theathletic.interview.articles.data.Article
import com.theathletic.interview.articles.data.ArticleRepository
import com.theathletic.interview.articles.data.toUiModel
import com.theathletic.interview.core.updateState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    articleRepository: ArticleRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(ArticlesViewState())
    private val _viewEvent = MutableSharedFlow<ArticleEvent>()
    val viewState = _viewState.asStateFlow()
    val viewEvent: Flow<ArticleEvent> = _viewEvent

    init {
        viewModelScope.launch {
            val articles = articleRepository.getArticles()
            onArticlesLoaded(articles)
        }
    }

    private fun onArticlesLoaded(articles: List<Article>) {
        _viewState.updateState {
            copy(articleModels = articles.map { it.toUiModel() }, isLoading = false)
        }
    }
}