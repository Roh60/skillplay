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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizUserAnswer
import com.example.data.model.CategoriesData
import com.example.ui.navigation.AppScreen
import com.example.ui.navigation.MainTab
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun QuizResultScreen(
    score: Int,
    total: Int,
    categoryId: String,
    isDaily: Boolean,
    wrongAnswers: List<QuizUserAnswer>,
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.selectTab(MainTab.Home)
    }

    val isHindi by viewModel.isHindi.collectAsState()
    val context = LocalContext.current
    val ist = TimeZone.getTimeZone("Asia/Kolkata")
    val todayKey = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { timeZone = ist }.format(Date())
    val todayNumber = ((SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { timeZone = ist }.parse(todayKey)?.time ?: 0L) / 86_400_000L).toInt().coerceAtLeast(0)
    val todayCategoryId = CategoriesData.allCategories.getOrNull(todayNumber % CategoriesData.allCategories.size)?.id
    LaunchedEffect(isDaily, categoryId, todayKey) {
        val prefs = context.getSharedPreferences("skillplay_study_boost", 0)
        if (isDaily) prefs.edit().putBoolean("daily_quiz_done_$todayKey", true).apply()
        if (!isDaily && categoryId == todayCategoryId) prefs.edit().putBoolean("daily_topic_done_$todayKey", true).apply()
    }
    val accuracyPercent = if (total > 0) (score * 100 / total) else 0
    val xpEarned = if (isDaily) (score * 15 + 25) else (score * 10)

    val verdict = when {
        accuracyPercent >= 80 -> if (isHindi) "उत्कृष्ट प्रदर्शन! 🌟" else "Outstanding Performance! 🌟"
        accuracyPercent >= 60 -> if (isHindi) "शानदार प्रयास! 👏" else "Great Job! 👏"
        else -> if (isHindi) "अभ्यास जारी रखें, सुधार होगा! 💪" else "Keep Practicing, You'll Get There! 💪"
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 32.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            // Trophy Circle
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(
                        if (accuracyPercent >= 60) GoldAccent.copy(alpha = 0.2f) else ChakraBlue.copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = if (accuracyPercent >= 60) GoldAccent else ChakraBlue,
                    modifier = Modifier.size(56.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isDaily) (if (isHindi) "दैनिक चुनौती परिणाम" else "Daily Challenge Results")
                else (if (isHindi) "क्विज़ परिणाम" else "Quiz Completed"),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = verdict,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp
                )
            )
        }

        // Score Card
        item {
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quiz_result_score_card")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$score / $total",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SaffronPrimary,
                            fontSize = 44.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (isHindi) "सटीकता: $accuracyPercent%" else "Accuracy: $accuracyPercent%",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // XP Earned Pill
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = GoldAccent.copy(alpha = 0.15f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = null,
                                tint = GoldAccent,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "+$xpEarned XP " + if (isHindi) "अर्जित किए" else "Earned",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // Action Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                if (wrongAnswers.isNotEmpty()) {
                    Button(
                        onClick = {
                            viewModel.navigateTo(AppScreen.ReviewWrong(wrongAnswers))
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ChakraBlue),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("review_wrong_answers_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isHindi) "गलत उत्तरों की समीक्षा करें (${wrongAnswers.size})" else "Review Wrong Answers (${wrongAnswers.size})",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                OutlinedButton(
                    onClick = { viewModel.retryQuiz() },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("retry_quiz_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "पुनः प्रयास करें" else "Retry Quiz",
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = { viewModel.selectTab(MainTab.Home) },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("quiz_finish_home_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "होम पर लौटें" else "Back to Home",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
