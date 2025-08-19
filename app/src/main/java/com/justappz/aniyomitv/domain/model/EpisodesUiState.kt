package com.justappz.aniyomitv.domain.model

sealed class EpisodesUiState {
    object Loading : EpisodesUiState()
    data class Success(val data: EpisodesDomain) : EpisodesUiState()
    data class Error(val message: String) : EpisodesUiState()
}