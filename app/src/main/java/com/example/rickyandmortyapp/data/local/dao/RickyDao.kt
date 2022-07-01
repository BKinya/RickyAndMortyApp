package com.example.rickyandmortyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickyandmortyapp.models.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface RickyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<Character>)

    @Query("SELECT * FROM characters_table ORDER BY createdAt ASC")
    fun getAllCharacters(): Flow<List<Character>>
}