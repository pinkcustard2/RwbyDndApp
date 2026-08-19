package com.example.rwbydnd.database.proficiency

sealed interface ProficiencyEvent {
    object newProficiency: ProficiencyEvent
    object resetState: ProficiencyEvent
    data class SetCharacterId(val characterId: Int): ProficiencyEvent
    data class SetStrength(val strength: Boolean): ProficiencyEvent
    data class SetAthletics(val athletics: Boolean): ProficiencyEvent
    data class SetDexterity(val dexterity: Boolean): ProficiencyEvent
    data class SetAcrobatics(val acrobatics: Boolean): ProficiencyEvent
    data class SetSleightOfHand(val sleightOfHand: Boolean): ProficiencyEvent
    data class SetStealth(val stealth: Boolean): ProficiencyEvent
    data class SetIntelligence(val intelligence: Boolean): ProficiencyEvent
    data class SetArcana(val arcana: Boolean): ProficiencyEvent
    data class SetHistory(val history: Boolean): ProficiencyEvent
    data class SetInvestigation(val investigation: Boolean): ProficiencyEvent
    data class SetNature(val nature: Boolean): ProficiencyEvent
    data class SetReligion(val religion: Boolean): ProficiencyEvent
    data class SetWisdom(val wisdom: Boolean): ProficiencyEvent
    data class SetAnimalHandling(val animalHandling: Boolean): ProficiencyEvent
    data class SetInsight(val insight: Boolean): ProficiencyEvent
    data class SetMedicine(val medicine: Boolean): ProficiencyEvent
    data class SetPerception(val perception: Boolean): ProficiencyEvent
    data class SetSurvival(val survival: Boolean): ProficiencyEvent
    data class SetConstitution(val constitution: Boolean): ProficiencyEvent
    data class SetCharisma(val charisma: Boolean): ProficiencyEvent
    data class SetDeception(val deception: Boolean): ProficiencyEvent
    data class SetIntimidation(val intimidation: Boolean): ProficiencyEvent
    data class SetPerformance(val performance: Boolean): ProficiencyEvent
    data class SetPersuasion(val persuasion: Boolean): ProficiencyEvent
}