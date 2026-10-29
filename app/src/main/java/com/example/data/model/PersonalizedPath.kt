package com.example.data.model

enum class GoalTrack(
    val id: String,
    val titleEn: String,
    val titleHi: String,
    val descriptionEn: String,
    val descriptionHi: String,
    val defaultCategories: List<String>
) {
    COMPETITIVE_EXAMS(
        id = "competitive_exams",
        titleEn = "UPSC & State PSC Prep",
        titleHi = "प्रतियोगी परीक्षाएं (UPSC / State PSC)",
        descriptionEn = "Master General Knowledge, Indian History, Geography, Polity & Reasoning",
        descriptionHi = "सामान्य ज्ञान, इतिहास, भूगोल, राजव्यवस्था और तर्कशक्ति में महारत",
        defaultCategories = listOf("gk", "history", "geography", "polity", "reasoning")
    ),
    CAREER_READINESS(
        id = "career_readiness",
        titleEn = "Job Interviews & Workplace",
        titleHi = "करियर एवं नौकरी साक्षात्कार",
        descriptionEn = "Excel in HR interviews, STAR technique, business emails & vocabulary",
        descriptionHi = "एचआर साक्षात्कार, STAR पद्धति, व्यावसायिक ईमेल और शब्दावली",
        defaultCategories = listOf("interview", "workplace", "vocabulary", "grammar")
    ),
    TECH_AND_FINANCE(
        id = "tech_and_finance",
        titleEn = "Digital Safety & Finance",
        titleHi = "डिजिटल सुरक्षा एवं वित्तीय ज्ञान",
        descriptionEn = "Shield against UPI cyber frauds, compound wealth, and master computers",
        descriptionHi = "यूपीआई साइबर धोखाधड़ी से बचाव, धन संचय और कंप्यूटर ज्ञान",
        defaultCategories = listOf("digital_safety", "finance", "computer")
    ),
    ANALYTICAL_APTITUDE(
        id = "analytical_aptitude",
        titleEn = "Quantitative Aptitude & Logic",
        titleHi = "तार्किक एवं संख्यात्मक योग्यता",
        descriptionEn = "Speed arithmetic, ratio tricks, patterns, and logical reasoning",
        descriptionHi = "चाल-समय, लाभ-हानि, श्रृंखला और तार्किक पहेलियों का तेज अभ्यास",
        defaultCategories = listOf("math", "reasoning", "science")
    );

    companion object {
        fun fromId(id: String): GoalTrack {
            return values().find { it.id == id } ?: COMPETITIVE_EXAMS
        }
    }
}

enum class RecommendationType(
    val tagEn: String,
    val tagHi: String
) {
    ACCURACY_BOOSTER("Accuracy Booster", "सटीकता सुधार"),
    STATED_INTEREST("Core Focus Goal", "प्राथमिक रुचि"),
    NEXT_SEQUENTIAL_LESSON("Next Milestone", "अगला पड़ाव"),
    COMPLEMENTARY_SKILL("Recommended Next Step", "अनुशंसित अगला कदम")
}

data class PathRecommendation(
    val id: String,
    val category: LearningCategory,
    val recommendedLesson: Lesson?,
    val type: RecommendationType,
    val headlineEn: String,
    val headlineHi: String,
    val rationaleEn: String,
    val rationaleHi: String,
    val progressPercent: Float,
    val recentAccuracy: Int?,
    val isQuizRecommendation: Boolean = false,
    val priorityScore: Int = 0
)
