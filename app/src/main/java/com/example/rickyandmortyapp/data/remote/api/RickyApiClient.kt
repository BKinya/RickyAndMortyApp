package com.example.rickyandmortyapp.data.remote.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
  return Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
}

fun createOkClient(): OkHttpClient {
  return OkHttpClient.Builder()
    .addInterceptor(getLoggingInterceptor())
    .build()
}

fun getLoggingInterceptor(): Interceptor {
  val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }
  return httpLoggingInterceptor
}