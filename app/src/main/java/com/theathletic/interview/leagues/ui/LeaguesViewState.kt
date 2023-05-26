package com.theathletic.interview.leagues.ui

data class LeaguesViewState(
    val isLoading: Boolean = true,
    val leagueModels: List<LeagueUiModel> = emptyList()
)

sealed interface LeagueEvent {
    object ShowSomeMessage : LeagueEvent
}