package com.example.rwbydnd

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Query
import androidx.room3.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao
{
    @Upsert
    suspend fun upsertCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM CharacterTest ORDER BY favourite DESC, characterName")
    fun getCharacters(): Flow<List<Character>>

    @Query("UPDATE CharacterTest \n" +
            "    SET favourite = :favourite \n" +
            "    WHERE characterId = :id")
    suspend fun updateFavourite(id: Int, favourite: Boolean)
}