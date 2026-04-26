package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import healthpath.shared.generated.resources.Res
import healthpath.shared.generated.resources.healthpath_logo
import healthpath.shared.generated.resources.logo
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    onDone: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(1200)
        onDone()
    }

    Column(
        modifier = Modifier
          //  .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(Res.drawable.logo),
            contentDescription = "HealthPath",
        )
        //Spacer(Modifier)
//        Text(
//            text = "HealthPath",
//            style = MaterialTheme.typography.headlineMedium,
//            fontWeight = FontWeight.SemiBold,
//        )
//        Spacer(Modifier.height(8.dp))
//        Text(
//            text = "Find the best care fast, based on urgency and location.",
//            style = MaterialTheme.typography.bodyMedium,
//        )
//        Spacer(Modifier.height(20.dp))
        CircularProgressIndicator()
    }
}

