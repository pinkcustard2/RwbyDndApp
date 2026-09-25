package com.example.rwbydnd.characterScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rwbydnd.database.character.CharacterState
import com.example.rwbydnd.database.proficiency.ProficiencyState
import com.example.rwbydnd.database.stats.StatsState

class CharacterTab
{
    @SuppressLint("NotConstructor", "UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun CharacterTab(statsState: StatsState, characterState: CharacterState, proficiencyState: ProficiencyState, isEditing: Boolean)
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

            val proficiencyCategory = mutableMapOf(
                "Strength Saving Throw" to statsState.strength,
                "Athletics" to statsState.strength,
                "Dexterity Saving Throw" to statsState.dexterity,
                "Acrobatics" to statsState.dexterity,
                "Sleight of Hand" to statsState.dexterity,
                "Stealth" to statsState.dexterity,
                "Intelligence Saving Throw" to statsState.intelligence,
                "Arcana" to statsState.intelligence,
                "History" to statsState.intelligence,
                "Investigation" to statsState.intelligence,
                "Nature" to statsState.intelligence,
                "Religion" to statsState.intelligence,
                "Wisdom Saving Throw" to statsState.wisdom,
                "Animal Handling" to statsState.wisdom,
                "Insight" to statsState.wisdom,
                "Medicine" to statsState.wisdom,
                "Perception" to statsState.wisdom,
                "Survival" to statsState.wisdom,
                "Constitution Saving Throw" to statsState.constitution,
                "Charisma Saving Throw" to statsState.charisma,
                "Deception" to statsState.charisma,
                "Intimidation" to statsState.charisma,
                "Performance" to statsState.charisma,
                "Persuasion" to statsState.charisma
            )

            val scrollState = rememberScrollState()
            Column(Modifier.padding().verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(Modifier.fillMaxWidth().padding(horizontal = 25.dp), horizontalArrangement = Arrangement.spacedBy(16.dp))
                {
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(GenericShape { size, _ ->
                            moveTo(size.width * 0.15f, 0f)
                            lineTo(size.width * 0.85f, 0f)
                            lineTo(size.width, size.height * 0.15f)
                            lineTo(size.width * 0.9f, size.height * 0.7f)
                            cubicTo(
                                size.width * 0.8f, size.height * 0.9f,
                                size.width * 0.5f, size.height,
                                size.width * 0.5f, size.height
                            )
                            cubicTo(
                                size.width * 0.5f, size.height,
                                size.width * 0.2f, size.height * 0.9f,
                                size.width * 0.1f, size.height * 0.7f
                            )
                            lineTo(0f, size.height * 0.15f)
                            close()
                        }).background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center)
                    {
                        Text(text = "" + characterState.currentAura, color = MaterialTheme.colorScheme.onPrimary, fontSize = 28.sp);
                    }

                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(GenericShape { size, _ ->
                            moveTo(size.width / 2, size.height)

                            cubicTo(
                                size.width * 0.35f, size.height * 0.8f,
                                0f, size.height * 0.6f,
                                0f, size.height * 0.25f
                            )

                            cubicTo(
                                0f, 0f,
                                size.width * 0.3f, 0f,
                                size.width / 2, size.height * 0.25f
                            )

                            cubicTo(
                                size.width * 0.7f, 0f,
                                size.width, 0f,
                                size.width, size.height * 0.25f
                            )

                            cubicTo(
                                size.width, size.height * 0.6f,
                                size.width * 0.65f, size.height * 0.8f,
                                size.width / 2, size.height
                            )

                            close()
                        }).background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center)
                    {
                        Text(text = "" + characterState.currentHealth, color = MaterialTheme.colorScheme.onPrimary, fontSize = 28.sp);
                    }

                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(CircleShape).background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center)
                    {
                        Text(text = "" + characterState.credits, color = MaterialTheme.colorScheme.onPrimary, fontSize = 28.sp);
                    }

                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(GenericShape { size, _ ->
                            val cut = size.width * 0.25f

                            moveTo(cut, 0f)
                            lineTo(size.width - cut, 0f)
                            lineTo(size.width, cut)
                            lineTo(size.width, size.height - cut)
                            lineTo(size.width - cut, size.height)
                            lineTo(cut, size.height)
                            lineTo(0f, size.height - cut)
                            lineTo(0f, cut)
                            close()
                        }).background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center)
                    {
                        Text(text = "" + characterState.skillPoints, color = MaterialTheme.colorScheme.onPrimary, fontSize = 28.sp);
                    }

                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(GenericShape { size, _ ->

                            val w = size.width
                            val h = size.height

                            // Width of each arm
                            val arm = w * 0.38f

                            // How rounded the corners are
                            val r = w * 0.2f

                            val x1 = (w - arm) / 2
                            val x2 = (w + arm) / 2
                            val y1 = (h - arm) / 2
                            val y2 = (h + arm) / 2

                            moveTo(x1 + r, 0f)

                            // Top edge
                            lineTo(x2 - r, 0f)

                            // Top-right corner
                            quadraticTo(x2, 0f, x2, r)

                            // Right side of vertical arm
                            lineTo(x2, y1 - r)

                            // Inner top-right corner
                            quadraticTo(x2, y1, x2 + r, y1)

                            // Top of right arm
                            lineTo(w - r, y1)

                            // Outer top-right corner
                            quadraticTo(w, y1, w, y1 + r)

                            // Right edge
                            lineTo(w, y2 - r)

                            // Outer bottom-right corner
                            quadraticTo(w, y2, w - r, y2)

                            // Bottom of right arm
                            lineTo(x2 + r, y2)

                            // Inner bottom-right corner
                            quadraticTo(x2, y2, x2, y2 + r)

                            // Right side of bottom arm
                            lineTo(x2, h - r)

                            // Bottom-right corner
                            quadraticTo(x2, h, x2 - r, h)

                            // Bottom edge
                            lineTo(x1 + r, h)

                            // Bottom-left corner
                            quadraticTo(x1, h, x1, h - r)

                            // Left side of bottom arm
                            lineTo(x1, y2 + r)

                            // Inner bottom-left corner
                            quadraticTo(x1, y2, x1 - r, y2)

                            // Bottom of left arm
                            lineTo(r, y2)

                            // Outer bottom-left corner
                            quadraticTo(0f, y2, 0f, y2 - r)

                            // Left edge
                            lineTo(0f, y1 + r)

                            // Outer top-left corner
                            quadraticTo(0f, y1, r, y1)

                            // Top of left arm
                            lineTo(x1 - r, y1)

                            // Inner top-left corner
                            quadraticTo(x1, y1, x1, y1 - r)

                            // Left side of top arm
                            lineTo(x1, r)

                            // Top-left corner
                            quadraticTo(x1, 0f, x1 + r, 0f)

                            close()
                        }).background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center)
                    {
                        Text(text = "+" + characterState.proficiencyBonus, color = MaterialTheme.colorScheme.onPrimary, fontSize = 28.sp);
                    }
                }
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
                Column(
                    Modifier
                        .padding(25.dp, 10.dp, 25.dp, 100.dp)
                        .fillMaxWidth()
                )
                {
                    proficiencies.forEach { (name, value) ->
                        if (name == "Strength Saving Throw" ||
                            name == "Dexterity Saving Throw" ||
                            name == "Intelligence Saving Throw" ||
                            name == "Wisdom Saving Throw" ||
                            name == "Constitution Saving Throw" ||
                            name == "Charisma Saving Throw"
                        ) {
                            Row(
                                Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(10.dp, 0.dp)
                                        .weight(1f)
                                )
                                {
                                    Text(text = name)
                                }
                                Box(modifier = Modifier.background(
                                        shape = RoundedCornerShape(16.dp),
                                        color = MaterialTheme.colorScheme.surfaceContainer
                                    ).border(
                                        width = 2.dp,
                                        color = MaterialTheme.colorScheme.outline,
                                        shape = RoundedCornerShape(16.dp)
                                    ).fillMaxHeight()
                                    .padding(10.dp, 0.dp))
                                {
                                    val baseBonus = ((proficiencyCategory[name])!! - 10) / 2
                                    if(value && baseBonus + (characterState.proficiencyBonus) >= 0)
                                    {
                                        Text(text = "+" + (baseBonus + characterState.proficiencyBonus))
                                    }
                                    else if(baseBonus >= 0)
                                    {
                                        Text(text = "+$baseBonus")
                                    }
                                    else
                                    {
                                        Text(text = "" + baseBonus)
                                    }
                                }
                                IconButton(onClick = {}, Modifier.padding(start = 5.dp))
                                {
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
                        } else {
                            Row(
                                Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(40.dp, 0.dp)
                                        .weight(1f)
                                )
                                {
                                    Text(text = name)
                                }
                                Box(modifier = Modifier.background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colorScheme.surfaceContainer
                                ).border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(16.dp)
                                ).fillMaxHeight()
                                    .padding(10.dp, 0.dp))
                                {
                                    val baseBonus = ((proficiencyCategory[name])!! - 10) / 2
                                    if(value && baseBonus + (characterState.proficiencyBonus) >= 0)
                                    {
                                        Text(text = "+" + (baseBonus + characterState.proficiencyBonus))
                                    }
                                    else if(baseBonus >= 0)
                                    {
                                        Text(text = "+$baseBonus")
                                    }
                                    else
                                    {
                                        Text(text = "" + baseBonus)
                                    }
                                }
                                IconButton(onClick = {}, Modifier.padding(start = 5.dp))
                                {
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
            }
        }
    }
}