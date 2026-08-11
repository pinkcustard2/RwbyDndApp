package com.example.rwbydnd.database

import com.example.rwbydnd.Species

sealed interface CharacterEvent
{
    object NewCharacter: CharacterEvent
    object NewCharacterWithStateReset: CharacterEvent
    data class SetCharacterName(val characterName: String): CharacterEvent
    data class SetFavourite(val favourite: Boolean): CharacterEvent
    data class SetSpecies(val species: Species?): CharacterEvent
    data class SetAppearance(val appearance: String): CharacterEvent
    data class SetWeaponName(val weaponName: String): CharacterEvent
    data class SetWeaponType1(val weaponType: String): CharacterEvent
    data class SetWeaponType2(val weaponType: String): CharacterEvent
    data class SetWeaponType3(val weaponType: String): CharacterEvent
    data class SetWeaponDescription(val weaponDescription: String): CharacterEvent
    data class SetSemblanceName(val semblanceName: String): CharacterEvent
    data class SetSemblanceDescription(val semblanceDescription: String): CharacterEvent
    data class SetSemblanceStrength(val semblanceStrength: Int): CharacterEvent
    data class SetSkillPoints(val skillPoints: Int): CharacterEvent
    data class SetCurrentHealth(val currentHealth: Int): CharacterEvent
    data class SetCurrentAura(val currentAura: Int): CharacterEvent
    data class SetMaxAura(val maxAura: Int): CharacterEvent
    data class SetCredits(val credits: Int): CharacterEvent
    data class SetProficiencyBonus(val proficiencyBonus: Int): CharacterEvent
    data class SetCharacterId(val characterId: Int): CharacterEvent
    data class DeleteCharacter(val character: Character): CharacterEvent

    data class SetCharacterFromName(val characterName: String): CharacterEvent
    data class SetCharacterFromId(val characterId: Int): CharacterEvent
}