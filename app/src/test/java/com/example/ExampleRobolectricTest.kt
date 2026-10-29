package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.CategoriesData
import com.example.data.dataset.QuestionBank
import com.example.data.model.Difficulty
import com.example.data.model.QuizUserAnswer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `app name is SkillPlay India`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("SkillPlay India", appName)
    }

    @Test
    fun `verify all 14 learning categories exist`() {
        val categories = CategoriesData.allCategories
        assertEquals(14, categories.size)
        assertTrue(categories.any { it.id == "gk" })
        assertTrue(categories.any { it.id == "history" })
        assertTrue(categories.any { it.id == "geography" })
        assertTrue(categories.any { it.id == "polity" })
        assertTrue(categories.any { it.id == "science" })
        assertTrue(categories.any { it.id == "math" })
        assertTrue(categories.any { it.id == "reasoning" })
        assertTrue(categories.any { it.id == "vocabulary" })
        assertTrue(categories.any { it.id == "grammar" })
        assertTrue(categories.any { it.id == "interview" })
        assertTrue(categories.any { it.id == "workplace" })
        assertTrue(categories.any { it.id == "computer" })
        assertTrue(categories.any { it.id == "digital_safety" })
        assertTrue(categories.any { it.id == "finance" })
    }

    @Test
    fun `verify question scoring and answer index validation`() {
        val question = QuestionBank.allQuestions.first()
        assertNotNull(question)
        assertEquals(4, question.optionsEn.size)
        assertEquals(4, question.optionsHi.size)
        assertTrue(question.correctIndex in 0..3)
        assertTrue(question.isVerified)
        assertTrue(question.sourceUrl.isNotEmpty())

        val correctUserAnswer = QuizUserAnswer(question, question.correctIndex)
        assertTrue(correctUserAnswer.isCorrect)

        val wrongIndex = (question.correctIndex + 1) % 4
        val wrongUserAnswer = QuizUserAnswer(question, wrongIndex)
        assertFalse(wrongUserAnswer.isCorrect)
    }
}
