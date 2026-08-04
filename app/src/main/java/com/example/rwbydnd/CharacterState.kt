package com.example.rwbydnd

data class CharacterState(
    val characters: List<Character> = emptyList(),
    val characterName: String = "",
    val favourite: Boolean = false,
    val characterId: Int = 0
)