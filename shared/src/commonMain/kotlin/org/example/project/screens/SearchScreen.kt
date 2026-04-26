package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.components.AppToolbar
import org.example.project.components.HospitalListCard
import org.example.project.components.HospitalSearchField
import org.example.project.data.sampleHospitals
import org.example.project.data.sampleServices

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBack: () -> Unit,
    favoriteHospitalIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
) {
    val all = remember { sampleHospitals() }
    val allServices = remember { sampleServices() }

    // Always start empty so the field opens focused with no “filled” text.
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val filteredHospitals = remember(query, all, allServices) {
        val q = query.trim()
        if (q.isEmpty()) {
            all
        } else {
            val matchingServiceIds = allServices
                .asSequence()
                .filter { it.name.contains(q, ignoreCase = true) || it.category.contains(q, ignoreCase = true) }
                .map { it.id }
                .toSet()

            all.filter { h ->
                h.name.contains(q, ignoreCase = true) || h.serviceIds.any { it in matchingServiceIds }
            }
        }
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
            HospitalSearchField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.focusRequester(focusRequester),
                placeholder = "Search services / hospitals…",
            )

            Spacer(Modifier.height(8.dp))
            Text(
                text = "${filteredHospitals.size} hospitals found in Chennai India",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(14.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 20.dp)
            ) {
                if (filteredHospitals.isEmpty()) {
                    item {
                        Text(
                            text = "No hospitals match your search.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                } else {
                    itemsIndexed(filteredHospitals) { idx, h ->
                        HospitalListCard(
                            hospital = h,
                            accent = if (idx % 2 == 0) Color(0xFF4F7DF3) else Color(0xFF8BC34A),
                            isFavorite = h.id in favoriteHospitalIds,
                            onToggleFavorite = { onToggleFavorite(h.id) },
                        )
                    }
                }
            }
        }
    }
}
