package com.example

import com.example.data.dataset.EnglishPracticeBank
import com.example.data.dataset.DailyUseSentenceContent
import com.example.data.dataset.LessonBank
import com.example.data.dataset.QuestionBank
import com.example.data.local.ChallengeRecordEntity
import com.example.data.model.EnglishModuleType
import com.example.data.model.QuizUserAnswer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SkillPlayLogicTest {

    @Test
    fun testQuizQuestionIntegrityAndVerification() {
        val questions = QuestionBank.allQuestions
        assertTrue("Question bank must not be empty", questions.isNotEmpty())

        for (q in questions) {
            assertTrue("Question must be verified: ${q.id}", q.isVerified)
            assertTrue("Source URL must be provided: ${q.id}", q.sourceUrl.startsWith("http"))
            assertEquals("English options must have exactly 4 choices: ${q.id}", 4, q.optionsEn.size)
            assertEquals("Hindi options must have exactly 4 choices: ${q.id}", 4, q.optionsHi.size)
            assertTrue("Correct index must be 0..3: ${q.id}", q.correctIndex in 0..3)
            assertTrue("Question text in EN must be present", q.questionEn.isNotBlank())
            assertTrue("Question text in HI must be present", q.questionHi.isNotBlank())
            assertTrue("Explanation in EN must be present", q.explanationEn.isNotBlank())
            assertTrue("Explanation in HI must be present", q.explanationHi.isNotBlank())
        }
    }

    @Test
    fun testInteractiveGkQuizQuestionsWereAdded() {
        val addedIds = setOf(
            "added_constitution_1950", "added_jupiter_largest", "added_rajasthan_area",
            "added_photosynthesis_co2", "added_national_animal_tiger", "added_ganga_source",
            "added_fundamental_rights", "added_inr_code", "added_tagore_anthem",
            "added_nitrogen_atmosphere", "added_parliament_houses", "added_mercury_liquid_metal",
            "added_sun_is_star", "added_national_flower_lotus", "added_freedom_movement_bhagat",
            "added_cpu_full_form"
        )
        val questions = QuestionBank.allQuestions.filter { it.id in addedIds }
        assertEquals("All 16 questions from the two interactive GK quizzes must be included", 16, questions.size)
        assertEquals("Added question IDs must be unique", 16, questions.map { it.id }.distinct().size)
        assertTrue("Every added question must have four Hindi options", questions.all { it.optionsHi.size == 4 })
        assertTrue("Every added question must have a valid answer index", questions.all { it.correctIndex in 0..3 })
    }

    @Test
    fun testQuizScoringCalculation() {
        val q1 = QuestionBank.allQuestions[0]
        val q2 = QuestionBank.allQuestions[1]

        val answer1 = QuizUserAnswer(q1, q1.correctIndex)
        val wrongIndex = (q2.correctIndex + 1) % 4
        val answer2 = QuizUserAnswer(q2, wrongIndex)

        val answers = listOf(answer1, answer2)
        val correctCount = answers.count { it.isCorrect }
        val accuracy = (correctCount * 100) / answers.size

        assertEquals(1, correctCount)
        assertEquals(50, accuracy)
    }

    @Test
    fun testDuplicateClaimPreventionLogic() {
        // Mock checking duplicate records for the same key
        val records = mutableListOf<ChallengeRecordEntity>()
        val todayKey = "2026-10-25"

        fun claimChallenge(key: String, score: Int): Boolean {
            if (records.any { it.challengeKey == key && it.isClaimed }) {
                return false // Duplicate blocked
            }
            records.add(ChallengeRecordEntity(challengeKey = key, score = score, isClaimed = true))
            return true
        }

        val firstClaim = claimChallenge(todayKey, 5)
        assertTrue("First claim of the day must succeed", firstClaim)

        val secondClaim = claimChallenge(todayKey, 4)
        assertFalse("Second claim of the same day must be rejected", secondClaim)
    }

    @Test
    fun testEnglishPracticeModulesCoverage() {
        val tracks = EnglishModuleType.values()
        assertEquals(13, tracks.size)

        for (track in tracks) {
            val exercises = EnglishPracticeBank.getExercisesByType(track)
            assertTrue("Track ${track.name} must have exercises", exercises.isNotEmpty())
            val ex = exercises.first()
            assertTrue("Sample answer in English must be provided", ex.sampleAnswerEn.isNotBlank())
            assertTrue("Sample answer in Hindi must be provided", ex.sampleAnswerHi.isNotBlank())
            assertTrue("Key tips must be provided", ex.keyTipsEn.isNotEmpty())
        }
    }

    @Test
    fun testEnglishExerciseIntegrityAndDailyUseTopics() {
        val exercises = EnglishPracticeBank.allExercises
        assertTrue("English practice bank must not be empty", exercises.isNotEmpty())
        assertEquals("Exercise IDs must be unique", exercises.size, exercises.map { it.id }.toSet().size)

        for (exercise in exercises) {
            assertTrue("English title missing: ${exercise.id}", exercise.titleEn.isNotBlank())
            assertTrue("Hindi title missing: ${exercise.id}", exercise.titleHi.isNotBlank())
            assertTrue("English prompt missing: ${exercise.id}", exercise.promptEn.isNotBlank())
            assertTrue("Hindi prompt missing: ${exercise.id}", exercise.promptHi.isNotBlank())
            assertTrue("English sample answer missing: ${exercise.id}", exercise.sampleAnswerEn.isNotBlank())
            assertTrue("Hindi sample answer missing: ${exercise.id}", exercise.sampleAnswerHi.isNotBlank())
            assertTrue("English tips missing: ${exercise.id}", exercise.keyTipsEn.isNotEmpty())
            assertTrue("Hindi tips missing: ${exercise.id}", exercise.keyTipsHi.isNotEmpty())
            if (exercise.options.isNotEmpty()) {
                assertEquals("MCQ must have four options: ${exercise.id}", 4, exercise.options.size)
                assertTrue("Correct option index out of range: ${exercise.id}", exercise.correctOptionIndex in exercise.options.indices)
            }
        }

        val dailyIds = DailyUseSentenceContent.exercises.map { it.id }.toSet()
        assertEquals("Expected 12 daily-use exercises", 12, dailyIds.size)
        assertTrue(dailyIds.containsAll(listOf(
            "daily_shop_1", "daily_shop_2", "daily_doctor_1", "daily_doctor_2",
            "daily_medical_1", "daily_medical_2", "daily_teacher_1", "daily_teacher_2",
            "daily_students_1", "daily_students_2", "daily_family_1", "daily_family_2"
        )))
        assertTrue("Daily-use content must be integrated into the main bank", exercises.map { it.id }.toSet().containsAll(dailyIds))
    }

    @Test
    fun testLessonBankIntegrity() {
        val lessons = LessonBank.allLessons
        assertTrue("Lessons must be provided", lessons.isNotEmpty())

        for (lesson in lessons) {
            assertTrue("Lesson title EN must exist", lesson.titleEn.isNotBlank())
            assertTrue("Lesson title HI must exist", lesson.titleHi.isNotBlank())
            assertTrue("Lesson sections must exist", lesson.sections.isNotEmpty())
            assertTrue("Estimated minutes must be > 0", lesson.estimatedMinutes > 0)
        }
    }

    @Test
    fun testPersonalizedRecommendationsForStatedInterests() {
        val user = com.example.data.local.UserProfileEntity(
            statedInterests = "gk,polity,interview",
            primaryGoalTrack = "competitive_exams"
        )
        val progress = emptyList<com.example.data.local.LessonProgressEntity>()

        val recommendations = com.example.data.engine.RecommendationEngine.generateRecommendations(user, progress)
        assertTrue("Recommendations must not be empty", recommendations.isNotEmpty())

        val recCategoryIds = recommendations.map { it.category.id }
        assertTrue("Must include stated interest gk or polity", recCategoryIds.contains("gk") || recCategoryIds.contains("polity"))

        val firstRec = recommendations.first()
        assertTrue("Headline must be non-empty", firstRec.headlineEn.isNotBlank())
        assertTrue("Rationale must be non-empty", firstRec.rationaleEn.isNotBlank())
    }

    @Test
    fun testPersonalizedRecommendationsAccuracyBooster() {
        // User scored 40% in Polity quiz
        val user = com.example.data.local.UserProfileEntity(
            statedInterests = "gk,polity",
            primaryGoalTrack = "competitive_exams"
        )
        val progress = listOf(
            com.example.data.local.LessonProgressEntity(
                lessonId = "quiz_polity",
                categoryId = "polity",
                completedAt = System.currentTimeMillis(),
                scorePercent = 40
            )
        )

        val recommendations = com.example.data.engine.RecommendationEngine.generateRecommendations(user, progress)
        val booster = recommendations.find { it.type == com.example.data.model.RecommendationType.ACCURACY_BOOSTER }
        org.junit.Assert.assertNotNull("Accuracy booster recommendation should be generated for low score", booster)
        assertEquals("polity", booster?.category?.id)
        assertEquals(40, booster?.recentAccuracy)
        assertTrue("Rationale should mention 40%", booster?.rationaleEn?.contains("40%") == true)
    }

    @Test
    fun testPersonalizedRecommendationsGoalTrackSwitch() {
        val careerUser = com.example.data.local.UserProfileEntity(
            statedInterests = "interview,workplace,vocabulary",
            primaryGoalTrack = "career_readiness"
        )
        val recommendations = com.example.data.engine.RecommendationEngine.generateRecommendations(careerUser, emptyList())
        val categoryIds = recommendations.map { it.category.id }
        assertTrue("Career track must recommend interview or workplace", categoryIds.contains("interview") || categoryIds.contains("workplace"))
    }
}
