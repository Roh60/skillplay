package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Spellcheck
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary

@Composable
fun UserAvatarView(
    avatarIndex: Int,
    size: Int = 40,
    modifier: Modifier = Modifier
) {
    val colors = listOf(
        SaffronPrimary,
        ChakraBlue,
        IndiaGreen,
        Color(0xFF8B5CF6),
        Color(0xFFEC4899),
        Color(0xFF0D9488)
    )
    val initials = listOf("BP", "AS", "RV", "PH", "VS", "KR")
    val selectedColor = colors.getOrElse(avatarIndex % colors.size) { SaffronPrimary }
    val initial = initials.getOrElse(avatarIndex % initials.size) { "SP" }

    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(selectedColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = (size * 0.4).sp
        )
    }
}

fun getCategoryIcon(iconName: String): ImageVector {
    return when (iconName) {
        "Public" -> Icons.Default.Public
        "AccountBalance" -> Icons.Default.AccountBalance
        "Landscape" -> Icons.Default.Landscape
        "Gavel" -> Icons.Default.Gavel
        "Science" -> Icons.Default.Science
        "Calculate" -> Icons.Default.Calculate
        "Psychology" -> Icons.Default.Psychology
        "Spellcheck" -> Icons.Default.Spellcheck
        "MenuBook" -> Icons.Default.MenuBook
        "WorkOutline" -> Icons.Default.WorkOutline
        "ChatBubbleOutline" -> Icons.Default.ChatBubbleOutline
        "Computer" -> Icons.Default.Computer
        "Security" -> Icons.Default.Security
        "AccountBalanceWallet" -> Icons.Default.AccountBalanceWallet
        "Flag" -> Icons.Default.Flag
        "EmojiEvents" -> Icons.Default.EmojiEvents
        "LocalFireDepartment" -> Icons.Default.LocalFireDepartment
        "School" -> Icons.Default.School
        "RecordVoiceOver" -> Icons.Default.RecordVoiceOver
        "VerifiedUser" -> Icons.Default.VerifiedUser
        "Stars" -> Icons.Default.Stars
        else -> Icons.Default.School
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillPlayTopBar(
    title: String,
    streak: Int,
    totalXp: Int,
    isHindi: Boolean,
    onToggleLanguage: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        },
        actions = {
            // Streak Pill
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = SaffronPrimary.copy(alpha = 0.15f),
                modifier = Modifier
                    .padding(end = 6.dp)
                    .testTag("streak_pill")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Streak",
                        tint = SaffronPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "$streak",
                        color = SaffronPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            // XP Pill
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = GoldAccent.copy(alpha = 0.18f),
                modifier = Modifier
                    .padding(end = 6.dp)
                    .testTag("xp_pill")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Stars,
                        contentDescription = "XP",
                        tint = GoldAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "$totalXp",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            // Language Toggle
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onToggleLanguage() }
                    .padding(end = 8.dp)
                    .testTag("language_toggle_button")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Language",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isHindi) "हिं" else "EN",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
    )
}
