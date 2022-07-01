package com.example.rickyandmortyapp.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import com.example.rickyandmortyapp.data.local.DataStore.FUN_FACT_KEY
import com.example.rickyandmortyapp.data.local.DataStore.NAME_KEY
import com.example.rickyandmortyapp.data.local.DataStore.dataStore
import com.example.rickyandmortyapp.data.local.dao.RickyDao
import com.example.rickyandmortyapp.data.remote.api.RickyApiService
import com.example.rickyandmortyapp.domain.repository.RickyRepository
import com.example.rickyandmortyapp.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickyRepositoryImpl(
  private val rickyApiService: RickyApiService,
  private val rickyDao: RickyDao
) : RickyRepository {

  override suspend fun getCharacters(): Flow<List<Character>> {
    try {
      val response = rickyApiService.getCharacters()
      val characters = response.results
      characters.map { character ->
        character.createdAt = System.currentTimeMillis()
      }
      rickyDao.insertAll(characters)

    } catch (e: Exception) {
      Log.e("NetworkError", e.toString())
    }

    return rickyDao.getAllCharacters()
  }

  override suspend fun saveUserPreferences(name: String, funFact: String, context: Context) {
    context.dataStore.edit { preferences ->
      preferences[NAME_KEY] = name
      preferences[FUN_FACT_KEY] = funFact
    }
  }

  override fun getUserName(context: Context): Flow<String?> {
    return context.dataStore.data.map { preferences ->
      val name = preferences[NAME_KEY]
      name
    }
  }
}