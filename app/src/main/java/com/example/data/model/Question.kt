package com.example.data.model

enum class Difficulty {
    EASY, MEDIUM, HARD
}

data class QuizQuestion(
    val id: String,
    val categoryId: String,
    val difficulty: Difficulty,
    val questionEn: String,
    val questionHi: String,
    val optionsEn: List<String>,
    val optionsHi: List<String>,
    val correctIndex: Int,
    val explanationEn: String,
    val explanationHi: String,
    val sourceUrl: String,
    val isVerified: Boolean
)

data class QuizUserAnswer(
    val question: QuizQuestion,
    val selectedIndex: Int
) {
    val isCorrect: Boolean get() = selectedIndex == question.correctIndex
}
