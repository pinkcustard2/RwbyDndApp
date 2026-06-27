package com.example.rwbydnd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rwbydnd.ui.theme.RwbydndTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val tabItems = listOf(
                Tab(title = "Characters"),
                Tab(title = "Rules")
            )
            val pagerState = rememberPagerState() {tabItems.size}
            var selectedTab by remember { mutableIntStateOf(0) }
            var characters = listOf(
                Character("asbcs"),
                Character("adasdfsdf")
            )
            LaunchedEffect(selectedTab) {pagerState.animateScrollToPage(selectedTab)}
            LaunchedEffect(pagerState.currentPage) {selectedTab = pagerState.currentPage}
            RwbydndTheme {
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

                    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize().offset(0.dp, 75.dp))
                    {
                            index -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){Text(text = tabItems[index].title)}

                    }
                }
                LazyColumn(Modifier.padding(0.dp, 75.dp, 0.dp, 25.dp)) {
                    items(characters) { character -> Box(Modifier.fillMaxWidth()) {Text(character.characterName)}}
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RwbydndTheme {
        Greeting("Android")
    }
}

data class Tab(
    val title: String
)

