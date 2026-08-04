package com.example.rwbydnd

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "CharacterTest")
data class Character
(
        @PrimaryKey(autoGenerate = true)
        var characterId: Int = 0,
        var characterName: String,
        var favourite: Boolean,
        /*var characterSpecies: Int,
        var appearance: String?,
        var weaponName: String,
        var weaponType1: Int,
        var weaponType2: Int,
        var weaponType3: Int?,
        var weaponDescription: String?,
        var semblanceName: String,
        var semblanceDescription: String,
        var semblanceStrength: Int,
        var skillPoints: Int,
        var currentHealth: Int,
        var currentAura: Int,
        var coins: Int,
        var proficiencyBonus: Int*/
)

data class Stats
(
        var characterId: Int
)

data class Proficiencies
(
        var characterId: Int
)
