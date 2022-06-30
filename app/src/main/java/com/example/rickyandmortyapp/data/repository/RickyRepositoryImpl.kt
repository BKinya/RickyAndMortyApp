package com.example.rickyandmortyapp.data.repository

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import com.example.rickyandmortyapp.data.dataSources.FUN_FACT_KEY
import com.example.rickyandmortyapp.data.dataSources.NAME_KEY
import com.example.rickyandmortyapp.data.dataSources.dataStore
import com.example.rickyandmortyapp.domain.repository.RickyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RickyRepositoryImpl() : RickyRepository {
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