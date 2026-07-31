package com.rewire21.app.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rewire21.app.presentation.viewmodel.DashboardViewModel

@Composable
fun OnboardingScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onComplete: () -> Unit
) {
    var selectedHours by remember { mutableStateOf(4) }
    var agreedToTerms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🧠 REWIRE21",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Rewrite Your Habits in 21 Days",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "📊 Choose Your Daily Limit",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Hours per day: $selectedHours")

                Slider(
                    value = selectedHours.toFloat(),
                    onValueChange = { selectedHours = it.toInt() },
                    valueRange = 1f..6f,
                    steps = 5
                )

                val points = when (selectedHours) {
                    1 -> 2000
                    2 -> 4000
                    3 -> 5000
                    4 -> 7000
                    5 -> 8500
                    else -> 10000
                }

                Text("Points for 21 days: $points")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it }
            )
            Text(
                text = "I agree to the Terms & Conditions",
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.updateDailyLimit(selectedHours)
                viewModel.setOnboardingComplete()
                onComplete()
            },
            enabled = agreedToTerms,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🚀 Start 21-Day Challenge")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "⚠️ Early Exit Penalty: ₹100",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error
        )
    }
}
