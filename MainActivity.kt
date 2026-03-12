import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                                    val icon = when(index) {
                                        0 -> androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = android.R.drawable.ic_menu_view)
                                        1 -> androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = android.R.drawable.ic_menu_sort_by_size)
                                        else -> androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = android.R.drawable.ic_menu_today)
                                    }
                                    // Using simple text for now to avoid resource ID issues in a prototype
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
                        1 -> ThreeCardPass()
                        2 -> AmbientPass()
                    }
                }
            }
        }
    }
}
