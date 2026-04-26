package org.example.project.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class HomeBottomTab {
    Home,
    Theme,
    Favourites,
    Profile,
}

@Composable
fun AdvancedBottomBar(
    selected: HomeBottomTab,
    onSelect: (HomeBottomTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val accent = Color(0xFF6366F1)
    val accentSoft = Color(0xFFEEF2FF)
    val outline = Color(0xFFE2E8F0)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0x406366F1),
                spotColor = Color(0x506366F1),
            )
            .clip(RoundedCornerShape(28.dp))
            .border(1.dp, outline.copy(alpha = 0.45f), RoundedCornerShape(28.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.98f),
                        Color(0xFFF8FAFC),
                    )
                )
            )
            .padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HomeBottomTab.entries.forEach { tab ->
                val isSelected = tab == selected
                val tint by animateColorAsState(
                    targetValue = if (isSelected) accent else Color(0xFF94A3B8),
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    label = "tint"
                )
                val pillHeight by animateDpAsState(
                    targetValue = if (isSelected) 44.dp else 0.dp,
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    label = "pillH"
                )
                val pillOffset by animateDpAsState(
                    targetValue = if (isSelected) 0.dp else 22.dp,
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    label = "pillY"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { onSelect(tab) }
                        .padding(vertical = 4.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(48.dp),
                    ) {
                        if (pillHeight > 0.dp) {
                            Box(
                                modifier = Modifier
                                    .size(width = 52.dp, height = pillHeight)
                                    .offset(y = pillOffset)
                                    .clip(RoundedCornerShape(22.dp))
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(accentSoft, Color.White.copy(alpha = 0.85f))
                                        )
                                    )
                            )
                        }
                        TabGlyph(tab = tab, tint = tint)
                    }
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = when (tab) {
                            HomeBottomTab.Home -> "Home"
                            HomeBottomTab.Theme -> "Theme"
                            HomeBottomTab.Favourites -> "Favourites"
                            HomeBottomTab.Profile -> "Profile"
                        },
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                        color = tint,
                    )
                }
            }
        }
    }
}

@Composable
private fun TabGlyph(tab: HomeBottomTab, tint: Color) {
    Canvas(modifier = Modifier.size(26.dp)) {
        val w = size.width
        val h = size.height
        val stroke = Stroke(width = 2.1f, cap = StrokeCap.Round)
        when (tab) {
            HomeBottomTab.Home -> {
                val path = Path().apply {
                    moveTo(w * 0.5f, h * 0.12f)
                    lineTo(w * 0.88f, h * 0.42f)
                    lineTo(w * 0.78f, h * 0.42f)
                    lineTo(w * 0.78f, h * 0.88f)
                    lineTo(w * 0.22f, h * 0.88f)
                    lineTo(w * 0.22f, h * 0.42f)
                    lineTo(w * 0.12f, h * 0.42f)
                    close()
                }
                drawPath(path, color = tint, style = stroke)
            }
            HomeBottomTab.Theme -> {
                drawArc(
                    color = tint,
                    startAngle = -90f,
                    sweepAngle = 180f,
                    useCenter = false,
                    style = stroke,
                    topLeft = Offset(w * 0.18f, h * 0.18f),
                    size = Size(w * 0.64f, h * 0.64f),
                )
                drawCircle(
                    color = tint.copy(alpha = 0.35f),
                    radius = w * 0.12f,
                    center = Offset(w * 0.72f, h * 0.32f),
                )
                drawCircle(
                    color = tint.copy(alpha = 0.55f),
                    radius = w * 0.08f,
                    center = Offset(w * 0.82f, h * 0.58f),
                )
                drawCircle(
                    color = tint.copy(alpha = 0.75f),
                    radius = w * 0.06f,
                    center = Offset(w * 0.62f, h * 0.72f),
                )
            }
            HomeBottomTab.Favourites -> {
                val path = Path().apply {
                    moveTo(w * 0.5f, h * 0.78f)
                    cubicTo(
                        w * 0.15f, h * 0.52f, w * 0.12f, h * 0.32f,
                        w * 0.5f, h * 0.22f
                    )
                    cubicTo(
                        w * 0.88f, h * 0.32f, w * 0.85f, h * 0.52f,
                        w * 0.5f, h * 0.78f
                    )
                }
                drawPath(path, color = tint, style = stroke)
            }
            HomeBottomTab.Profile -> {
                drawCircle(
                    color = tint,
                    center = Offset(w * 0.5f, h * 0.34f),
                    radius = w * 0.16f,
                    style = stroke,
                )
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(w * 0.22f, h * 0.56f),
                    size = Size(w * 0.56f, h * 0.36f),
                    cornerRadius = CornerRadius(w * 0.18f, w * 0.18f),
                    style = stroke,
                )
            }
        }
    }
}
