package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Difficulty
import com.example.data.model.QuizQuestion
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    categoryId: String,
    difficulty: Difficulty,
    isDaily: Boolean,
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val isHindi by viewModel.isHindi.collectAsState()
    val questions by viewModel.quizQuestions.collectAsState()
    val currentIndex by viewModel.quizIndex.collectAsState()
    val selectedOption by viewModel.selectedOption.collectAsState()
    val isSubmitted by viewModel.isAnswerSubmitted.collectAsState()
    val answers by viewModel.quizAnswers.collectAsState()

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = if (isHindi) "प्रश्न लोड हो रहे हैं..." else "Loading questions...")
        }
        return
    }

    val currentQuestion = questions.getOrElse(currentIndex) { questions.first() }
    val totalQuestions = questions.size
    val progress = (currentIndex.toFloat() / totalQuestions).coerceIn(0f, 1f)

    val questionText = if (isHindi) currentQuestion.questionHi else currentQuestion.questionEn
    val options = if (isHindi) currentQuestion.optionsHi else currentQuestion.optionsEn
    val explanation = if (isHindi) currentQuestion.explanationHi else currentQuestion.explanationEn

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (isDaily) (if (isHindi) "दैनिक चुनौती क्विज़" else "Daily Blitz Challenge")
                            else (if (isHindi) "अभ्यास क्विज़" else "Practice Quiz"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = if (isHindi) "प्रश्न ${currentIndex + 1}/$totalQuestions" else "Question ${currentIndex + 1} of $totalQuestions",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("quiz_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Exit Quiz"
                        )
                    }
                },
                actions = {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = SaffronPrimary.copy(alpha = 0.15f),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text(
                            text = difficulty.name,
                            color = SaffronPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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
            contentPadding = PaddingValues(top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Linear Progress Bar
            item {
                LinearProgressIndicator(
                    progress = { (currentIndex + 1).toFloat() / totalQuestions },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = SaffronPrimary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = StrokeCap.Round
                )
            }

            // Question Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("quiz_question_card")
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (currentQuestion.isVerified) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(IndiaGreen.copy(alpha = 0.12f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = "Verified Source",
                                        tint = IndiaGreen,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isHindi) "प्रमाणित तथ्य" else "Verified Fact",
                                        fontSize = 11.sp,
                                        color = IndiaGreen,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = "${currentIndex + 1}/$totalQuestions",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = questionText,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                lineHeight = 24.sp
                            )
                        )
                    }
                }
            }

            // 4 Multiple Choice Options
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    val optionLetters = listOf("A", "B", "C", "D")
                    options.forEachIndexed { index, optionLabel ->
                        val isSelected = selectedOption == index
                        val isCorrectOption = index == currentQuestion.correctIndex

                        val (bgColor, borderColor, textColor) = when {
                            isSubmitted && isCorrectOption -> Triple(
                                IndiaGreen.copy(alpha = 0.15f),
                                IndiaGreen,
                                IndiaGreen
                            )
                            isSubmitted && isSelected && !isCorrectOption -> Triple(
                                ErrorRed.copy(alpha = 0.15f),
                                ErrorRed,
                                ErrorRed
                            )
                            isSelected -> Triple(
                                SaffronPrimary.copy(alpha = 0.12f),
                                SaffronPrimary,
                                SaffronPrimary
                            )
                            else -> Triple(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = bgColor),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
                                .clickable(enabled = !isSubmitted) {
                                    viewModel.selectQuizOption(index)
                                }
                                .testTag("quiz_option_$index")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(14.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(borderColor),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isSubmitted && isCorrectOption) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    } else if (isSubmitted && isSelected && !isCorrectOption) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    } else {
                                        Text(
                                            text = optionLetters.getOrElse(index) { "$index" },
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = optionLabel,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 15.sp,
                                        color = textColor
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Explanation Card (Visible after submit)
            if (isSubmitted) {
                item {
                    val wasCorrect = selectedOption == currentQuestion.correctIndex
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (wasCorrect) IndiaGreen.copy(alpha = 0.1f) else ErrorRed.copy(alpha = 0.08f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quiz_explanation_card")
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (wasCorrect) Icons.Default.CheckCircle else Icons.Default.Info,
                                    contentDescription = null,
                                    tint = if (wasCorrect) IndiaGreen else ErrorRed,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (wasCorrect) (if (isHindi) "सही उत्तर! 🎉" else "Correct Answer! 🎉")
                                    else (if (isHindi) "गलत उत्तर" else "Incorrect Answer"),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = if (wasCorrect) IndiaGreen else ErrorRed
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = explanation,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    lineHeight = 22.sp
                                )
                            )

                            if (currentQuestion.sourceUrl.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.OpenInBrowser,
                                        contentDescription = "Source",
                                        tint = ChakraBlue,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (isHindi) "सत्यापित स्रोत: " else "Verified Source: ",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = currentQuestion.sourceUrl.substringAfter("https://").substringBefore("/"),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = ChakraBlue,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Bottom Action Button: Submit or Proceed
            item {
                Spacer(modifier = Modifier.height(8.dp))
                if (!isSubmitted) {
                    Button(
                        onClick = { viewModel.submitCurrentAnswer() },
                        enabled = selectedOption != null,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("submit_answer_button")
                    ) {
                        Text(
                            text = if (isHindi) "उत्तर सबमिट करें" else "Submit Answer",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                } else {
                    val isLastQuestion = currentIndex >= totalQuestions - 1
                    Button(
                        onClick = { viewModel.proceedToNextQuestion() },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SaffronPrimary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("next_question_button")
                    ) {
                        Text(
                            text = if (isLastQuestion) (if (isHindi) "परिणाम देखें 🏁" else "View Results 🏁")
                            else (if (isHindi) "अगला प्रश्न ➔" else "Next Question ➔"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}
