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
        when(event)
        {
            is CharacterEvent.DeleteCharacter -> {
                viewModelScope.launch {
                    characterDao.deleteCharacter(event.character)
                }
            }
            CharacterEvent.NewCharacter -> {
                val characterName = state.value.characterName
                val characterId = state.value.characterId
                val favourite = state.value.favourite

                if (characterName.isBlank())
                {
                    return
                }

                val character = Character(
                    characterId = characterId,
                    characterName = characterName,
                    favourite = favourite
                )
                viewModelScope.launch {
                    characterDao.upsertCharacter(character)
                }
                _state.update {
                    it.copy(characterName = "")
                    it.copy(characterId = 0)
                    it.copy(favourite = false)
                }
            }
            is CharacterEvent.SetCharacterName -> {
                _state.update { it.copy(
                    characterName = event.characterName
                ) }
            }

            is CharacterEvent.SetFavourite -> {
                viewModelScope.launch {
                    characterDao.updateFavourite(state.value.characterId, event.favourite)
                }
                _state.update {
                    it.copy(characterName = "")
                    it.copy(characterId = 0)
                    it.copy(favourite = false)
                }
            }

            is CharacterEvent.SetCharacterId -> {
                _state.update { it.copy(
                    characterId = event.characterId
                ) }
            }
        }
    }
}