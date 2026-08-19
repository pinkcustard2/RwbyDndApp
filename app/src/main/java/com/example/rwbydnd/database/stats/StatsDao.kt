package com.example.rwbydnd.database.stats

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert
import com.example.rwbydnd.database.Stats

@Dao
interface StatsDao
{
    @Upsert
    suspend fun upsertStats(stats: Stats)

    @Query("SELECT * \n" +
            "FROM Stats \n" +
            "WHERE characterId = :id")
    suspend fun getStatsFromId(id: Int): Stats?
}