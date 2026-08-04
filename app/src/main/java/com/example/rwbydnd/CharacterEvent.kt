package com.example.rwbydnd

sealed interface CharacterEvent
{
    object NewCharacter: CharacterEvent
    data class SetCharacterName(val characterName: String): CharacterEvent
    data class SetFavourite(val favourite: Boolean): CharacterEvent
    data class SetCharacterId(val characterId: Int): CharacterEvent
    data class DeleteCharacter(val character: Character): CharacterEvent
}