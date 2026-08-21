package com.example.rwbydnd.characterCreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rwbydnd.CharacterCreationPg2
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState
import com.example.rwbydnd.Species

class CharacterCreatorPg1
{
    @Composable
    fun CharacterCreationPg1(navController: NavController, state: CharacterState, onEvent: (CharacterEvent) -> Unit)
    {
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
        var species by remember { mutableStateOf("Human") }
        var selectedVariant by remember {mutableStateOf("Select Variant")}
        var showError by remember {mutableStateOf("")}
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if(state.characters.any{it.characterName == state.characterName})
                    {
                        showError = "character with this name already exists"
                    }
                    else
                    {
                        onEvent(CharacterEvent.NewCharacter)
                        navController.navigate(CharacterCreationPg2)
                    }
                }
            ) {
                Icon(imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Next",)
            }
        })
        { innerPadding ->
            val scrollState = rememberScrollState()
            if(showError.isNotEmpty())
            {
                CharacterCreationAlert().CharacterCreatorError(
                    errorText = showError,
                    onDismiss = {showError = ""}
                )
            }
            Column(Modifier.padding(innerPadding).verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                {
                    Text(text = "Step 1 - Name and Appearance",
                        style = MaterialTheme.typography.titleLarge)
                }
                var labelText by remember {mutableStateOf("Character Name")}
                var error by remember {mutableStateOf(false)}
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.characterName,
                    onValueChange = {
                        val name = it
                        if(state.characters.any{it.characterName == name})
                        {
                            labelText = "Character with same name already exists"
                            error = true
                            onEvent(CharacterEvent.SetCharacterName(it))
                        }
                        else
                        {
                            labelText = "Character Name"
                            error = false
                            onEvent(CharacterEvent.SetCharacterName(it))
                        }
                    },
                    placeholder = {
                        Text(text = "Character Name")
                    },
                    isError = error,
                    label = {Text(text = labelText)},
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        errorBorderColor = Color.Red
                    )
                )
                Row(
                    Modifier.fillMaxWidth()
                )
                {
                    val radioOptions = listOf("Human", "Faunus")
                    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
                    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
                    Column(Modifier.selectableGroup()) {
                        Text(text = "Species", modifier = Modifier.padding(start = 20.dp))
                        radioOptions.forEach { text ->
                            Row(
                                Modifier
                                    .height(38.dp)
                                    .selectable(
                                        selected = (text == selectedOption),
                                        onClick = { onOptionSelected(text)
                                            species = text
                                            selectedVariant = "Select Variant"
                                            onEvent(CharacterEvent.SetSpecies(null))},
                                        role = RadioButton
                                    )
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (text == selectedOption),
                                    onClick = null // null recommended for accessibility with screen readers
                                )
                                Text(
                                    text = text,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
                    var expanded by remember { mutableStateOf(false) }
                    Box(Modifier.padding(top = 36.dp))
                    {
                        TextButton(onClick = {expanded = !expanded}, Modifier.fillMaxWidth())
                        {
                            Text(text = selectedVariant)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            if(species == "Human")
                            {
                                DropdownMenuItem(
                                    text = { Text("+1 Strength") },
                                    onClick = {
                                        selectedVariant = "+1 Strength"
                                        onEvent(CharacterEvent.SetSpecies(Species.HUMAN_STR))
                                        expanded = !expanded
                                    },
                                    Modifier.padding(end = 128.dp)
                                )
                                DropdownMenuItem(
                                    text = { Text("+1 Dexterity") },
                                    onClick = {
                                        selectedVariant = "+1 Dexterity"
                                        onEvent(CharacterEvent.SetSpecies(Species.HUMAN_DEX))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+1 Intelligence") },
                                    onClick = {
                                        selectedVariant = "+1 Intelligence"
                                        onEvent(CharacterEvent.SetSpecies(Species.HUMAN_INT))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+1 Wisdom") },
                                    onClick = {
                                        selectedVariant = "+1 Wisdom"
                                        onEvent(CharacterEvent.SetSpecies(Species.HUMAN_WIS))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+1 Constitution") },
                                    onClick = {
                                        selectedVariant = "+1 Constitution"
                                        onEvent(CharacterEvent.SetSpecies(Species.HUMAN_CON))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+1 Charisma") },
                                    onClick = {
                                        selectedVariant = "+1 Charisma"
                                        onEvent(CharacterEvent.SetSpecies(Species.HUMAN_CHA))
                                        expanded = !expanded
                                    }
                                )
                            }
                            else
                            {
                                DropdownMenuItem(
                                    text = { Text("+3 Strength, -1 All Other Stats") },
                                    onClick = {
                                        selectedVariant = "+3 Strength, -1 All Other Stats"
                                        onEvent(CharacterEvent.SetSpecies(Species.FAUNUS_STR))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+3 Dexterity, -1 All Other Stats") },
                                    onClick = {
                                        selectedVariant = "+3 Dexterity, -1 All Other Stats"
                                        onEvent(CharacterEvent.SetSpecies(Species.FAUNUS_DEX))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+3 Intelligence, -1 All Other Stats") },
                                    onClick = {
                                        selectedVariant = "+3 Intelligence, -1 All Other Stats"
                                        onEvent(CharacterEvent.SetSpecies(Species.FAUNUS_INT))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+3 Wisdom, -1 All Other Stats") },
                                    onClick = {
                                        selectedVariant = "+3 Wisdom, -1 All Other Stats"
                                        onEvent(CharacterEvent.SetSpecies(Species.FAUNUS_WIS))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+3 Constitution, -1 All Other Stats") },
                                    onClick = {
                                        selectedVariant = "+3 Constitution, -1 All Other Stats"
                                        onEvent(CharacterEvent.SetSpecies(Species.FAUNUS_CON))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+3 Charisma, -1 All Other Stats") },
                                    onClick = {
                                        selectedVariant = "+3 Charisma, -1 All Other Stats"
                                        onEvent(CharacterEvent.SetSpecies(Species.FAUNUS_CHA))
                                        expanded = !expanded
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("+Night Vision") },
                                    onClick = {
                                        selectedVariant = "+Night Vision"
                                        onEvent(CharacterEvent.SetSpecies(Species.FAUNUS_NV))
                                        expanded = !expanded
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                    value = state.appearance,
                    onValueChange = {
                        onEvent(CharacterEvent.SetAppearance(it))
                    },
                    placeholder = {
                        Text(text = "Describe Character Appearance")
                    },
                    label = {Text(text = "Appearance")},
                    minLines = 5
                )
            }
        }
    }
}

