package com.example.rwbydnd.database

sealed interface StatsEvent
{
    object NewStats: StatsEvent
    object ResetState: StatsEvent
    data class SetCharacterId(val characterId: Int): StatsEvent
    data class SetStrength(val strength: Int): StatsEvent
    data class SetDexterity(val dexterity: Int): StatsEvent
    data class SetIntelligence(val intelligence: Int): StatsEvent
    data class SetWisdom(val wisdom: Int): StatsEvent
    data class SetConstitution(val constitution: Int): StatsEvent
    data class SetCharisma(val charisma: Int): StatsEvent
    data class SetStatsFromId(val characterId: Int): StatsEvent
}