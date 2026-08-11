package com.example.rwbydnd.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rwbydnd.database.Stats
import com.example.rwbydnd.database.StatsDao
import com.example.rwbydnd.database.StatsEvent
import com.example.rwbydnd.database.StatsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StatsViewModel(private val statsDao: StatsDao): ViewModel()
{
    private val _state = MutableStateFlow(StatsState())
    val state = _state.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        StatsState()
    )

    fun onEvent(event: StatsEvent)
    {
        when(event)
        {
            StatsEvent.NewStats -> {
                val characterId = state.value.characterId
                val strength = state.value.strength
                val dexterity = state.value.dexterity
                val intelligence = state.value.intelligence
                val wisdom = state.value.wisdom
                val constitution = state.value.constitution
                val charisma = state.value.charisma

                if (characterId == 0)
                {
                    return
                }

                val stats = Stats(
                    characterId = characterId,
                    strength = strength,
                    dexterity = dexterity,
                    intelligence = intelligence,
                    wisdom = wisdom,
                    constitution = constitution,
                    charisma = charisma
                )
                
                viewModelScope.launch {
                    statsDao.upsertStats(stats)
                }
            }
            is StatsEvent.SetCharacterId -> {
                _state.update { it.copy(
                    characterId = event.characterId
                ) }
            }
            is StatsEvent.SetCharisma -> {
                _state.update { it.copy(
                    charisma = event.charisma
                ) }
            }
            is StatsEvent.SetConstitution -> {
                _state.update { it.copy(
                    constitution = event.constitution
                ) }
            }
            is StatsEvent.SetDexterity -> {
                _state.update { it.copy(
                    dexterity = event.dexterity
                ) }
            }
            is StatsEvent.SetIntelligence -> {
                _state.update { it.copy(
                    intelligence = event.intelligence
                ) }
            }
            is StatsEvent.SetStatsFromId -> {
                viewModelScope.launch {
                    val stats = statsDao.getStatsFromId(event.characterId)
                    _state.update {
                        it.copy(
                            characterId = stats.characterId,
                            strength = stats.strength,
                            dexterity = stats.dexterity,
                            intelligence = stats.intelligence,
                            wisdom = stats.wisdom,
                            constitution = stats.constitution,
                            charisma = stats.charisma
                        )
                    }
                }
            }
            is StatsEvent.SetStrength -> {
                _state.update { it.copy(
                    strength = event.strength
                ) }
            }
            is StatsEvent.SetWisdom -> {
                _state.update { it.copy(
                    wisdom = event.wisdom
                ) }
            }
        }
    }
}