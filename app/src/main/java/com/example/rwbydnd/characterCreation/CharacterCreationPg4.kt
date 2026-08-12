package com.example.rwbydnd.characterCreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rwbydnd.database.CharacterEvent
import com.example.rwbydnd.database.CharacterState
import com.example.rwbydnd.database.StatsEvent
import com.example.rwbydnd.database.StatsState

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
            onCharacterEvent(CharacterEvent.SetCharacterFromName(characterState.characterName))
            onStatsEvent(StatsEvent.SetStatsFromId(characterState.characterId))
        }
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //onCharacterEvent(CharacterEvent.NewCharacter)
                    //navController.navigate(CharacterCreationPg4)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Next",
                )
            }
        })
        { innerPadding ->
            val scrollState = rememberScrollState()
            var skillPoints by remember {
                mutableIntStateOf(
                    76 + characterState.semblanceStrength - statsState.strength - statsState.dexterity - statsState.intelligence - statsState.wisdom - statsState.constitution - statsState.charisma
                )
            }
            val stats = mutableListOf(
                "Strength" to statsState.strength,
                "Dexterity" to statsState.dexterity,
                "Intelligence" to statsState.intelligence,
                "Wisdom" to statsState.wisdom,
                "Constitution" to statsState.constitution,
                "Charisma" to statsState.charisma
            )
            Column(Modifier.padding(innerPadding).verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Step 4 - Stats",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                    Column(Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth())
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

                                    skillPoints += 1
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Remove,
                                    contentDescription = "-1 stat",
                                )
                            }
                            IconButton(onClick = {
                                if(skillPoints > 0 && value < 20)
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

                                    skillPoints -= 1
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
                Box(Modifier.padding(20.dp, 0.dp))
                {
                    Text(text = "Skill Points: $skillPoints")
                }
            }
        }
    }
}