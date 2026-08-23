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
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rwbydnd.CharacterCreationPg4
import com.example.rwbydnd.CharacterView
import com.example.rwbydnd.MainMenu
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState
import com.example.rwbydnd.database.proficiency.ProficiencyEvent
import com.example.rwbydnd.database.proficiency.ProficiencyState
import com.example.rwbydnd.database.stats.StatsEvent
import com.example.rwbydnd.database.stats.StatsState

class CharacterCreatorPg5 {
    @Composable
    fun CharacterCreationPg5(
        navController: NavController,
        characterState: CharacterState,
        proficiencyState: ProficiencyState,
        statsState: StatsState,
        onCharacterEvent: (CharacterEvent) -> Unit,
        onProficiencyEvent: (ProficiencyEvent) -> Unit,
        onStatsEvent: (StatsEvent) -> Unit
    ) {
        LaunchedEffect(Unit) {
            if (characterState.characterId != 0 && characterState.characterName.isEmpty()) {
                onCharacterEvent(CharacterEvent.SetCharacterFromId(characterState.characterId))
            } else if (characterState.characterName.isNotEmpty() && characterState.characterId == 0) {
                onCharacterEvent(CharacterEvent.SetCharacterFromName(characterState.characterName))
            }
            onStatsEvent(StatsEvent.ResetState)
            onProficiencyEvent(ProficiencyEvent.SetCharacterId(characterState.characterId))
            onProficiencyEvent(ProficiencyEvent.SetProficiencyFromId)
        }
        var loadedStats by remember {mutableStateOf(false)}
        var loadedProficiencies by remember{mutableStateOf(false)}
        var savingThrows by remember{ mutableIntStateOf(2) }
        var proficienciesLeft by remember{ mutableIntStateOf(4) }
        var showAlert by remember {mutableStateOf("")}
        var showMenuAlert by remember { mutableStateOf(false) }
        @OptIn(ExperimentalMaterial3Api::class)
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Step 5 - Proficiencies",
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
            val proficiencies = mutableListOf(
                "Strength Saving Throw" to proficiencyState.strength,
                "Athletics" to proficiencyState.athletics,
                "Dexterity Saving Throw" to proficiencyState.dexterity,
                "Acrobatics" to proficiencyState.acrobatics,
                "Sleight of Hand" to proficiencyState.sleightOfHand,
                "Stealth" to proficiencyState.stealth,
                "Intelligence Saving Throw" to proficiencyState.intelligence,
                "Arcana" to proficiencyState.arcana,
                "History" to proficiencyState.history,
                "Investigation" to proficiencyState.investigation,
                "Nature" to proficiencyState.nature,
                "Religion" to proficiencyState.religion,
                "Wisdom Saving Throw" to proficiencyState.wisdom,
                "Animal Handling" to proficiencyState.animalHandling,
                "Insight" to proficiencyState.insight,
                "Medicine" to proficiencyState.medicine,
                "Perception" to proficiencyState.perception,
                "Survival" to proficiencyState.survival,
                "Constitution Saving Throw" to proficiencyState.constitution,
                "Charisma Saving Throw" to proficiencyState.charisma,
                "Deception" to proficiencyState.deception,
                "Intimidation" to proficiencyState.intimidation,
                "Performance" to proficiencyState.performance,
                "Persuasion" to proficiencyState.persuasion
            )
            if (!loadedStats && characterState.characterId != 0)
            {
                onStatsEvent(StatsEvent.SetCharacterId(characterState.characterId))
                onStatsEvent(StatsEvent.SetStatsFromId)
                loadedStats = true
            }
            if(!loadedProficiencies && loadedStats && proficiencyState.characterId != 0&& (statsState.strength != 6 || statsState.dexterity != 6 || statsState.intelligence != 6 || statsState.wisdom != 6 || statsState.constitution != 6 || statsState.charisma != 6))
            {
                for(proficiency in proficiencies)
                {
                    if(proficiency.component2())
                    {
                        savingThrows = 0
                        proficienciesLeft = 0
                    }
                }

                loadedProficiencies = true
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
                    Text(text = "Saving Throws Left: $savingThrows")
                }
                Box(Modifier.padding(20.dp, 0.dp))
                {
                    Text(text = "Proficiencies Left: $proficienciesLeft")
                }
                Column(Modifier
                    .padding(25.dp, 0.dp, 25.dp, 0.dp)
                    .fillMaxWidth())
                {
                    proficiencies.forEach { (name, value) ->
                        if (name == "Strength Saving Throw" ||
                            name == "Dexterity Saving Throw" ||
                            name == "Intelligence Saving Throw" ||
                            name == "Wisdom Saving Throw" ||
                            name == "Constitution Saving Throw" ||
                            name == "Charisma Saving Throw")
                        {
                            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                            {
                                Box(modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp, 0.dp)
                                    .weight(1f))
                                {
                                    Text(text = name)
                                }
                                IconButton(onClick = {
                                    if(!value)
                                    {
                                        if(savingThrows > 0)
                                        {
                                            when (name)
                                            {
                                                "Strength Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetStrength(!proficiencyState.strength))
                                                "Dexterity Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetDexterity(!proficiencyState.dexterity))
                                                "Intelligence Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetIntelligence(!proficiencyState.intelligence))
                                                "Wisdom Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetWisdom(!proficiencyState.wisdom))
                                                "Constitution Saving Throw"  -> onProficiencyEvent(ProficiencyEvent.SetConstitution(!proficiencyState.constitution))
                                                "Charisma Saving Throw"  -> onProficiencyEvent(ProficiencyEvent.SetCharisma(!proficiencyState.charisma))
                                            }

                                            savingThrows--
                                        }
                                    }
                                    else
                                    {
                                        when (name)
                                        {
                                            "Strength Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetStrength(!proficiencyState.strength))
                                            "Dexterity Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetDexterity(!proficiencyState.dexterity))
                                            "Intelligence Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetIntelligence(!proficiencyState.intelligence))
                                            "Wisdom Saving Throw" -> onProficiencyEvent(ProficiencyEvent.SetWisdom(!proficiencyState.wisdom))
                                            "Constitution Saving Throw"  -> onProficiencyEvent(ProficiencyEvent.SetConstitution(!proficiencyState.constitution))
                                            "Charisma Saving Throw"  -> onProficiencyEvent(ProficiencyEvent.SetCharisma(!proficiencyState.charisma))
                                        }

                                        savingThrows++
                                    }
                                }) {
                                    if(!value)
                                    {
                                        Icon(imageVector = Icons.Filled.RadioButtonUnchecked,
                                            contentDescription = "Add proficiency",)
                                    }
                                    else
                                    {
                                        Icon(imageVector = Icons.Filled.RadioButtonChecked,
                                            contentDescription = "Add proficiency",)
                                    }
                                }
                            }
                        }
                        else
                        {
                            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                            {
                                Box(modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(40.dp, 0.dp)
                                    .weight(1f))
                                {
                                    Text(text = name)
                                }
                                IconButton(onClick = {
                                    if(!value)
                                    {
                                        if(proficienciesLeft > 0)
                                        {
                                            when(name)
                                            {
                                                "Athletics" -> onProficiencyEvent(ProficiencyEvent.SetAthletics(!proficiencyState.athletics))
                                                "Acrobatics" -> onProficiencyEvent(ProficiencyEvent.SetAcrobatics(!proficiencyState.acrobatics))
                                                "Sleight of Hand" -> onProficiencyEvent(ProficiencyEvent.SetSleightOfHand(!proficiencyState.sleightOfHand))
                                                "Stealth" -> onProficiencyEvent(ProficiencyEvent.SetStealth(!proficiencyState.stealth))
                                                "Arcana" -> onProficiencyEvent(ProficiencyEvent.SetArcana(!proficiencyState.arcana))
                                                "History" -> onProficiencyEvent(ProficiencyEvent.SetHistory(!proficiencyState.history))
                                                "Investigation" -> onProficiencyEvent(ProficiencyEvent.SetInvestigation(!proficiencyState.investigation))
                                                "Nature" -> onProficiencyEvent(ProficiencyEvent.SetNature(!proficiencyState.nature))
                                                "Religion" -> onProficiencyEvent(ProficiencyEvent.SetReligion(!proficiencyState.religion))
                                                "Animal Handling" -> onProficiencyEvent(ProficiencyEvent.SetAnimalHandling(!proficiencyState.animalHandling))
                                                "Insight" -> onProficiencyEvent(ProficiencyEvent.SetInsight(!proficiencyState.insight))
                                                "Medicine" -> onProficiencyEvent(ProficiencyEvent.SetMedicine(!proficiencyState.medicine))
                                                "Perception" -> onProficiencyEvent(ProficiencyEvent.SetPerception(!proficiencyState.perception))
                                                "Survival" -> onProficiencyEvent(ProficiencyEvent.SetSurvival(!proficiencyState.survival))
                                                "Deception" -> onProficiencyEvent(ProficiencyEvent.SetDeception(!proficiencyState.deception))
                                                "Intimidation" -> onProficiencyEvent(ProficiencyEvent.SetIntimidation(!proficiencyState.intimidation))
                                                "Performance" -> onProficiencyEvent(ProficiencyEvent.SetPerformance(!proficiencyState.performance))
                                                "Persuasion" -> onProficiencyEvent(ProficiencyEvent.SetPersuasion(!proficiencyState.persuasion))
                                            }
                                            proficienciesLeft--
                                        }
                                    }
                                    else
                                    {
                                        when(name)
                                        {
                                            "Athletics" -> onProficiencyEvent(ProficiencyEvent.SetAthletics(!proficiencyState.athletics))
                                            "Acrobatics" -> onProficiencyEvent(ProficiencyEvent.SetAcrobatics(!proficiencyState.acrobatics))
                                            "Sleight of Hand" -> onProficiencyEvent(ProficiencyEvent.SetSleightOfHand(!proficiencyState.sleightOfHand))
                                            "Stealth" -> onProficiencyEvent(ProficiencyEvent.SetStealth(!proficiencyState.stealth))
                                            "Arcana" -> onProficiencyEvent(ProficiencyEvent.SetArcana(!proficiencyState.arcana))
                                            "History" -> onProficiencyEvent(ProficiencyEvent.SetHistory(!proficiencyState.history))
                                            "Investigation" -> onProficiencyEvent(ProficiencyEvent.SetInvestigation(!proficiencyState.investigation))
                                            "Nature" -> onProficiencyEvent(ProficiencyEvent.SetNature(!proficiencyState.nature))
                                            "Religion" -> onProficiencyEvent(ProficiencyEvent.SetReligion(!proficiencyState.religion))
                                            "Animal Handling" -> onProficiencyEvent(ProficiencyEvent.SetAnimalHandling(!proficiencyState.animalHandling))
                                            "Insight" -> onProficiencyEvent(ProficiencyEvent.SetInsight(!proficiencyState.insight))
                                            "Medicine" -> onProficiencyEvent(ProficiencyEvent.SetMedicine(!proficiencyState.medicine))
                                            "Perception" -> onProficiencyEvent(ProficiencyEvent.SetPerception(!proficiencyState.perception))
                                            "Survival" -> onProficiencyEvent(ProficiencyEvent.SetSurvival(!proficiencyState.survival))
                                            "Deception" -> onProficiencyEvent(ProficiencyEvent.SetDeception(!proficiencyState.deception))
                                            "Intimidation" -> onProficiencyEvent(ProficiencyEvent.SetIntimidation(!proficiencyState.intimidation))
                                            "Performance" -> onProficiencyEvent(ProficiencyEvent.SetPerformance(!proficiencyState.performance))
                                            "Persuasion" -> onProficiencyEvent(ProficiencyEvent.SetPersuasion(!proficiencyState.persuasion))
                                        }
                                        proficienciesLeft++
                                    }
                                }) {
                                    if(!value)
                                    {
                                        Icon(imageVector = Icons.Filled.RadioButtonUnchecked,
                                            contentDescription = "Add proficiency",)
                                    }
                                    else
                                    {
                                        Icon(imageVector = Icons.Filled.RadioButtonChecked,
                                            contentDescription = "Add proficiency",)
                                    }
                                }
                            }
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Button(onClick = {
                        onCharacterEvent(CharacterEvent.NewCharacter)
                        navController.navigate(CharacterCreationPg4)
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
                        if(savingThrows == 0 && proficienciesLeft == 0)
                        {
                            onCharacterEvent(CharacterEvent.SetCurrentHealth(20 + ((statsState.constitution - 10) / 2)))
                            onCharacterEvent(CharacterEvent.SetCurrentAura(100 + (((statsState.constitution - 10) / 2) * 5)))
                            onCharacterEvent(CharacterEvent.SetMaxAura(100 + (((statsState.constitution - 10) / 2) * 5)))
                            onCharacterEvent(CharacterEvent.SetCredits(100))
                            onCharacterEvent(CharacterEvent.SetProficiencyBonus(2))
                            onCharacterEvent(CharacterEvent.NewCharacter)
                            onProficiencyEvent(ProficiencyEvent.SetCharacterId(characterState.characterId))
                            onProficiencyEvent(ProficiencyEvent.NewProficiency)
                            navController.navigate(CharacterView)
                        }
                        else
                        {
                            showAlert = "not all saving throws and proficiencies have been allocated"
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
                Box(Modifier.padding(0.dp, 100.dp))
            }
        }
    }
}