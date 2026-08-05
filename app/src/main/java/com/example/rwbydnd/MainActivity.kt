package com.example.rwbydnd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room3.Room

import com.example.rwbydnd.ui.theme.RwbydndTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CharacterDatabase::class.java,
            "CharacterTest.db"
        ).build()
    }

    private val viewModel by viewModels<CharacterViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CharacterViewModel(db.characterDao) as T
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
                val state by viewModel.state.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = MainMenu
                )
                {
                    composable<MainMenu>
                    {
                        MainMenuScreen().MainScreen(
                            navController,
                            state,
                            viewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg1>
                    {
                        CharacterCreationScreen().CharacterCreationPg1(
                            navController,
                            state,
                            viewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg2>
                    {
                        CharacterCreationScreen().CharacterCreationPg2(
                            navController,
                            state,
                            viewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg3>
                    {
                        CharacterCreationScreen().CharacterCreationPg3(
                            navController,
                            state,
                            viewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg4>
                    {
                        CharacterCreationScreen().CharacterCreationPg4(
                            navController,
                            state,
                            viewModel::onEvent
                        )
                    }
                    composable<CharacterCreationPg5>
                    {
                        CharacterCreationScreen().CharacterCreationPg5(
                            navController,
                            state,
                            viewModel::onEvent
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

