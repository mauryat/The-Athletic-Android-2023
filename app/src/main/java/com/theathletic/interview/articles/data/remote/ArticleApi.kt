package com.theathletic.interview.articles.data.remote

import retrofit2.http.GET

interface ArticleApi {
    @GET("articles")
    suspend fun getArticles(): List<ArticleApiModel>
}

