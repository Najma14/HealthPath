package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import healthpath.shared.generated.resources.Res
import healthpath.shared.generated.resources.marker_pin
import healthpath.shared.generated.resources.search_icon
import org.example.project.components.AdvancedBottomBar
import org.example.project.components.HomeBottomTab
import org.example.project.components.HospitalListCard
import org.example.project.components.HospitalSearchField
import org.example.project.data.HospitalBrowseMode
import org.example.project.data.sampleHospitals
import org.example.project.theme.AppTheme
import org.example.project.theme.LocalAppTheme
import org.example.project.theme.LocalAppThemeSetter
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onSearch: () -> Unit,
    favoriteHospitalIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
    onBrowseHospitals: (String, HospitalBrowseMode) -> Unit,
) {
    var selectedTab by remember { mutableStateOf(HomeBottomTab.Home) }

    val categories = remember {
        listOf(
            Category("Nearpy", "N", Color(0xFF3B82F6), CategoryNav.Browse("Nearpy", HospitalBrowseMode.Nearby)),
            Category("Favourites", "F", Color(0xFF06B6D4), CategoryNav.OpenFavouritesTab),
            Category("Top 10", "10", Color(0xFF60A5FA), CategoryNav.Browse("Top 10", HospitalBrowseMode.Top10)),
            Category("Trending", "T", Color(0xFF1D4ED8), CategoryNav.Browse("Trending", HospitalBrowseMode.Trending)),
            Category("New Additions", "+", Color(0xFF2563EB), CategoryNav.Browse("New Additions", HospitalBrowseMode.NewAdditions)),
            Category("View All", "A", Color(0xFF64748B), CategoryNav.Browse("All hospitals", HospitalBrowseMode.ViewAll)),
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 20.dp)
                .padding(bottom = 96.dp)
        ) {
            Spacer(Modifier.height(12.dp))
            TopHeader(
                userName = "Kavya Iyer",
                location = "T. Nagar, Chennai, Tamil Nadu",
                onNotifications = { /* TODO */ },
            )

            Spacer(Modifier.height(16.dp))
            SearchBar(
                placeholder = "Search for diagnosis / service / hospital",
                onClick = onSearch
            )

            Spacer(Modifier.height(18.dp))

            when (selectedTab) {
                HomeBottomTab.Home -> {
                    Text(
                        text = "Find your Hospital",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(Modifier.height(12.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(categories) { item ->
                            CategoryCard(
                                label = item.label,
                                badge = item.badge,
                                accent = item.accent,
                                onClick = {
                                    when (val nav = item.nav) {
                                        CategoryNav.OpenFavouritesTab -> selectedTab = HomeBottomTab.Favourites
                                        is CategoryNav.Browse -> onBrowseHospitals(nav.title, nav.mode)
                                    }
                                },
                            )
                        }
                    }
                }
                HomeBottomTab.Theme -> ThemePlaceholderTab()
                HomeBottomTab.Favourites -> FavouritesTab(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    favoriteHospitalIds = favoriteHospitalIds,
                    onToggleFavorite = onToggleFavorite,
                )
                HomeBottomTab.Profile -> ProfileTab(onSignOut = onLogout)
            }
        }

        AdvancedBottomBar(
            selected = selectedTab,
            onSelect = { selectedTab = it },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(WindowInsets.navigationBars.asPaddingValues()),
        )
    }
}

private sealed interface CategoryNav {
    data object OpenFavouritesTab : CategoryNav
    data class Browse(val title: String, val mode: HospitalBrowseMode) : CategoryNav
}

private data class Category(
    val label: String,
    val badge: String,
    val accent: Color,
    val nav: CategoryNav,
)

@Composable
private fun ThemePlaceholderTab() {
    val appTheme = LocalAppTheme.current
    val setTheme = LocalAppThemeSetter.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Theme",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Choose light, dark, or match your device — same idea as the rikaab driver app.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ThemeChip(
                label = "Light",
                selected = appTheme == AppTheme.LIGHT,
                onClick = { setTheme(AppTheme.LIGHT) },
            )
            ThemeChip(
                label = "Dark",
                selected = appTheme == AppTheme.DARK,
                onClick = { setTheme(AppTheme.DARK) },
            )
            ThemeChip(
                label = "System",
                selected = appTheme == AppTheme.SYSTEM,
                onClick = { setTheme(AppTheme.SYSTEM) },
            )
        }
    }
}

@Composable
private fun ThemeChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val scheme = MaterialTheme.colorScheme
    val bg = if (selected) {
        Brush.linearGradient(listOf(scheme.primary, scheme.tertiary))
    } else {
        Brush.linearGradient(
            listOf(
                scheme.surfaceVariant,
                scheme.surfaceContainerHigh,
            )
        )
    }
    Text(
        text = label,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .background(bg)
            .padding(horizontal = 18.dp, vertical = 10.dp),
        color = if (selected) scheme.onPrimary else scheme.onSurfaceVariant,
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
    )
}

@Composable
private fun FavouritesTab(
    modifier: Modifier = Modifier,
    favoriteHospitalIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
) {
    val all = remember { sampleHospitals() }
    val favourites = remember(favoriteHospitalIds, all) {
        all.filter { it.id in favoriteHospitalIds }
    }

    var query by remember { mutableStateOf("") }
    LaunchedEffect(favoriteHospitalIds) {
        if (favoriteHospitalIds.isEmpty()) query = ""
    }
    val displayed = remember(query, favourites) {
        val q = query.trim()
        if (q.isEmpty()) favourites else favourites.filter { it.name.contains(q, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(bottom = 20.dp)
    ) {
        item {
            Text(
                text = "Favourites",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Same list style as search — tap ♡ on any hospital card to save it.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(16.dp))
        }
        if (favourites.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "No favourites yet",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Open Search or a category list and tap the heart on a hospital.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        } else {
            item {
                Column(Modifier.fillMaxWidth()) {
                    HospitalSearchField(
                        value = query,
                        onValueChange = { query = it },
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "${displayed.size} saved hospitals",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
            if (displayed.isEmpty()) {
                item {
                    Text(
                        text = "No favourites match your search.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            } else {
                itemsIndexed(displayed) { idx, h ->
                    HospitalListCard(
                        hospital = h,
                        accent = if (idx % 2 == 0) Color(0xFF4F7DF3) else Color(0xFF8BC34A),
                        isFavorite = true,
                        onToggleFavorite = { onToggleFavorite(h.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileTab(onSignOut: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(Color(0xFF6366F1), Color(0xFF22D3EE))
                            )
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "K",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text("Kavya Iyer", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.titleMedium)
                    Text(
                        "T. Nagar, Chennai, Tamil Nadu",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = onSignOut,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF111111),
                contentColor = Color.White,
            ),
        ) {
            Text("Sign out", fontWeight = FontWeight.SemiBold)
        }
    }
}

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
                    modifier = Modifier.align(Alignment.Center),
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.marker_pin),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
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

//        IconButton(onClick = onNotifications) {
//            Box(
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(CircleShape)
//                    .background(MaterialTheme.colorScheme.surfaceVariant),
//                contentAlignment = Alignment.Center,
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(8.dp)
//                        .clip(CircleShape)
//                        .background(Color(0xFFE11D48))
//                )
//            }
//        }
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
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.search_icon),
                contentDescription = "Search",
                modifier = Modifier.size(22.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
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
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = badge,
                        modifier = Modifier.align(Alignment.Center),
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

