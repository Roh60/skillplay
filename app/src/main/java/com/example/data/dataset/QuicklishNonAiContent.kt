package com.example.data.dataset

import com.example.data.model.EnglishExercise
import com.example.data.model.EnglishModuleType

/** Original, offline practice content inspired by common language-learning activities. No AI calls. */
object QuicklishNonAiContent {
    val exercises = listOf(
        EnglishExercise(
            id = "think_1", moduleType = EnglishModuleType.THINK_IN_ENGLISH,
            titleEn = "Respond without translating", titleHi = "अनुवाद किए बिना जवाब दें",
            contextEn = "Read the situation and choose the natural English response.", contextHi = "स्थिति पढ़ें और स्वाभाविक अंग्रेज़ी उत्तर चुनें।",
            promptEn = "A colleague says, 'Would you like some tea?' What is a natural response if you do?",
            promptHi = "सहकर्मी पूछता है, 'Would you like some tea?' यदि आपको चाय चाहिए तो स्वाभाविक उत्तर क्या है?",
            options = listOf("Yes, I would like some, please.", "Yes, I am liking tea.", "I like to take tea now only.", "Give tea to me."), correctOptionIndex = 0,
            sampleAnswerEn = "Natural response: 'Yes, I'd love some, thank you.'", sampleAnswerHi = "स्वाभाविक उत्तर: 'Yes, I'd love some, thank you.'",
            keyTipsEn = listOf("Learn complete phrases, not word-for-word translations.", "Listen for the purpose of the question."), keyTipsHi = listOf("शब्द-दर-शब्द अनुवाद के बजाय पूरे वाक्यांश सीखें।")
        ),
        EnglishExercise(
            id = "think_2", moduleType = EnglishModuleType.THINK_IN_ENGLISH,
            titleEn = "Describe what is happening", titleHi = "जो हो रहा है उसका वर्णन करें",
            contextEn = "Think about a scene and choose the sentence that describes it naturally.", contextHi = "दृश्य की कल्पना करें और सही वर्णन चुनें।",
            promptEn = "You see a person running to catch a bus. Which sentence fits?", promptHi = "एक व्यक्ति बस पकड़ने के लिए दौड़ रहा है। कौन-सा वाक्य सही है?",
            options = listOf("He is running to catch the bus.", "He run for catch bus.", "He running catch the bus.", "He is run to bus."), correctOptionIndex = 0,
            sampleAnswerEn = "Use the present continuous for an action happening now: subject + am/is/are + verb-ing.", sampleAnswerHi = "अभी चल रही क्रिया के लिए Present Continuous: subject + am/is/are + verb-ing।",
            keyTipsEn = listOf("Picture the scene before forming a sentence."), keyTipsHi = listOf("वाक्य बनाने से पहले दृश्य की कल्पना करें।")
        ),
        EnglishExercise(
            id = "builder_1", moduleType = EnglishModuleType.SENTENCE_BUILDER,
            titleEn = "Build a simple sentence", titleHi = "सरल वाक्य बनाएं",
            contextEn = "Arrange the idea into a standard English sentence.", contextHi = "विचार को सही अंग्रेज़ी वाक्य में व्यवस्थित करें।",
            promptEn = "Choose the correct order for: every day / practices / Rina / English", promptHi = "सही क्रम चुनें: every day / practices / Rina / English",
            options = listOf("Rina practices English every day.", "Practices Rina every day English.", "English every day Rina practices.", "Every practices Rina English day."), correctOptionIndex = 0,
            sampleAnswerEn = "Pattern: Subject + Verb + Object + Time. 'Rina practices English every day.'", sampleAnswerHi = "पैटर्न: Subject + Verb + Object + Time। 'Rina practices English every day.'",
            keyTipsEn = listOf("Start with who does the action.", "Place the object after the verb in a basic statement."), keyTipsHi = listOf("पहले कर्ता, फिर क्रिया और उसके बाद कर्म रखें।")
        ),
        EnglishExercise(
            id = "builder_2", moduleType = EnglishModuleType.SENTENCE_BUILDER,
            titleEn = "Make a polite request", titleHi = "विनम्र अनुरोध बनाएं",
            contextEn = "Practice a useful phrase for everyday life.", contextHi = "रोज़मर्रा की स्थिति के लिए उपयोगी वाक्यांश सीखें।",
            promptEn = "Which sentence politely asks someone to repeat something?", promptHi = "किसी से बात दोहराने का विनम्र अनुरोध कौन-सा है?",
            options = listOf("Could you please repeat that?", "Repeat it now!", "You repeat again this.", "Why you said?"), correctOptionIndex = 0,
            sampleAnswerEn = "'Could you please repeat that?' is polite and clear.", sampleAnswerHi = "'Could you please repeat that?' विनम्र और स्पष्ट है।",
            keyTipsEn = listOf("Could you please…? is a polite request pattern."), keyTipsHi = listOf("'Could you please…?' विनम्र अनुरोध का पैटर्न है।")
        ),
        EnglishExercise(
            id = "sentence_practice_1", moduleType = EnglishModuleType.SENTENCE_PRACTICE,
            titleEn = "Practice everyday phrases", titleHi = "रोज़मर्रा के वाक्यांशों का अभ्यास",
            contextEn = "Choose the natural phrase for a common situation.", contextHi = "आम स्थिति के लिए स्वाभाविक वाक्यांश चुनें।",
            promptEn = "You did not hear the last sentence. What should you say?", promptHi = "आपको पिछला वाक्य सुनाई नहीं दिया। क्या कहेंगे?",
            options = listOf("Sorry, could you say that again?", "What you speak?", "Say again fast.", "I didn't listened you."), correctOptionIndex = 0,
            sampleAnswerEn = "Say: 'Sorry, could you say that again?'", sampleAnswerHi = "कहें: 'Sorry, could you say that again?'",
            keyTipsEn = listOf("Use a short apology and a polite request."), keyTipsHi = listOf("छोटी माफ़ी और विनम्र अनुरोध का प्रयोग करें।")
        ),
        EnglishExercise(
            id = "sentence_practice_2", moduleType = EnglishModuleType.SENTENCE_PRACTICE,
            titleEn = "Talk about your routine", titleHi = "अपनी दिनचर्या बताएं",
            contextEn = "Use the simple present for habits and routines.", contextHi = "आदतों और दिनचर्या के लिए Simple Present का प्रयोग करें।",
            promptEn = "Choose the correct sentence.", promptHi = "सही वाक्य चुनें।",
            options = listOf("I usually read before bed.", "I usually reading before bed.", "I reads usually before bed.", "I am usually read before bed."), correctOptionIndex = 0,
            sampleAnswerEn = "Use the base verb with I/you/we/they: 'I usually read before bed.'", sampleAnswerHi = "I/you/we/they के साथ verb का base form: 'I usually read before bed.'",
            keyTipsEn = listOf("Use usually, often and every day to talk about routines."), keyTipsHi = listOf("दिनचर्या के लिए usually, often और every day उपयोगी हैं।")
        ),
        EnglishExercise(
            id = "structure_1", moduleType = EnglishModuleType.STRUCTURE,
            titleEn = "Subject, verb and object", titleHi = "कर्ता, क्रिया और कर्म",
            contextEn = "Identify the basic parts of a sentence.", contextHi = "वाक्य के मूल भाग पहचानें।",
            promptEn = "In 'The student completed the task', what is the object?", promptHi = "'The student completed the task' में कर्म क्या है?",
            options = listOf("The student", "completed", "the task", "student completed"), correctOptionIndex = 2,
            sampleAnswerEn = "The student = subject, completed = verb, the task = object.", sampleAnswerHi = "The student = कर्ता, completed = क्रिया, the task = कर्म।",
            keyTipsEn = listOf("Ask 'who?' for the subject and 'what?' after the verb for the object."), keyTipsHi = listOf("कर्ता के लिए 'कौन?' और कर्म के लिए क्रिया के बाद 'क्या?' पूछें।")
        ),
        EnglishExercise(
            id = "structure_2", moduleType = EnglishModuleType.STRUCTURE,
            titleEn = "Choose the correct tense", titleHi = "सही काल चुनें",
            contextEn = "Use time markers to understand sentence structure.", contextHi = "वाक्य संरचना समझने के लिए समय-सूचक शब्द देखें।",
            promptEn = "Complete: 'She ____ to college every weekday.'", promptHi = "पूरा करें: 'She ____ to college every weekday.'",
            options = listOf("go", "goes", "going", "gone"), correctOptionIndex = 1,
            sampleAnswerEn = "With he/she/it in simple present, add -s or -es: 'She goes'.", sampleAnswerHi = "Simple Present में he/she/it के साथ verb में -s/-es: 'She goes'।",
            keyTipsEn = listOf("Every weekday signals a regular habit."), keyTipsHi = listOf("Every weekday नियमित आदत बताता है।")
        ),
        EnglishExercise(
            id = "paragraph_1", moduleType = EnglishModuleType.PARAGRAPHS,
            titleEn = "Organize a short paragraph", titleHi = "छोटा पैराग्राफ व्यवस्थित करें",
            contextEn = "A clear paragraph has a main idea, supporting details and a closing sentence.", contextHi = "अच्छे पैराग्राफ में मुख्य विचार, सहायक विवरण और समापन वाक्य होता है।",
            promptEn = "Which sentence is the best topic sentence for a paragraph about daily reading?", promptHi = "रोज़ पढ़ने पर पैराग्राफ के लिए सबसे अच्छा मुख्य वाक्य कौन-सा है?",
            options = listOf("Reading for a few minutes each day can build useful habits.", "Yesterday was Tuesday.", "My shoes are blue.", "The bus stopped near a shop."), correctOptionIndex = 0,
            sampleAnswerEn = "Start with the main idea, add two reasons or examples, then conclude with the benefit.", sampleAnswerHi = "मुख्य विचार से शुरू करें, दो कारण या उदाहरण दें और अंत में लाभ बताएं।",
            keyTipsEn = listOf("Keep one main idea per paragraph.", "Use linking words such as first, also, because and finally."), keyTipsHi = listOf("एक पैराग्राफ में एक मुख्य विचार रखें।")
        ),
        EnglishExercise(
            id = "paragraph_2", moduleType = EnglishModuleType.PARAGRAPHS,
            titleEn = "Choose a linking word", titleHi = "संबंध जोड़ने वाला शब्द चुनें",
            contextEn = "Linking words make writing easier to follow.", contextHi = "Linking words लेखन को स्पष्ट बनाते हैं।",
            promptEn = "Choose the best word: 'I was tired; ____, I finished my assignment.'", promptHi = "सही शब्द चुनें: 'I was tired; ____, I finished my assignment.'",
            options = listOf("however", "because of", "unless", "during"), correctOptionIndex = 0,
            sampleAnswerEn = "'However' shows contrast between being tired and finishing the assignment.", sampleAnswerHi = "'However' थकान और काम पूरा करने के बीच विरोध दिखाता है।",
            keyTipsEn = listOf("Use however for contrast and therefore for a result."), keyTipsHi = listOf("विरोध के लिए however और परिणाम के लिए therefore उपयोग करें।")
        ),
        EnglishExercise(
            id = "ielts_1", moduleType = EnglishModuleType.IELTS,
            titleEn = "IELTS-style speaking: a useful skill", titleHi = "IELTS शैली: उपयोगी कौशल",
            contextEn = "This is offline practice with a sample framework, not an official IELTS score.", contextHi = "यह ऑफलाइन अभ्यास है; यह आधिकारिक IELTS स्कोर नहीं देता।",
            promptEn = "Prompt: Describe a skill you would like to learn. What is a good way to organize your 1–2 minute answer?", promptHi = "प्रश्न: ऐसी skill बताएं जिसे आप सीखना चाहते हैं। 1–2 मिनट का उत्तर कैसे व्यवस्थित करें?",
            options = listOf("Name the skill, explain why, describe how you will learn it, and share the expected benefit.", "List unrelated facts without an example.", "Repeat the same sentence for two minutes.", "Only say the skill name."), correctOptionIndex = 0,
            sampleAnswerEn = "Framework: 1) name the skill, 2) why it matters, 3) your learning plan, 4) how it may help you.", sampleAnswerHi = "ढांचा: 1) skill का नाम, 2) क्यों जरूरी है, 3) सीखने की योजना, 4) इससे होने वाला लाभ।",
            keyTipsEn = listOf("Practice speaking aloud with a timer; this app does not grade pronunciation."), keyTipsHi = listOf("टाइमर लगाकर बोलने का अभ्यास करें; ऐप उच्चारण का स्कोर नहीं देता।")
        ),
        EnglishExercise(
            id = "ielts_2", moduleType = EnglishModuleType.IELTS,
            titleEn = "IELTS-style follow-up: give reasons", titleHi = "IELTS शैली: कारण बताएं",
            contextEn = "Build a supported opinion instead of giving a one-word answer.", contextHi = "एक शब्द में जवाब देने के बजाय कारण सहित राय दें।",
            promptEn = "Question: Is it better to study alone or with a group? Which answer is best developed?", promptHi = "प्रश्न: अकेले पढ़ना बेहतर है या समूह में? किस उत्तर में अच्छा विस्तार है?",
            options = listOf("It depends on the task. I prefer a group for discussion because others can share different approaches, but I study alone when I need concentration.", "Group.", "I don't know and that's all.", "Study is study."), correctOptionIndex = 0,
            sampleAnswerEn = "Give an opinion, a reason and a specific example; mention a contrast when useful.", sampleAnswerHi = "राय, कारण और उदाहरण दें; जरूरत हो तो अंतर भी बताएं।",
            keyTipsEn = listOf("Use because, for example, however and in my experience."), keyTipsHi = listOf("because, for example, however और in my experience उपयोग करें।")
        )
    )
}
