package com.example.rwbydnd.database.proficiency

data class ProficiencyState(
    val characterId: Int = 0,
    val strength: Boolean = false,
    var athletics: Boolean = false,
    var dexterity: Boolean = false,
    var acrobatics: Boolean = false,
    var sleightOfHand: Boolean = false,
    var stealth: Boolean = false,
    var intelligence: Boolean = false,
    var arcana: Boolean = false,
    var history: Boolean = false,
    var investigation: Boolean = false,
    var nature: Boolean = false,
    var religion: Boolean = false,
    var wisdom: Boolean = false,
    var animalHandling: Boolean = false,
    var insight: Boolean = false,
    var medicine: Boolean = false,
    var perception: Boolean = false,
    var survival: Boolean = false,
    var constitution: Boolean = false,
    var charisma: Boolean = false,
    var deception: Boolean = false,
    var intimidation: Boolean = false,
    var performance: Boolean = false,
    var persuasion: Boolean = false
)
