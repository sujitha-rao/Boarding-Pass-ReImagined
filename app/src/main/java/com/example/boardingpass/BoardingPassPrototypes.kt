package com.example.boardingpass

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

// --- Delta Brand Colors ---
val DeltaBlue = Color(0xFF003366)
val DeltaRed = Color(0xFFE3132F)
val AmbientBg = Color(0xFF1C1C1E).copy(alpha = 0.95f)
val ScreenBg = Color(0xFFFFFFFF)

// --- Shared Components ---

@Composable
fun RandomQRCode(modifier: Modifier = Modifier, gridSize: Int = 21) {
    val qrPattern = remember(gridSize) {
        List(gridSize * gridSize) { Random.nextBoolean() }
    }
    
    Canvas(modifier = modifier) {
        val cellSize = gridSize.toFloat().let { minOf(size.width, size.height) / it }
        
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                if (qrPattern[i * gridSize + j]) {
                    drawRect(
                        color = Color.Black,
                        topLeft = androidx.compose.ui.geometry.Offset(i * cellSize, j * cellSize),
                        size = androidx.compose.ui.geometry.Size(cellSize, cellSize)
                    )
                }
            }
        }
        
        drawFinderPattern(0f, 0f, cellSize)
        drawFinderPattern((gridSize - 7) * cellSize, 0f, cellSize)
        drawFinderPattern(0f, (gridSize - 7) * cellSize, cellSize)
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawFinderPattern(x: Float, y: Float, cellSize: Float) {
    drawRect(Color.Black, topLeft = androidx.compose.ui.geometry.Offset(x, y), size = androidx.compose.ui.geometry.Size(cellSize * 7, cellSize * 7))
    drawRect(Color.White, topLeft = androidx.compose.ui.geometry.Offset(x + cellSize, y + cellSize), size = androidx.compose.ui.geometry.Size(cellSize * 5, cellSize * 5))
    drawRect(Color.Black, topLeft = androidx.compose.ui.geometry.Offset(x + cellSize * 2, y + cellSize * 2), size = androidx.compose.ui.geometry.Size(cellSize * 3, cellSize * 3))
}

@Composable
fun DeviceFrame(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 410.dp, height = 860.dp)
            .background(Color(0xFF1A1A1A), RoundedCornerShape(54.dp)) // Outer Bezel
            .padding(8.dp)
            .border(2.dp, Color(0xFF333333), RoundedCornerShape(46.dp)) // Mid Frame
            .padding(4.dp)
            .background(Color.White, RoundedCornerShape(42.dp)) // Screen
    ) {
        content()
        // Dynamic Island
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 14.dp)
                .size(width = 126.dp, height = 38.dp)
                .background(Color.Black, RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun TabItem(text: String, selected: Boolean, modifier: Modifier) {
    Column(
        modifier = modifier.padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = if (selected) DeltaRed else Color.Gray,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 9.sp
        )
        if (selected) {
            Spacer(Modifier.height(4.dp))
            Box(Modifier.size(24.dp, 2.dp).background(DeltaRed))
        }
    }
}

@Composable
fun DeltaHeader(highlight: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 54.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Menu, contentDescription = null, tint = DeltaBlue)
            Text(
                "DELTA",
                color = DeltaRed,
                fontWeight = FontWeight.Black,
                letterSpacing = 4.sp,
                fontSize = 20.sp,
                style = if (highlight) LocalTextStyle.current.copy(shadow = androidx.compose.ui.graphics.Shadow(Color.LightGray, blurRadius = 4f)) else LocalTextStyle.current
            )
            Icon(Icons.Default.Notifications, contentDescription = null, tint = DeltaBlue)
        }
        Spacer(Modifier.height(8.dp))
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)
    }
}

@Composable
fun DetailsDrawerHandle() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 20.dp,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(36.dp, 4.dp).background(Color.LightGray, RoundedCornerShape(2.dp)))
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = DeltaRed)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Seat 12A • Flight DL142 • JFK to LAX",
                    fontWeight = FontWeight.Bold,
                    color = DeltaBlue,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// --- Alternative 1: Stage Spotlight ---

@Composable
fun StageSpotlightSecurity() {
    DeviceFrame {
        Column(
            modifier = Modifier.fillMaxSize().background(ScreenBg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DeltaHeader(highlight = true)
            Spacer(modifier = Modifier.height(24.dp))
            Surface(color = DeltaRed, shape = RoundedCornerShape(16.dp)) {
                Text(
                    "SECURITY",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text("SUJITHA RAO", fontSize = 28.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(16.dp).background(Color(0xFF2E7D32), CircleShape), contentAlignment = Alignment.Center) {
                    Text("✓", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.width(6.dp))
                Text("TSA PRECHECK • DIGITAL ID", color = Color(0xFF2E7D32), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text("FLIGHT DL 142", fontSize = 24.sp, fontWeight = FontWeight.Black, color = DeltaRed) // Highlighted
            Text("SOUTH TERMINAL", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            
            Spacer(modifier = Modifier.height(24.dp))
            Card(elevation = 12.dp, shape = RoundedCornerShape(16.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(220.dp).background(Color.White).padding(16.dp)) {
                        RandomQRCode(modifier = Modifier.fillMaxSize())
                    }
                    Text("HJ8S2L", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("📍", fontSize = 18.sp)
                Spacer(Modifier.width(4.dp))
                Text("NAVIGATE TO GATE A18", color = DeltaBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            Surface(
                color = Color.Black,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth().height(50.dp).clickable { }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text("Add to Wallet", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            DetailsDrawerHandle()
        }
    }
}

@Composable
fun StageSpotlightGate() {
    DeviceFrame {
        Column(
            modifier = Modifier.fillMaxSize().background(ScreenBg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DeltaHeader()
            Spacer(modifier = Modifier.height(32.dp))
            Surface(color = DeltaRed, shape = RoundedCornerShape(16.dp)) {
                Text("DEPARTURE GATE", modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "A18", fontSize = 80.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
            Spacer(modifier = Modifier.height(8.dp))
            
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    drawCircle(color = Color.LightGray.copy(alpha = 0.2f), style = Stroke(width = 14.dp.toPx()))
                    drawArc(color = DeltaRed, startAngle = -90f, sweepAngle = 270f, useCenter = false, style = Stroke(width = 14.dp.toPx(), cap = StrokeCap.Round))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "BOARDING", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text(text = "10:15 AM", fontSize = 36.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                    Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(8.dp)) {
                        Text("ON TIME", color = Color(0xFF2E7D32), fontSize = 10.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "ZONE", fontSize = 18.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "4", fontSize = 48.sp, fontWeight = FontWeight.Black, color = DeltaRed)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Card(elevation = 8.dp, shape = RoundedCornerShape(12.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(130.dp).background(Color.White).padding(10.dp)) {
                        RandomQRCode(modifier = Modifier.fillMaxSize())
                    }
                    Text("HJ8S2L", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(bottom = 4.dp))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            DetailsDrawerHandle()
        }
    }
}

@Composable
fun StageSpotlightInsideFlight() {
    DeviceFrame {
        Column(
            modifier = Modifier.fillMaxSize().background(ScreenBg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DeltaHeader()
            Spacer(modifier = Modifier.height(32.dp))
            Surface(color = DeltaRed, shape = RoundedCornerShape(16.dp)) {
                Text("INSIDE FLIGHT", modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(32.dp))
            
            // Departure Time Timer
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(180.dp)) {
                    drawCircle(color = Color.LightGray.copy(alpha = 0.2f), style = Stroke(width = 12.dp.toPx()))
                    drawArc(color = DeltaBlue, startAngle = -90f, sweepAngle = 300f, useCenter = false, style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("DEPARTURE", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("11:00 AM", fontSize = 28.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                    Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(8.dp)) {
                        Text("ON TIME", color = Color(0xFF2E7D32), fontSize = 10.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("YOUR SEAT", fontSize = 18.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                Text("12A", fontSize = 120.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                Text("WINDOW • COMFORT+", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DeltaRed)
                Text("VIEW SEAT MAP", color = DeltaBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, modifier = Modifier.padding(top = 8.dp).clickable { })
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Surface(
                    color = Color(0xFFF0F4F8),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth().clickable { }
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("🙋", fontSize = 24.sp)
                        Spacer(Modifier.width(12.dp))
                        Text("REQUEST ASSISTANCE", color = DeltaBlue, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
                    }
                }
                Surface(
                    color = Color(0xFFF0F4F8),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth().clickable { }
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("🍔", fontSize = 24.sp)
                        Spacer(Modifier.width(12.dp))
                        Text("ORDER MEAL", color = DeltaBlue, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
                    }
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            DetailsDrawerHandle()
        }
    }
}

// --- Alternative 2: Three-Card Pass ---

@Composable
fun ThreeCardSecurity() {
    DeviceFrame {
        ThreeCardBase(selectedTab = 0) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("SUJITHA RAO", fontSize = 24.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                
                Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(8.dp)) {
                    Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(12.dp).background(Color(0xFF2E7D32), CircleShape), contentAlignment = Alignment.Center) {
                            Text("✓", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.width(6.dp))
                        Text("DIGITAL ID • TSA PRE", color = Color(0xFF2E7D32), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                Text("FLIGHT DL 142", fontSize = 20.sp, fontWeight = FontWeight.Black, color = DeltaRed) // Highlighted
                Text("SOUTH TERMINAL", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                
                Spacer(modifier = Modifier.height(24.dp))
                Text("SECURITY PASS", fontWeight = FontWeight.Bold, color = Color.Gray, letterSpacing = 1.sp, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(240.dp).background(Color.White).padding(10.dp)) {
                        RandomQRCode(modifier = Modifier.fillMaxSize())
                    }
                    Text("HJ8S2L", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.clickable { }) {
                    Text("📍", fontSize = 16.sp)
                    Spacer(Modifier.width(4.dp))
                    Text("GATE A18", color = DeltaBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(200.dp).height(44.dp).clickable { }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Text("Add to Wallet", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ThreeCardGate() {
    DeviceFrame {
        ThreeCardBase(selectedTab = 1) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("GATE", fontWeight = FontWeight.Bold, color = Color.Gray, letterSpacing = 1.sp)
                Text("A18", fontSize = 100.sp, fontWeight = FontWeight.Black, color = DeltaBlue) // Large Gate
                
                Spacer(modifier = Modifier.height(8.dp))
                Text("ZONE", fontWeight = FontWeight.Bold, color = Color.Gray, letterSpacing = 1.sp)
                Text("4", fontSize = 100.sp, fontWeight = FontWeight.Black, color = DeltaRed) // Large Zone
                
                Spacer(modifier = Modifier.height(12.dp))
                Card(elevation = 4.dp, shape = RoundedCornerShape(12.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.size(110.dp).background(Color.White).padding(8.dp)) {
                            RandomQRCode(modifier = Modifier.fillMaxSize())
                        }
                        Text("HJ8S2L", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(bottom = 4.dp))
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Surface(
                    color = DeltaBlue.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("BOARDING TIME", style = MaterialTheme.typography.overline, color = Color.Gray)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("10:15 AM", fontSize = 32.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                            Spacer(Modifier.width(12.dp))
                            Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(4.dp)) {
                                Text("ON TIME", color = Color(0xFF2E7D32), fontSize = 10.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = 0.75f,
                            modifier = Modifier.fillMaxWidth().height(12.dp).background(Color.White, RoundedCornerShape(6.dp)),
                            color = DeltaRed,
                            backgroundColor = Color(0xFFF0F0F0)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                Text("DELTA • JFK TO LAX", fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun ThreeCardInsideFlight() {
    DeviceFrame {
        ThreeCardBase(selectedTab = 2) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                // Departure Time with Status and Timer Bar
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("11:00 AM", fontSize = 24.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                        Spacer(Modifier.width(8.dp))
                        Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(4.dp)) {
                            Text("ON TIME", color = Color(0xFF2E7D32), fontSize = 10.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = 0.2f,
                        modifier = Modifier.fillMaxWidth().height(8.dp).background(Color.White, RoundedCornerShape(4.dp)),
                        color = DeltaBlue,
                        backgroundColor = Color(0xFFF0F0F0)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SEAT", fontWeight = FontWeight.Bold, color = Color.Gray, letterSpacing = 1.sp)
                    Text("12A", fontSize = 120.sp, fontWeight = FontWeight.Black, color = DeltaBlue)
                    Text("WINDOW • COMFORT+", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = DeltaRed)
                    Text("VIEW SEAT MAP", color = DeltaBlue, fontSize = 11.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, modifier = Modifier.padding(top = 4.dp).clickable { })
                }
                
                Divider(Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = Color.LightGray.copy(alpha = 0.5f))
                
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = DeltaBlue),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("🙋", fontSize = 20.sp)
                        Spacer(Modifier.width(8.dp))
                        Text("REQUEST ASSISTANCE", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = DeltaBlue),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("🍔", fontSize = 20.sp)
                        Spacer(Modifier.width(8.dp))
                        Text("ORDER MEAL", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                Text("CONFIRMATION: HJ8S2L", fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun ThreeCardBase(selectedTab: Int, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxWidth().padding(top = 54.dp)) {
            Text("DELTA", color = DeltaRed, fontWeight = FontWeight.Black, letterSpacing = 4.sp, modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                TabItem("SECURITY", selectedTab == 0, Modifier.weight(1f))
                TabItem("DEPARTURE GATE", selectedTab == 1, Modifier.weight(1f))
                TabItem("INSIDE FLIGHT", selectedTab == 2, Modifier.weight(1f))
            }
            Divider(color = Color.LightGray.copy(alpha = 0.3f))
        }
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF2F2F2)).padding(16.dp)) {
            Card(elevation = 8.dp, shape = RoundedCornerShape(28.dp)) {
                content()
            }
        }
    }
}

// --- Alternative 3: Ambient Pass ---

@Composable
fun AmbientSecurity() {
    LockScreenFrame {
        AmbientBase {
            Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(60.dp).background(Color.White).padding(4.dp)) {
                        RandomQRCode(modifier = Modifier.fillMaxSize())
                    }
                    Text("HJ8S2L", color = Color.White.copy(alpha = 0.6f), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("SUJITHA RAO", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.width(6.dp))
                        Box(Modifier.size(14.dp).background(Color(0xFF4CAF50), CircleShape), contentAlignment = Alignment.Center) {
                            Text("✓", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Text("DL 142 • SOUTH TERMINAL", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                    Text("📍 GATE A18", color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun AmbientGate() {
    LockScreenFrame {
        AmbientBase {
            Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(54.dp).background(Color.White).padding(4.dp)) {
                        RandomQRCode(modifier = Modifier.fillMaxSize())
                    }
                    Text("HJ8S2L", color = Color.White.copy(alpha = 0.6f), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("GATE A18", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("10:15 AM", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black)
                        Spacer(Modifier.width(8.dp))
                        Surface(color = Color(0xFF4CAF50), shape = RoundedCornerShape(4.dp)) {
                            Text("ON TIME", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("ZONE", color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text("4", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

@Composable
fun AmbientInsideFlight() {
    LockScreenFrame {
        AmbientBase {
            Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("12A • WINDOW • COM+", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("11:00 AM", color = Color.White, fontSize = 12.sp)
                        Spacer(Modifier.width(4.dp))
                        CircularProgressIndicator(progress = 0.2f, color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("ON TIME", color = Color(0xFF4CAF50), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                        Text("MAP 🗺️", color = Color.White.copy(alpha = 0.7f), fontSize = 10.sp, textDecoration = TextDecoration.Underline)
                        Spacer(Modifier.width(8.dp))
                        Text("🙋 ASSIST", color = Color.White.copy(alpha = 0.7f), fontSize = 10.sp, textDecoration = TextDecoration.Underline)
                        Spacer(Modifier.width(8.dp))
                        Text("🍔 MEAL", color = Color.White.copy(alpha = 0.7f), fontSize = 10.sp, textDecoration = TextDecoration.Underline)
                    }
                }
                Surface(color = DeltaRed, shape = RoundedCornerShape(12.dp)) {
                    Text("INSIDE", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }
        }
    }
}

@Composable
fun LockScreenFrame(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 410.dp, height = 860.dp)
            .background(Color(0xFF1A1A1A), RoundedCornerShape(54.dp))
            .padding(12.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1A2A6C), Color(0xFFB21F1F), Color(0xFFFDBB2D))
                ),
                RoundedCornerShape(42.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("9:41", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Row {
                Box(modifier = Modifier.size(16.dp, 10.dp).background(Color.White.copy(alpha = 0.5f)))
                Spacer(Modifier.width(4.dp))
                Box(modifier = Modifier.size(10.dp).background(Color.White.copy(alpha = 0.5f)))
            }
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("9:41", fontSize = 100.sp, color = Color.White, fontWeight = FontWeight.ExtraLight)
            Text("Tuesday, October 24", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Normal)
        }
        
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp)
                .size(width = 126.dp, height = 38.dp)
                .background(Color.Black, RoundedCornerShape(20.dp))
        )

        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 180.dp)) {
            content()
        }

        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 60.dp).fillMaxWidth().padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(modifier = Modifier.size(52.dp), color = Color.Black.copy(alpha = 0.3f), shape = CircleShape) {
                Box(contentAlignment = Alignment.Center) { 
                    Box(modifier = Modifier.size(20.dp).background(Color.White, CircleShape)) 
                }
            }
            Surface(modifier = Modifier.size(52.dp), color = Color.Black.copy(alpha = 0.3f), shape = CircleShape) {
                Box(contentAlignment = Alignment.Center) { 
                    Box(modifier = Modifier.size(24.dp).background(Color.White, RoundedCornerShape(4.dp))) 
                }
            }
        }
        
        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp).size(140.dp, 5.dp).background(Color.White, RoundedCornerShape(3.dp)))
    }
}

@Composable
fun AmbientBase(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(0.94f).height(130.dp),
        color = AmbientBg,
        shape = RoundedCornerShape(36.dp),
        elevation = 16.dp
    ) {
        content()
    }
}

// --- Previews ---

@Preview(name = "Alt 1: Spotlight - Security") @Composable fun PreviewSpotlightSecurity() = StageSpotlightSecurity()
@Preview(name = "Alt 1: Spotlight - Departure Gate") @Composable fun PreviewSpotlightGate() = StageSpotlightGate()
@Preview(name = "Alt 1: Spotlight - Inside Flight") @Composable fun PreviewSpotlightInsideFlight() = StageSpotlightInsideFlight()

@Preview(name = "Alt 2: Three-Card - Security") @Composable fun PreviewThreeCardSecurity() = ThreeCardSecurity()
@Preview(name = "Alt 2: Three-Card - Departure Gate") @Composable fun PreviewThreeCardGate() = ThreeCardGate()
@Preview(name = "Alt 2: Three-Card - Inside Flight") @Composable fun PreviewThreeCardInsideFlight() = ThreeCardInsideFlight()

@Preview(name = "Alt 3: Ambient - Security") @Composable fun PreviewAmbientSecurity() = AmbientSecurity()
@Preview(name = "Alt 3: Ambient - Departure Gate") @Composable fun PreviewAmbientGate() = AmbientGate()
@Preview(name = "Alt 3: Ambient - Inside Flight") @Composable fun PreviewAmbientInsideFlight() = AmbientInsideFlight()
