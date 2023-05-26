package com.theathletic.interview.leagues.data

data class League(
    val shortName: String,
    val updatedAt: String,
    val deletedAt: String,
    val title: String,
    val sportType: String,
    val id: String,
    val name: String,
    val createdAt: String,
    val imageUrl: String
) {
    companion object {
        const val UNKNOWN = ""
    }
}