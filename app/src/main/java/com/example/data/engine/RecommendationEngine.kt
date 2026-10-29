package com.example.data.engine

import com.example.data.dataset.LessonBank
import com.example.data.local.LessonProgressEntity
import com.example.data.local.UserProfileEntity
import com.example.data.model.CategoriesData
import com.example.data.model.GoalTrack
import com.example.data.model.LearningCategory
import com.example.data.model.Lesson
import com.example.data.model.PathRecommendation
import com.example.data.model.RecommendationType

object RecommendationEngine {

    fun generateRecommendations(
        user: UserProfileEntity,
        progressList: List<LessonProgressEntity>
    ): List<PathRecommendation> {
        val userGoal = GoalTrack.fromId(user.primaryGoalTrack)
        val statedInterestIds = user.statedInterests.split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .ifEmpty { userGoal.defaultCategories }
            .toSet()

        val progressByCat = progressList.groupBy { it.categoryId }
        val recommendations = mutableListOf<PathRecommendation>()

        // 1. Check Accuracy Booster needs (< 70% in recent quizzes)
        for ((catId, catProgress) in progressByCat) {
            val quizRecord = catProgress.find { it.lessonId.startsWith("quiz_") }
            val lowScoreRecord = catProgress.filter { it.scorePercent in 1..69 }.minByOrNull { it.scorePercent }
            val score = lowScoreRecord?.scorePercent ?: quizRecord?.scorePercent

            if (score != null && score < 70) {
                val category = CategoriesData.getCategoryById(catId)
                val lessons = LessonBank.getLessonsForCategory(catId)
                val uncompleted = lessons.find { l -> catProgress.none { it.lessonId == l.id && it.completedAt > 0 } }
                    ?: lessons.firstOrNull()

                recommendations.add(
                    PathRecommendation(
                        id = "rec_boost_$catId",
                        category = category,
                        recommendedLesson = uncompleted,
                        type = RecommendationType.ACCURACY_BOOSTER,
                        headlineEn = "Boost ${category.nameEn} Accuracy",
                        headlineHi = "${category.nameHi} में सटीकता सुधारें",
                        rationaleEn = "Recent quiz accuracy was $score%. Revisiting key takeaways will lift your recall to 85%+.",
                        rationaleHi = "हालिया अभ्यास में आपकी सटीकता $score% रही। मुख्य बिंदुओं को दोहराने से आपका स्कोर बढ़ेगा।",
                        progressPercent = (catProgress.count { it.completedAt > 0 }.toFloat() / category.totalLessons).coerceIn(0f, 1f),
                        recentAccuracy = score,
                        isQuizRecommendation = false,
                        priorityScore = 120 + (70 - score)
                    )
                )
            }
        }

        // 2. Stated Interests: Next core milestones
        for (catId in statedInterestIds) {
            val category = CategoriesData.getCategoryById(catId)
            val catProgress = progressByCat[catId] ?: emptyList()
            val lessons = LessonBank.getLessonsForCategory(catId)
            val completedCount = catProgress.count { it.completedAt > 0 }
            val nextLesson = lessons.find { l -> catProgress.none { it.lessonId == l.id && it.completedAt > 0 } }

            if (nextLesson != null) {
                val pct = (completedCount.toFloat() / category.totalLessons).coerceIn(0f, 1f)
                recommendations.add(
                    PathRecommendation(
                        id = "rec_core_${nextLesson.id}",
                        category = category,
                        recommendedLesson = nextLesson,
                        type = if (completedCount > 0) RecommendationType.NEXT_SEQUENTIAL_LESSON else RecommendationType.STATED_INTEREST,
                        headlineEn = if (completedCount > 0) "Next Milestone: ${nextLesson.titleEn}" else "Start ${category.nameEn} Track",
                        headlineHi = if (completedCount > 0) "अगला लक्ष्य: ${nextLesson.titleHi}" else "${category.nameHi} ट्रैक प्रारंभ करें",
                        rationaleEn = "High priority in your ${userGoal.titleEn} goal. (${nextLesson.estimatedMinutes} min read)",
                        rationaleHi = "आपके ${userGoal.titleHi} लक्ष्य के लिए अत्यंत महत्वपूर्ण। (${nextLesson.estimatedMinutes} मिनट पठन)",
                        progressPercent = pct,
                        recentAccuracy = catProgress.find { it.scorePercent > 0 }?.scorePercent,
                        isQuizRecommendation = false,
                        priorityScore = 95 - completedCount * 5
                    )
                )
            } else if (completedCount > 0) {
                // All lessons read; recommend quiz challenge to certify
                recommendations.add(
                    PathRecommendation(
                        id = "rec_quiz_$catId",
                        category = category,
                        recommendedLesson = null,
                        type = RecommendationType.NEXT_SEQUENTIAL_LESSON,
                        headlineEn = "Knowledge Check: ${category.nameEn} Quiz",
                        headlineHi = "ज्ञान परीक्षण: ${category.nameHi} क्विज़",
                        rationaleEn = "You completed all reading modules in ${category.nameEn}. Test your mastery with 5 questions.",
                        rationaleHi = "आपने ${category.nameHi} के सभी पाठ पूरे कर लिए हैं। 5 प्रश्नों से अपनी तैयारी परखें।",
                        progressPercent = 1f,
                        recentAccuracy = catProgress.find { it.scorePercent > 0 }?.scorePercent,
                        isQuizRecommendation = true,
                        priorityScore = 90
                    )
                )
            }
        }

        // 3. Complementary Expansion Paths
        val complementaryPairs = listOf(
            Pair("history", "polity"),
            Pair("polity", "geography"),
            Pair("interview", "workplace"),
            Pair("vocabulary", "interview"),
            Pair("math", "reasoning"),
            Pair("computer", "digital_safety"),
            Pair("digital_safety", "finance")
        )

        for ((src, dest) in complementaryPairs) {
            val srcProgress = progressByCat[src]?.count { it.completedAt > 0 } ?: 0
            val destProgress = progressByCat[dest]?.count { it.completedAt > 0 } ?: 0

            if (srcProgress > 0 && destProgress == 0 && dest !in statedInterestIds) {
                val destCat = CategoriesData.getCategoryById(dest)
                val srcCat = CategoriesData.getCategoryById(src)
                val destLessons = LessonBank.getLessonsForCategory(dest)

                recommendations.add(
                    PathRecommendation(
                        id = "rec_comp_$dest",
                        category = destCat,
                        recommendedLesson = destLessons.firstOrNull(),
                        type = RecommendationType.COMPLEMENTARY_SKILL,
                        headlineEn = "Expand into ${destCat.nameEn}",
                        headlineHi = "${destCat.nameHi} का पूरक अध्ययन",
                        rationaleEn = "Directly complements your progress in ${srcCat.nameEn}. Sharpens comprehensive exam readiness.",
                        rationaleHi = "यह आपके ${srcCat.nameHi} ज्ञान का स्वाभाविक विस्तार है और समग्र तैयारी को मजबूत करता है।",
                        progressPercent = 0f,
                        recentAccuracy = null,
                        isQuizRecommendation = false,
                        priorityScore = 75
                    )
                )
            }
        }

        // Deduplicate by category ID to keep varied suggestions
        val seenCategories = mutableSetOf<String>()
        val distinctList = mutableListOf<PathRecommendation>()

        for (rec in recommendations.sortedByDescending { it.priorityScore }) {
            if (rec.category.id !in seenCategories) {
                seenCategories.add(rec.category.id)
                distinctList.add(rec)
            }
        }

        // Fallback if user is totally new: return initial recommendations for stated interests
        if (distinctList.isEmpty()) {
            for (catId in statedInterestIds.take(3)) {
                val cat = CategoriesData.getCategoryById(catId)
                val lessons = LessonBank.getLessonsForCategory(catId)
                distinctList.add(
                    PathRecommendation(
                        id = "rec_fallback_$catId",
                        category = cat,
                        recommendedLesson = lessons.firstOrNull(),
                        type = RecommendationType.STATED_INTEREST,
                        headlineEn = "Recommended: ${cat.nameEn}",
                        headlineHi = "अनुशंसित: ${cat.nameHi}",
                        rationaleEn = "Foundational topic for ${userGoal.titleEn}.",
                        rationaleHi = "${userGoal.titleHi} के लिए आवश्यक विषय।",
                        progressPercent = 0f,
                        recentAccuracy = null,
                        isQuizRecommendation = false,
                        priorityScore = 70
                    )
                )
            }
        }

        return distinctList.take(4)
    }
}
