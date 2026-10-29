package com.example.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CategoriesData
import com.example.data.model.LearningCategory
import com.example.ui.components.PersonalizedPathSection
import com.example.ui.components.getCategoryIcon
import com.example.ui.navigation.AppScreen
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@Composable
fun LearnScreen(
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()
    val user by viewModel.userProfile.collectAsState()
    val personalizedRecs by viewModel.personalizedRecommendations.collectAsState()
    val lessonProgress by viewModel.lessonProgressList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredCategories = remember(searchQuery, isHindi) {
        val q = searchQuery.trim().lowercase()
        if (q.isEmpty()) {
            CategoriesData.allCategories
        } else {
            CategoriesData.allCategories.filter {
                it.nameEn.lowercase().contains(q) ||
                it.nameHi.contains(q) ||
                it.descriptionEn.lowercase().contains(q) ||
                it.descriptionHi.contains(q)
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Search Input
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        text = if (isHindi) "श्रेणी या विषय खोजें..." else "Search 14 categories...",
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("category_search_input")
            )
        }

        // English Practice Hub Spotlight Banner
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ChakraBlue.copy(alpha = 0.12f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.navigateTo(AppScreen.EnglishHub) }
                    .testTag("english_hub_spotlight_banner")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = ChakraBlue,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.RecordVoiceOver,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isHindi) "अंग्रेजी कौशल एवं साक्षात्कार अभ्यास" else "English Practice & Interview Hub",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = ChakraBlue
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isHindi) "शब्दावली, व्याकरण, कार्यस्थल संचार और संवाद" else "Vocab, grammar, STAR interview answers & emails",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Open",
                        tint = ChakraBlue
                    )
                }
            }
        }

        // Personalized Learning Path
        item {
            PersonalizedPathSection(
                recommendations = personalizedRecs,
                currentGoalTrackId = user?.primaryGoalTrack ?: "competitive_exams",
                currentStatedInterestsCsv = user?.statedInterests ?: "gk,polity,interview,digital_safety",
                isHindi = isHindi,
                onOpenLesson = { lessonId ->
                    viewModel.navigateTo(AppScreen.LessonView(lessonId))
                },
                onStartQuiz = { categoryId ->
                    viewModel.startCategoryQuiz(categoryId)
                },
                onUpdateInterests = { goalTrack, categories ->
                    viewModel.updateInterestsAndGoal(goalTrack, categories)
                }
            )
        }

        // Section Title
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isHindi) "सभी 14 विषय श्रेणियां" else "All 14 Learning Categories",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${filteredCategories.size} " + if (isHindi) "उपलब्ध" else "Found",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // List of Categories
        items(filteredCategories) { category ->
            val completedCount = lessonProgress.count { it.categoryId == category.id && it.completedAt > 0 }
            val progressPercent = (completedCount.toFloat() / category.totalLessons).coerceIn(0f, 1f)

            CategoryCardItem(
                category = category,
                isHindi = isHindi,
                completedCount = completedCount,
                progressPercent = progressPercent,
                onOpen = {
                    viewModel.navigateTo(AppScreen.CategoryLessons(category.id))
                },
                onQuickQuiz = {
                    viewModel.startCategoryQuiz(category.id)
                }
            )
        }
    }
}

@Composable
fun CategoryCardItem(
    category: LearningCategory,
    isHindi: Boolean,
    completedCount: Int,
    progressPercent: Float,
    onOpen: () -> Unit,
    onQuickQuiz: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() }
            .testTag("category_card_${category.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SaffronPrimary.copy(alpha = 0.12f),
                    modifier = Modifier.size(46.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = getCategoryIcon(category.iconName),
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isHindi) category.nameHi else category.nameEn,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = if (isHindi) category.descriptionHi else category.descriptionEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Progress Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isHindi) "$completedCount/${category.totalLessons} पूर्ण" else "$completedCount/${category.totalLessons} Completed",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${(progressPercent * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = if (progressPercent > 0.5f) IndiaGreen else SaffronPrimary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { progressPercent },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = if (progressPercent > 0.5f) IndiaGreen else SaffronPrimary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                strokeCap = StrokeCap.Round
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Actions
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .clickable { onQuickQuiz() }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("quiz_action_${category.id}")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = SaffronPrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isHindi) "क्विज़ खेलें" else "Take Quiz",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SaffronPrimary
                        )
                    }
                }
            }
        }
    }
}
