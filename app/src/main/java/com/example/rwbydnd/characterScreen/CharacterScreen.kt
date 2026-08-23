package com.example.rwbydnd.characterScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.rwbydnd.MainMenu
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState
import com.example.rwbydnd.database.proficiency.ProficiencyEvent
import com.example.rwbydnd.database.proficiency.ProficiencyState
import com.example.rwbydnd.database.stats.StatsEvent
import com.example.rwbydnd.database.stats.StatsState
import com.example.rwbydnd.icons.ink_pen
import com.example.rwbydnd.icons.money_bag

class CharacterScreen
{
    @Composable
    fun characterScreen(
        navController: NavController,
        characterState: CharacterState,
        proficiencyState: ProficiencyState,
        statsState: StatsState,
        onCharacterEvent: (CharacterEvent) -> Unit,
        onProficiencyEvent: (ProficiencyEvent) -> Unit,
        onStatsEvent: (StatsEvent) -> Unit)
    {
        LaunchedEffect(Unit) {
            onCharacterEvent(CharacterEvent.SetCharacterFromId(characterState.characterId))
            onStatsEvent(StatsEvent.SetCharacterId(characterState.characterId))
            onStatsEvent(StatsEvent.SetStatsFromId)
            onProficiencyEvent(ProficiencyEvent.SetCharacterId(characterState.characterId))
            onProficiencyEvent(ProficiencyEvent.SetProficiencyFromId)
        }

        var showMenuAlert by remember { mutableStateOf(false) }
        var selectedTab by remember { mutableIntStateOf(0)}
        val tabItems = listOf(
            "Character",
            "Inventory",
            "Description",
            "Backstory",
            "Notes"
        )
        val pagerState = rememberPagerState() { tabItems.size }
        LaunchedEffect(selectedTab) { pagerState.animateScrollToPage(selectedTab) }
        LaunchedEffect(pagerState.currentPage) { selectedTab = pagerState.currentPage }
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {
                    Text(
                        text = characterState.characterName,
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
        },
            bottomBar = {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets)
                {
                    tabItems.forEachIndexed { index, tab ->
                        NavigationBarItem(
                            selected = index == selectedTab,
                            onClick = { selectedTab = index },
                            modifier = Modifier.weight(1f),
                            icon = {
                                when(tab)
                                {
                                    "Character" -> {
                                        Icon(imageVector = Icons.Outlined.Person,
                                            contentDescription = "Home")
                                    }
                                    "Inventory" -> {
                                        Icon(imageVector = money_bag,
                                            contentDescription = "Home")
                                    }
                                    "Description" -> {
                                        Icon(imageVector = Icons.Outlined.AccountCircle,
                                            contentDescription = "Home")
                                    }
                                    "Backstory" -> {
                                        Icon(imageVector = ink_pen,
                                            contentDescription = "Home")
                                    }
                                    "Notes" -> {
                                        Icon(imageVector = Icons.AutoMirrored.Outlined.Notes,
                                            contentDescription = "Home")
                                    }
                                }
                            },
                            label = { Text(text = tab) }
                        )
                    }
                }
            })
        { innerPadding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.padding(innerPadding).fillMaxSize()
            )
            {}
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

            when(selectedTab)
            {
                1 -> {

                }
                2 -> {

                }
                3 -> {

                }
                4 -> {

                }
                5 -> {
                    
                }
            }
        }
    }
}