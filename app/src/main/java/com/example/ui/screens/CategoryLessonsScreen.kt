package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.dataset.LessonBank
import com.example.data.model.CategoriesData
import com.example.data.model.Difficulty
import com.example.data.model.Lesson
import com.example.ui.components.getCategoryIcon
import com.example.ui.navigation.AppScreen
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryLessonsScreen(
    categoryId: String,
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val isHindi by viewModel.isHindi.collectAsState()
    val lessonProgress by viewModel.lessonProgressList.collectAsState()
    val category = CategoriesData.getCategoryById(categoryId)
    val lessons = LessonBank.getLessonsForCategory(categoryId)

    var selectedDifficulty by remember { mutableStateOf(Difficulty.EASY) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHindi) category.nameHi else category.nameEn,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("category_back_button")
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
            contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Category Summary Header
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = SaffronPrimary,
                            modifier = Modifier.size(50.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = getCategoryIcon(category.iconName),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = if (isHindi) category.descriptionHi else category.descriptionEn,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // Difficulty Filter Row
            item {
                Column {
                    Text(
                        text = if (isHindi) "क्विज़ कठिनाई स्तर चुनें" else "Select Quiz Difficulty",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Difficulty.values().forEach { diff ->
                            FilterChip(
                                selected = selectedDifficulty == diff,
                                onClick = { selectedDifficulty = diff },
                                label = {
                                    Text(
                                        text = when (diff) {
                                            Difficulty.EASY -> if (isHindi) "सरल (Easy)" else "Easy"
                                            Difficulty.MEDIUM -> if (isHindi) "मध्यम (Medium)" else "Medium"
                                            Difficulty.HARD -> if (isHindi) "कठिन (Hard)" else "Hard"
                                        }
                                    )
                                },
                                modifier = Modifier.testTag("filter_chip_${diff.name.lowercase()}")
                            )
                        }
                    }
                }
            }

            // Quick Start Quiz Action
            item {
                Button(
                    onClick = {
                        viewModel.startCategoryQuiz(categoryId, selectedDifficulty)
                    },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("start_category_quiz_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "${category.nameHi} 5-प्रश्न क्विज़ शुरू करें" else "Start 5-Question Quiz",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Section Lessons Title
            item {
                Text(
                    text = if (isHindi) "पाठ एवं अध्ययन सामग्री" else "Lessons & Reading Notes",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            // Lessons List
            items(lessons) { lesson ->
                val progress = lessonProgress.find { it.lessonId == lesson.id }
                val isCompleted = progress != null && progress.completedAt > 0
                val isSaved = progress?.isSaved ?: false

                LessonCardItem(
                    lesson = lesson,
                    isHindi = isHindi,
                    isCompleted = isCompleted,
                    isSaved = isSaved,
                    onOpen = {
                        viewModel.navigateTo(AppScreen.LessonView(lesson.id))
                    },
                    onToggleSave = {
                        viewModel.toggleSaveLesson(lesson.id, categoryId)
                    }
                )
            }
        }
    }
}

@Composable
fun LessonCardItem(
    lesson: Lesson,
    isHindi: Boolean,
    isCompleted: Boolean,
    isSaved: Boolean,
    onOpen: () -> Unit,
    onToggleSave: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() }
            .testTag("lesson_item_${lesson.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isHindi) lesson.titleHi else lesson.titleEn,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (isHindi) lesson.summaryHi else lesson.summaryEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }

                IconButton(
                    onClick = onToggleSave,
                    modifier = Modifier.testTag("save_lesson_button_${lesson.id}")
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Save",
                        tint = if (isSaved) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${lesson.estimatedMinutes} " + if (isHindi) "मिनट पठन" else "min read",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (isCompleted) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = IndiaGreen,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isHindi) "पूर्ण" else "Completed",
                            color = IndiaGreen,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
