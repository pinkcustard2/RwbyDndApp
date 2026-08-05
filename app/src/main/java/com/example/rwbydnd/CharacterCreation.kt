package com.example.rwbydnd

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

class CharacterCreationScreen
{
    @Composable
    fun CharacterCreationPg1(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit)
    {
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(CharacterEvent.NewCharacter)
                }
            ) {
                Icon(imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Next",)
            }
        })
        { innerPadding ->
            OutlinedTextField(
                modifier = Modifier.padding(innerPadding),
                value = state.characterName,
                onValueChange = {
                    onEvent(CharacterEvent.SetCharacterName(it))
                },
                placeholder = {
                    Text(text = "Character Name")
                },
                label = {Text(text = "Character Name")},
            )
        }
    }
}

