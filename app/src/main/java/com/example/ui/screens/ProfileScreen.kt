package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.dataset.LessonBank
import com.example.data.model.BadgesData
import com.example.data.model.CategoriesData
import com.example.data.model.GoalTrack
import com.example.ui.components.UserAvatarView
import com.example.ui.components.getCategoryIcon
import com.example.ui.navigation.AppScreen
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@Composable
fun ProfileScreen(
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    val user by viewModel.userProfile.collectAsState()
    val isHindi by viewModel.isHindi.collectAsState()
    val lessonProgress by viewModel.lessonProgressList.collectAsState()
    val savedLessons by viewModel.savedLessons.collectAsState()

    var showEditDialog by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }
    var selectedProfileTab by remember { mutableStateOf(0) } // 0: Stats, 1: Badges, 2: Saved

    val totalLessonsCompleted = lessonProgress.count { it.completedAt > 0 }
    val totalQuizzes = user?.totalQuizzesTaken ?: 0
    val totalCorrect = user?.totalQuizzesCorrect ?: 0
    val totalQuestions = user?.totalQuestionsAnswered ?: 0
    val accuracy = if (totalQuestions > 0) (totalCorrect * 100 / totalQuestions) else 75

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // User Profile Header Card
        item {
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("profile_header_card")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        UserAvatarView(avatarIndex = user?.avatarIndex ?: 0, size = 76)
                        Surface(
                            shape = CircleShape,
                            color = SaffronPrimary,
                            modifier = Modifier
                                .size(26.dp)
                                .clickable { showEditDialog = true }
                                .testTag("edit_avatar_button")
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Profile",
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = user?.displayName ?: "Bharat Learner",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = user?.stateLocation ?: "New Delhi, India",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = SaffronPrimary.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "🇮🇳 " + (if (isHindi) "स्किलप्ले विद्वान" else "SkillPlay Scholar"),
                                color = SaffronPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable { viewModel.navigateTo(AppScreen.Settings) }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isHindi) "सेटिंग्स" else "Settings",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    val currentGoal = GoalTrack.fromId(user?.primaryGoalTrack ?: "competitive_exams")
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = ChakraBlue.copy(alpha = 0.12f),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "🎯 " + (if (isHindi) currentGoal.titleHi else currentGoal.titleEn),
                            color = ChakraBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }

        // Stats Overview Grid (4 Key Metrics)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Total XP
                MetricCard(
                    title = if (isHindi) "कुल XP" else "Total XP",
                    value = "${user?.totalXp ?: 120}",
                    icon = Icons.Default.Stars,
                    color = GoldAccent,
                    modifier = Modifier.weight(1f)
                )

                // Streak
                MetricCard(
                    title = if (isHindi) "स्ट्रीक" else "Streak",
                    value = "${user?.streakDays ?: 1} " + if (isHindi) "दिन" else "d",
                    icon = Icons.Default.LocalFireDepartment,
                    color = SaffronPrimary,
                    modifier = Modifier.weight(1f)
                )

                // Accuracy
                MetricCard(
                    title = if (isHindi) "सटीकता" else "Accuracy",
                    value = "$accuracy%",
                    icon = Icons.Default.EmojiEvents,
                    color = ChakraBlue,
                    modifier = Modifier.weight(1f)
                )

                // Lessons Completed
                MetricCard(
                    title = if (isHindi) "पाठ" else "Lessons",
                    value = "$totalLessonsCompleted",
                    icon = Icons.Default.CheckCircle,
                    color = IndiaGreen,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Tab Navigation inside Profile
        item {
            TabRow(
                selectedTabIndex = selectedProfileTab,
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.clip(RoundedCornerShape(14.dp))
            ) {
                Tab(
                    selected = selectedProfileTab == 0,
                    onClick = { selectedProfileTab = 0 },
                    text = { Text(if (isHindi) "प्रगति" else "Progress", fontSize = 13.sp) }
                )
                Tab(
                    selected = selectedProfileTab == 1,
                    onClick = { selectedProfileTab = 1 },
                    text = { Text(if (isHindi) "पदक" else "Badges", fontSize = 13.sp) }
                )
                Tab(
                    selected = selectedProfileTab == 2,
                    onClick = { selectedProfileTab = 2 },
                    text = { Text(if (isHindi) "सहेजे गए" else "Saved (${savedLessons.size})", fontSize = 13.sp) }
                )
            }
        }

        // Tab 0: Category-wise Progress
        if (selectedProfileTab == 0) {
            item {
                Text(
                    text = if (isHindi) "विषयवार सीखने की प्रगति" else "Category Progress Breakdown",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            items(CategoriesData.allCategories) { category ->
                val count = lessonProgress.count { it.categoryId == category.id && it.completedAt > 0 }
                val pct = (count.toFloat() / category.totalLessons).coerceIn(0f, 1f)

                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (isHindi) category.nameHi else category.nameEn,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "$count / ${category.totalLessons}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { pct },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(5.dp),
                            color = if (pct >= 0.5f) IndiaGreen else SaffronPrimary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            strokeCap = StrokeCap.Round
                        )
                    }
                }
            }
        }

        // Tab 1: Earned Badges
        if (selectedProfileTab == 1) {
            items(BadgesData.allBadges) { badge ->
                val isUnlocked = (user?.totalXp ?: 0) >= badge.requiredXp &&
                        totalLessonsCompleted >= badge.requiredLessons &&
                        (user?.streakDays ?: 0) >= badge.requiredStreak

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(if (isUnlocked) GoldAccent.copy(alpha = 0.2f) else Color.Gray.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isUnlocked) getCategoryIcon(badge.iconName) else Icons.Default.Lock,
                                contentDescription = null,
                                tint = if (isUnlocked) GoldAccent else Color.Gray,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isHindi) badge.titleHi else badge.titleEn,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (isHindi) badge.descriptionHi else badge.descriptionEn,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (isUnlocked) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = IndiaGreen.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = if (isHindi) "प्राप्त" else "Earned",
                                    color = IndiaGreen,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Tab 2: Saved / Bookmarked Lessons
        if (selectedProfileTab == 2) {
            if (savedLessons.isEmpty()) {
                item {
                    Text(
                        text = if (isHindi) "कोई सहेजा गया पाठ नहीं है। पाठों पर बुकमार्क आइकन दबाकर उन्हें यहाँ सहेजें।"
                        else "No saved lessons yet. Tap the bookmark icon on any lesson to save it for quick revision.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                items(savedLessons) { saved ->
                    val lesson = LessonBank.getLessonById(saved.lessonId)
                    if (lesson != null) {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.navigateTo(AppScreen.LessonView(lesson.id))
                                }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(14.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = null,
                                    tint = SaffronPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = if (isHindi) lesson.titleHi else lesson.titleEn,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "${lesson.estimatedMinutes} " + if (isHindi) "मिनट पठन" else "min read",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Open",
                                    tint = SaffronPrimary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Account & Privacy Management Section
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (isHindi) "डेटा व गोपनीयता नियंत्रण" else "Data & Privacy Controls",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (isHindi)
                            "स्किलप्ले इंडिया आपकी गोपनीयता का सम्मान करता है। कोई अनावश्यक व्यक्तिगत डेटा एकत्र नहीं किया जाता।"
                        else
                            "SkillPlay India respects your privacy. No unnecessary personal info is required or shared.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.navigateTo(AppScreen.PrivacyDetails) },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Security, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = if (isHindi) "गोपनीयता" else "Privacy", fontSize = 12.sp)
                        }

                        Button(
                            onClick = { showResetDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = ErrorRed),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("reset_account_data_button")
                        ) {
                            Icon(Icons.Default.DeleteForever, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = if (isHindi) "डेटा रीसेट" else "Reset Data", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }

    // Edit Profile Dialog
    if (showEditDialog) {
        var editName by remember { mutableStateOf(user?.displayName ?: "") }
        var editLocation by remember { mutableStateOf(user?.stateLocation ?: "") }
        var editAvatar by remember { mutableStateOf(user?.avatarIndex ?: 0) }
        var editMinutes by remember { mutableStateOf(user?.targetDailyMinutes ?: 15) }

        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = {
                Text(
                    text = if (isHindi) "प्रोफ़ाइल संपादित करें" else "Edit Profile & Avatar",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = if (isHindi) "अवतार चुनें:" else "Select Avatar:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        (0..5).forEach { idx ->
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = if (editAvatar == idx) 2.5.dp else 0.dp,
                                        color = if (editAvatar == idx) SaffronPrimary else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .clickable { editAvatar = idx }
                            ) {
                                UserAvatarView(avatarIndex = idx, size = 42)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text(if (isHindi) "प्रदर्शन नाम" else "Display Name") },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = editLocation,
                        onValueChange = { editLocation = it },
                        label = { Text(if (isHindi) "राज्य / शहर" else "State / Region") },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateProfile(editName, editAvatar, editLocation, editMinutes)
                        showEditDialog = false
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary)
                ) {
                    Text(text = if (isHindi) "सहेजें" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text(text = if (isHindi) "रद्द करें" else "Cancel")
                }
            }
        )
    }

    // Reset Data Confirmation Dialog
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = {
                Text(
                    text = if (isHindi) "डेटा रीसेट और खाता हटाना" else "Reset All Data / Account",
                    fontWeight = FontWeight.Bold,
                    color = ErrorRed
                )
            },
            text = {
                Text(
                    text = if (isHindi)
                        "क्या आप निश्चित रूप से अपना सभी स्थानीय अध्ययन डेटा, अंक, स्ट्रीक और क्विज़ इतिहास हटाना चाहते हैं? यह प्रक्रिया वापस नहीं की जा सकती।"
                    else
                        "Are you sure you want to erase all local study progress, XP, streaks, and quiz history? This action cannot be undone."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetAllData()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRed),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("confirm_reset_data_button")
                ) {
                    Text(text = if (isHindi) "हाँ, डेटा हटाएं" else "Yes, Erase All Data")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text(text = if (isHindi) "रद्द करें" else "Cancel")
                }
            }
        )
    }
}

@Composable
fun MetricCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp
            )
        }
    }
}
