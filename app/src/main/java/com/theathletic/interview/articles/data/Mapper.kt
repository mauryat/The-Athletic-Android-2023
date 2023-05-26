package com.theathletic.interview.articles.data

import com.theathletic.interview.articles.data.Article.Companion.UNKNOWN
import com.theathletic.interview.articles.data.remote.ArticleApiModel
import com.theathletic.interview.articles.ui.ArticleUiModel
import java.text.SimpleDateFormat
import java.util.Locale


fun ArticleApiModel.toDomain() = Article(
    id = id,
    title = title,
    body = body,
    teamId = teamId ?: UNKNOWN,
    leagueId = leagueId ?: UNKNOWN,
    authorId = authorId ?: UNKNOWN,
    imageUrl = imageUrlString ?: "",
    updatedAt = updatedAt ?: UNKNOWN
)

fun Article.toUiModel(authorName: String?, authorImageUrl: String?) = ArticleUiModel(
    title = title,
    author = authorName,
    imageUrl = imageUrl,
    authorImageUrl = authorImageUrl,
    updatedAt = format(updatedAt),
    body = body
)

fun format(updatedAt: String): String? {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("d MMM yyyy", Locale.US)
    val parsedDate = inputFormat.parse(updatedAt)

    return parsedDate?.let { outputFormat.format(parsedDate) }
}
