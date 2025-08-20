package com.justappz.aniyomitv.domain.model.streams

import com.justappz.aniyomitv.domain.model.anime.AnimeDomain

sealed class StreamsUiState {
    object Loading : StreamsUiState()
    data class Success(val data: List<StreamsDomain>) : StreamsUiState()
    data class Error(val message: String) : StreamsUiState()
}