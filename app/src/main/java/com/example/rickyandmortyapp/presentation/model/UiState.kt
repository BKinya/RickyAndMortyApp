package com.example.rickyandmortyapp.presentation.model

import com.example.rickyandmortyapp.models.Character

sealed class UiState{
    object LoadingState: UiState()
    data class Characters(val data: List<Character>): UiState()
    data class Error(val error: String): UiState()
}

