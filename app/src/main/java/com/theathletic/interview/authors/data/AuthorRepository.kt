package com.theathletic.interview.authors.data

import com.theathletic.interview.articles.data.toDomain
import com.theathletic.interview.authors.data.remote.AuthorApi

class AuthorRepository(private val authorApi: AuthorApi, private val authorsMap: HashMap<String, Author>) {

    suspend fun getAuthors(): List<Author> {
        return authorApi.getAuthors().map { it.toDomain() }
    }

    fun getAuthor(authorID: String): Author? {
        return authorsMap[authorID]
    }
}
