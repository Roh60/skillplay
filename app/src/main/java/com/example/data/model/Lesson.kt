package com.example.data.model

data class LessonSection(
    val headingEn: String,
    val headingHi: String,
    val contentEn: String,
    val contentHi: String,
    val keyTakeawayEn: String? = null,
    val keyTakeawayHi: String? = null
)

data class Lesson(
    val id: String,
    val categoryId: String,
    val titleEn: String,
    val titleHi: String,
    val estimatedMinutes: Int,
    val summaryEn: String,
    val summaryHi: String,
    val sections: List<LessonSection>,
    val quizQuestionIds: List<String>
)
