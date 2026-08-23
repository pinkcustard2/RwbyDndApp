package com.example.rwbydnd.characterCreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rwbydnd.CharacterCreationPg1
import com.example.rwbydnd.CharacterCreationPg3
import com.example.rwbydnd.MainMenu
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState

class CharacterCreatorPg2
{
    @Composable
    fun CharacterCreationPg2(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit) {
        LaunchedEffect(Unit) {
            if(state.characterId != 0 && state.characterName.isEmpty())
            {
                onEvent(CharacterEvent.SetCharacterFromId(state.characterId))
            }
            else if(state.characterName.isNotEmpty() && state.characterId == 0)
            {
                onEvent(CharacterEvent.SetCharacterFromName(state.characterName))
            }
        }
        var showMenuAlert by remember { mutableStateOf(false) }
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Step 2 - Weapon",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        showMenuAlert = true
                    }) {
                        Icon(imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",)
                    }
                }
            )
        })
        {
            innerPadding ->
            val scrollState = rememberScrollState()
            if(showMenuAlert)
            {
                AlertDialog(
                    confirmButton = { TextButton(onClick = {
                        onEvent(CharacterEvent.NewCharacter)
                        navController.navigate(MainMenu)
                    }) {Text("Return Home")} },
                    dismissButton = { TextButton(onClick = { showMenuAlert = false }) {Text("Cancel")} },
                    onDismissRequest = { showMenuAlert = false },
                    title = {Text("Return Home")},
                    text = {Text("Are you sure you want to return home (entered data will be saved)")}
                )
            }
            Column(Modifier.padding(innerPadding).verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp))
            {
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
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Button(onClick = {
                        onEvent(CharacterEvent.NewCharacter)
                        navController.navigate(CharacterCreationPg1)
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ))
                    {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back",
                        )
                        Text(text = "Back")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {
                        onEvent(CharacterEvent.NewCharacter)
                        navController.navigate(CharacterCreationPg3)
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ))
                    {
                        Text(text = "Next")
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                            contentDescription = "Next",
                        )
                    }
                }
            }
        }
    }
}