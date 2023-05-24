package com.theathletic.interview.authors.data.remote

import com.theathletic.interview.articles.data.remote.ArticleApiModel
import retrofit2.http.GET

interface AuthorApi {
    @GET("authors")
    suspend fun getAuthors(): List<AuthorApiModel>
}