package com.example.rickyandmortyapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class Character(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    var createdAt: Long?
)