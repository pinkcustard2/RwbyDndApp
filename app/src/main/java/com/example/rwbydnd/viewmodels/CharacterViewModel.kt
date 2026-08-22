package com.example.rwbydnd.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rwbydnd.database.Character
import com.example.rwbydnd.database.character.CharacterDao
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(private val characterDao: CharacterDao): ViewModel()
{
    private val _state = MutableStateFlow(CharacterState())
    val state = _state.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        CharacterState()
    )

    init {
        viewModelScope.launch {
            characterDao.getCharacters().collect { characters ->
                _state.update {
                    it.copy(
                        characters = characters
                    )
                }
            }
        }
    }

    fun onEvent(event: CharacterEvent)
    {
        when(event) {
            is CharacterEvent.DeleteCharacter -> {
                viewModelScope.launch {
                    characterDao.deleteCharacter(event.character)
                }
            }

            CharacterEvent.NewCharacter -> {
                val characterName = state.value.characterName
                val characterId = state.value.characterId
                val favourite = state.value.favourite
                val species = state.value.species
                val appearance = state.value.appearance
                val weaponName = state.value.weaponName
                val weaponType1 = state.value.weaponType1
                val weaponType2 = state.value.weaponType2
                val weaponType3 = state.value.weaponType3
                val weaponDescription = state.value.weaponDescription
                val semblanceName = state.value.semblanceName
                val semblanceDescription = state.value.semblanceDescription
                val semblanceStrength = state.value.semblanceStrength
                val skillPoints = state.value.skillPoints
                val currentHealth = state.value.currentHealth
                val currentAura = state.value.currentAura
                val maxAura = state.value.maxAura
                val credits = state.value.credits
                val proficiencyBonus = state.value.proficiencyBonus

                if (characterName.isBlank()) {
                    return
                }

                val character = Character(
                    characterId = characterId,
                    characterName = characterName,
                    favourite = favourite,
                    species = species,
                    appearance = appearance,
                    weaponName = weaponName,
                    weaponType1 = weaponType1,
                    weaponType2 = weaponType2,
                    weaponType3 = weaponType3,
                    weaponDescription = weaponDescription,
                    semblanceName = semblanceName,
                    semblanceDescription = semblanceDescription,
                    semblanceStrength = semblanceStrength,
                    skillPoints = skillPoints,
                    currentHealth = currentHealth,
                    currentAura = currentAura,
                    maxAura = maxAura,
                    credits = credits,
                    proficiencyBonus = proficiencyBonus
                )
                viewModelScope.launch {
                    characterDao.upsertCharacter(character)
                }
            }

            CharacterEvent.NewCharacterWithStateReset -> {
                val characterName = state.value.characterName
                val characterId = state.value.characterId
                val favourite = state.value.favourite
                val species = state.value.species
                val appearance = state.value.appearance
                val weaponName = state.value.weaponName
                val weaponType1 = state.value.weaponType1
                val weaponType2 = state.value.weaponType2
                val weaponType3 = state.value.weaponType3
                val weaponDescription = state.value.weaponDescription
                val semblanceName = state.value.semblanceName
                val semblanceDescription = state.value.semblanceDescription
                val semblanceStrength = state.value.semblanceStrength
                val skillPoints = state.value.skillPoints
                val currentHealth = state.value.currentHealth
                val currentAura = state.value.currentAura
                val maxAura = state.value.maxAura
                val credits = state.value.credits
                val proficiencyBonus = state.value.proficiencyBonus

                if (characterName.isBlank()) {
                    return
                }

                val character = Character(
                    characterId = characterId,
                    characterName = characterName,
                    favourite = favourite,
                    species = species,
                    appearance = appearance,
                    weaponName = weaponName,
                    weaponType1 = weaponType1,
                    weaponType2 = weaponType2,
                    weaponType3 = weaponType3,
                    weaponDescription = weaponDescription,
                    semblanceName = semblanceName,
                    semblanceDescription = semblanceDescription,
                    semblanceStrength = semblanceStrength,
                    skillPoints = skillPoints,
                    currentHealth = currentHealth,
                    currentAura = currentAura,
                    maxAura = maxAura,
                    credits = credits,
                    proficiencyBonus = proficiencyBonus
                )
                viewModelScope.launch {
                    characterDao.upsertCharacter(character)
                }
                onEvent(CharacterEvent.ResetState)
            }

            is CharacterEvent.SetCharacterName -> {
                _state.update {
                    it.copy(
                        characterName = event.characterName
                    )
                }
            }

            is CharacterEvent.SetFavourite -> {
                viewModelScope.launch {
                    characterDao.updateFavourite(state.value.characterId, event.favourite)
                }
                _state.update {
                    it.copy(
                        characterId = 0,
                        characterName = "",
                        favourite = false,
                        species = null,
                        appearance = "",
                        weaponName = "",
                        weaponType1 = "",
                        weaponType2 = "",
                        weaponType3 = "",
                        weaponDescription = "",
                        semblanceName = "",
                        semblanceDescription = "",
                        semblanceStrength = -1,
                        skillPoints = -1,
                        currentHealth = -1,
                        currentAura = -1,
                        maxAura = -1,
                        credits = -1,
                        proficiencyBonus = -1
                    )
                }
            }

            is CharacterEvent.SetCharacterId -> {
                _state.update {
                    it.copy(
                        characterId = event.characterId
                    )
                }
            }

            is CharacterEvent.SetAppearance -> {
                _state.update {
                    it.copy(
                        appearance = event.appearance
                    )
                }
            }

            is CharacterEvent.SetSpecies -> {
                _state.update {
                    it.copy(
                        species = event.species
                    )
                }
            }

            is CharacterEvent.SetCredits -> {
                _state.update {
                    it.copy(
                        credits = event.credits
                    )
                }
            }

            is CharacterEvent.SetCurrentAura -> {
                _state.update {
                    it.copy(
                        currentAura = event.currentAura
                    )
                }
            }

            is CharacterEvent.SetCurrentHealth -> {
                _state.update {
                    it.copy(
                        currentHealth = event.currentHealth
                    )
                }
            }

            is CharacterEvent.SetMaxAura -> {
                _state.update {
                    it.copy(
                        maxAura = event.maxAura
                    )
                }
            }

            is CharacterEvent.SetProficiencyBonus -> {
                _state.update {
                    it.copy(
                        proficiencyBonus = event.proficiencyBonus
                    )
                }
            }

            is CharacterEvent.SetSemblanceDescription -> {
                _state.update {
                    it.copy(
                        semblanceDescription = event.semblanceDescription
                    )
                }
            }

            is CharacterEvent.SetSemblanceName -> {
                _state.update {
                    it.copy(
                        semblanceName = event.semblanceName
                    )
                }
            }

            is CharacterEvent.SetSemblanceStrength -> {
                _state.update {
                    it.copy(
                        semblanceStrength = event.semblanceStrength
                    )
                }
            }

            is CharacterEvent.SetSkillPoints -> {
                _state.update {
                    it.copy(
                        skillPoints = event.skillPoints
                    )
                }
            }

            is CharacterEvent.SetWeaponDescription -> {
                _state.update {
                    it.copy(
                        weaponDescription = event.weaponDescription
                    )
                }
            }

            is CharacterEvent.SetWeaponName -> {
                _state.update {
                    it.copy(
                        weaponName = event.weaponName
                    )
                }
            }

            is CharacterEvent.SetWeaponType1 -> {
                _state.update {
                    it.copy(
                        weaponType1 = event.weaponType
                    )
                }
            }

            is CharacterEvent.SetWeaponType2 -> {
                _state.update {
                    it.copy(
                        weaponType2 = event.weaponType
                    )
                }
            }

            is CharacterEvent.SetWeaponType3 -> {
                _state.update {
                    it.copy(
                        weaponType3 = event.weaponType
                    )
                }
            }

            is CharacterEvent.SetCharacterFromId -> {
                viewModelScope.launch {
                    val character = characterDao.getCharacterFromId(event.characterId)
                    _state.update {
                        it.copy(
                            characterId = character.characterId,
                            characterName = character.characterName,
                            favourite = character.favourite,
                            species = character.species,
                            appearance = character.appearance,
                            weaponName = character.weaponName,
                            weaponType1 = character.weaponType1,
                            weaponType2 = character.weaponType2,
                            weaponType3 = character.weaponType3,
                            weaponDescription = character.weaponDescription,
                            semblanceName = character.semblanceName,
                            semblanceDescription = character.semblanceDescription,
                            semblanceStrength = character.semblanceStrength,
                            skillPoints = character.skillPoints,
                            currentHealth = character.currentHealth,
                            currentAura = character.currentAura,
                            maxAura = character.maxAura,
                            credits = character.credits,
                            proficiencyBonus = character.proficiencyBonus
                        )
                    }
                }
            }

            is CharacterEvent.SetCharacterFromName -> {
                viewModelScope.launch {
                    val characterName = _state.first { it.characterName.isNotEmpty() }.characterName
                    val character = characterDao.getCharacterFromCharacterName(characterName)

                    _state.update {
                        it.copy(
                            characterId = character.characterId,
                            characterName = character.characterName,
                            favourite = character.favourite,
                            species = character.species,
                            appearance = character.appearance,
                            weaponName = character.weaponName,
                            weaponType1 = character.weaponType1,
                            weaponType2 = character.weaponType2,
                            weaponType3 = character.weaponType3,
                            weaponDescription = character.weaponDescription,
                            semblanceName = character.semblanceName,
                            semblanceDescription = character.semblanceDescription,
                            semblanceStrength = character.semblanceStrength,
                            skillPoints = character.skillPoints,
                            currentHealth = character.currentHealth,
                            currentAura = character.currentAura,
                            maxAura = character.maxAura,
                            credits = character.credits,
                            proficiencyBonus = character.proficiencyBonus
                        )
                    }
                }

            }

            CharacterEvent.ResetState -> {
                _state.update {
                    it.copy(
                        characterId = 0,
                        characterName = "",
                        favourite = false,
                        species = null,
                        appearance = "",
                        weaponName = "",
                        weaponType1 = "",
                        weaponType2 = "",
                        weaponType3 = "",
                        weaponDescription = "",
                        semblanceName = "",
                        semblanceDescription = "",
                        semblanceStrength = -1,
                        skillPoints = -1,
                        currentHealth = -1,
                        currentAura = -1,
                        maxAura = -1,
                        credits = -1,
                        proficiencyBonus = -1
                    )
                }
            }

            CharacterEvent.SetInitialSkillPoints -> {
                viewModelScope.launch {
                    val semblanceStrength =
                        _state.first { it.semblanceStrength != -1 && it.characterId != 0 }.semblanceStrength
                    if (_state.first{it.characterId != 0}.skillPoints == -1) {
                        _state.update {
                            it.copy(
                                skillPoints = 40 + semblanceStrength
                            )
                        }
                    }
                    else
                    {
                        _state.update {
                            it.copy(
                                skillPoints = 0
                            )
                        }
                    }
                }
            }
        }
    }
}