package com.example.rwbydnd.database

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.PrimaryKey
import com.example.rwbydnd.Species

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

@Entity(tableName = "Stats",
        foreignKeys = [
                ForeignKey(
                        entity = Character::class,
                        parentColumns = ["characterId"],
                        childColumns = ["characterId"],
                        onDelete = ForeignKey.CASCADE
                )
        ])
data class Stats
(
        @PrimaryKey
        var characterId: Int,
        var strength: Int,
        var dexterity: Int,
        var intelligence: Int,
        var wisdom: Int,
        var constitution: Int,
        var charisma: Int
)

@Entity(tableName = "Proficiencies",
        foreignKeys = [
                ForeignKey(
                        entity = Character::class,
                        parentColumns = ["characterId"],
                        childColumns = ["characterId"],
                        onDelete = ForeignKey.CASCADE
                )
        ])
data class Proficiencies
(
        @PrimaryKey
        var characterId: Int,
        var strength: Boolean,
        var athletics: Boolean,
        var dexterity: Boolean,
        var acrobatics: Boolean,
        var sleightOfHand: Boolean,
        var stealth: Boolean,
        var intelligence: Boolean,
        var arcana: Boolean,
        var history: Boolean,
        var investigation: Boolean,
        var nature: Boolean,
        var religion: Boolean,
        var wisdom: Boolean,
        var animalHandling: Boolean,
        var insight: Boolean,
        var medicine: Boolean,
        var perception: Boolean,
        var survival: Boolean,
        var constitution: Boolean,
        var charisma: Boolean,
        var deception: Boolean,
        var intimidation: Boolean,
        var performance: Boolean,
        var persuasion: Boolean
)

data class Backstory
(
        var characterId: Int
)
