package com.example.rwbydnd.viewmodels

import androidx.lifecycle.ViewModel
import com.example.rwbydnd.database.Proficiency
import com.example.rwbydnd.database.Stat
import com.example.rwbydnd.database.character.CharacterEvent
import com.example.rwbydnd.database.character.CharacterState
import com.example.rwbydnd.database.proficiency.ProficiencyEvent
import com.example.rwbydnd.database.proficiency.ProficiencyState
import com.example.rwbydnd.database.stats.StatsEvent
import com.example.rwbydnd.database.stats.StatsState

class CharacterTabViewModel(): ViewModel() {
    fun incrementStat(
        characterState: CharacterState,
        onCharacterEvent: (CharacterEvent) -> Unit,
        statsState: StatsState,
        onStatsEvent: (StatsEvent) -> Unit,
        stat: Stat
    ): Int {
        val statValue: Int
        val skillPoints = characterState.skillPoints

        when (stat) {
            Stat.STRENGTH -> statValue = statsState.strength
            Stat.DEXTERITY -> statValue = statsState.dexterity
            Stat.INTELLIGENCE -> statValue = statsState.intelligence
            Stat.WISDOM -> statValue = statsState.wisdom
            Stat.CONSTITUTION -> statValue = statsState.constitution
            Stat.CHARISMA -> statValue = statsState.charisma
        }

        var required = statValue + 1

        if (statValue >= 20) {
            required = 0

            for (i in 20..statValue + 1) {
                required += i
            }
        }

        if (skillPoints >= required) {
            onCharacterEvent(CharacterEvent.SetSkillPoints(skillPoints - required))
            when (stat) {
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

    fun decrementStat(
        characterState: CharacterState,
        onCharacterEvent: (CharacterEvent) -> Unit,
        statsState: StatsState,
        onStatsEvent: (StatsEvent) -> Unit,
        stat: Stat
    ) {
        val statValue: Int
        val skillPoints = characterState.skillPoints

        when (stat) {
            Stat.STRENGTH -> statValue = statsState.strength
            Stat.DEXTERITY -> statValue = statsState.dexterity
            Stat.INTELLIGENCE -> statValue = statsState.intelligence
            Stat.WISDOM -> statValue = statsState.wisdom
            Stat.CONSTITUTION -> statValue = statsState.constitution
            Stat.CHARISMA -> statValue = statsState.charisma
        }

        var toGet = statValue

        if (statValue >= 20) {
            toGet = 0

            for (i in 20..statValue) {
                toGet += i
            }
        }

        onCharacterEvent(CharacterEvent.SetSkillPoints(skillPoints + toGet))
        when (stat) {
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

    fun toggleProficiency(enabled: Boolean,
                          isMajorProficiency: Boolean,
                          proficiency: Proficiency,
                          proficiencyState: ProficiencyState,
                          onProficiencyEvent: (ProficiencyEvent) -> Unit,
                          characterState: CharacterState,
                          onCharacterEvent: (CharacterEvent) -> Unit): Int
    {
        val cost: Int
        val skillPoints = characterState.skillPoints

        if(isMajorProficiency)
        {
            cost = 40
        }
        else
        {
            cost = 10
        }

        var shouldChange = false
        if(enabled)
        {
            onCharacterEvent(CharacterEvent.SetSkillPoints(skillPoints + cost))

            shouldChange = true
        }
        else if(skillPoints >= cost)
        {
            onCharacterEvent(CharacterEvent.SetSkillPoints(skillPoints - cost))

            shouldChange = true
        }

        if(shouldChange)
        {
            when(proficiency)
            {
                Proficiency.STRENGTH -> onProficiencyEvent(ProficiencyEvent.SetStrength(!proficiencyState.strength))
                Proficiency.DEXTERITY -> onProficiencyEvent(ProficiencyEvent.SetDexterity(!proficiencyState.dexterity))
                Proficiency.INTELLIGENCE -> onProficiencyEvent(ProficiencyEvent.SetIntelligence(!proficiencyState.intelligence))
                Proficiency.WISDOM -> onProficiencyEvent(ProficiencyEvent.SetWisdom(!proficiencyState.wisdom))
                Proficiency.CONSTITUTION  -> onProficiencyEvent(ProficiencyEvent.SetConstitution(!proficiencyState.constitution))
                Proficiency.CHARISMA  -> onProficiencyEvent(ProficiencyEvent.SetCharisma(!proficiencyState.charisma))
                Proficiency.ATHLETICS -> onProficiencyEvent(ProficiencyEvent.SetAthletics(!proficiencyState.athletics))
                Proficiency.ACROBATICS -> onProficiencyEvent(ProficiencyEvent.SetAcrobatics(!proficiencyState.acrobatics))
                Proficiency.SLEIGHT_OF_HAND -> onProficiencyEvent(ProficiencyEvent.SetSleightOfHand(!proficiencyState.sleightOfHand))
                Proficiency.STEALTH -> onProficiencyEvent(ProficiencyEvent.SetStealth(!proficiencyState.stealth))
                Proficiency.ARCANA -> onProficiencyEvent(ProficiencyEvent.SetArcana(!proficiencyState.arcana))
                Proficiency.HISTORY -> onProficiencyEvent(ProficiencyEvent.SetHistory(!proficiencyState.history))
                Proficiency.INVESTIGATION -> onProficiencyEvent(ProficiencyEvent.SetInvestigation(!proficiencyState.investigation))
                Proficiency.NATURE -> onProficiencyEvent(ProficiencyEvent.SetNature(!proficiencyState.nature))
                Proficiency.RELIGION -> onProficiencyEvent(ProficiencyEvent.SetReligion(!proficiencyState.religion))
                Proficiency.ANIMAL_HANDLING -> onProficiencyEvent(ProficiencyEvent.SetAnimalHandling(!proficiencyState.animalHandling))
                Proficiency.INSIGHT -> onProficiencyEvent(ProficiencyEvent.SetInsight(!proficiencyState.insight))
                Proficiency.MEDICINE -> onProficiencyEvent(ProficiencyEvent.SetMedicine(!proficiencyState.medicine))
                Proficiency.PERCEPTION -> onProficiencyEvent(ProficiencyEvent.SetPerception(!proficiencyState.perception))
                Proficiency.SURVIVAL -> onProficiencyEvent(ProficiencyEvent.SetSurvival(!proficiencyState.survival))
                Proficiency.DECEPTION -> onProficiencyEvent(ProficiencyEvent.SetDeception(!proficiencyState.deception))
                Proficiency.INTIMIDATION -> onProficiencyEvent(ProficiencyEvent.SetIntimidation(!proficiencyState.intimidation))
                Proficiency.PERFORMANCE -> onProficiencyEvent(ProficiencyEvent.SetPerformance(!proficiencyState.performance))
                Proficiency.PERSUASION -> onProficiencyEvent(ProficiencyEvent.SetPersuasion(!proficiencyState.persuasion))
            }

            return -1;
        }

        return cost;
    }
}