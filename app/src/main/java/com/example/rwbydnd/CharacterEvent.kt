package com.example.rwbydnd

sealed interface CharacterEvent
{
    object NewCharacter: CharacterEvent
    data class SetCharacterName(val characterName: String): CharacterEvent
    data class DeleteCharacter(val character: Character): CharacterEvent
}