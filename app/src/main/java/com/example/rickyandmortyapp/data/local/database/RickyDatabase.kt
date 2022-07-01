package com.example.rickyandmortyapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickyandmortyapp.data.local.dao.RickyDao
import com.example.rickyandmortyapp.models.Character

@Database(
    entities = [Character::class],
    version = 5
)

abstract class RickyDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: RickyDatabase? = null

        fun getInstance(context: Context): RickyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RickyDatabase::class.java,
                    "ricky_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun rickyDao(): RickyDao
}