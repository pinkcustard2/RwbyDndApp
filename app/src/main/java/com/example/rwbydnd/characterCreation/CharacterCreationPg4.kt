package com.example.rwbydnd.characterCreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.rwbydnd.CharacterCreationPg3
import com.example.rwbydnd.CharacterCreationPg5
import com.example.rwbydnd.MainMenu
import com.example.rwbydnd.Species
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState
import com.example.rwbydnd.database.stats.StatsEvent
import com.example.rwbydnd.database.stats.StatsState

class CharacterCreatorPg4 {
    @Composable
    fun CharacterCreationPg4(
        navController: NavController,
        characterState: CharacterState,
        statsState: StatsState,
        onCharacterEvent: (CharacterEvent) -> Unit,
        onStatsEvent: (StatsEvent) -> Unit
    ) {
        LaunchedEffect(Unit) {
            onStatsEvent(StatsEvent.ResetState)
            if(characterState.characterId != 0 && characterState.characterName.isEmpty())
            {
                onCharacterEvent(CharacterEvent.SetCharacterFromId(characterState.characterId))
            }
            else if(characterState.characterName.isNotEmpty() && characterState.characterId == 0)
            {
                onCharacterEvent(CharacterEvent.SetCharacterFromName(characterState.characterName))
            }
            onCharacterEvent(CharacterEvent.SetInitialSkillPoints)
            onStatsEvent(StatsEvent.SetCharacterId(characterState.characterId))
            onStatsEvent(StatsEvent.SetStatsFromId)
        }
        var showAlert by remember {mutableStateOf("")}
        var showMenuAlert by remember { mutableStateOf(false) }
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Step 4 - Stats",
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
        { innerPadding ->
            val scrollState = rememberScrollState()
            val stats = mutableListOf(
                "Strength" to statsState.strength,
                "Dexterity" to statsState.dexterity,
                "Intelligence" to statsState.intelligence,
                "Wisdom" to statsState.wisdom,
                "Constitution" to statsState.constitution,
                "Charisma" to statsState.charisma
            )
            var updatedSpecies by remember {mutableStateOf(false)}
            if(
                statsState.strength == 6 &&
                statsState.dexterity == 6 &&
                statsState.wisdom == 6 &&
                statsState.intelligence == 6 &&
                statsState.constitution == 6 &&
                statsState.charisma == 6 &&
                characterState.species != Species.FAUNUS_NV &&
                characterState.skillPoints == (40 + characterState.semblanceStrength)
            ) {updatedSpecies = false}

            if(!updatedSpecies && characterState.species != null)
            {
                when(characterState.species) {
                    Species.HUMAN_STR -> {
                        onStatsEvent(StatsEvent.SetStrength(statsState.strength + 1))
                    }

                    Species.HUMAN_DEX -> {
                        onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity + 1))
                    }

                    Species.HUMAN_INT -> {
                        onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence + 1))
                    }

                    Species.HUMAN_WIS -> {
                        onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom + 1))
                    }

                    Species.HUMAN_CON -> {
                        onStatsEvent(StatsEvent.SetConstitution(statsState.constitution + 1))
                    }

                    Species.HUMAN_CHA -> {
                        onStatsEvent(StatsEvent.SetCharisma(statsState.charisma + 1))
                    }

                    Species.FAUNUS_STR -> {
                        onStatsEvent(StatsEvent.SetStrength(statsState.strength + 3))
                        onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity - 1))
                        onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence - 1))
                        onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom - 1))
                        onStatsEvent(StatsEvent.SetConstitution(statsState.constitution - 1))
                        onStatsEvent(StatsEvent.SetCharisma(statsState.charisma - 1))
                    }

                    Species.FAUNUS_DEX -> {
                        onStatsEvent(StatsEvent.SetStrength(statsState.strength - 1))
                        onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity + 3))
                        onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence - 1))
                        onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom - 1))
                        onStatsEvent(StatsEvent.SetConstitution(statsState.constitution - 1))
                        onStatsEvent(StatsEvent.SetCharisma(statsState.charisma - 1))
                    }

                    Species.FAUNUS_INT -> {
                        onStatsEvent(StatsEvent.SetStrength(statsState.strength - 1))
                        onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity - 1))
                        onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence + 3))
                        onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom - 1))
                        onStatsEvent(StatsEvent.SetConstitution(statsState.constitution - 1))
                        onStatsEvent(StatsEvent.SetCharisma(statsState.charisma - 1))
                    }

                    Species.FAUNUS_WIS -> {
                        onStatsEvent(StatsEvent.SetStrength(statsState.strength - 1))
                        onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity - 1))
                        onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence - 1))
                        onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom + 3))
                        onStatsEvent(StatsEvent.SetConstitution(statsState.constitution - 1))
                        onStatsEvent(StatsEvent.SetCharisma(statsState.charisma - 1))
                    }

                    Species.FAUNUS_CON -> {
                        onStatsEvent(StatsEvent.SetStrength(statsState.strength - 1))
                        onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity - 1))
                        onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence - 1))
                        onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom - 1))
                        onStatsEvent(StatsEvent.SetConstitution(statsState.constitution + 3))
                        onStatsEvent(StatsEvent.SetCharisma(statsState.charisma - 1))
                    }

                    Species.FAUNUS_CHA -> {
                        onStatsEvent(StatsEvent.SetStrength(statsState.strength - 1))
                        onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity - 1))
                        onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence - 1))
                        onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom - 1))
                        onStatsEvent(StatsEvent.SetConstitution(statsState.constitution - 1))
                        onStatsEvent(StatsEvent.SetCharisma(statsState.charisma + 3))
                    }

                    Species.FAUNUS_NV -> {}
                }

                updatedSpecies = true
            }
            if(showAlert.isNotEmpty())
            {
                CharacterCreationAlert().CharacterCreatorAlert(
                    alertText = showAlert,
                    onDismiss = {showAlert = ""}
                )
            }
            if(showMenuAlert)
            {
                AlertDialog(
                    confirmButton = { TextButton(onClick = {
                        onCharacterEvent(CharacterEvent.NewCharacter)
                        navController.navigate(MainMenu)
                    }) {Text("Return Home")} },
                    dismissButton = { TextButton(onClick = { showMenuAlert = false }) {Text("Cancel")} },
                    onDismissRequest = { showMenuAlert = false },
                    title = {Text("Return Home")},
                    text = {Text("Are you sure you want to return home (entered data will be saved)")}
                )
            }
            Column(Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                Box(Modifier.padding(20.dp, top = 10.dp))
                {
                    Text(text = "Skill Points: ${characterState.skillPoints}")
                }
                    Column(Modifier
                        .padding(25.dp, 0.dp, 25.dp, 0.dp)
                        .fillMaxWidth())
                    {
                        stats.forEach { (name, value) ->
                        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .padding(10.dp, 0.dp)
                                .weight(1f))
                            {
                                Text(text = "$name - $value")
                            }
                            IconButton(onClick = {
                                if(value > 0)
                                {
                                    when(name)
                                    {
                                        "Strength" -> onStatsEvent(StatsEvent.SetStrength(statsState.strength - 1))
                                        "Dexterity" -> onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity - 1))
                                        "Intelligence" -> onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence - 1))
                                        "Wisdom" -> onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom - 1))
                                        "Constitution" -> onStatsEvent(StatsEvent.SetConstitution(statsState.constitution - 1))
                                        "Charisma" -> onStatsEvent(StatsEvent.SetCharisma(statsState.charisma - 1))
                                    }

                                    onCharacterEvent(CharacterEvent.SetSkillPoints(characterState.skillPoints + 1))
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Remove,
                                    contentDescription = "-1 stat",
                                )
                            }
                            IconButton(onClick = {
                                if(characterState.skillPoints > 0 && value < 20)
                                {
                                    when(name)
                                    {
                                        "Strength" -> onStatsEvent(StatsEvent.SetStrength(statsState.strength + 1))
                                        "Dexterity" -> onStatsEvent(StatsEvent.SetDexterity(statsState.dexterity + 1))
                                        "Intelligence" -> onStatsEvent(StatsEvent.SetIntelligence(statsState.intelligence + 1))
                                        "Wisdom" -> onStatsEvent(StatsEvent.SetWisdom(statsState.wisdom + 1))
                                        "Constitution" -> onStatsEvent(StatsEvent.SetConstitution(statsState.constitution + 1))
                                        "Charisma" -> onStatsEvent(StatsEvent.SetCharisma(statsState.charisma + 1))
                                    }

                                    onCharacterEvent(CharacterEvent.SetSkillPoints(characterState.skillPoints - 1))
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "+1 stat",
                                )
                            }
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Button(onClick = {
                        onCharacterEvent(CharacterEvent.NewCharacter)
                        navController.navigate(CharacterCreationPg3)
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
                        if(characterState.skillPoints == 0)
                        {
                            onStatsEvent(StatsEvent.SetCharacterId(characterState.characterId))
                            onCharacterEvent(CharacterEvent.NewCharacter)
                            onStatsEvent(StatsEvent.NewStats)
                            navController.navigate(CharacterCreationPg5)
                        }
                        else
                        {
                            showAlert = "you have not allocated all skill points"
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