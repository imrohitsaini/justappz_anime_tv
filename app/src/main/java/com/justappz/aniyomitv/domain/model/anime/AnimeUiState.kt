package com.justappz.aniyomitv.domain.model.anime

sealed class AnimeUiState {
    object Loading : AnimeUiState()
    data class Success(val data: List<AnimeDomain>) : AnimeUiState()
    data class Error(val message: String) : AnimeUiState()
}