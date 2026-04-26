package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.components.AppToolbar

private data class Hospital(
    val name: String,
    val rating: Double,
    val ratingCountText: String,
    val distanceKm: Double,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBack: () -> Unit,
) {
    val all = remember {
        listOf(
            Hospital(
                name = "Apollo Hospitals Chennai",
                rating = 4.5,
                ratingCountText = "51k rating",
                distanceKm = 2.9
            ),
            Hospital(
                name = "City Care Medical Center",
                rating = 4.2,
                ratingCountText = "12k rating",
                distanceKm = 4.1
            ),
            Hospital(
                name = "Green Valley Clinic",
                rating = 4.0,
                ratingCountText = "4.8k rating",
                distanceKm = 1.4
            ),
        )
    }

    // Always start empty so the field opens focused with no “filled” text.
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val filtered = remember(query) {
        val q = query.trim()
        if (q.isEmpty()) all else all.filter { it.name.contains(q, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppToolbar(title = "Search", onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = {
                    Text(
                        text = "Search hospitals…",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.45f)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF7F7F7),
                    focusedContainerColor = Color(0xFFF7F7F7),
                    unfocusedBorderColor = Color(0xFFE6E6E6),
                    focusedBorderColor = Color(0xFFE6E6E6),
                )
            )

            Spacer(Modifier.height(8.dp))
            Text(
                text = "${filtered.size} hospitals found in Chennai India",
                color = Color(0xFF9AA0A6),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(14.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 20.dp)
            ) {
                itemsIndexed(filtered) { idx, h ->
                    HospitalCard(
                        hospital = h,
                        accent = if (idx % 2 == 0) Color(0xFF4F7DF3) else Color(0xFF8BC34A)
                    )
                }
            }
        }
    }
}

@Composable
private fun HospitalCard(
    hospital: Hospital,
    accent: Color,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F5FF)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(accent.copy(alpha = 0.22f))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                        .size(52.dp)
                        .background(Color.White.copy(alpha = 0.35f), CircleShape)
                )
            }
        }

        Spacer(Modifier.height(10.dp))
        Text(
            text = hospital.name,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("⭐ ${hospital.rating}", fontWeight = FontWeight.SemiBold)
            Text(" (${hospital.ratingCountText})", color = Color(0xFF9AA0A6))
            Spacer(Modifier.size(10.dp))
            Text("📍 ${hospital.distanceKm}km away", color = Color(0xFF9AA0A6))
        }
    }
}
