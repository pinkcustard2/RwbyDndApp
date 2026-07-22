package com.example.rwbydnd

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class MainMenuScreen {
    @Composable
    fun MainScreen(navController: NavController)
    {
        val tabItems = listOf(
            Tab(title = "Characters"),
            Tab(title = "Rules")
        )
        val pagerState = rememberPagerState() { tabItems.size }
        var selectedTab by remember { mutableIntStateOf(0) }
        var characters = listOf(
            Character(0, "asbcs"),
            Character(1, "adasdfsdf")
        )
        val favourites = remember {
            mutableStateMapOf<Int, Boolean>()
        }
        LaunchedEffect(selectedTab) { pagerState.animateScrollToPage(selectedTab) }
        LaunchedEffect(pagerState.currentPage) { selectedTab = pagerState.currentPage }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SecondaryTabRow(selectedTab, modifier = Modifier.padding(innerPadding)) {
                tabItems.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == selectedTab,
                        onClick = { selectedTab = index },
                        text = { Text(text = tab.title) },
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(0.dp, 75.dp)
            )
            {
                //index -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){Text(text = tabItems[index].title)}

            }

            if (selectedTab == 0) {
                LazyColumn(modifier = Modifier.padding(0.dp, 85.dp, 0.dp, 25.dp)) {

                    val sortedCharacters = characters.sortedByDescending {
                        favourites[it.characterId] ?: false
                    }

                    items(sortedCharacters, key = { it.characterId }) { character ->
                        var isToggled = favourites[character.characterId] ?: false

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .animateItem(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            IconButton(
                                onClick = {
                                    favourites[character.characterId] = !isToggled
                                    println("CLICKED ${character.characterId}")
                                }
                            ) {
                                Icon(
                                    imageVector = if (isToggled) {
                                        Icons.Filled.Star
                                    } else {
                                        Icons.Outlined.StarRate
                                    },
                                    contentDescription = if (isToggled) "Selected icon button" else "Unselected icon button."
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp, 5.dp)
                                    .weight(1f),
                            ) { Text(character.characterName, fontSize = 18.sp) }
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = "View character",
                                Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
                            )
                        }
                    }
                }
                FloatingActionButton(
                    onClick = {navController.navigate(CharacterCreation)},
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(310.dp, 710.dp, 0.dp, 0.dp)
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            } else
            {
                val rules = remember{ mutableStateListOf(
                    false, false
                )}
                Box(modifier = Modifier
                    .padding(10.dp, 85.dp, 10.dp, 25.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .fillMaxWidth()
                )
                {
                    LazyColumn(Modifier.padding(10.dp))
                    {
                        item{
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                                rules[0] = !rules[0]
                            })
                            {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(5.dp, 5.dp))
                                {
                                    Text(rules[0].toString(), fontSize = 18.sp)
                                }
                                if(rules[0])
                                {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowDropDown,
                                        contentDescription = "Expanded",
                                    )
                                }
                                else
                                {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                                        contentDescription = "Expanded",
                                    )
                                }
                            }
                        }
                        if(rules[0])
                        {
                            item{
                                Box(modifier = Modifier
                                    .padding(5.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .fillMaxWidth())
                                {
                                    Text("asdfsdafsdafssadfs", Modifier.padding(10.dp, 5.dp))
                                }
                            }
                        }
                        item{
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                                rules[1] = !rules[1]
                            })
                            {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(5.dp, 5.dp))
                                {
                                    Text(rules[1].toString(), fontSize = 18.sp)
                                }
                                if(rules[1])
                                {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowDropDown,
                                        contentDescription = "Expanded",
                                    )
                                }
                                else
                                {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                                        contentDescription = "Expanded",
                                    )
                                }
                            }
                        }
                        if(rules[1])
                        {
                            item{
                                Box(modifier = Modifier
                                    .padding(5.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .fillMaxWidth())
                                {
                                    Text("asdfsdafsdafssadfs", Modifier.padding(10.dp, 5.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
