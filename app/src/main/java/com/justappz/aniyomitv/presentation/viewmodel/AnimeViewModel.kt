package com.justappz.aniyomitv.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justappz.aniyomitv.domain.model.AnimeUiState
import com.justappz.aniyomitv.domain.usecase.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnimeUiState>(AnimeUiState.Loading)
    val uiState: StateFlow<AnimeUiState> = _uiState

    fun fetchAnimeList(
    ) {
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
}