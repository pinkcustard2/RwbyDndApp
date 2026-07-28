package com.example.rwbydnd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(private val characterDao: CharacterDao): ViewModel()
{
    private val _state = MutableStateFlow(CharacterState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CharacterState())

    fun onEvent(event: CharacterEvent)
    {
        when(event)
        {
            is CharacterEvent.DeleteCharacter -> {
                viewModelScope.launch {
                    characterDao.deleteCharacter(event.character)
                }
            }
            CharacterEvent.NewCharacter -> {
                val characterName = state.value.characterName

                if (characterName.isBlank())
                {
                    return
                }

                val character = Character(
                    characterName = characterName
                )
                viewModelScope.launch {
                    characterDao.upsertCharacter(character)
                }
                _state.update {
                    it.copy(characterName = "")
                }
            }
            is CharacterEvent.SetCharacterName -> {
                _state.update { it.copy(
                    characterName = event.characterName
                ) }
            }
        }
    }
}