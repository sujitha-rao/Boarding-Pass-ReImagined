package com.example.boardingpass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentPrototype by remember { mutableStateOf(0) }
            val prototypes = listOf("Spotlight", "Three-Card", "Ambient")

            Scaffold(
                bottomBar = {
                    BottomNavigation(backgroundColor = DeltaBlue, contentColor = androidx.compose.ui.graphics.Color.White) {
                        prototypes.forEachIndexed { index, title ->
                            BottomNavigationItem(
                                selected = currentPrototype == index,
                                onClick = { currentPrototype = index },
                                label = { Text(title) },
                                icon = {
                                    Text(if(currentPrototype == index) "●" else "○")
                                }
                            )
                        }
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    when (currentPrototype) {
                        0 -> StageSpotlightGate()
                        1 -> ThreeCardGate()
                        2 -> AmbientGate()
                    }
                }
            }
        }
    }
}
