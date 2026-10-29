package com.example.data.dataset

import com.example.data.model.EnglishExercise
import com.example.data.model.EnglishModuleType

object EnglishPracticeBank {
    val allExercises: List<EnglishExercise> = listOf(
        // Vocabulary
        EnglishExercise(
            id = "eng_vocab_1",
            moduleType = EnglishModuleType.VOCABULARY,
            titleEn = "Essential Words: Resilience & Pragmatic",
            titleHi = "महत्वपूर्ण शब्द: Resilience और Pragmatic",
            contextEn = "Commonly used in professional writing and competitive examinations.",
            contextHi = "पेशेवर लेखन और प्रतियोगी परीक्षाओं में अक्सर पूछे जाने वाले शब्द।",
            promptEn = "What is the most accurate definition of 'Pragmatic'?",
            promptHi = "'Pragmatic' का सबसे सटीक अर्थ क्या है?",
            options = listOf(
                "Dealing with things sensibly and realistically based on practical rather than theoretical considerations",
                "Having excessive pride in one's personal achievements",
                "Extremely hesitant to take any action",
                "Following strict philosophical theories without compromise"
            ),
            correctOptionIndex = 0,
            sampleAnswerEn = "Example: 'She took a pragmatic approach to budget planning, cutting unnecessary expenses first.'",
            sampleAnswerHi = "उदाहरण: 'उसने बजट योजना में व्यावहारिक दृष्टिकोण अपनाया और पहले अनावश्यक खर्चों में कटौती की।'",
            keyTipsEn = listOf(
                "Root: Greek 'pragma' meaning deed or matter of fact.",
                "Synonyms: Practical, realistic, sensible, down-to-earth.",
                "Antonyms: Idealistic, impractical, romanticized."
            ),
            keyTipsHi = listOf(
                "अर्थ: व्यावहारिक, यथार्थवादी, तथ्यपरक।",
                "समानार्थी: Practical, realistic.",
                "विलोम शब्द: Idealistic, impractical."
            )
        ),
        // Grammar
        EnglishExercise(
            id = "eng_gram_1",
            moduleType = EnglishModuleType.GRAMMAR,
            titleEn = "Subject-Verb Agreement with Collective Nouns",
            titleHi = "कलेक्टिव नाउन के साथ सब्जेक्ट-वर्ब समन्वय",
            contextEn = "Errors often occur when nouns like 'committee', 'jury', or 'family' are treated inconsistently.",
            contextHi = "कमिटी, जूरी या टीम जैसे शब्दों के साथ एकवचन और बहुवचन क्रिया के प्रयोग के नियम।",
            promptEn = "Complete the sentence: 'The committee ______ divided in their opinions regarding the new proposal.'",
            promptHi = "रिक्त स्थान भरें: 'The committee ______ divided in their opinions regarding the new proposal.'",
            options = listOf("was", "were", "is", "has"),
            correctOptionIndex = 1,
            sampleAnswerEn = "Correct sentence: 'The committee were divided in their opinions regarding the new proposal.'",
            sampleAnswerHi = "सही वाक्य: 'The committee were divided in their opinions...'",
            keyTipsEn = listOf(
                "When members of a collective noun act as separate individuals with differing views, use a plural verb ('were divided').",
                "When the group acts as a single unified entity, use a singular verb: 'The committee has approved the budget unanimously'."
            ),
            keyTipsHi = listOf(
                "जब समूह के सदस्य अलग-अलग राय रखते हैं तो बहुवचन (were) का प्रयोग होता है।",
                "जब पूरा समूह एकमत होता है तो एकवचन (was / has) का प्रयोग होता है।"
            )
        ),
        // Sentence Correction
        EnglishExercise(
            id = "eng_corr_1",
            moduleType = EnglishModuleType.SENTENCE_CORRECTION,
            titleEn = "Common Error: 'Discuss about' & Redundancy",
            titleHi = "सामान्य अशुद्धि: 'Discuss about' और अनावश्यक शब्द",
            contextEn = "Notice how prepositions are often unnecessarily added after transitive verbs.",
            contextHi = "सकर्मक क्रियाओं के बाद अक्सर अनावश्यक प्रीपोजिशन जोड़ने की भूल होती है।",
            promptEn = "Identify the corrected version of: 'Let us discuss about the agenda items in the meeting.'",
            promptHi = "इस वाक्य का शुद्ध रूप पहचानें: 'Let us discuss about the agenda items in the meeting.'",
            options = listOf(
                "Let us discuss the agenda items in the meeting.",
                "Let us discuss on the agenda items in the meeting.",
                "Let us discuss with the agenda items in the meeting.",
                "Let us discussing about the agenda items."
            ),
            correctOptionIndex = 0,
            sampleAnswerEn = "'Let us discuss the agenda items in the meeting.' (The verb 'discuss' already means 'to talk about', so 'about' is redundant).",
            sampleAnswerHi = "'Let us discuss the agenda items...' ('Discuss' का अर्थ ही 'के बारे में बात करना' होता है, इसलिए 'about' लगाना गलत है)।",
            keyTipsEn = listOf(
                "Similar verbs that do not take prepositions: 'order' (order food, not order for food), 'reach' (reach Delhi, not reach to Delhi), 'resemble' (resembles his father).",
                "Avoid double prepositions in formal writing."
            ),
            keyTipsHi = listOf(
                "ध्यान रखें: Order lunch (order for lunch नहीं), Reach the station (reach to station नहीं)।"
            )
        ),
        // Everyday Conversations
        EnglishExercise(
            id = "eng_conv_1",
            moduleType = EnglishModuleType.CONVERSATIONS,
            titleEn = "Polite Enquiries at Banks and Customer Desks",
            titleHi = "बैंक अथवा ग्राहक सेवा केंद्र पर विनम्र बातचीत",
            contextEn = "Scenario: Requesting an update on a delayed bank debit card or document verification.",
            contextHi = "परिदृश्य: बैंक में एटीएम कार्ड या दस्तावेज सत्यापन की स्थिति जानने के लिए विनम्र संवाद।",
            promptEn = "Which response is most appropriate and polite when approaching a customer service representative?",
            promptHi = "ग्राहक प्रतिनिधि से बातचीत शुरू करते समय कौन सा वाक्य सबसे विनम्र और प्रभावी है?",
            options = listOf(
                "Give me my card status right now, it has been two weeks!",
                "Excuse me, could you please help me check the status of my debit card application?",
                "Why didn't you people process my request yet?",
                "I want my account checked today itself."
            ),
            correctOptionIndex = 1,
            sampleAnswerEn = "Dialogue:\nCustomer: 'Good morning. Excuse me, could you please help me check the status of my debit card application? Here is my reference token.'\nExecutive: 'Certainly, sir. Please allow me a moment while I pull up your record.'",
            sampleAnswerHi = "संवाद:\nग्राहक: 'सुप्रभात। क्या आप कृपया मेरे डेबिट कार्ड आवेदन की स्थिति जांचने में मेरी सहायता कर सकते हैं? यह मेरा संदर्भ टोकन है।'\nअधिकारी: 'अवश्य, कृपया मुझे रिकॉर्ड देखने के लिए एक क्षण दें।'" ,
            keyTipsEn = listOf(
                "Use 'Could you please...' or 'Would it be possible to...' to soften requests.",
                "Always have your token or account reference ready to save mutual time.",
                "Notice: This is a written practice module designed to refine phraseology and structure."
            ),
            keyTipsHi = listOf(
                "अनुरोध करते समय 'Could you please...' का प्रयोग विनम्रता दर्शाता है।",
                "नोट: यह लिखित अभ्यास मॉड्यूल है जो संवाद कौशल को निखारने के लिए है।"
            )
        ),
        // Interview Question Practice
        EnglishExercise(
            id = "eng_inter_1",
            moduleType = EnglishModuleType.INTERVIEW,
            titleEn = "'Tell Me About Yourself' (The 90-Second Pitch)",
            titleHi = "'अपने बारे में बताएं' (90 सेकंड का प्रभावी परिचय)",
            contextEn = "The universal opening question in campus placements and corporate job interviews.",
            contextHi = "कैंपस प्लेसमेंट और नौकरियों के साक्षात्कार में पूछा जाने वाला पहला बुनियादी प्रश्न।",
            promptEn = "What is the recommended chronological formula to answer 'Tell me about yourself'?",
            promptHi = "इस प्रश्न का उत्तर देने का सबसे संतुलित और पेशेवर तरीका क्या है?",
            options = listOf(
                "Tell your entire life story starting from primary school hobbies",
                "Present (Current role/education) -> Past (Relevant projects/skills) -> Future (Why this company excites you)",
                "Focus exclusively on your personal salary expectations and family background",
                "Ask the interviewer to directly read your printed resume"
            ),
            correctOptionIndex = 1,
            sampleAnswerEn = "Sample Pitch:\n'Thank you for this opportunity. I am a Computer Applications graduate with a passion for software solutions. Recently, I led a four-member capstone team building a logistics tracking tool that reduced dispatch delays by 20%. I have honed practical skills in Kotlin and database design, and I am eager to contribute my problem-solving drive to your agile development team.'",
            sampleAnswerHi = "नमूना उत्तर:\n'इस अवसर के लिए धन्यवाद। मैं कंप्यूटर विज्ञान स्नातक हूँ। हाल ही में मैंने एक लॉजिस्टिक्स ट्रैकिंग टूल बनाने वाली चार सदस्यीय टीम का नेतृत्व किया, जिसने देरी को 20% कम किया। मैंने कोटलिन और डेटाबेस में व्यावहारिक अनुभव प्राप्त किया है, और मैं आपकी टीम में अपना योगदान देने के लिए उत्सुक हूँ।'" ,
            keyTipsEn = listOf(
                "Keep your pitch between 60 to 90 seconds.",
                "Highlight 1-2 tangible achievements or projects.",
                "Connect your skill set directly to the position being discussed."
            ),
            keyTipsHi = listOf(
                "उत्तर को 60 से 90 सेकंड के भीतर सीमित रखें।",
                "1-2 ठोस उपलब्धियों का उल्लेख अवश्य करें।"
            )
        ),
        // Workplace Communication
        EnglishExercise(
            id = "eng_work_1",
            moduleType = EnglishModuleType.WORKPLACE,
            titleEn = "Drafting a Sick Leave / Urgent Absence Email",
            titleHi = "बीमारी अथवा आकस्मिक अवकाश हेतु पेशेवर ईमेल",
            contextEn = "Prompt communication with your team lead or manager ensures work continuity without friction.",
            contextHi = "मैनेजर को समय पर सूचना देने से काम में रुकावट नहीं आती और पेशेवर छवि बनी रहती है।",
            promptEn = "What is the essential element that must be included when notifying your team of unplanned leave?",
            promptHi = "अचानक छुट्टी की सूचना देते समय ईमेल में कौन सी जानकारी शामिल करना अनिवार्य है?",
            options = listOf(
                "A doctor's handwritten confidential medical prescription copy only",
                "Absence duration, current status of urgent tasks, and an emergency point of contact",
                "A lengthy complaint about workload stress",
                "No details, simply an empty email with subject 'Sick'"
            ),
            correctOptionIndex = 1,
            sampleAnswerEn = "Subject: Urgent Leave Notification - [Your Name] - [Date]\n\nDear [Manager's Name],\nI am writing to inform you that I am unwell today due to sudden fever and will be unable to attend office.\n\nI have delegated the urgent client query to Priya, and all pending documentation is updated on the shared drive. I will be checking emergency messages periodically if anything critical arises.\n\nThank you for understanding.\nBest regards,\n[Your Name]",
            sampleAnswerHi = "विषय: आकस्मिक अवकाश की सूचना - [आपका नाम] - [तारीख]\n\nआदरणीय [मैनेजर का नाम],\nमैं सूचित करना चाहता हूँ कि तेज बुखार के कारण आज कार्यालय में उपस्थित नहीं हो सकूँगा। मैंने जरूरी क्लाइंट कार्य का दायित्व प्रिया को सौंप दिया है। जरूरी स्थिति में मैं फोन पर उपलब्ध रहूँगा।\n\nसधन्यवाद,\n[आपका नाम]",
            keyTipsEn = listOf(
                "Always notify before or at the start of working hours.",
                "Mention who is covering your immediate responsibilities.",
                "Keep tone professional, polite, and reassuring."
            ),
            keyTipsHi = listOf(
                "कार्य समय शुरू होने से पहले सूचित करना चाहिए।",
                "अपने जरूरी कार्यों की वैकल्पिक व्यवस्था का उल्लेख करें।"
            )
        ),
        // Reading Comprehension
        EnglishExercise(
            id = "eng_read_1",
            moduleType = EnglishModuleType.READING,
            titleEn = "Passage: India's Digital Public Infrastructure (DPI)",
            titleHi = "गद्यांश: भारत का डिजिटल पब्लिक इंफ्रास्ट्रक्चर (DPI)",
            contextEn = "Read the concise passage and answer the inference question.",
            contextHi = "संक्षिप्त गद्यांश पढ़ें और प्रश्न का उत्तर दें।",
            promptEn = "Passage:\n'India's Digital Public Infrastructure, popularly known as the India Stack, combines identity (Aadhaar), payments (UPI), and data governance (DigiLocker and Account Aggregators). Unlike walled proprietary systems, this architecture relies on open APIs, interoperability, and public oversight, allowing hundreds of private startups and public agencies to innovate atop a shared foundation.'\n\nQuestion: According to the passage, what is the key characteristic of India's DPI?",
            promptHi = "गद्यांश के अनुसार, भारत के डीपीआई (DPI) की मुख्य विशेषता क्या है?",
            options = listOf(
                "It is a closed private monopoly accessible to only one company",
                "It is built on open APIs and interoperability that foster widespread innovation",
                "It requires all citizens to visit physical banks daily",
                "It replaces all smartphones with government terminals"
            ),
            correctOptionIndex = 1,
            sampleAnswerEn = "The passage directly highlights: 'Unlike walled proprietary systems, this architecture relies on open APIs, interoperability, and public oversight, allowing hundreds of private startups and public agencies to innovate atop a shared foundation.'",
            sampleAnswerHi = "गद्यांश में स्पष्ट कहा गया है कि यह ओपन एपीआई और इंटरऑपरेबिलिटी पर आधारित है, जिससे विभिन्न संगठन इस पर नवाचार कर सकते हैं।",
            keyTipsEn = listOf(
                "Skim the question first to know what keyword to look for.",
                "Stick strictly to facts stated in the passage; do not assume outside knowledge."
            ),
            keyTipsHi = listOf(
                "गद्यांश में दिए गए तथ्यों के आधार पर ही निष्कर्ष निकालें।"
            )
        )
    ) + QuicklishNonAiContent.exercises + EnglishExpansionContent.exercises + DailyUseSentenceContent.exercises

    fun getExercisesByType(type: EnglishModuleType): List<EnglishExercise> {
        return allExercises.filter { it.moduleType == type }
    }
}
