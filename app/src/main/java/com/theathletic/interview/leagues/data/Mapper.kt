package com.theathletic.interview.leagues.data

import com.theathletic.interview.articles.data.Article.Companion.UNKNOWN
import com.theathletic.interview.leagues.data.remote.LeagueApiModel
import com.theathletic.interview.leagues.ui.LeagueUiModel
import java.text.SimpleDateFormat
import java.util.Locale


fun LeagueApiModel.toDomain() = League(
    shortName = shortName ?: UNKNOWN,
    updatedAt = updatedAt ?: UNKNOWN,
    deletedAt = deletedAt ?: UNKNOWN,
    title = title,
    sportType = sportType ?: UNKNOWN,
    id = id,
    name = name ?: UNKNOWN,
    createdAt = createdAt ?: UNKNOWN,
    imageUrl = imageUrlString ?: UNKNOWN
)

fun League.toUiModel() = LeagueUiModel(
    title = title,
    imageUrl = imageUrl,
    updatedAt = format(updatedAt),
)

fun format(updatedAt: String): String? {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("d MMM yyyy", Locale.US)
    val parsedDate = inputFormat.parse(updatedAt)

    return parsedDate?.let { outputFormat.format(parsedDate) }
}
