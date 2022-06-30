package com.example.rickyandmortyapp.data.dataSources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userPreferences")

val NAME_KEY = stringPreferencesKey("name")
val FUN_FACT_KEY = stringPreferencesKey("funFact")