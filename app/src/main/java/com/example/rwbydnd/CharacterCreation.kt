package com.example.rwbydnd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Box(Modifier.padding(innerPadding).fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Text(text = "Step 1 - Name and Appearance",
                    style = MaterialTheme.typography.titleLarge)
            }
            val scrollState = rememberScrollState()
            Column(Modifier.padding(innerPadding).verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 35.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.characterName,
                    onValueChange = {
                        onEvent(CharacterEvent.SetCharacterName(it))
                    },
                    placeholder = {
                        Text(text = "Character Name")
                    },
                    label = {Text(text = "Character Name")},
                    singleLine = true
                )
            }
        }
    }

    @Composable
    fun CharacterCreationPg2(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit)
    {
    }
    @Composable
    fun CharacterCreationPg3(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit)
    {
    }
    @Composable
    fun CharacterCreationPg4(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit)
    {
    }
    @Composable
    fun CharacterCreationPg5(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit)
    {
    }
}

