package com.theathletic.interview.leagues.data.remote

import com.theathletic.interview.articles.data.remote.ArticleApiModel
import retrofit2.http.GET

interface LeagueApi {
    @GET("leagues")
    suspend fun getLeagues(): List<LeagueApiModel>
}

