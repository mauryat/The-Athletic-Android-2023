package com.theathletic.interview.authors.data

import com.theathletic.interview.articles.data.Article.Companion.UNKNOWN
import com.theathletic.interview.articles.data.remote.ArticleApiModel
import com.theathletic.interview.articles.ui.ArticleUiModel
import com.theathletic.interview.authors.data.remote.AuthorApiModel

fun AuthorApiModel.toDomain() = Author(
    name = name ?: UNKNOWN,
    shortname = shortname ?: UNKNOWN,
    deletedAt = deletedAt ?: UNKNOWN,
    updatedAt = updatedAt ?: UNKNOWN,
    title = title ?: UNKNOWN,
    imageUrlString = imageUrlString ?: "",
    createdAt = createdAt ?: UNKNOWN,
    id = id ?: UNKNOWN
)

//fun Author.toUiModel() = ArticleUiModel(
//    title = title,
//    imageUrl = imageUrl
//)