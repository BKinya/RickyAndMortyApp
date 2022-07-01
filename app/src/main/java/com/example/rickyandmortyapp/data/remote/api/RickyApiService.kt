package com.example.rickyandmortyapp.data.remote.api

import com.example.rickyandmortyapp.models.CharacterResponse
import retrofit2.http.GET

interface RickyApiService {

    @GET("character/?page=1")
    suspend fun getCharacters(): CharacterResponse
}