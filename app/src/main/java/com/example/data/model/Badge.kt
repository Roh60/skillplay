package com.example.data.model

data class SkillBadge(
    val id: String,
    val titleEn: String,
    val titleHi: String,
    val descriptionEn: String,
    val descriptionHi: String,
    val iconName: String,
    val requiredXp: Int = 0,
    val requiredLessons: Int = 0,
    val requiredStreak: Int = 0,
    val requiredQuizzes: Int = 0
)

object BadgesData {
    val allBadges = listOf(
        SkillBadge(
            id = "first_step",
            titleEn = "First Step",
            titleHi = "पहला कदम",
            descriptionEn = "Completed your first lesson in SkillPlay India",
            descriptionHi = "स्किलप्ले इंडिया में अपना पहला पाठ पूरा किया",
            iconName = "Flag",
            requiredLessons = 1
        ),
        SkillBadge(
            id = "quiz_master",
            titleEn = "Quiz Ace",
            titleHi = "क्विज महारथी",
            descriptionEn = "Finished 5 quizzes with strong accuracy",
            descriptionHi = "मजबूत सटीकता के साथ 5 क्विज पूरे किए",
            iconName = "EmojiEvents",
            requiredQuizzes = 5
        ),
        SkillBadge(
            id = "streak_fire",
            titleEn = "Consistency Star",
            titleHi = "निरंतरता स्टार",
            descriptionEn = "Maintained a continuous daily learning streak",
            descriptionHi = "लगातार दैनिक सीखने की स्ट्रीक बनाए रखी",
            iconName = "LocalFireDepartment",
            requiredStreak = 3
        ),
        SkillBadge(
            id = "gk_scholar",
            titleEn = "GK Scholar",
            titleHi = "जीके विद्वान",
            descriptionEn = "Mastered key General Knowledge and Indian History modules",
            descriptionHi = "सामान्य ज्ञान और भारतीय इतिहास मॉड्यूल पूरे किए",
            iconName = "School",
            requiredLessons = 4
        ),
        SkillBadge(
            id = "vocab_pro",
            titleEn = "English Pro",
            titleHi = "अंग्रेजी प्रो",
            descriptionEn = "Practiced vocabulary, grammar, and workplace communication",
            descriptionHi = "शब्दावली, व्याकरण और कार्यस्थल संचार का अभ्यास किया",
            iconName = "RecordVoiceOver",
            requiredLessons = 6
        ),
        SkillBadge(
            id = "interview_ready",
            titleEn = "Interview Ready",
            titleHi = "साक्षात्कार तैयार",
            descriptionEn = "Explored practical interview preparation exercises",
            descriptionHi = "व्यावहारिक साक्षात्कार तैयारी अभ्यासों को पूरा किया",
            iconName = "VerifiedUser",
            requiredLessons = 8
        ),
        SkillBadge(
            id = "centurion",
            titleEn = "Knowledge Centurion",
            titleHi = "ज्ञान शतकवीर",
            descriptionEn = "Accumulated over 500 virtual learning XP",
            descriptionHi = "500 से अधिक वर्चुअल लर्निंग एक्सपी अर्जित की",
            iconName = "Stars",
            requiredXp = 500
        )
    )
}

data class LeaderboardUser(
    val rank: Int,
    val name: String,
    val avatarIndex: Int,
    val stateName: String,
    val points: Int,
    val streak: Int,
    val isCurrentUser: Boolean = false
)
