package com.example.rickyandmortyapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.rickyandmortyapp.data.local.DataStore.FUN_FACT_KEY
import com.example.rickyandmortyapp.data.local.DataStore.NAME_KEY
import com.example.rickyandmortyapp.data.local.DataStore.dataStore
import com.example.rickyandmortyapp.data.local.dao.RickyDao
import com.example.rickyandmortyapp.data.remote.api.RickyApiService
import com.example.rickyandmortyapp.domain.repository.RickyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickyRepositoryImpl(
  private val rickyApiService: RickyApiService,
  private val rickyDao: RickyDao
) : RickyRepository {

  // TODO 3: Add methods to fetch data from the api and save to db
  // TODO 4: Add methods to read data from db

  override suspend fun saveUserPreferences(name: String, funFact: String, context: Context) {
    context.dataStore.edit{ preferences  ->
      preferences[NAME_KEY] = name
      preferences[FUN_FACT_KEY] = funFact
    }
  }

  override fun getUserName(context: Context): Flow<String?>{
  return context.dataStore.data.map { preferences ->
     val name = preferences[NAME_KEY]
     name
   }
  }
}