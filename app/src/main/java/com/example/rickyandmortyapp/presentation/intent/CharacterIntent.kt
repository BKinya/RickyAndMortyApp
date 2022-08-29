package com.example.rickyandmortyapp.presentation.intent

sealed class CharacterIntent {
    object FetchCharacters: CharacterIntent()
}