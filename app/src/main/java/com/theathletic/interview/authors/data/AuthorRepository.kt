package com.theathletic.interview.authors.data

import com.theathletic.interview.articles.data.toDomain
import com.theathletic.interview.authors.data.remote.AuthorApi

class AuthorRepository(val authorApi: AuthorApi) {

    suspend fun getAuthors(): List<Author> {
        return authorApi.getAuthors().map { it.toDomain() }
    }

    suspend fun getAuthor(authorID: String): Author? {
        return null
    }
}
