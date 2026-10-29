package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.dataset.QuestionBank
import com.example.data.dataset.LessonBank
import com.example.ui.navigation.AppScreen
import com.example.data.model.CategoriesData
import com.example.ui.viewmodel.SkillPlayViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun StudyBoostScreen(viewModel: SkillPlayViewModel, onBack: () -> Unit) {
    val context = LocalContext.current
    val isHindi by viewModel.isHindi.collectAsState()
    val user by viewModel.userProfile.collectAsState()
    val lessons by viewModel.lessonProgressList.collectAsState()
    val challenges by viewModel.challengeRecords.collectAsState()
    val prefs = remember(context) { context.getSharedPreferences("skillplay_study_boost", 0) }
    val categories = CategoriesData.allCategories
    var refresh by remember { mutableIntStateOf(0) }
    var weakIds by remember { mutableStateOf(prefs.getStringSet("weak_topics", emptySet())?.toSet() ?: emptySet()) }
    var savedNotes by remember { mutableStateOf(prefs.getString("saved_notes", "") ?: "") }
    var noteInput by remember { mutableStateOf("") }
    var joinedGroups by remember { mutableStateOf(prefs.getStringSet("joined_groups", emptySet())?.toSet() ?: emptySet()) }
    var savedQuestionIds by remember { mutableStateOf(prefs.getStringSet("saved_question_ids", emptySet())?.toSet() ?: emptySet()) }
    var bookmarkSearch by remember { mutableStateOf("") }
    var showAllQuestions by remember { mutableStateOf(false) }
    var timerRunning by remember { mutableStateOf(false) }
    var secondsLeft by remember { mutableIntStateOf(300) }
    val istTimeZone = remember { TimeZone.getTimeZone("Asia/Kolkata") }
    var dateKey by remember { mutableStateOf(SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { timeZone = istTimeZone }.format(Date())) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(60_000)
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { timeZone = istTimeZone }.format(Date())
            if (currentDate != dateKey) dateKey = currentDate
        }
    }
    val weekKey = remember(dateKey) { SimpleDateFormat("YYYY-ww", Locale.US).apply { timeZone = istTimeZone }.format(SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { timeZone = istTimeZone }.parse(dateKey) ?: Date()) }
    val dayNumber = remember(dateKey) { ((SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { timeZone = istTimeZone }.parse(dateKey)?.time ?: 0L) / 86_400_000L).toInt().coerceAtLeast(0) }
    val dailyLessonId = remember(dateKey, lessons) {
        val key = "daily_lesson_id_$dateKey"
        prefs.getString(key, null) ?: run {
            val offset = dayNumber % LessonBank.allLessons.size
            val rotated = LessonBank.allLessons.drop(offset) + LessonBank.allLessons.take(offset)
            val chosen = rotated.firstOrNull { candidate -> lessons.none { it.lessonId == candidate.id && it.scorePercent >= 100 } } ?: rotated.first()
            prefs.edit().putString(key, chosen.id).apply()
            chosen.id
        }
    }
    val dailyLesson = LessonBank.getLessonById(dailyLessonId)
    val dailyCategory = categories.getOrNull(dayNumber % categories.size) ?: categories.first()
    val dailyQuizDone = prefs.getBoolean("daily_quiz_done_$dateKey", false)
    val dailyTopicDone = prefs.getBoolean("daily_topic_done_$dateKey", false)
    val dailyLessonDone = dailyLesson?.let { lesson -> lessons.any { it.lessonId == lesson.id && it.scorePercent >= 100 } } ?: false
    val todayDone = prefs.getBoolean("five_min_done_$dateKey", false)
    val wrongQuestionIds = remember(refresh) { prefs.getStringSet("wrong_question_ids", emptySet())?.toSet() ?: emptySet() }
    val autoWeakCategories = remember(refresh, wrongQuestionIds) {
        QuestionBank.allQuestions.filter { wrongQuestionIds.contains(it.id) }.groupingBy { it.categoryId }.eachCount()
            .filterValues { it >= 1 }.keys
    }
    val combinedWeakIds = weakIds + autoWeakCategories
    val savedQuestions = QuestionBank.allQuestions.filter { savedQuestionIds.contains(it.id) }
    val matchingQuestions = QuestionBank.allQuestions.filter {
        bookmarkSearch.isBlank() || it.questionEn.contains(bookmarkSearch, true) || it.questionHi.contains(bookmarkSearch, true)
    }

    LaunchedEffect(timerRunning) {
        while (timerRunning && secondsLeft > 0) {
            delay(1000)
            secondsLeft -= 1
            if (secondsLeft <= 0) timerRunning = false
        }
    }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier.weight(1f)) {
                Text("Study Boost", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(if (isHindi) "कमज़ोर विषय, सेव किए सवाल और रोज़ाना अभ्यास" else "Weak topics, saved questions and daily practice", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            OutlinedButton(onClick = onBack) { Text(if (isHindi) "वापस" else "Back") }
        }

        SectionCard("आज का Daily Learning Plan", "हर दिन नया quiz, topic और lesson। Daily tasks भारत के समय (IST) के अनुसार बदलते हैं; आपकी पुरानी progress, XP, notes और bookmarks सुरक्षित रहते हैं।") {
            val completedCount = listOf(dailyQuizDone, dailyTopicDone, dailyLessonDone).count { it }
            Text("आज की प्रगति: $completedCount/3", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            androidx.compose.material3.LinearProgressIndicator(progress = { completedCount / 3f }, modifier = Modifier.fillMaxWidth())
            Text(if (isHindi) "आज का Quiz: 5 दैनिक प्रश्न" else "Today's quiz: 5 daily rotating questions")
            Button(onClick = { viewModel.startDailyChallenge() }, modifier = Modifier.fillMaxWidth()) {
                Text(if (dailyQuizDone) "आज का Quiz फिर से करें" else "आज का Quiz शुरू करें")
            }
            Text(if (dailyQuizDone) "✓ आज का quiz पूरा" else "Quiz पूरा करने पर अपने-आप पूरा mark होगा", style = MaterialTheme.typography.bodySmall)

            dailyLesson?.let { lesson ->
                Spacer(Modifier.height(4.dp))
                Text("आज का नया Topic: ${if (isHindi) lesson.titleHi else lesson.titleEn}", fontWeight = FontWeight.SemiBold)
                Text(if (isHindi) lesson.summaryHi else lesson.summaryEn)
                Button(onClick = { viewModel.navigateTo(AppScreen.LessonView(lesson.id)) }, modifier = Modifier.fillMaxWidth()) {
                    Text(if (dailyLessonDone) "Lesson दोबारा खोलें" else "आज का Lesson पढ़ें · ${lesson.estimatedMinutes} मिनट")
                }
                Text(if (dailyLessonDone) "✓ यह lesson पहले पूरा किया जा चुका है" else "Lesson पूरा करके अपनी पढ़ाई आगे बढ़ाएँ", style = MaterialTheme.typography.bodySmall)
            }

            Text("आज का अभ्यास विषय: ${if (isHindi) dailyCategory.nameHi else dailyCategory.nameEn}", fontWeight = FontWeight.SemiBold)
            Text(if (isHindi) dailyCategory.descriptionHi else dailyCategory.descriptionEn)
            Button(onClick = { viewModel.startCategoryQuiz(dailyCategory.id) }, modifier = Modifier.fillMaxWidth()) {
                Text("आज के Topic पर Practice Quiz")
            }
            Text(if (dailyTopicDone) "✓ आज का topic quiz पूरा" else "इस विषय का quiz पूरा करने पर अपने-आप mark होगा", style = MaterialTheme.typography.bodySmall)
            Text("नया daily plan अगले दिन अपने-आप आएगा। केवल daily status reset होगा; पुरानी XP, notes और bookmarks नहीं मिटेंगे।", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        SectionCard("1. Weekly Quiz Competition", if (isHindi) "इस सप्ताह quiz करके अपना local score बढ़ाएँ। यह इस फोन का अभ्यास रिकॉर्ड है, online प्रतियोगिता या दूसरे users की live ranking नहीं।" else "Take quizzes to build your weekly local score. This is a record on this phone, not an online contest or live ranking against other users.") {
            Text("${if (isHindi) "इस सप्ताह के अंक" else "Your points this week"}: ${prefs.getInt("weekly_points_$weekKey", 0)}", style = MaterialTheme.typography.titleMedium)
            Text("${if (isHindi) "कुल quizzes" else "Quizzes taken"}: ${prefs.getInt("weekly_quizzes_$weekKey", 0)}")
            categories.take(5).forEach { c -> OutlinedButton(onClick = { viewModel.startCategoryQuiz(c.id) }, modifier = Modifier.fillMaxWidth()) { Text("${if (isHindi) c.nameHi else c.nameEn} · ${if (isHindi) "Quiz शुरू करें" else "Start quiz"}") } }
        }

        SectionCard("2. Preparation Comparison", if (isHindi) "अपनी पिछली और मौजूदा तैयारी की प्रगति देखें। दूसरे users के आंकड़े Firebase/backend जुड़ने के बाद ही तुलना किए जा सकेंगे।" else "Review your own progress. Comparing with other users requires Firebase/backend later.") {
            Text("${if (isHindi) "कुल XP" else "Total XP"}: ${user?.totalXp ?: 0}")
            Text("${if (isHindi) "पूरे lessons" else "Completed lessons"}: ${lessons.count { it.scorePercent >= 100 }}")
            Text("${if (isHindi) "Challenge records" else "Challenge records"}: ${challenges.size}")
            Text("${if (isHindi) "इस सप्ताह के सही उत्तर" else "Correct answers this week"}: ${prefs.getInt("weekly_points_$weekKey", 0)}")
        }

        SectionCard("3. Weak Topics Tracker", if (isHindi) "गलत उत्तरों से विषय अपने-आप पहचाने जाते हैं। आप किसी विषय को खुद भी कमज़ोर चिन्हित कर सकते हैं।" else "Topics with wrong answers are tracked automatically. You can also mark a topic manually.") {
            if (autoWeakCategories.isEmpty()) Text(if (isHindi) "अभी गलत उत्तरों का रिकॉर्ड नहीं है—quiz पूरा करें, फिर यह सूची अपडेट होगी।" else "No wrong-answer history yet. Complete a quiz to populate this list.")
            categories.forEach { c ->
                val weak = combinedWeakIds.contains(c.id)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(Modifier.weight(1f)) {
                        Text(if (isHindi) c.nameHi else c.nameEn)
                        if (autoWeakCategories.contains(c.id)) Text(if (isHindi) "गलत उत्तरों से ट्रैक किया गया" else "Tracked from wrong answers", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                    }
                    OutlinedButton(onClick = {
                        weakIds = if (weakIds.contains(c.id)) weakIds - c.id else weakIds + c.id
                        prefs.edit().putStringSet("weak_topics", weakIds).apply()
                        refresh++
                    }) { Text(if (weakIds.contains(c.id)) (if (isHindi) "हटाएँ" else "Unmark") else (if (isHindi) "चुनें" else "Mark weak")) }
                }
            }
            categories.filter { combinedWeakIds.contains(it.id) }.forEach { c ->
                Button(onClick = { viewModel.startCategoryQuiz(c.id) }, modifier = Modifier.fillMaxWidth()) { Text("${if (isHindi) c.nameHi else c.nameEn} — ${if (isHindi) "दोबारा अभ्यास" else "Practise again"}") }
            }
            if (wrongQuestionIds.isNotEmpty()) OutlinedButton(onClick = {
                prefs.edit().remove("wrong_question_ids").apply()
                QuestionBank.allQuestions.map { it.categoryId }.distinct().forEach { prefs.edit().remove("wrong_count_$it") }
                refresh++
            }) { Text(if (isHindi) "गलत उत्तरों का इतिहास साफ़ करें" else "Clear wrong-answer history") }
        }

        SectionCard("4. Bookmarks & Notes", if (isHindi) "सवाल bookmark करें और अपने notes फोन पर सेव रखें।" else "Bookmark questions and keep your revision notes on this phone.") {
            OutlinedTextField(value = bookmarkSearch, onValueChange = { bookmarkSearch = it }, label = { Text(if (isHindi) "सवाल खोजें / Search questions" else "Search questions") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedButton(onClick = { showAllQuestions = !showAllQuestions }, modifier = Modifier.fillMaxWidth()) { Text(if (showAllQuestions) (if (isHindi) "सवाल सूची छिपाएँ" else "Hide question list") else (if (isHindi) "सवाल bookmark करें" else "Browse and bookmark questions")) }
            if (showAllQuestions) matchingQuestions.forEach { q ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(if (isHindi) q.questionHi else q.questionEn, fontWeight = FontWeight.SemiBold)
                        Text(if (isHindi) q.optionsHi.getOrNull(q.correctIndex).orEmpty() else q.optionsEn.getOrNull(q.correctIndex).orEmpty(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        OutlinedButton(onClick = {
                            savedQuestionIds = if (savedQuestionIds.contains(q.id)) savedQuestionIds - q.id else savedQuestionIds + q.id
                            prefs.edit().putStringSet("saved_question_ids", savedQuestionIds).apply()
                        }) { Text(if (savedQuestionIds.contains(q.id)) (if (isHindi) "Saved ✓ · हटाएँ" else "Saved ✓ · Remove") else (if (isHindi) "Bookmark करें" else "Bookmark")) }
                    }
                }
            }
            Text(if (isHindi) "Saved सवाल: ${savedQuestions.size}" else "Saved questions: ${savedQuestions.size}", fontWeight = FontWeight.Bold)
            savedQuestions.forEach { q ->
                Text("• ${if (isHindi) q.questionHi else q.questionEn}")
                OutlinedButton(onClick = {
                    savedQuestionIds = savedQuestionIds - q.id
                    prefs.edit().putStringSet("saved_question_ids", savedQuestionIds).apply()
                }) { Text(if (isHindi) "Bookmark हटाएँ" else "Remove bookmark") }
            }
            OutlinedTextField(value = noteInput, onValueChange = { noteInput = it }, label = { Text(if (isHindi) "अपना revision note लिखें" else "Write a revision note") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            Button(onClick = {
                if (noteInput.isNotBlank()) {
                    savedNotes = (savedNotes.lines().filter { it.isNotBlank() } + noteInput.trim()).joinToString("\n")
                    prefs.edit().putString("saved_notes", savedNotes).apply()
                    noteInput = ""
                }
            }, modifier = Modifier.fillMaxWidth()) { Text(if (isHindi) "Note सेव करें" else "Save note") }
            if (savedNotes.isNotBlank()) {
                savedNotes.lines().forEachIndexed { index, note ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("• $note", modifier = Modifier.weight(1f))
                        OutlinedButton(onClick = {
                            savedNotes = savedNotes.lines().filterIndexed { i, _ -> i != index }.joinToString("\n")
                            prefs.edit().putString("saved_notes", savedNotes).apply()
                        }) { Text("×") }
                    }
                }
            }
        }

        SectionCard("5. Study Groups", if (isHindi) "Exam और English practice के लिए अपने समूह चुनें। अभी membership फोन पर सेव होती है; साझा chat और online members backend के बाद जुड़ेंगे।" else "Choose groups for exams and English practice. Membership is local; shared chat and online members come with the backend later.") {
            listOf("GK & Competitive Exams", "English Speaking Practice", "Interview Preparation", "Daily Quiz Buddies").forEach { group ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(group, modifier = Modifier.weight(1f))
                    OutlinedButton(onClick = {
                        joinedGroups = if (joinedGroups.contains(group)) joinedGroups - group else joinedGroups + group
                        prefs.edit().putStringSet("joined_groups", joinedGroups).apply()
                    }) { Text(if (joinedGroups.contains(group)) "Joined ✓" else "Join") }
                }
            }
            Text("${if (isHindi) "आपके समूह" else "Your groups"}: ${joinedGroups.size}")
        }

        SectionCard("6. Daily 5-Minute Challenge", if (isHindi) "पाँच मिनट का timer शुरू करें, छोटा quiz करें और अभ्यास के बाद completion दर्ज करें।" else "Start a five-minute timer, take a short quiz and record completion after practising.") {
            Text(if (todayDone) (if (isHindi) "आज का challenge पूरा ✓" else "Today's challenge completed ✓") else (if (isHindi) "आज का challenge बाकी है" else "Today's challenge is pending"))
            Text(String.format(Locale.getDefault(), "%02d:%02d", secondsLeft / 60, secondsLeft % 60), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { secondsLeft = 300; timerRunning = true }) { Text(if (isHindi) "Timer शुरू" else "Start timer") }
                OutlinedButton(onClick = { timerRunning = false; secondsLeft = 300 }) { Text(if (isHindi) "Reset" else "Reset") }
            }
            Button(onClick = { viewModel.startCategoryQuiz(categories.firstOrNull()?.id ?: "gk") }, modifier = Modifier.fillMaxWidth()) { Text(if (isHindi) "5-minute quiz शुरू करें" else "Start practice quiz") }
            OutlinedButton(onClick = {
                prefs.edit().putBoolean("five_min_done_$dateKey", true).apply()
                refresh++
            }, modifier = Modifier.fillMaxWidth()) { Text(if (isHindi) "अभ्यास पूरा mark करें" else "Mark practice complete") }
            if (secondsLeft == 0) Text(if (isHindi) "5 मिनट पूरे! बढ़िया काम। अब अभ्यास पूरा mark करें।" else "Five minutes completed! Mark your practice complete.", color = MaterialTheme.colorScheme.primary)
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun SectionCard(title: String, description: String, content: @Composable () -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(description, color = MaterialTheme.colorScheme.onSurfaceVariant)
            content()
        }
    }
}
