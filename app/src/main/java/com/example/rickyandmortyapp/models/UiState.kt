package com.example.rickyandmortyapp.models

data class UiState(
    val isLoading: Boolean = false,
    val data: List<Character> = listOf(),
    val error: Boolean = false
)