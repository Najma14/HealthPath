package org.example.project.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onSearch: () -> Unit,
) {
    val categories = remember {
        listOf(
            Category("Nearpy", "N", Color(0xFF3B82F6)),
            Category("Favourites", "F", Color(0xFF06B6D4)),
            Category("Top 10", "10", Color(0xFF60A5FA)),
            Category("Trending", "T", Color(0xFF1D4ED8)),
            Category("New Additions", "+", Color(0xFF2563EB)),
            Category("View All", "A", Color(0xFF64748B)),
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(12.dp))
            TopHeader(
                userName = "Jessica",
                location = "Bronx, New York, USA",
                onNotifications = { /* TODO */ },
            )

            Spacer(Modifier.height(16.dp))
            SearchBar(
                placeholder = "Search for diagnois / hospital",
                onClick = onSearch
            )

            Spacer(Modifier.height(18.dp))
            Text(
                text = "Find you Hospital",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 100.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(categories) { item ->
                    CategoryCard(
                        label = item.label,
                        badge = item.badge,
                        accent = item.accent
                    )
                }
            }
        }

        BottomNav(
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.BottomCenter)
                .padding(WindowInsets.navigationBars.asPaddingValues()),
            onLogout = onLogout
        )
    }
}

private data class Category(
    val label: String,
    val badge: String,
    val accent: Color,
)

@Composable
private fun TopHeader(
    userName: String,
    location: String,
    onNotifications: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            ) {
                Text(
                    text = userName.take(1).uppercase(),
                    modifier = Modifier.align(androidx.compose.ui.Alignment.Center),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    BadgeDot()
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        IconButton(onClick = onNotifications) {
            BadgeDot()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    placeholder: String,
    onClick: () -> Unit,
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        placeholder = { Text(placeholder) },
        leadingIcon = { BadgeDot() },
        singleLine = true,
        enabled = false,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
private fun CategoryCard(
    label: String,
    badge: String,
    accent: Color,
) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(34.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(accent.copy(alpha = 0.18f))
                        .align(androidx.compose.ui.Alignment.Center)
                ) {
                    Text(
                        text = badge,
                        modifier = Modifier.align(androidx.compose.ui.Alignment.Center),
                        color = accent,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun BottomNav(
    modifier: Modifier,
    onLogout: () -> Unit,
) {
    // simple visual match; actions are placeholders
    Box(modifier = modifier.fillMaxWidth()) {
        Surface(
            tonalElevation = 2.dp,
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 0.dp,
                ) {
                    NavigationBarItem(
                        selected = true,
                        onClick = { /* Home */ },
                        icon = { Text("H") },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { /* Forum */ },
                        icon = { Text("F") },
                        label = { Text("Forum") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { /* placeholder for center */ },
                        icon = { Spacer(Modifier.size(24.dp)) },
                        label = { Text("") },
                        enabled = false
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { /* Save */ },
                        icon = { Text("S") },
                        label = { Text("Save") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onLogout,
                        icon = { Text("P") },
                        label = { Text("Profile") }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { /* Add */ },
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.TopCenter)
                .padding(top = 8.dp),
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Text("+", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun BadgeDot() {
    Box(
        modifier = Modifier
            .size(18.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.35f))
    )
}

