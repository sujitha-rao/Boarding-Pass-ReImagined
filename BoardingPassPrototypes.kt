import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Colors ---
val DeltaBlue = Color(0xFF003366)
val DeltaRed = Color(0xFFE3132F)

@Composable
fun StageSpotlightGate() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        
        Text(
            text = "DEPARTURE GATE",
            style = MaterialTheme.typography.overline,
            color = Color.Gray
        )
        
        Text(
            text = "A18",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = DeltaBlue
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Countdown Timer Ring
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(200.dp)) {
                drawCircle(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    style = Stroke(width = 12.dp.toPx())
                )
                drawArc(
                    color = DeltaBlue,
                    startAngle = -90f,
                    sweepAngle = 270f, // 75% depleted
                    useCenter = false,
                    style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "BOARDING AT", fontSize = 14.sp, color = Color.Gray)
                Text(text = "10:15 AM", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "ZONE", fontSize = 18.sp, color = Color.Gray)
        Text(text = "4", fontSize = 80.sp, fontWeight = FontWeight.Black, color = DeltaBlue)

        Spacer(modifier = Modifier.weight(1f))

        // "More Details" Drawer Handle
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            color = Color(0xFFF5F5F5)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Seat 12A • Flight DL142", fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun ThreeCardPass() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEEEE))
    ) {
        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeltaBlue)
                .padding(top = 16.dp)
        ) {
            TabItem("SECURITY", false, Modifier.weight(1f))
            TabItem("GATE", true, Modifier.weight(1f))
            TabItem("BOARDING", false, Modifier.weight(1f))
        }

        // Card Content
        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            elevation = 4.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ZONE", style = MaterialTheme.typography.overline)
                Text("4", fontSize = 100.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                
                Divider(Modifier.padding(vertical = 24.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("BOARDING TIME", style = MaterialTheme.typography.overline)
                        Text("10:15 AM", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("GATE", style = MaterialTheme.typography.overline)
                        Text("A18", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Countdown Bar
                LinearProgressIndicator(
                    progress = 0.75f,
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = DeltaBlue,
                    backgroundColor = Color.LightGray
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    "DL 142 • ATL to JFK • S. RAO",
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun TabItem(text: String, selected: Boolean, modifier: Modifier) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color.White.copy(alpha = 0.6f),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 12.sp
        )
        if (selected) {
            Spacer(Modifier.height(4.dp))
            Box(Modifier.size(4.dp).background(Color.White, CircleShape))
        }
    }
}

@Composable
fun AmbientPass() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Simulating Lock Screen
        contentAlignment = Alignment.Center
    ) {
        // Dynamic Island / Widget Style
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(100.dp),
            color = Color(0xFF1C1C1E),
            shape = RoundedCornerShape(32.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Gate A18", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Boarding 10:15 AM", color = Color.Gray, fontSize = 14.sp)
                }
                
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = 0.75f,
                        color = Color.White,
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(48.dp)
                    )
                    Text("Z4", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Alternative 1: Stage Spotlight")
@Composable
fun PreviewStageSpotlight() {
    StageSpotlightGate()
}

@Preview(showBackground = true, name = "Alternative 2: Three-Card Pass")
@Composable
fun PreviewThreeCardPass() {
    ThreeCardPass()
}

@Preview(showBackground = true, name = "Alternative 3: Ambient Pass")
@Composable
fun PreviewAmbientPass() {
    AmbientPass()
}
