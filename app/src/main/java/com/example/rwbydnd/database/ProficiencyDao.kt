package com.example.rwbydnd.database

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert

@Dao
interface ProficiencyDao
{
    @Upsert
    suspend fun upsertProficiencies(proficiencies: Proficiencies)

    @Query("SELECT * \n" +
            "FROM Proficiencies \n" +
            "WHERE characterId = :id")
    suspend fun getProficienciesFromId(id: Int): Proficiencies?
}