package com.theathletic.interview.leagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theathletic.interview.authors.data.AuthorRepository
import com.theathletic.interview.core.updateState
import com.theathletic.interview.leagues.data.League
import com.theathletic.interview.leagues.data.toUiModel
import com.theathletic.interview.leagues.ui.LeagueEvent
import com.theathletic.interview.leagues.ui.LeaguesViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaguesViewModel(
    leagueRepository: LeagueRepository
    ) : ViewModel() {

    private val _viewState = MutableStateFlow(LeaguesViewState())
    private val _viewEvent = MutableSharedFlow<LeagueEvent>()
    val viewState = _viewState.asStateFlow()
    val viewEvent: Flow<LeagueEvent> = _viewEvent

    init {
        viewModelScope.launch {
            val leagues = leagueRepository.getLeagues()
            onLeaguesLoaded(leagues)
        }
    }

    private fun onLeaguesLoaded(leagues: List<League>) {
        _viewState.updateState {
            copy(leagueModels = leagues.map {
                it.toUiModel() }, isLoading = false)
        }
    }
}