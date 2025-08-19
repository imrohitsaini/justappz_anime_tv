package com.justappz.aniyomitv.domain.model

sealed class AnimeUiState {
    object Loading : AnimeUiState()
    data class Success(val data: List<AnimeDomain>) : AnimeUiState()
    data class Error(val message: String) : AnimeUiState()
}