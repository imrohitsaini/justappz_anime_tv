package com.justappz.aniyomitv.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justappz.aniyomitv.domain.model.anime.AnimeUiState
import com.justappz.aniyomitv.domain.model.episodes.EpisodesUiState
import com.justappz.aniyomitv.domain.model.streams.StreamsUiState
import com.justappz.aniyomitv.domain.usecase.GetAnimeListUseCase
import com.justappz.aniyomitv.domain.usecase.GetEpisodeListUseCase
import com.justappz.aniyomitv.domain.usecase.GetStreamsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val getEpisodesListUseCase: GetEpisodeListUseCase,
    private val getStreamsListUseCase: GetStreamsListUseCase
) : ViewModel() {

    private val _animeState = MutableStateFlow<AnimeUiState>(AnimeUiState.Loading)
    val animeState: StateFlow<AnimeUiState> = _animeState

    fun fetchAnimeList() {
        viewModelScope.launch {
            _animeState.value = AnimeUiState.Loading
            try {
                val animeList = getAnimeListUseCase()
                _animeState.value = AnimeUiState.Success(animeList)
            } catch (e: Exception) {
                _animeState.value = AnimeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private val _episodeListState = MutableStateFlow<EpisodesUiState>(EpisodesUiState.Loading)
    val episodesState: StateFlow<EpisodesUiState> = _episodeListState

    fun fetchEpisodesList(queryId: String) {
        viewModelScope.launch {
            _episodeListState.value = EpisodesUiState.Loading
            try {
                val episodesList = getEpisodesListUseCase(queryId)
                _episodeListState.value = EpisodesUiState.Success(episodesList)
            } catch (e: Exception) {
                _episodeListState.value = EpisodesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private val _streamsListState = MutableStateFlow<StreamsUiState>(StreamsUiState.Loading)
    val streamsState: StateFlow<StreamsUiState> = _streamsListState

    fun fetchStreamsList(showId: String, episodeString: String) {
        viewModelScope.launch {
            _streamsListState.value = StreamsUiState.Loading
            try {
                val streamsList = getStreamsListUseCase(showId, episodeString)
                _streamsListState.value = StreamsUiState.Success(streamsList)
            } catch (e: Exception) {
                _streamsListState.value = StreamsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }


}