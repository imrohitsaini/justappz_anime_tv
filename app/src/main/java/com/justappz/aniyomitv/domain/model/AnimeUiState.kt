package com.justappz.aniyomitv.domain.model

sealed class AnimeUiState {
    object Loading : AnimeUiState()
    data class Success(val data: List<Anime>) : AnimeUiState()
    data class Error(val message: String) : AnimeUiState()
}