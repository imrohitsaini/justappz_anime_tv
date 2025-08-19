package com.justappz.aniyomitv.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justappz.aniyomitv.domain.model.anime.AnimeUiState
import com.justappz.aniyomitv.domain.model.episodes.EpisodesUiState
import com.justappz.aniyomitv.domain.usecase.GetAnimeListUseCase
import com.justappz.aniyomitv.domain.usecase.GetEpisodeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase, private val getEpisodesListUseCase: GetEpisodeListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnimeUiState>(AnimeUiState.Loading)
    val uiState: StateFlow<AnimeUiState> = _uiState

    fun fetchAnimeList() {
        viewModelScope.launch {
            _uiState.value = AnimeUiState.Loading
            try {
                val animeList = getAnimeListUseCase()
                _uiState.value = AnimeUiState.Success(animeList)
            } catch (e: Exception) {
                _uiState.value = AnimeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private val _uiEpisodesState = MutableStateFlow<EpisodesUiState>(EpisodesUiState.Loading)
    val uiEpisodesState: StateFlow<EpisodesUiState> = _uiEpisodesState

    fun fetchEpisodesList(queryId: String) {
        viewModelScope.launch {
            _uiEpisodesState.value = EpisodesUiState.Loading
            try {
                val episodesList = getEpisodesListUseCase(queryId)
                _uiEpisodesState.value = EpisodesUiState.Success(episodesList)
            } catch (e: Exception) {
                _uiEpisodesState.value = EpisodesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}