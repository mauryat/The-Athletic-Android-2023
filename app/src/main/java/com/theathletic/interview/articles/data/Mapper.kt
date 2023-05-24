package com.theathletic.interview.articles.data

import com.theathletic.interview.articles.data.Article.Companion.UNKNOWN
import com.theathletic.interview.articles.data.remote.ArticleApiModel
import com.theathletic.interview.articles.ui.ArticleUiModel

fun ArticleApiModel.toDomain() = Article(
    id = id,
    title = title,
    body = body,
    teamId = teamId ?: UNKNOWN,
    leagueId = leagueId ?: UNKNOWN,
    authorId = authorId ?: UNKNOWN,
    imageUrl = imageUrlString ?: ""
)

fun Article.toUiModel() = ArticleUiModel(
    title = title,
    imageUrl = imageUrl
)