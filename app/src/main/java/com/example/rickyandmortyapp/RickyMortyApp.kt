package com.example.rickyandmortyapp

import android.app.Application
import com.example.rickyandmortyapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RickyMortyApp : Application() {

    companion object {
        lateinit var INSTANCE: RickyMortyApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@RickyMortyApp)
            modules(appModules)
        }
    }
}