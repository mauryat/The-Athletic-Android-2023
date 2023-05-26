package com.theathletic.interview.injection

import com.theathletic.interview.articles.data.ArticleRepository
import com.theathletic.interview.articles.data.remote.ArticleApi
import com.theathletic.interview.articles.ui.ArticlesViewModel
import com.theathletic.interview.authors.data.Author
import com.theathletic.interview.authors.data.AuthorRepository
import com.theathletic.interview.authors.data.remote.AuthorApi
import com.theathletic.interview.leagues.LeagueRepository
import com.theathletic.interview.leagues.LeaguesViewModel
import com.theathletic.interview.leagues.data.remote.LeagueApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val baseModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://mobile-interview-backend.theathletic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(ArticleApi::class.java) }

    factory { get<Retrofit>().create(AuthorApi::class.java) }

    factory { get<Retrofit>().create(LeagueApi::class.java) }

    single { ArticleRepository(get()) }

    single { AuthorRepository(get(), get()) }

    single { LeagueRepository(get()) }

    viewModel { ArticlesViewModel(get(), get()) }

    viewModel { LeaguesViewModel(get()) }

    single { HashMap<String, Author>() } // TODO: init map
}