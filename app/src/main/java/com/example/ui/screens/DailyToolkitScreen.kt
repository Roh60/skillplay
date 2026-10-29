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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.CategoriesData
import com.example.ui.navigation.AppScreen
import com.example.ui.viewmodel.SkillPlayViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private data class DailyWord(val word: String, val meaningHi: String, val meaningEn: String, val example: String)

private val dailyWords = listOf(
    DailyWord("Improve", "सुधारना", "to make something better", "I practise daily to improve my English."),
    DailyWord("Confident", "आत्मविश्वासी", "believing in yourself", "I feel confident during interviews."),
    DailyWord("Reliable", "भरोसेमंद", "able to be trusted", "A reliable employee completes work on time."),
    DailyWord("Opportunity", "अवसर", "a chance to do something", "This course is a good opportunity to learn."),
    DailyWord("Prepare", "तैयारी करना", "to get ready", "I prepare for my exam every evening."),
    DailyWord("Communicate", "बातचीत करना", "to share information clearly", "Speak slowly to communicate clearly."),
    DailyWord("Achieve", "हासिल करना", "to succeed in reaching a goal", "Small habits help me achieve my goals.")
)

@Composable
fun DailyToolkitScreen(viewModel: SkillPlayViewModel, onBack: () -> Unit) {
    val context = LocalContext.current
    val user by viewModel.userProfile.collectAsState()
    val isHindi by viewModel.isHindi.collectAsState()
    val lessons by viewModel.lessonProgressList.collectAsState()
    val challenges by viewModel.challengeRecords.collectAsState()
    val today = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    val prefs = remember(context) { context.getSharedPreferences("skillplay_daily_toolkit", 0) }
    val tasks = listOf("daily_quiz", "word_practice", "lesson", "revision", "career_practice")
    val done = remember(today) {
        mutableStateListOf<Boolean>().apply {
            tasks.forEach { key -> add(prefs.getBoolean("${today}_$key", false)) }
        }
    }
    val word = dailyWords[(today.hashCode().toLong().let { kotlin.math.abs(it) } % dailyWords.size).toInt()]
    val labels = if (isHindi) listOf("5 GK सवाल हल करें", "आज का English शब्द सीखें", "एक lesson पूरा करें", "किसी पुराने topic की revision करें", "Interview या workplace English का अभ्यास करें") else listOf("Complete 5 GK questions", "Learn today's English word", "Finish one lesson", "Revise an older topic", "Practise interview or workplace English")

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(1f)) {
                Text(if (isHindi) "Daily Learning Toolkit" else "Daily Learning Toolkit", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(if (isHindi) "आपकी पढ़ाई, करियर और progress—एक जगह" else "Study, career practice and progress in one place", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            OutlinedButton(onClick = onBack) { Text(if (isHindi) "वापस" else "Back") }
        }

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(if (isHindi) "1. आज का प्लान" else "1. Today's plan", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("${done.count { it }} / ${tasks.size} ${if (isHindi) "काम पूरे" else "tasks complete"}")
                labels.forEachIndexed { index, label ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text((if (done[index]) "✓ " else "○ ") + label, modifier = Modifier.weight(1f))
                        OutlinedButton(onClick = {
                            done[index] = !done[index]
                            prefs.edit().putBoolean("${today}_${tasks[index]}", done[index]).apply()
                        }) { Text(if (done[index]) (if (isHindi) "पूरा" else "Done") else (if (isHindi) "चुनें" else "Mark")) }
                    }
                }
                Text(if (isHindi) "टास्क इस फोन पर सेव होते हैं; ऐप में AI का उपयोग नहीं होता।" else "Checklist is saved on this phone. No AI is used.", style = MaterialTheme.typography.bodySmall)
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(if (isHindi) "2. आज का English शब्द" else "2. Word of the day", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(word.word, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Text(if (isHindi) "अर्थ: ${word.meaningHi}" else "Meaning: ${word.meaningEn}")
                Text("Example: ${word.example}")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(if (isHindi) "3. Revision और कमजोर विषय" else "3. Revision and weak topics", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(if (isHindi) "किसी विषय का छोटा quiz देकर अपनी याददाश्त जाँचें।" else "Take a short quiz to refresh what you have learned.")
                CategoriesData.allCategories.take(4).forEach { category ->
                    OutlinedButton(onClick = { viewModel.startCategoryQuiz(category.id) }, modifier = Modifier.fillMaxWidth()) {
                        Text(if (isHindi) "${category.nameHi} — Quiz" else "${category.nameEn} — Quiz")
                    }
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(if (isHindi) "4. Career और Interview अभ्यास" else "4. Career and interview practice", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(if (isHindi) "इन सवालों के जवाब बोलकर या लिखकर अभ्यास करें:" else "Practise answering these questions aloud or in writing:")
                listOf(
                    "Tell me about yourself.",
                    "What are your strengths?",
                    "Why should we hire you?",
                    "Write a polite email asking for an update."
                ).forEachIndexed { i, prompt -> Text("${i + 1}. $prompt") }
                Button(onClick = { viewModel.navigateTo(AppScreen.EnglishHub) }, modifier = Modifier.fillMaxWidth()) {
                    Text(if (isHindi) "English Practice खोलें" else "Open English Practice")
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(if (isHindi) "5. आपकी Progress" else "5. Your progress", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("${if (isHindi) "कुल XP" else "Total XP"}: ${user?.totalXp ?: 0}")
                Text("${if (isHindi) "पूरे किए गए lessons" else "Lessons completed"}: ${lessons.count { it.scorePercent >= 100 }}")
                Text("${if (isHindi) "Challenge records" else "Challenge records"}: ${challenges.size}")
                Text(if (isHindi) "Progress इस डिवाइस पर उपलब्ध रिकॉर्ड पर आधारित है।" else "Progress is based on records available on this device.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}
