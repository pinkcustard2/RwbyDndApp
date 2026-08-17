package com.example.rwbydnd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room3.Room
import com.example.rwbydnd.characterCreation.CharacterCreatorPg1
import com.example.rwbydnd.characterCreation.CharacterCreatorPg2
import com.example.rwbydnd.characterCreation.CharacterCreatorPg3
import com.example.rwbydnd.characterCreation.CharacterCreatorPg4
import com.example.rwbydnd.database.CharacterDatabase
import com.example.rwbydnd.ui.theme.RwbydndTheme
import com.example.rwbydnd.viewmodels.CharacterViewModel
import com.example.rwbydnd.viewmodels.StatsViewModel
import kotlinx.serialization.Serializable


// To do:
// Make so cant have 2 characters of same name at some point
// Make so species and semblance strength text defaults to correct one when editing character
class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CharacterDatabase::class.java,
            "CharacterTest.db"
        ).build()
    }

    private val characterViewModel by viewModels<CharacterViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CharacterViewModel(db.characterDao) as T
                }
            }
        }
    )

    private val statsViewModel by viewModels<StatsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return StatsViewModel(db.statsDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RwbydndTheme {
                val navController = rememberNavController()
                val characterState by characterViewModel.state.collectAsState()
                val statsState by statsViewModel.state.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = MainMenu
                )
                {
                    composable<MainMenu>
                    {
                        MainMenuScreen().MainScreen(
                            navController,
                            characterState,
                            characterViewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg1>
                    {
                        CharacterCreatorPg1().CharacterCreationPg1(
                            navController,
                            characterState,
                            characterViewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg2>
                    {
                        CharacterCreatorPg2().CharacterCreationPg2(
                            navController,
                            characterState,
                            characterViewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg3>
                    {
                        CharacterCreatorPg3().CharacterCreationPg3(
                            navController,
                            characterState,
                            characterViewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg4>
                    {
                        CharacterCreatorPg4().CharacterCreationPg4(
                            navController,
                            characterState,
                            statsState,
                            characterViewModel::onEvent,
                            statsViewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}


@Serializable
object MainMenu

@Serializable
object CharacterCreationPg1

@Serializable
object CharacterCreationPg2

@Serializable
object CharacterCreationPg3

@Serializable
object CharacterCreationPg4

@Serializable
object CharacterCreationPg5

data class Tab(
    val title: String
)

