package com.example.rwbydnd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.rwbydnd.ui.theme.RwbydndTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RwbydndTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = MainMenu
                )
                {
                    composable<MainMenu>
                    {
                        MainMenuScreen().MainScreen(navController)
                    }
                    composable<CharacterCreation>
                    {
                        Text("Character creation screen")
                    }
                }


            }
        }
    }
}


@Serializable
object MainMenu

@Serializable
object CharacterCreation

data class Tab(
    val title: String
)

