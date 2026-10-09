package ir.bof.connect.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.bof.connect.ui.theme.BofGreen
import ir.bof.connect.ui.theme.BofGreenDark
import ir.bof.connect.ui.theme.BofPurple
import ir.bof.connect.ui.theme.BofPurpleDark

@Composable
fun HomeScreen() {
    var isConnected by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isConnected) 1.1f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0F0B1F),
                        Color(0xFF1A1235),
                        Color(0xFF0F0B1F)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Logo
            Text(
                text = "BOF",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = BofPurple
            )
            Text(
                text = "CONNECT",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFFB0A0FF),
                letterSpacing = 8.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Connect Button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(220.dp)
                    .scale(if (isConnected) pulseScale else 1f)
                    .clip(CircleShape)
                    .background(
                        if (isConnected)
                            Brush.radialGradient(listOf(BofGreen, BofGreenDark))
                        else
                            Brush.radialGradient(listOf(BofPurple, BofPurpleDark))
                    )
                    .clickable { isConnected = !isConnected }
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.PowerSettingsNew,
                        contentDescription = "Power",
                        tint = Color.White,
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isConnected) "متصل" else "قطع",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Status Text
            Text(
                text = if (isConnected) "✓ اتصال برقرار است" else "برای اتصال لمس کنید",
                fontSize = 14.sp,
                color = if (isConnected) BofGreen else Color(0xFF888888)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Button
            Button(
                onClick = { isConnected = !isConnected },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isConnected) Color(0xFFE53935) else BofPurple
                )
            ) {
                Text(
                    text = if (isConnected) "قطع اتصال" else "شروع اتصال",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
