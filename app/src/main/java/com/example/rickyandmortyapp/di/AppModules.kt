package com.example.rickyandmortyapp.di

import com.example.rickyandmortyapp.RickyMortyApp
import com.example.rickyandmortyapp.data.database.RickyDatabase
import org.koin.dsl.module

val appModules = module {
  single { RickyMortyApp.INSTANCE }
  single { RickyDatabase.getInstance(get()) }
}