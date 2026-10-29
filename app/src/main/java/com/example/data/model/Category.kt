package com.example.data.model

data class LearningCategory(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val descriptionEn: String,
    val descriptionHi: String,
    val iconName: String,
    val totalLessons: Int = 3
)

object CategoriesData {
    val allCategories = listOf(
        LearningCategory(
            id = "gk",
            nameEn = "General Knowledge",
            nameHi = "सामान्य ज्ञान",
            descriptionEn = "National symbols, awards, culture, and key facts about India & world",
            descriptionHi = "राष्ट्रीय प्रतीक, पुरस्कार, संस्कृति और भारत व विश्व के प्रमुख तथ्य",
            iconName = "Public"
        ),
        LearningCategory(
            id = "history",
            nameEn = "Indian History",
            nameHi = "भारतीय इतिहास",
            descriptionEn = "Ancient civilizations, medieval empires, and freedom struggle",
            descriptionHi = "प्राचीन सभ्यताएं, मध्यकालीन साम्राज्य और स्वतंत्रता संग्राम",
            iconName = "AccountBalance"
        ),
        LearningCategory(
            id = "geography",
            nameEn = "Indian Geography",
            nameHi = "भारतीय भूगोल",
            descriptionEn = "Rivers, mountain ranges, climate, soil types, and agriculture",
            descriptionHi = "नदियां, पर्वत श्रृंखलाएं, जलवायु, मिट्टी के प्रकार और कृषि",
            iconName = "Landscape"
        ),
        LearningCategory(
            id = "polity",
            nameEn = "Indian Polity",
            nameHi = "भारतीय राजव्यवस्था",
            descriptionEn = "Constitution, fundamental rights, parliament, and governance",
            descriptionHi = "संविधान, मौलिक अधिकार, संसद और शासन प्रणाली",
            iconName = "Gavel"
        ),
        LearningCategory(
            id = "science",
            nameEn = "General Science",
            nameHi = "सामान्य विज्ञान",
            descriptionEn = "Everyday physics, chemical reactions, human biology, and space",
            descriptionHi = "दैनिक भौतिकी, रासायनिक अभिक्रियाएं, मानव जीव विज्ञान और अंतरिक्ष",
            iconName = "Science"
        ),
        LearningCategory(
            id = "math",
            nameEn = "Mathematics",
            nameHi = "गणित",
            descriptionEn = "Percentages, ratios, profit & loss, averages, and speed-time",
            descriptionHi = "प्रतिशत, अनुपात, लाभ-हानि, औसत और चाल-समय",
            iconName = "Calculate"
        ),
        LearningCategory(
            id = "reasoning",
            nameEn = "Logical Reasoning",
            nameHi = "तार्किक तर्कशक्ति",
            descriptionEn = "Pattern recognition, syllogisms, coding-decoding, and series",
            descriptionHi = "पैटर्न पहचान, न्यायवाक्य, कोडिंग-डिकोडिंग और श्रृंखला",
            iconName = "Psychology"
        ),
        LearningCategory(
            id = "vocabulary",
            nameEn = "English Vocabulary",
            nameHi = "अंग्रेजी शब्दावली",
            descriptionEn = "High-frequency root words, idioms, synonyms, and antonyms",
            descriptionHi = "महत्वपूर्ण मूल शब्द, मुहावरे, पर्यायवाची और विलोम शब्द",
            iconName = "Spellcheck"
        ),
        LearningCategory(
            id = "grammar",
            nameEn = "English Grammar",
            nameHi = "अंग्रेजी व्याकरण",
            descriptionEn = "Tenses, prepositions, active-passive voice, and subject-verb agreement",
            descriptionHi = "काल (Tenses), कारक (Prepositions), वाच्य और विषय-क्रिया समन्वय",
            iconName = "MenuBook"
        ),
        LearningCategory(
            id = "interview",
            nameEn = "Interview Preparation",
            nameHi = "साक्षात्कार तैयारी",
            descriptionEn = "HR questions, STAR method, body language, and resume tips",
            descriptionHi = "एचआर प्रश्न, स्टार पद्धति, शारीरिक हावभाव और बायोडाटा सुझाव",
            iconName = "WorkOutline"
        ),
        LearningCategory(
            id = "workplace",
            nameEn = "Workplace Communication",
            nameHi = "कार्यस्थल संचार",
            descriptionEn = "Professional emailing, meeting etiquette, and constructive feedback",
            descriptionHi = "व्यावसायिक ईमेल, बैठक शिष्टाचार और रचनात्मक प्रतिक्रिया",
            iconName = "ChatBubbleOutline"
        ),
        LearningCategory(
            id = "computer",
            nameEn = "Computer Basics",
            nameHi = "कंप्यूटर मूल बातें",
            descriptionEn = "Operating systems, shortcuts, office tools, and file management",
            descriptionHi = "ऑपरेटिंग सिस्टम, शॉर्टकट, ऑफिस टूल्स और फाइल प्रबंधन",
            iconName = "Computer"
        ),
        LearningCategory(
            id = "digital_safety",
            nameEn = "Digital Safety",
            nameHi = "डिजिटल सुरक्षा",
            descriptionEn = "Spotting UPI scams, phishing links, OTP hygiene, and privacy",
            descriptionHi = "यूपीआई घोटाले पहचानना, फ़िशिंग लिंक, ओटीपी सुरक्षा और गोपनीयता",
            iconName = "Security"
        ),
        LearningCategory(
            id = "finance",
            nameEn = "Financial Literacy",
            nameHi = "वित्तीय साक्षरता",
            descriptionEn = "UPI, budgeting, compound interest, emergency funds, and mutual funds",
            descriptionHi = "यूपीआई, बजट, चक्रवृद्धि ब्याज, आपातकालीन निधि और म्यूचुअल फंड",
            iconName = "AccountBalanceWallet"
        )
    )

    fun getCategoryById(id: String): LearningCategory {
        return allCategories.find { it.id == id } ?: allCategories.first()
    }
}
