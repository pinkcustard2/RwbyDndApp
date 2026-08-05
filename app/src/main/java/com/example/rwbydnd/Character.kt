package com.example.rwbydnd

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "Characters")
data class Character
(
        @PrimaryKey(autoGenerate = true)
        var characterId: Int = 0,
        var characterName: String,
        var favourite: Boolean,
        var species: Species?,
        var appearance: String,
        var weaponName: String,
        var weaponType1: String,
        var weaponType2: String,
        var weaponType3: String,
        var weaponDescription: String,
        var semblanceName: String,
        var semblanceDescription: String,
        var semblanceStrength: Int,
        var skillPoints: Int,
        var currentHealth: Int,
        var currentAura: Int,
        var maxAura: Int,
        var credits: Int,
        var proficiencyBonus: Int
)

data class Stats
(
        var characterId: Int
)

data class Proficiencies
(
        var characterId: Int
)

data class Backstory
(
        var characterId: Int
)
