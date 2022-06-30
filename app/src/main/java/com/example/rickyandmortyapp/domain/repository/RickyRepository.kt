package com.example.rickyandmortyapp.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface RickyRepository {

  suspend fun saveUserPreferences(name: String, funFact: String, context: Context)

  fun getUserName(context: Context): Flow<String?>
}