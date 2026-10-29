package com.example.ui.navigation

import com.example.data.model.Difficulty
import com.example.data.model.EnglishModuleType
import com.example.data.model.QuizUserAnswer

sealed class MainTab(val route: String, val titleEn: String, val titleHi: String) {
    object Home : MainTab("home", "Home", "होम")
    object Learn : MainTab("learn", "Learn", "सीखें")
    object Challenges : MainTab("challenges", "Challenges", "चुनौतियां")
    object Community : MainTab("community", "Community", "समुदाय")
    object Profile : MainTab("profile", "Profile", "प्रोफ़ाइल")

    companion object {
        val allTabs = listOf(Home, Learn, Challenges, Community, Profile)
    }
}

sealed interface AppScreen {
    object MainNavigation : AppScreen
    data class CategoryLessons(val categoryId: String) : AppScreen
    data class LessonView(val lessonId: String) : AppScreen
    data class QuizActive(val categoryId: String, val difficulty: Difficulty, val isDaily: Boolean) : AppScreen
    data class QuizResult(val score: Int, val total: Int, val categoryId: String, val isDaily: Boolean, val wrongAnswers: List<QuizUserAnswer>) : AppScreen
    data class ReviewWrong(val wrongAnswers: List<QuizUserAnswer>) : AppScreen
    object EnglishHub : AppScreen
    object DailyToolkit : AppScreen
    object StudyBoost : AppScreen
    data class EnglishModuleView(val moduleType: EnglishModuleType) : AppScreen
    object FullLeaderboard : AppScreen
    object CommunityGuidelines : AppScreen
    object ModerationQueue : AppScreen
    object Settings : AppScreen
    object PrivacyDetails : AppScreen
}
