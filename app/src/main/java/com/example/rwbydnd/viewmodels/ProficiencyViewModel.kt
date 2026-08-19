package com.example.rwbydnd.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rwbydnd.database.Proficiencies
import com.example.rwbydnd.database.ProficiencyDao
import com.example.rwbydnd.database.ProficiencyEvent
import com.example.rwbydnd.database.ProficiencyState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProficiencyViewModel(private val proficiencyDao: ProficiencyDao) : ViewModel()
{
    private val _state = MutableStateFlow(ProficiencyState())

    val state = _state.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        ProficiencyState()
    )

    fun onEvent(event: ProficiencyEvent) {
        when (event) {
            is ProficiencyEvent.SetAcrobatics -> {
                _state.update { it.copy(
                    acrobatics = event.acrobatics
                ) }
            }
            is ProficiencyEvent.SetAnimalHandling -> {
                _state.update { it.copy(
                    animalHandling = event.animalHandling
                ) }
            }
            is ProficiencyEvent.SetArcana -> {
                _state.update { it.copy(
                    arcana = event.arcana
                ) }
            }
            is ProficiencyEvent.SetAthletics -> {
                _state.update { it.copy(
                    athletics = event.athletics
                ) }
            }
            is ProficiencyEvent.SetCharacterId -> {
                _state.update { it.copy(
                    characterId = event.characterId
                ) }
            }
            is ProficiencyEvent.SetCharisma -> {
                _state.update { it.copy(
                    charisma = event.charisma
                ) }
            }
            is ProficiencyEvent.SetConstitution -> {
                _state.update { it.copy(
                    constitution = event.constitution
                ) }
            }
            is ProficiencyEvent.SetDeception -> {
                _state.update { it.copy(
                    deception = event.deception
                ) }
            }
            is ProficiencyEvent.SetDexterity -> {
                _state.update { it.copy(
                    dexterity = event.dexterity
                ) }
            }
            is ProficiencyEvent.SetHistory -> {
                _state.update { it.copy(
                    history = event.history
                ) }
            }
            is ProficiencyEvent.SetInsight -> {
                _state.update { it.copy(
                    insight = event.insight
                ) }
            }
            is ProficiencyEvent.SetIntelligence -> {
                _state.update { it.copy(
                    intelligence = event.intelligence
                ) }
            }
            is ProficiencyEvent.SetIntimidation -> {
                _state.update { it.copy(
                    intimidation = event.intimidation
                ) }
            }
            is ProficiencyEvent.SetInvestigation -> {
                _state.update { it.copy(
                    investigation = event.investigation
                ) }
            }
            is ProficiencyEvent.SetMedicine -> {
                _state.update { it.copy(
                    medicine = event.medicine
                ) }
            }
            is ProficiencyEvent.SetNature -> {
                _state.update { it.copy(
                    nature = event.nature
                ) }
            }
            is ProficiencyEvent.SetPerception -> {
                _state.update { it.copy(
                    perception = event.perception
                ) }
            }
            is ProficiencyEvent.SetPerformance -> {
                _state.update { it.copy(
                    performance = event.performance
                ) }
            }
            is ProficiencyEvent.SetPersuasion -> {
                _state.update { it.copy(
                    persuasion = event.persuasion
                ) }
            }
            is ProficiencyEvent.SetReligion -> {
                _state.update { it.copy(
                    religion = event.religion
                ) }
            }
            is ProficiencyEvent.SetSleightOfHand -> {
                _state.update { it.copy(
                    sleightOfHand = event.sleightOfHand
                ) }
            }
            is ProficiencyEvent.SetStealth -> {
                _state.update { it.copy(
                    stealth = event.stealth
                ) }
            }
            is ProficiencyEvent.SetStrength -> {
                _state.update { it.copy(
                    strength = event.strength
                ) }
            }
            is ProficiencyEvent.SetSurvival -> {
                _state.update { it.copy(
                    survival = event.survival
                ) }
            }
            is ProficiencyEvent.SetWisdom -> {
                _state.update { it.copy(
                    wisdom = event.wisdom
                ) }
            }
            ProficiencyEvent.newProficiency -> viewModelScope.launch {
                val characterId = _state.first {it.characterId != 0}.characterId
                val strength = state.value.strength
                val athletics = state.value.athletics
                val dexterity = state.value.dexterity
                val acrobatics = state.value.acrobatics
                val sleightOfHand = state.value.sleightOfHand
                val stealth = state.value.stealth
                val intelligence = state.value.intelligence
                val arcana = state.value.arcana
                val history = state.value.history
                val investigation = state.value.investigation
                val nature = state.value.nature
                val religion = state.value.religion
                val wisdom = state.value.wisdom
                val animalHandling = state.value.animalHandling
                val insight = state.value.insight
                val medicine = state.value.medicine
                val perception = state.value.perception
                val survival = state.value.survival
                val constitution = state.value.constitution
                val charisma = state.value.charisma
                val deception = state.value.deception
                val intimidation = state.value.intimidation
                val performance = state.value.performance
                val persuasion = state.value.persuasion

                if (characterId == 0)
                {
                    return@launch
                }

                val proficiencies = Proficiencies(
                    characterId = characterId,
                    strength = strength,
                    dexterity = dexterity,
                    intelligence = intelligence,
                    wisdom = wisdom,
                    constitution = constitution,
                    charisma = charisma,
                    athletics = athletics,
                    acrobatics = acrobatics,
                    sleightOfHand = sleightOfHand,
                    stealth = stealth,
                    arcana = arcana,
                    history = history,
                    investigation = investigation,
                    nature = nature,
                    religion = religion,
                    animalHandling = animalHandling,
                    insight = insight,
                    medicine = medicine,
                    perception = perception,
                    survival = survival,
                    deception = deception,
                    intimidation = intimidation,
                    performance = performance,
                    persuasion = persuasion
                )

                proficiencyDao.upsertProficiencies(proficiencies)
            }
            ProficiencyEvent.resetState -> {
                _state.update {
                    it.copy(
                        characterId = 0,
                        strength = false,
                        dexterity = false,
                        intelligence = false,
                        wisdom = false,
                        constitution = false,
                        charisma = false,
                        athletics = false,
                        acrobatics = false,
                        sleightOfHand = false,
                        stealth = false,
                        arcana = false,
                        history = false,
                        investigation = false,
                        nature = false,
                        religion = false,
                        animalHandling = false,
                        insight = false,
                        medicine = false,
                        perception = false,
                        survival = false,
                        deception = false,
                        intimidation = false,
                        performance = false,
                        persuasion = false
                    )
                }
            }
        }
    }
}