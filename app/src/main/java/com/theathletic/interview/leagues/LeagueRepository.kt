package com.theathletic.interview.leagues

import com.theathletic.interview.leagues.data.League
import com.theathletic.interview.leagues.data.remote.LeagueApi
import com.theathletic.interview.leagues.data.toDomain

class LeagueRepository(private val leagueApi: LeagueApi) {

    suspend fun getLeagues(): List<League> {
        return leagueApi.getLeagues().map { it.toDomain() }
    }

}
