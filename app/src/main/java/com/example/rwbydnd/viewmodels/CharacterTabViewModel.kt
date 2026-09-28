package com.example.rwbydnd.viewmodels

import androidx.lifecycle.ViewModel
import com.example.rwbydnd.database.Stat
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState
import com.example.rwbydnd.database.stats.StatsEvent
import com.example.rwbydnd.database.stats.StatsState

class CharacterTabViewModel(): ViewModel()
{
    fun incrementStat(characterState: CharacterState,
                      onCharacterEvent: (CharacterEvent) -> Unit,
                      statsState: StatsState,
                      onStatsEvent: (StatsEvent) -> Unit,
                      stat: Stat): Int
    {
        val statValue: Int
        val skillPoints = characterState.skillPoints

        when(stat)
        {
            Stat.STRENGTH -> statValue = statsState.strength
            Stat.DEXTERITY -> statValue = statsState.dexterity
            Stat.INTELLIGENCE -> statValue = statsState.intelligence
            Stat.WISDOM -> statValue = statsState.wisdom
            Stat.CONSTITUTION -> statValue = statsState.constitution
            Stat.CHARISMA -> statValue = statsState.charisma
        }

        var required = statValue + 1

        if(statValue >= 20)
        {
            required = 0

            for(i in 20..statValue + 1)
            {
                required += i
            }
        }

        if(skillPoints >= required)
        {
            onCharacterEvent(CharacterEvent.SetSkillPoints(skillPoints - required))
            when(stat)
            {
                Stat.STRENGTH -> {
                    onStatsEvent(StatsEvent.SetStrength(statValue + 1))
                }
                Stat.DEXTERITY -> {
                    onStatsEvent(StatsEvent.SetDexterity(statValue + 1))
                }
                Stat.INTELLIGENCE -> {
                    onStatsEvent(StatsEvent.SetIntelligence(statValue + 1))
                }
                Stat.WISDOM -> {
                    onStatsEvent(StatsEvent.SetWisdom(statValue + 1))
                }
                Stat.CONSTITUTION -> {
                    onStatsEvent(StatsEvent.SetConstitution(statValue + 1))
                }
                Stat.CHARISMA -> {
                    onStatsEvent(StatsEvent.SetCharisma(statValue + 1))
                }
            }

            return -1
        }
        return required
    }

    fun decrementStat(characterState: CharacterState,
                      onCharacterEvent: (CharacterEvent) -> Unit,
                      statsState: StatsState,
                      onStatsEvent: (StatsEvent) -> Unit,
                      stat: Stat)
    {
        val statValue: Int
        val skillPoints = characterState.skillPoints

        when(stat)
        {
            Stat.STRENGTH -> statValue = statsState.strength
            Stat.DEXTERITY -> statValue = statsState.dexterity
            Stat.INTELLIGENCE -> statValue = statsState.intelligence
            Stat.WISDOM -> statValue = statsState.wisdom
            Stat.CONSTITUTION -> statValue = statsState.constitution
            Stat.CHARISMA -> statValue = statsState.charisma
        }

        var toGet = statValue

        if(statValue >= 20)
        {
            toGet = 0

            for(i in 20..statValue)
            {
                toGet += i
            }
        }

        onCharacterEvent(CharacterEvent.SetSkillPoints(skillPoints + toGet))
        when(stat)
        {
            Stat.STRENGTH -> {
                onStatsEvent(StatsEvent.SetStrength(statValue - 1))
            }
            Stat.DEXTERITY -> {
                onStatsEvent(StatsEvent.SetDexterity(statValue - 1))
            }
            Stat.INTELLIGENCE -> {
                onStatsEvent(StatsEvent.SetIntelligence(statValue - 1))
            }
            Stat.WISDOM -> {
                onStatsEvent(StatsEvent.SetWisdom(statValue - 1))
            }
            Stat.CONSTITUTION -> {
                onStatsEvent(StatsEvent.SetConstitution(statValue - 1))
            }
            Stat.CHARISMA -> {
                onStatsEvent(StatsEvent.SetCharisma(statValue - 1))
            }
        }

    }
}