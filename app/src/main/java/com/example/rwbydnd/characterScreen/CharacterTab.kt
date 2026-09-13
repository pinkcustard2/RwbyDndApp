package com.example.rwbydnd.characterScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rwbydnd.database.stats.StatsState

class CharacterTab
{
    @SuppressLint("NotConstructor", "UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun CharacterTab(statsState: StatsState, isEditing: Boolean)
    {
        Scaffold(modifier = Modifier.fillMaxSize())
        {
            val stats = mutableListOf(
                "Strength" to statsState.strength,
                "Dexterity" to statsState.dexterity,
                "Intelligence" to statsState.intelligence,
                "Wisdom" to statsState.wisdom,
                "Constitution" to statsState.constitution,
                "Charisma" to statsState.charisma
            )

            val scrollState = rememberScrollState()
            Column(Modifier.padding().verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(Modifier.fillMaxWidth().padding(horizontal = 25.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)){
                    stats.forEach { (name, value) ->
                        if(name == "Strength" || name == "Dexterity" || name == "Intelligence")
                        {
                            Box(
                                modifier = Modifier.background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colorScheme.surfaceContainer
                                ).border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(16.dp)
                                ).width(100.dp), contentAlignment = Alignment.Center
                            )
                            {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        modifier = Modifier.background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = MaterialTheme.colorScheme.surfaceContainer
                                        ).border(
                                            width = 2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            shape = RoundedCornerShape(16.dp)
                                        ).fillMaxWidth().padding(vertical = 5.dp), contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(text = name, textAlign = TextAlign.Center)
                                    }

                                    Box(Modifier.fillMaxWidth().padding(vertical = 10.dp), contentAlignment = Alignment.Center)
                                    {
                                        if(value >= 10)
                                        {
                                            Text("+" + (value - 10) / 2, fontSize = 32.sp, textAlign = TextAlign.Center)
                                        }
                                        else
                                        {
                                            Text(text = "" + (value - 10) / 2, fontSize = 32.sp, textAlign = TextAlign.Center)
                                        }

                                        if(isEditing)
                                        {
                                            IconButton(onClick = {}, Modifier.align(Alignment.CenterStart).offset(x = (-8).dp)) {
                                                Icon(
                                                    imageVector = Icons.Filled.Remove,
                                                    contentDescription = "-1 stat"
                                                )
                                            }

                                            IconButton(onClick = {}, Modifier.align(Alignment.CenterEnd).offset(x = 8.dp)) {
                                                Icon(
                                                    imageVector = Icons.Filled.Add,
                                                    contentDescription = "+1 stat"
                                                )
                                            }
                                        }
                                    }

                                    Box(
                                        modifier = Modifier.background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = MaterialTheme.colorScheme.surfaceContainer
                                        ).border(
                                            width = 2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            shape = RoundedCornerShape(16.dp)
                                        ).fillMaxWidth().padding(vertical = 5.dp), contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(text = "" + value, textAlign = TextAlign.Center)
                                    }
                                }
                            }
                        }
                    }
                }
                Row(Modifier.fillMaxWidth().padding(start = 25.dp, end = 25.dp, top = 10.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)){
                    stats.forEach { (name, value) ->
                        if(name == "Wisdom" || name == "Constitution" || name == "Charisma")
                        {
                            Box(
                                modifier = Modifier.background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colorScheme.surfaceContainer
                                ).border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(16.dp)
                                ).width(100.dp), contentAlignment = Alignment.Center
                            )
                            {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        modifier = Modifier.background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = MaterialTheme.colorScheme.surfaceContainer
                                        ).border(
                                            width = 2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            shape = RoundedCornerShape(16.dp)
                                        ).fillMaxWidth().padding(vertical = 5.dp), contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(text = name, textAlign = TextAlign.Center)
                                    }

                                    Box(Modifier.fillMaxWidth().padding(vertical = 10.dp), contentAlignment = Alignment.Center)
                                    {
                                        if(value >= 10)
                                        {
                                            Text("+" + (value - 10) / 2, fontSize = 32.sp, textAlign = TextAlign.Center)
                                        }
                                        else
                                        {
                                            Text(text = "" + (value - 10) / 2, fontSize = 32.sp, textAlign = TextAlign.Center)
                                        }

                                        if(isEditing)
                                        {
                                            IconButton(onClick = {}, Modifier.align(Alignment.CenterStart).offset(x = (-8).dp)) {
                                                Icon(
                                                    imageVector = Icons.Filled.Remove,
                                                    contentDescription = "-1 stat"
                                                )
                                            }

                                            IconButton(onClick = {}, Modifier.align(Alignment.CenterEnd).offset(x = 8.dp)) {
                                                Icon(
                                                    imageVector = Icons.Filled.Add,
                                                    contentDescription = "+1 stat"
                                                )
                                            }
                                        }
                                    }

                                    Box(
                                        modifier = Modifier.background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = MaterialTheme.colorScheme.surfaceContainer
                                        ).border(
                                            width = 2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            shape = RoundedCornerShape(16.dp)
                                        ).fillMaxWidth().padding(vertical = 5.dp), contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(text = "" + value, textAlign = TextAlign.Center)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}