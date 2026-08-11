package com.example.rwbydnd.characterCreation

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rwbydnd.CharacterCreationPg3
import com.example.rwbydnd.database.CharacterEvent
import com.example.rwbydnd.database.CharacterState

class CharacterCreatorPg2
{
    @Composable
    fun CharacterCreationPg2(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit) {
        LaunchedEffect(Unit) {
            onEvent(CharacterEvent.SetCharacterFromName(state.characterName))
        }
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(CharacterEvent.NewCharacter)
                    navController.navigate(CharacterCreationPg3)
                }
            ) {
                Icon(imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Next",)
            }
        })
        {
            innerPadding ->
            val scrollState = rememberScrollState()
            Column(Modifier.padding(innerPadding).verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                {
                    Text(text = "Step 2 - Weapon",
                        style = MaterialTheme.typography.titleLarge)
                }
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.weaponName,
                    onValueChange = {
                        onEvent(CharacterEvent.SetWeaponName(it))
                    },
                    placeholder = {
                        Text(text = "Weapon Name")
                    },
                    label = {Text(text = "Weapon Name")},
                    singleLine = true
                )
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.weaponType1,
                    onValueChange = {
                        onEvent(CharacterEvent.SetWeaponType1(it))
                    },
                    placeholder = {
                        Text(text = "Weapon Type (Melee)")
                    },
                    label = {Text(text = "Weapon Type (Melee)")},
                    singleLine = true
                )
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.weaponType2,
                    onValueChange = {
                        onEvent(CharacterEvent.SetWeaponType2(it))
                    },
                    placeholder = {
                        Text(text = "Weapon Type (Ranged)")
                    },
                    label = {Text(text = "Weapon Type (Ranged)")},
                    singleLine = true
                )
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.weaponType3,
                    onValueChange = {
                        onEvent(CharacterEvent.SetWeaponType3(it))
                    },
                    placeholder = {
                        Text(text = "Weapon Type (Optional)")
                    },
                    label = {Text(text = "Weapon Type (Optional)")},
                    singleLine = true
                )
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.weaponDescription,
                    onValueChange = {
                        onEvent(CharacterEvent.SetWeaponDescription(it))
                    },
                    placeholder = {
                        Text(text = "Describe Weapon")
                    },
                    label = {Text(text = "Weapon Description")},
                    minLines = 5
                )
            }
        }
    }
}