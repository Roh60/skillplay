package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CategoriesData
import com.example.data.model.GoalTrack
import com.example.data.model.PathRecommendation
import com.example.data.model.RecommendationType
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary

@Composable
fun PersonalizedPathSection(
    recommendations: List<PathRecommendation>,
    currentGoalTrackId: String,
    currentStatedInterestsCsv: String,
    isHindi: Boolean,
    onOpenLesson: (String) -> Unit,
    onStartQuiz: (String) -> Unit,
    onUpdateInterests: (goalTrack: String, categories: Set<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var showGoalDialog by remember { mutableStateOf(false) }
    val currentGoal = GoalTrack.fromId(currentGoalTrackId)

    Column(modifier = modifier.fillMaxWidth()) {
        // Section Header Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = SaffronPrimary.copy(alpha = 0.15f),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = if (isHindi) "आपके लिए व्यक्तिगत शिक्षण पथ" else "Personalized Learning Path",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = if (isHindi)
                            "आपके प्रदर्शन और रुचि अनुसार अनुशंसित"
                        else
                            "Tailored to your quiz accuracy & stated focus",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { showGoalDialog = true }
                    .testTag("customize_learning_path_button")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = SaffronPrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isHindi) "लक्ष्य बदलें" else "Tune",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SaffronPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Current Goal Badge Chip
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.TrackChanges,
                    contentDescription = null,
                    tint = ChakraBlue,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isHindi) "सक्रिय पथ: " else "Active Goal: ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = if (isHindi) currentGoal.titleHi else currentGoal.titleEn,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = ChakraBlue
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Recommendations List / Carousel
        if (recommendations.isEmpty()) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isHindi) "पथ का विश्लेषण हो रहा है..." else "Analyzing your learning trajectory...",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.testTag("personalized_path_carousel")
            ) {
                items(recommendations) { rec ->
                    RecommendationPathCard(
                        recommendation = rec,
                        isHindi = isHindi,
                        onOpenLesson = {
                            rec.recommendedLesson?.let { onOpenLesson(it.id) }
                        },
                        onStartQuiz = {
                            onStartQuiz(rec.category.id)
                        }
                    )
                }
            }
        }
    }

    // Modal Dialog to Customize Goals & Stated Interests
    if (showGoalDialog) {
        CustomizeInterestsDialog(
            currentGoalTrackId = currentGoalTrackId,
            currentStatedInterestsCsv = currentStatedInterestsCsv,
            isHindi = isHindi,
            onDismiss = { showGoalDialog = false },
            onSave = { selectedGoal, selectedCategories ->
                onUpdateInterests(selectedGoal, selectedCategories)
                showGoalDialog = false
            }
        )
    }
}

@Composable
fun RecommendationPathCard(
    recommendation: PathRecommendation,
    isHindi: Boolean,
    onOpenLesson: () -> Unit,
    onStartQuiz: () -> Unit
) {
    val (badgeBg, badgeColor, badgeIcon) = when (recommendation.type) {
        RecommendationType.ACCURACY_BOOSTER -> Triple(
            ErrorRed.copy(alpha = 0.12f),
            ErrorRed,
            Icons.Default.TrendingUp
        )
        RecommendationType.STATED_INTEREST -> Triple(
            SaffronPrimary.copy(alpha = 0.12f),
            SaffronPrimary,
            Icons.Default.AutoAwesome
        )
        RecommendationType.NEXT_SEQUENTIAL_LESSON -> Triple(
            ChakraBlue.copy(alpha = 0.12f),
            ChakraBlue,
            Icons.Default.Lightbulb
        )
        RecommendationType.COMPLEMENTARY_SKILL -> Triple(
            Color(0xFF8B5CF6).copy(alpha = 0.12f),
            Color(0xFF8B5CF6),
            Icons.Default.Psychology
        )
    }

    val badgeText = if (isHindi) recommendation.type.tagHi else recommendation.type.tagEn

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .width(280.dp)
            .border(
                width = if (recommendation.type == RecommendationType.ACCURACY_BOOSTER) 1.5.dp else 1.dp,
                color = if (recommendation.type == RecommendationType.ACCURACY_BOOSTER) ErrorRed.copy(alpha = 0.4f)
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
                shape = RoundedCornerShape(18.dp)
            )
            .testTag("rec_card_${recommendation.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Category & Badge Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SaffronPrimary.copy(alpha = 0.15f),
                        modifier = Modifier.size(28.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = getCategoryIcon(recommendation.category.iconName),
                                contentDescription = null,
                                tint = SaffronPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isHindi) recommendation.category.nameHi else recommendation.category.nameEn,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        maxLines = 1
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = badgeBg
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Icon(
                            imageVector = badgeIcon,
                            contentDescription = null,
                            tint = badgeColor,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = badgeText,
                            color = badgeColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Headline
            Text(
                text = if (isHindi) recommendation.headlineHi else recommendation.headlineEn,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Rationale explanation
            Text(
                text = if (isHindi) recommendation.rationaleHi else recommendation.rationaleEn,
                style = MaterialTheme.typography.bodySmall.copy(
                    lineHeight = 17.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Progress indicator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isHindi) "प्रगति" else "Track Progress",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${(recommendation.progressPercent * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { recommendation.progressPercent },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = if (recommendation.type == RecommendationType.ACCURACY_BOOSTER) ErrorRed else SaffronPrimary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                strokeCap = StrokeCap.Round
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Action Button
            if (recommendation.isQuizRecommendation) {
                Button(
                    onClick = onStartQuiz,
                    colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("rec_action_quiz_${recommendation.id}")
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isHindi) "क्विज़ खेलें" else "Take Quiz",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            } else {
                Button(
                    onClick = onOpenLesson,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (recommendation.type == RecommendationType.ACCURACY_BOOSTER) SaffronPrimary else ChakraBlue
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("rec_action_lesson_${recommendation.id}")
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isHindi) "पाठ शुरू करें" else "Start Lesson",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomizeInterestsDialog(
    currentGoalTrackId: String,
    currentStatedInterestsCsv: String,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onSave: (selectedGoal: String, selectedCategories: Set<String>) -> Unit
) {
    var selectedGoal by remember { mutableStateOf(currentGoalTrackId) }
    var selectedCategories by remember {
        mutableStateOf(
            currentStatedInterestsCsv.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toSet()
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (isHindi) "अपना व्यक्तिगत शिक्षण पथ चुनें" else "Personalize Your Learning Path",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = if (isHindi) "1. अपना प्राथमिक लक्ष्य ट्रैक चुनें:" else "1. Choose Your Primary Goal Track:",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )

                GoalTrack.values().forEach { track ->
                    val isChosen = selectedGoal == track.id
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isChosen) SaffronPrimary.copy(alpha = 0.12f)
                            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedGoal = track.id
                                // Preselect default categories for this track
                                selectedCategories = (selectedCategories + track.defaultCategories)
                            }
                            .border(
                                width = if (isChosen) 1.5.dp else 0.dp,
                                color = if (isChosen) SaffronPrimary else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(if (isChosen) SaffronPrimary else Color.Gray.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isChosen) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = if (isHindi) track.titleHi else track.titleEn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = if (isHindi) track.descriptionHi else track.descriptionEn,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (isHindi) "2. अपनी रुचि के विषय चुनें (कम से कम 2):" else "2. Select Specific Focus Topics (At least 2):",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CategoriesData.allCategories.forEach { cat ->
                        val isSelected = cat.id in selectedCategories
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                selectedCategories = if (isSelected) {
                                    if (selectedCategories.size > 2) selectedCategories - cat.id else selectedCategories
                                } else {
                                    selectedCategories + cat.id
                                }
                            },
                            label = {
                                Text(
                                    text = if (isHindi) cat.nameHi else cat.nameEn,
                                    fontSize = 11.sp
                                )
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(selectedGoal, selectedCategories) },
                enabled = selectedCategories.size >= 2,
                colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.testTag("save_interests_button")
            ) {
                Text(text = if (isHindi) "पथ सहेजें और लागू करें" else "Save & Apply Path", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = if (isHindi) "रद्द करें" else "Cancel")
            }
        }
    )
}
