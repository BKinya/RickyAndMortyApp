package com.example.rickyandmortyapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickyandmortyapp.data.entity.SampleEntity

@Database(
  entities = [SampleEntity::class],
  version = 1
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
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}