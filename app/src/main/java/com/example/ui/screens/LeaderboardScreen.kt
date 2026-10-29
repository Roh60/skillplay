package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.dataset.DefaultCommunityData
import com.example.data.model.LeaderboardUser
import com.example.ui.components.UserAvatarView
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val isHindi by viewModel.isHindi.collectAsState()
    val user by viewModel.userProfile.collectAsState()

    val currentUserEntry = LeaderboardUser(
        rank = 7,
        name = user?.displayName ?: "Bharat Learner",
        avatarIndex = user?.avatarIndex ?: 0,
        stateName = user?.stateLocation ?: "India",
        points = user?.totalXp ?: 120,
        streak = user?.streakDays ?: 1,
        isCurrentUser = true
    )

    val allRanks = (DefaultCommunityData.weeklyLeaderboard + currentUserEntry).sortedByDescending { it.points }
        .mapIndexed { index, item -> item.copy(rank = index + 1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHindi) "साप्ताहिक राज्य लीडरबोर्ड" else "Weekly State Leaderboard",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("leaderboard_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = GoldAccent.copy(alpha = 0.14f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = GoldAccent,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (isHindi) "साप्ताहिक ज्ञान लीग" else "Weekly Knowledge League",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = if (isHindi)
                                    "प्रतिदिन क्विज़ हल करें और राज्य रैंकिंग में शीर्ष पर पहुंचें!"
                                else
                                    "Solve challenges daily to climb the state leaderboards!",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            items(allRanks) { entry ->
                val isMe = entry.isCurrentUser || entry.name == (user?.displayName ?: "")
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isMe) SaffronPrimary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        val rankColor = when (entry.rank) {
                            1 -> GoldAccent
                            2 -> Color(0xFF94A3B8)
                            3 -> Color(0xFFB45309)
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        }

                        Text(
                            text = "#${entry.rank}",
                            fontWeight = FontWeight.Bold,
                            color = rankColor,
                            fontSize = 15.sp,
                            modifier = Modifier.width(36.dp)
                        )

                        UserAvatarView(avatarIndex = entry.avatarIndex, size = 38)

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = entry.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                if (isMe) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = SaffronPrimary
                                    ) {
                                        Text(
                                            text = if (isHindi) "आप" else "You",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = entry.stateName,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${entry.points} XP",
                                fontWeight = FontWeight.Bold,
                                color = SaffronPrimary,
                                fontSize = 14.sp
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LocalFireDepartment,
                                    contentDescription = null,
                                    tint = SaffronPrimary,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = "${entry.streak}d",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
