package com.example.data.model

enum class EnglishModuleType(val titleEn: String, val titleHi: String, val descriptionEn: String) {
    VOCABULARY("Vocabulary & Meanings", "शब्दावली एवं अर्थ", "Learn high-frequency root words, prefixes, and exam synonyms"),
    GRAMMAR("Grammar Foundations", "व्याकरण के नियम", "Master tenses, prepositions, and subject-verb concord"),
    SENTENCE_CORRECTION("Sentence Correction", "वाक्य सुधार", "Identify common grammatical errors in real sentences"),
    CONVERSATIONS("Everyday Conversations", "दैनिक बातचीत", "Practical dialogues for shopping, travel, banking, and social life"),
    INTERVIEW("Interview Question Practice", "साक्षात्कार प्रश्न अभ्यास", "Prepare structured STAR answers for common HR rounds"),
    WORKPLACE("Workplace Communication", "कार्यस्थल संचार", "Draft professional emails, updates, and polite requests"),
    READING("Reading Comprehension", "पठन बोध", "Read concise passages and answer inferential questions"),
    THINK_IN_ENGLISH("Think in English", "अंग्रेज़ी में सोचें", "Build direct English responses without translating word by word"),
    SENTENCE_BUILDER("Sentence Builder", "वाक्य बनाना", "Arrange words into clear and correct English sentences"),
    SENTENCE_PRACTICE("Sentence Practice", "वाक्य अभ्यास", "Practice everyday sentence patterns with fixed answer feedback"),
    STRUCTURE("Sentence Structure", "वाक्य संरचना", "Learn subject, verb, object, clauses and sentence patterns"),
    PARAGRAPHS("Paragraph Writing", "पैराग्राफ लेखन", "Build organized paragraphs with topic, supporting points and conclusion"),
    IELTS("IELTS Speaking Preparation", "IELTS स्पीकिंग तैयारी", "Practice IELTS-style speaking prompts with sample frameworks")
}

data class EnglishExercise(
    val id: String,
    val moduleType: EnglishModuleType,
    val titleEn: String,
    val titleHi: String,
    val contextEn: String,
    val contextHi: String,
    val promptEn: String,
    val promptHi: String,
    val options: List<String> = emptyList(),
    val correctOptionIndex: Int = -1,
    val sampleAnswerEn: String,
    val sampleAnswerHi: String,
    val keyTipsEn: List<String>,
    val keyTipsHi: List<String>
)
