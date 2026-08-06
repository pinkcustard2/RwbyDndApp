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

    @Query("SELECT * FROM Characters ORDER BY favourite DESC, characterName")
    fun getCharacters(): Flow<List<Character>>

    @Query("UPDATE Characters \n" +
            "    SET favourite = :favourite \n" +
            "    WHERE characterId = :id")
    suspend fun updateFavourite(id: Int, favourite: Boolean)

    @Query("SELECT * \n" +
            "FROM Characters \n" +
            "WHERE characterId = :id")
    suspend fun getCharacterFromId(id: Int): Character

    @Query("SELECT * \n" +
            "FROM Characters \n" +
            "WHERE characterName = :characterName")
    suspend fun getCharacterFromCharacterName(characterName: String): Character
}