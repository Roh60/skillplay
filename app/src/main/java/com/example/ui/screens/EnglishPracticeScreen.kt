package com.example.ui.screens

import android.speech.tts.TextToSpeech
import java.util.Locale

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Spellcheck
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.dataset.EnglishPracticeBank
import com.example.data.model.EnglishExercise
import com.example.data.model.EnglishModuleType
import com.example.ui.navigation.AppScreen
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnglishPracticeHubScreen(
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val isHindi by viewModel.isHindi.collectAsState()
    val completedExercises by viewModel.englishProgressList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHindi) "अंग्रेजी अभ्यास केंद्र" else "English Practice Hub",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("english_hub_back_button")
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Notice Card: Non-AI & Speech Disclaimer
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ChakraBlue.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = ChakraBlue,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (isHindi)
                                "नोट: यह संरचित लिखित अभ्यास मॉड्यूल है (शब्दावली, व्याकरण और साक्षात्कार उत्तर)। यह माइक्रोफोन के बिना उच्चारण मूल्यांकन का दावा नहीं करता।"
                            else
                                "Note: Structured text-based learning modules for grammar, vocabulary & interview templates. Does not evaluate speech pronunciation without microphone.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                lineHeight = 18.sp,
                                color = ChakraBlue
                            )
                        )
                    }
                }
            }

            // Overview Header
            item {
                Text(
                    text = if (isHindi) "13 व्यावहारिक शिक्षण ट्रैक" else "13 Practical Learning Tracks",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            // Tracks
            items(EnglishModuleType.values()) { module ->
                val trackExercises = EnglishPracticeBank.getExercisesByType(module)
                val completedCount = trackExercises.count { ex ->
                    completedExercises.any { it.exerciseId == ex.id }
                }

                EnglishTrackCard(
                    module = module,
                    isHindi = isHindi,
                    exerciseCount = trackExercises.size,
                    completedCount = completedCount,
                    onOpen = {
                        viewModel.navigateTo(AppScreen.EnglishModuleView(module))
                    }
                )
            }
        }
    }
}

@Composable
fun EnglishTrackCard(
    module: EnglishModuleType,
    isHindi: Boolean,
    exerciseCount: Int,
    completedCount: Int,
    onOpen: () -> Unit
) {
    val icon = when (module) {
        EnglishModuleType.VOCABULARY -> Icons.Default.Spellcheck
        EnglishModuleType.GRAMMAR -> Icons.Default.MenuBook
        EnglishModuleType.SENTENCE_CORRECTION -> Icons.Default.CheckCircle
        EnglishModuleType.CONVERSATIONS -> Icons.Default.RecordVoiceOver
        EnglishModuleType.INTERVIEW -> Icons.Default.WorkOutline
        EnglishModuleType.WORKPLACE -> Icons.Default.WorkOutline
        EnglishModuleType.READING -> Icons.Default.MenuBook
        EnglishModuleType.THINK_IN_ENGLISH -> Icons.Default.RecordVoiceOver
        EnglishModuleType.SENTENCE_BUILDER -> Icons.Default.MenuBook
        EnglishModuleType.SENTENCE_PRACTICE -> Icons.Default.CheckCircle
        EnglishModuleType.STRUCTURE -> Icons.Default.MenuBook
        EnglishModuleType.PARAGRAPHS -> Icons.Default.MenuBook
        EnglishModuleType.IELTS -> Icons.Default.WorkOutline
    }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() }
            .testTag("english_track_${module.name.lowercase()}")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = SaffronPrimary.copy(alpha = 0.12f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = SaffronPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isHindi) module.titleHi else module.titleEn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = module.descriptionEn,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (completedCount > 0) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = IndiaGreen.copy(alpha = 0.12f),
                    modifier = Modifier.padding(end = 6.dp)
                ) {
                    Text(
                        text = "✓",
                        color = IndiaGreen,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Open",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnglishModuleDetailScreen(
    moduleType: EnglishModuleType,
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val isHindi by viewModel.isHindi.collectAsState()
    val completedExercises by viewModel.englishProgressList.collectAsState()
    val context = LocalContext.current
    var speechEngine by remember { mutableStateOf<TextToSpeech?>(null) }
    DisposableEffect(context) {
        val engineRef = arrayOfNulls<TextToSpeech>(1)
        val engine = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) engineRef[0]?.language = Locale.US
        }
        engineRef[0] = engine
        speechEngine = engine
        onDispose {
            engine.stop()
            engine.shutdown()
            speechEngine = null
        }
    }
    val prefs = remember { context.getSharedPreferences("skillplay_english_prefs", android.content.Context.MODE_PRIVATE) }
    var favoriteIds by remember { mutableStateOf(prefs.getStringSet("favorite_words", emptySet())?.toSet() ?: emptySet()) }
    var showFavoritesOnly by remember { mutableStateOf(false) }
    val allModuleExercises = EnglishPracticeBank.getExercisesByType(moduleType)
    val exercises = if (moduleType == EnglishModuleType.VOCABULARY && showFavoritesOnly) {
        allModuleExercises.filter { it.id in favoriteIds }
    } else allModuleExercises

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHindi) moduleType.titleHi else moduleType.titleEn,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("module_detail_back_button")
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (moduleType == EnglishModuleType.VOCABULARY) {
                item {
                    OutlinedButton(onClick = { showFavoritesOnly = !showFavoritesOnly }, modifier = Modifier.fillMaxWidth()) {
                        Text(if (showFavoritesOnly) {
                            if (isHindi) "सभी शब्द दिखाएँ" else "Show all words"
                        } else {
                            if (isHindi) "★ पसंदीदा शब्द" else "★ Favorite words"
                        })
                    }
                }
            }
            if (exercises.isEmpty()) {
                item { Text(if (isHindi) "अभी कोई पसंदीदा शब्द सेव नहीं है। पहले किसी शब्द को पसंदीदा बनाएं।" else "No favorite words saved yet. Mark a word as favorite first.") }
            }
            items(exercises) { exercise ->
                val isCompleted = completedExercises.any { it.exerciseId == exercise.id }
                EnglishExerciseView(
                    exercise = exercise,
                    isHindi = isHindi,
                    isCompleted = isCompleted,
                    isFavorite = exercise.id in favoriteIds,
                    onToggleFavorite = {
                        val updated = favoriteIds.toMutableSet().apply {
                            if (contains(exercise.id)) remove(exercise.id) else add(exercise.id)
                        }.toSet()
                        favoriteIds = updated
                        prefs.edit().putStringSet("favorite_words", updated).apply()
                    },
                    onSpeak = { phrase ->
                        speechEngine?.speak(phrase, TextToSpeech.QUEUE_FLUSH, null, "skillplay_english_phrase")
                    },
                    onSaveProgress = { notes ->
                        viewModel.recordEnglishCompletion(exercise, notes)
                    }
                )
            }
        }
    }
}

@Composable
fun EnglishExerciseView(
    exercise: EnglishExercise,
    isHindi: Boolean,
    isCompleted: Boolean,
    isFavorite: Boolean = false,
    onToggleFavorite: () -> Unit = {},
    onSpeak: (String) -> Unit = {},
    onSaveProgress: (String) -> Unit
) {
    var userNotes by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var isRevealed by remember { mutableStateOf(isCompleted) }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("exercise_card_${exercise.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isHindi) exercise.titleHi else exercise.titleEn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = SaffronPrimary
                )

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
                            text = if (isHindi) "पूर्ण" else "Done",
                            color = IndiaGreen,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (isHindi) exercise.contextHi else exercise.contextEn,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (exercise.moduleType == EnglishModuleType.VOCABULARY) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(onClick = onToggleFavorite, modifier = Modifier.fillMaxWidth()) {
                    Text(if (isFavorite) {
                        if (isHindi) "★ पसंदीदा में सेव है" else "★ Saved to favorites"
                    } else {
                        if (isHindi) "☆ पसंदीदा में सेव करें" else "☆ Save word to favorites"
                    })
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Exercise Prompt
            Text(
                text = if (isHindi) exercise.promptHi else exercise.promptEn,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            OutlinedButton(
                onClick = { onSpeak(if (isRevealed) exercise.sampleAnswerEn else exercise.promptEn) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isHindi) "🔊 वाक्य सुनें और बोलकर अभ्यास करें" else "🔊 Listen & practise aloud")
            }

            // Multiple Choice Options if applicable
            if (exercise.options.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    exercise.options.forEachIndexed { idx, opt ->
                        val isChosen = selectedOption == idx
                        val isCorrect = idx == exercise.correctOptionIndex

                        val optColor = when {
                            isRevealed && isCorrect -> IndiaGreen
                            isRevealed && isChosen && !isCorrect -> MaterialTheme.colorScheme.error
                            isChosen -> SaffronPrimary
                            else -> MaterialTheme.colorScheme.outline
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = optColor.copy(alpha = 0.08f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (!isRevealed) selectedOption = idx
                                }
                                .padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = opt,
                                fontSize = 13.sp,
                                fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                                color = if (isRevealed && isCorrect) IndiaGreen else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sample Answer & Key Tips
            if (isRevealed) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(GoldAccent.copy(alpha = 0.12f))
                        .padding(12.dp)
                ) {
                    Text(
                        text = if (isHindi) "अनुशंसित आदर्श उत्तर / स्पष्टीकरण:" else "Sample Model Answer & Explanation:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isHindi) exercise.sampleAnswerHi else exercise.sampleAnswerEn,
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    val tips = if (isHindi) exercise.keyTipsHi else exercise.keyTipsEn
                    tips.forEach { tip ->
                        Text(
                            text = "• $tip",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }
                }
            } else {
                Button(
                    onClick = { isRevealed = true },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ChakraBlue),
                    modifier = Modifier.testTag("reveal_solution_button_${exercise.id}")
                ) {
                    Text(text = if (isHindi) "आदर्श उत्तर एवं टिप्स देखें" else "Reveal Model Answer & Tips", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // User notes input
            OutlinedTextField(
                value = userNotes,
                onValueChange = { userNotes = it },
                placeholder = {
                    Text(
                        text = if (isHindi) "अपनी व्यक्तिगत टिप्पणी या अभ्यास वाक्य यहाँ लिखें..." else "Write your own sentence or notes here...",
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("exercise_notes_input_${exercise.id}")
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (!isCompleted) {
                Button(
                    onClick = {
                        onSaveProgress(userNotes)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = IndiaGreen),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("complete_exercise_button_${exercise.id}")
                ) {
                    Text(
                        text = if (isHindi) "अभ्यास पूरा करें (+25 XP)" else "Mark Practice Completed (+25 XP)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}
