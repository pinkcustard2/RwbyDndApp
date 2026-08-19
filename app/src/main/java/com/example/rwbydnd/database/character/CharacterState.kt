package com.example.rwbydnd.database.character

import com.example.rwbydnd.Species
import com.example.rwbydnd.database.Character

data class CharacterState(
    val characters: List<Character> = emptyList(),
    val characterName: String = "",
    val favourite: Boolean = false,
    val species: Species? = null,
    val appearance: String = "",
    val weaponName: String = "",
    val weaponType1: String = "",
    val weaponType2: String = "",
    val weaponType3: String = "",
    val weaponDescription: String = "",
    val semblanceName: String = "",
    val semblanceDescription: String = "",
    val semblanceStrength: Int = -1,
    val skillPoints: Int = -1,
    val currentHealth: Int = -1,
    val currentAura: Int = -1,
    val maxAura: Int = -1,
    val credits: Int = -1,
    val proficiencyBonus: Int = -1,
    val characterId: Int = 0
)