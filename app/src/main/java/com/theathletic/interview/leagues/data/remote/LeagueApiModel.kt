package com.theathletic.interview.leagues.data.remote

data class LeagueApiModel(
    val id: String,
    val body: String,
    val teamId: String?,
    val leagueId: String?,
    val title: String,
    val imageUrlString: String?,
    val authorId: String?,
    val updatedAt: String?,
    val createdAt: String?,
    val deletedAt: String?,
    val name: String?,
    val shortName: String?,
    val sportType: String?

)