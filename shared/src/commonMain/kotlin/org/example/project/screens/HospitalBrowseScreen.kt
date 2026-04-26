package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.components.AppToolbar
import org.example.project.components.HospitalListCard
import org.example.project.data.HospitalBrowseMode
import org.example.project.data.forBrowse
import org.example.project.data.sampleHospitals

@Composable
fun HospitalBrowseScreen(
    title: String,
    mode: HospitalBrowseMode,
    favoriteHospitalIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
    onBack: () -> Unit,
) {
    val items = remember(mode) {
        sampleHospitals().forBrowse(mode)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppToolbar(title = title, onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${items.size} hospitals in Chennai, India",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(14.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                itemsIndexed(items) { idx, h ->
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
