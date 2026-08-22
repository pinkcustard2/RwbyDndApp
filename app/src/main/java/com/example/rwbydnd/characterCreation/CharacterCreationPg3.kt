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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.example.rwbydnd.CharacterCreationPg2
import com.example.rwbydnd.CharacterCreationPg4
import com.example.rwbydnd.MainMenu
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState

class CharacterCreatorPg3
{
    @Composable
    fun CharacterCreationPg3(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit) {
        LaunchedEffect(Unit) {
            if(state.characterId != 0 && state.characterName.isEmpty())
            {
                onEvent(CharacterEvent.SetCharacterFromId(state.characterId))
            }
            else if(state.characterName.isNotEmpty() && state.characterId == 0)
            {
                onEvent(CharacterEvent.SetCharacterFromName(state.characterName))
            }
            if(state.skillPoints != -1)
            {
                onEvent(CharacterEvent.SetSkillPoints(-1))
            }
        }
        var showAlert by remember {mutableStateOf("")}
        Scaffold(modifier = Modifier.fillMaxSize())
        { innerPadding ->
            val scrollState = rememberScrollState()
            if(showAlert.isNotEmpty())
            {
                CharacterCreationAlert().CharacterCreatorAlert(
                    alertText = showAlert,
                    onDismiss = {showAlert = ""}
                )
            }
            var showMenuAlert by remember { mutableStateOf(false) }
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
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                {
                    Text(text = "Step 3 - Semblance",
                        style = MaterialTheme.typography.titleLarge)

                    IconButton(onClick = {
                        showMenuAlert = true
                    }, Modifier.align(Alignment.CenterEnd)) {
                        Icon(imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",)
                    }
                }
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.semblanceName,
                    onValueChange = {
                        onEvent(CharacterEvent.SetSemblanceName(it))
                    },
                    placeholder = {
                        Text(text = "Semblance Name")
                    },
                    label = {Text(text = "Semblance Name")},
                    singleLine = true
                )
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.semblanceDescription,
                    onValueChange = {
                        onEvent(CharacterEvent.SetSemblanceDescription(it))
                    },
                    placeholder = {
                        Text(text = "Describe Semblance")
                    },
                    label = {Text(text = "Semblance Description")},
                    minLines = 5
                )
                var expanded by remember { mutableStateOf(false) }
                var selectedStrength by remember {mutableStateOf("Select Variant")}
                TextButton(onClick = {expanded = !expanded}, Modifier.padding(5.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth())
                {
                    Text(text = selectedStrength)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("No semblance (+10 starting SP)") },
                        onClick = {
                            selectedStrength = "No semblance (+10 starting SP)"
                            onEvent(CharacterEvent.SetSemblanceStrength(10))
                            expanded = !expanded
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Tier 1 (+5 starting SP)") },
                        onClick = {
                            selectedStrength = "Tier 1 (+5 starting SP)"
                            onEvent(CharacterEvent.SetSemblanceStrength(5))
                            expanded = !expanded
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Tier 2 (+0 starting SP)") },
                        onClick = {
                            selectedStrength = "Tier 2 (+0 starting SP)"
                            onEvent(CharacterEvent.SetSemblanceStrength(0))
                            expanded = !expanded
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Tier 3 (-5 starting SP)") },
                        onClick = {
                            selectedStrength = "Tier 3 (-5 starting SP)"
                            onEvent(CharacterEvent.SetSemblanceStrength(-5))
                            expanded = !expanded
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Tier 4 (-10 starting SP)") },
                        onClick = {
                            selectedStrength = "Tier 4 (-10 starting SP)"
                            onEvent(CharacterEvent.SetSemblanceStrength(-10))
                            expanded = !expanded
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Tier 5 (-15 starting SP)") },
                        onClick = {
                            selectedStrength = "Tier 5 (-15 starting SP)"
                            onEvent(CharacterEvent.SetSemblanceStrength(-15))
                            expanded = !expanded
                        }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Button(onClick = {
                        onEvent(CharacterEvent.NewCharacter)
                        navController.navigate(CharacterCreationPg2)
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
                        if(state.semblanceStrength == -1 && state.species == null)
                        {
                            showAlert = "character species/varient and semblance strength have not been selected"
                        }
                        else if(state.semblanceStrength == -1)
                        {
                            showAlert = "semblance strength has not been selected"
                        }
                        else if(state.species == null)
                        {
                            showAlert = "character species/varient has not been selected"
                        }
                        else
                        {
                            navController.navigate(CharacterCreationPg4)
                        }
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