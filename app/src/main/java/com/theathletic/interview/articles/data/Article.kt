package com.theathletic.interview.articles.data

data class Article(
    val id: String,
    val body: String,
    val teamId: String,
    val leagueId: String,
    val title: String,
    val imageUrl: String,
    val authorId: String
) {
    companion object {
        const val UNKNOWN = ""
    }
}