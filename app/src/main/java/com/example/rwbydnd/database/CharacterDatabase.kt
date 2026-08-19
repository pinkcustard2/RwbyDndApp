package com.example.rwbydnd.database

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [Character::class, Stats::class, Proficiencies::class],
    version = 1
)
abstract class CharacterDatabase: RoomDatabase()
{
    abstract val characterDao: CharacterDao
    abstract val statsDao: StatsDao
    abstract val proficiencyDao: ProficiencyDao
}