package com.example.rwbydnd

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [Character::class],
    version = 1
)
abstract class CharacterDatabase: RoomDatabase()
{
    abstract val characterDao: CharacterDao
}