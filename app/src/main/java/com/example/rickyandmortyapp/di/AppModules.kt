package com.example.rickyandmortyapp.di

import com.example.rickyandmortyapp.RickyMortyApp
import com.example.rickyandmortyapp.data.local.database.RickyDatabase
import com.example.rickyandmortyapp.data.remote.api.RickyApiService
import com.example.rickyandmortyapp.data.remote.api.createOkClient
import com.example.rickyandmortyapp.data.remote.api.createRetrofit
import com.example.rickyandmortyapp.data.repository.RickyRepositoryImpl
import com.example.rickyandmortyapp.domain.repository.RickyRepository
import com.example.rickyandmortyapp.presentation.RickyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
  single { RickyMortyApp.INSTANCE }
  single { RickyDatabase.getInstance(get()) }
  factory { (get() as RickyDatabase).rickyDao() }

  single { createOkClient() }
  single { createRetrofit(baseUrl = "https://rickandmortyapi.com/api/character/?page=1", get()) } // TODO 1: Replace "" with baseurl
  single<RickyApiService> {(get() as Retrofit).create(RickyApiService::class.java)  }

  factory<RickyRepository> { RickyRepositoryImpl(get(), get()) }

  viewModel { RickyViewModel(get()) }
}