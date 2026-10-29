package com.example.data.dataset

import com.example.data.model.Difficulty
import com.example.data.model.QuizQuestion

object QuestionBank {
    val allQuestions: List<QuizQuestion> = listOf(
        // General Knowledge
        QuizQuestion(
            id = "gk_1",
            categoryId = "gk",
            difficulty = Difficulty.EASY,
            questionEn = "Which national symbol of India features the motto 'Satyameva Jayate' inscribed below it?",
            questionHi = "भारत के किस राष्ट्रीय प्रतीक के नीचे 'सत्यमेव जयते' आदर्श वाक्य अंकित है?",
            optionsEn = listOf("National Flag", "State Emblem of India (Lion Capital)", "National Seal", "Supreme Court Crest"),
            optionsHi = listOf("राष्ट्रीय ध्वज", "भारत का राजचिह्न (अशोक स्तंभ सिंह शीर्ष)", "राष्ट्रीय मुहर", "सर्वोच्च न्यायालय का प्रतीक"),
            correctIndex = 1,
            explanationEn = "The State Emblem of India is an adaptation of the Lion Capital of Ashoka at Sarnath. 'Satyameva Jayate' (Truth Alone Triumphs) is taken from the Mundaka Upanishad.",
            explanationHi = "भारत का राजचिह्न सारनाथ स्थित अशोक के सिंह स्तम्भ की अनुकृति है। इसके नीचे मुण्डकोपनिषद से लिया गया सूत्र वाक्य 'सत्यमेव जयते' देवनागरी लिपि में अंकित है।",
            sourceUrl = "https://knowindia.india.gov.in/national-identity-elements/state-emblem.php",
            isVerified = true
        ),
        QuizQuestion(
            id = "gk_2",
            categoryId = "gk",
            difficulty = Difficulty.MEDIUM,
            questionEn = "In which year did Rabindranath Tagore become the first Asian to win the Nobel Prize in Literature?",
            questionHi = "रवींद्रनाथ टैगोर किस वर्ष साहित्य का नोबेल पुरस्कार जीतने वाले पहले एशियाई बने?",
            optionsEn = listOf("1911", "1913", "1919", "1923"),
            optionsHi = listOf("1911", "1913", "1919", "1923"),
            correctIndex = 1,
            explanationEn = "Rabindranath Tagore was awarded the Nobel Prize in Literature in 1913 for 'Gitanjali', becoming the first non-European and first Asian laureate.",
            explanationHi = "रवींद्रनाथ टैगोर को 1913 में उनके काव्य संग्रह 'गीतांजलि' के लिए साहित्य का नोबेल पुरस्कार प्रदान किया गया था।",
            sourceUrl = "https://www.nobelprize.org/prizes/literature/1913/tagore/facts/",
            isVerified = true
        ),
        // Indian History
        QuizQuestion(
            id = "hist_1",
            categoryId = "history",
            difficulty = Difficulty.EASY,
            questionEn = "From which historic place did Mahatma Gandhi begin the Salt Satyagraha (Dandi March) in 1930?",
            questionHi = "महात्मा गांधी ने 1930 में नमक सत्याग्रह (दांडी मार्च) किस ऐतिहासिक स्थान से प्रारंभ किया था?",
            optionsEn = listOf("Sevagram Ashram", "Sabarmati Ashram", "Champaran", "Bardoli"),
            optionsHi = listOf("सेवाग्राम आश्रम", "साबरमती आश्रम", "चंपारण", "बारडोली"),
            correctIndex = 1,
            explanationEn = "Gandhiji started the 240-mile march from Sabarmati Ashram in Ahmedabad on 12 March 1930 and reached Dandi on 5 April 1930 to break the salt law.",
            explanationHi = "गांधीजी ने 12 मार्च 1930 को अहमदाबाद के साबरमती आश्रम से 78 अनुयायियों के साथ 240 मील की यात्रा शुरू की और 6 अप्रैल को दांडी में नमक कानून तोड़ा।",
            sourceUrl = "https://www.gandhiheritageportal.org/the-dandi-march",
            isVerified = true
        ),
        QuizQuestion(
            id = "hist_2",
            categoryId = "history",
            difficulty = Difficulty.MEDIUM,
            questionEn = "Who was the founder of the Maurya Empire with the guidance of Acharya Chanakya?",
            questionHi = "आचार्य चाणक्य के मार्गदर्शन में मौर्य साम्राज्य की स्थापना किसने की थी?",
            optionsEn = listOf("Ashoka the Great", "Chandragupta Maurya", "Bindusara", "Samudragupta"),
            optionsHi = listOf("सम्राट अशोक", "चन्द्रगुप्त मौर्य", "बिन्दुसार", "समुद्रगुप्त"),
            correctIndex = 1,
            explanationEn = "Chandragupta Maurya founded the Maurya Empire around 322 BCE after overthrowing the Nanda dynasty with strategic counsel from Chanakya (Kautilya).",
            explanationHi = "चन्द्रगुप्त मौर्य ने लगभग 322 ईसा पूर्व में चाणक्य (कौटिल्य) के मार्गदर्शन में नंद वंश को समाप्त कर मौर्य साम्राज्य की स्थापना की।",
            sourceUrl = "https://ncert.nic.in/textbook/pdf/fess106.pdf",
            isVerified = true
        ),
        // Indian Geography
        QuizQuestion(
            id = "geo_1",
            categoryId = "geography",
            difficulty = Difficulty.EASY,
            questionEn = "Which is the highest mountain peak situated entirely within India?",
            questionHi = "भारत में पूर्णतः स्थित सबसे ऊंची पर्वत चोटी कौन सी है?",
            optionsEn = listOf("Kanchenjunga", "Nanda Devi", "Kamet", "Anamudi"),
            optionsHi = listOf("कंचनजंगा", "नंदा देवी", "कामेत", "अनामुडी"),
            correctIndex = 0,
            explanationEn = "Kangchenjunga (8,586 m) on the India-Nepal border in Sikkim is the highest peak in India. (Nanda Devi at 7,816 m is the highest peak entirely enclosed within Indian territory boundaries).",
            explanationHi = "सिक्किम में स्थित कंचनजंगा (8,586 मीटर) भारत की सर्वोच्च पर्वत चोटी है।",
            sourceUrl = "https://knowindia.india.gov.in/profile/physical-features.php",
            isVerified = true
        ),
        QuizQuestion(
            id = "geo_2",
            categoryId = "geography",
            difficulty = Difficulty.MEDIUM,
            questionEn = "Which Indian river originates at Trimbakeshwar near Nashik and flows eastward into the Bay of Bengal?",
            questionHi = "कौन सी भारतीय नदी नासिक के निकट त्र्यंबकेश्वर से निकलती है और पूर्व दिशा में बहते हुए बंगाल की खाड़ी में गिरती है?",
            optionsEn = listOf("Krishna", "Godavari", "Cauvery", "Narmada"),
            optionsHi = listOf("कृष्णा", "गोदावरी", "कावेरी", "नर्मदा"),
            correctIndex = 1,
            explanationEn = "The Godavari is India's second-longest river (approx. 1,465 km) often referred to as Dakshin Ganga. It rises in Maharashtra at Trimbakeshwar.",
            explanationHi = "गोदावरी नदी महाराष्ट्र के नासिक जिले के त्र्यंबकेश्वर से निकलती है। इसे दक्षिण गंगा के नाम से भी जाना जाता है।",
            sourceUrl = "https://cwc.gov.in/godavari-basin",
            isVerified = true
        ),
        // Indian Polity
        QuizQuestion(
            id = "polity_1",
            categoryId = "polity",
            difficulty = Difficulty.EASY,
            questionEn = "Under which Article of the Indian Constitution is the 'Right to Equality' guaranteed?",
            questionHi = "भारतीय संविधान के किस अनुच्छेद समूह के तहत 'समानता का अधिकार' प्रदान किया गया है?",
            optionsEn = listOf("Articles 14 to 18", "Articles 19 to 22", "Articles 25 to 28", "Articles 29 to 30"),
            optionsHi = listOf("अनुच्छेद 14 से 18", "अनुच्छेद 19 से 22", "अनुच्छेद 25 से 28", "अनुच्छेद 29 से 30"),
            correctIndex = 0,
            explanationEn = "Articles 14 to 18 of Part III of the Constitution guarantee equality before law, prohibition of discrimination, and equality of opportunity.",
            explanationHi = "संविधान के भाग III के अनुच्छेद 14 से 18 में कानून के समक्ष समानता, भेदभाव के निषेध और अवसर की समानता का अधिकार दिया गया है।",
            sourceUrl = "https://legislative.gov.in/constitution-of-india/",
            isVerified = true
        ),
        QuizQuestion(
            id = "polity_2",
            categoryId = "polity",
            difficulty = Difficulty.MEDIUM,
            questionEn = "What is the minimum age required for an Indian citizen to be eligible for election as a Member of the Lok Sabha?",
            questionHi = "लोकसभा सदस्य के चुनाव के लिए किसी भारतीय नागरिक की न्यूनतम आयु कितनी होनी चाहिए?",
            optionsEn = listOf("18 years", "21 years", "25 years", "30 years"),
            optionsHi = listOf("18 वर्ष", "21 वर्ष", "25 वर्ष", "30 वर्ष"),
            correctIndex = 2,
            explanationEn = "According to Article 84(b) of the Constitution of India, a person must be at least 25 years old to be a member of the Lok Sabha.",
            explanationHi = "भारतीय संविधान के अनुच्छेद 84(ख) के अनुसार, लोकसभा सदस्य बनने के लिए न्यूनतम आयु 25 वर्ष और राज्यसभा के लिए 30 वर्ष निर्धारित है।",
            sourceUrl = "https://sansad.in/ls",
            isVerified = true
        ),
        // General Science
        QuizQuestion(
            id = "sci_1",
            categoryId = "science",
            difficulty = Difficulty.EASY,
            questionEn = "Which component of human blood is primarily responsible for transporting oxygen to cells?",
            questionHi = "मानव रक्त का कौन सा घटक कोशिकाओं तक ऑक्सीजन पहुंचाने के लिए मुख्य रूप से उत्तरदायी है?",
            optionsEn = listOf("White Blood Cells (Leukocytes)", "Platelets (Thrombocytes)", "Red Blood Cells (Hemoglobin)", "Blood Plasma"),
            optionsHi = listOf("श्वेत रक्त कोशिकाएं", "प्लेटलेट्स", "लाल रक्त कोशिकाएं (हीमोग्लोबिन)", "रक्त प्लाज्मा"),
            correctIndex = 2,
            explanationEn = "Hemoglobin inside Red Blood Cells (Erythrocytes) binds reversible oxygen molecules in the lungs and delivers them to bodily tissues.",
            explanationHi = "लाल रक्त कणिकाओं (RBC) में उपस्थित हीमोग्लोबिन फेफड़ों से ऑक्सीजन को बांधकर शरीर के सभी ऊतकों और कोशिकाओं तक पहुंचाता है।",
            sourceUrl = "https://ncert.nic.in/textbook/pdf/jesc110.pdf",
            isVerified = true
        ),
        QuizQuestion(
            id = "sci_2",
            categoryId = "science",
            difficulty = Difficulty.MEDIUM,
            questionEn = "Which principle explains why an iron ship floats on water while a solid iron needle sinks?",
            questionHi = "किस सिद्धांत से यह स्पष्ट होता है कि लोहे का विशाल जहाज पानी में तैरता है जबकि लोहे की छोटी कील डूब जाती है?",
            optionsEn = listOf("Pascal's Principle", "Archimedes' Principle of Buoyancy", "Bernoulli's Principle", "Newton's First Law"),
            optionsHi = listOf("पास्कल का नियम", "आर्किमिडीज का उत्प्लावन सिद्धांत", "बरनौली का सिद्धांत", "न्यूटन का पहला नियम"),
            correctIndex = 1,
            explanationEn = "Archimedes' principle states that buoyant force equals the weight of fluid displaced. A hollowed ship displaces water weight equal to its own weight.",
            explanationHi = "आर्किमिडीज के सिद्धांत के अनुसार, किसी तरल में डूबी वस्तु पर लगने वाला उत्प्लावन बल उसके द्वारा विस्थापित द्रव के भार के बराबर होता है।",
            sourceUrl = "https://ncert.nic.in/textbook/pdf/iesc110.pdf",
            isVerified = true
        ),
        // Mathematics
        QuizQuestion(
            id = "math_1",
            categoryId = "math",
            difficulty = Difficulty.EASY,
            questionEn = "If an article bought for ₹400 is sold for ₹500, what is the profit percentage?",
            questionHi = "यदि ₹400 में खरीदी गई वस्तु को ₹500 में बेचा जाता है, तो लाभ प्रतिशत क्या होगा?",
            optionsEn = listOf("20%", "25%", "30%", "15%"),
            optionsHi = listOf("20%", "25%", "30%", "15%"),
            correctIndex = 1,
            explanationEn = "Profit = Selling Price - Cost Price = ₹500 - ₹400 = ₹100. Profit % = (Profit / Cost Price) * 100 = (100 / 400) * 100 = 25%.",
            explanationHi = "लाभ = विक्रय मूल्य - क्रय मूल्य = 500 - 400 = ₹100। लाभ % = (लाभ / क्रय मूल्य) × 100 = (100 / 400) × 100 = 25%।",
            sourceUrl = "https://ncert.nic.in/textbook/pdf/gemp108.pdf",
            isVerified = true
        ),
        QuizQuestion(
            id = "math_2",
            categoryId = "math",
            difficulty = Difficulty.MEDIUM,
            questionEn = "A train travelling at 72 km/h crosses a pole in 10 seconds. What is the length of the train?",
            questionHi = "72 किमी/घंटा की गति से चल रही एक रेलगाड़ी एक खंभे को 10 सेकंड में पार करती है। रेलगाड़ी की लंबाई क्या है?",
            optionsEn = listOf("150 meters", "200 meters", "220 meters", "180 meters"),
            optionsHi = listOf("150 मीटर", "200 मीटर", "220 मीटर", "180 मीटर"),
            correctIndex = 1,
            explanationEn = "Speed in m/s = 72 * (5/18) = 20 m/s. Distance (train length) = Speed * Time = 20 m/s * 10 s = 200 meters.",
            explanationHi = "गति (मीटर/सेकंड) = 72 × (5/18) = 20 मी/से। दूरी = गति × समय = 20 × 10 = 200 मीटर।",
            sourceUrl = "https://ncert.nic.in/textbook/pdf/hemh113.pdf",
            isVerified = true
        ),
        // Logical Reasoning
        QuizQuestion(
            id = "reas_1",
            categoryId = "reasoning",
            difficulty = Difficulty.EASY,
            questionEn = "Find the missing number in the sequence: 3, 7, 15, 31, 63, ?",
            questionHi = "इस श्रृंखला में लुप्त संख्या ज्ञात करें: 3, 7, 15, 31, 63, ?",
            optionsEn = listOf("95", "127", "118", "125"),
            optionsHi = listOf("95", "127", "118", "125"),
            correctIndex = 1,
            explanationEn = "Pattern: Each term is obtained by (Previous * 2) + 1. Here, 63 * 2 + 1 = 126 + 1 = 127.",
            explanationHi = "पैटर्न: प्रत्येक पद (पिछला × 2) + 1 है। इसलिए 63 × 2 + 1 = 126 + 1 = 127।",
            sourceUrl = "https://ncert.nic.in/exemplar-problems.php",
            isVerified = true
        ),
        QuizQuestion(
            id = "reas_2",
            categoryId = "reasoning",
            difficulty = Difficulty.MEDIUM,
            questionEn = "If 'CLOCK' is coded as 'DMPDL', how will 'WATCH' be coded in the same pattern?",
            questionHi = "यदि 'CLOCK' को 'DMPDL' लिखा जाता है, तो उसी नियम से 'WATCH' को क्या लिखा जाएगा?",
            optionsEn = listOf("XBUDI", "XBUEI", "XAVDI", "WCUDI"),
            optionsHi = listOf("XBUDI", "XBUEI", "XAVDI", "WCUDI"),
            correctIndex = 0,
            explanationEn = "Each letter is shifted forward by 1 (+1): W->X, A->B, T->U, C->D, H->I => XBUDI.",
            explanationHi = "प्रत्येक अक्षर में 1 जोड़कर (+1) कोड बनाया गया है: W→X, A→B, T→U, C→D, H→I अर्थात XBUDI।",
            sourceUrl = "https://ncert.nic.in",
            isVerified = true
        ),
        // English Vocabulary
        QuizQuestion(
            id = "vocab_1",
            categoryId = "vocabulary",
            difficulty = Difficulty.EASY,
            questionEn = "Which word is the closest SYNONYM for 'DILIGENT'?",
            questionHi = "'DILIGENT' (परिश्रमी) शब्द का सबसे सटीक पर्यायवाची (समानार्थी) क्या है?",
            optionsEn = listOf("Careless", "Hardworking", "Passive", "Impatient"),
            optionsHi = listOf("लापरवाह", "मेहनती (Hardworking)", "निष्क्रिय", "अधीर"),
            correctIndex = 1,
            explanationEn = "'Diligent' means showing steady, energetic, and painstaking effort in one's work. Synonym: Hardworking, industrious.",
            explanationHi = "'Diligent' का अर्थ निरंतर लगन और मेहनत से काम करने वाला होता है। इसका सटीक पर्यायवाची Hardworking है।",
            sourceUrl = "https://dictionary.cambridge.org/dictionary/english/diligent",
            isVerified = true
        ),
        QuizQuestion(
            id = "vocab_2",
            categoryId = "vocabulary",
            difficulty = Difficulty.MEDIUM,
            questionEn = "What is the meaning of the common idiom 'Bite the bullet'?",
            questionHi = "अंग्रेजी मुहावरे 'Bite the bullet' का सही अर्थ क्या है?",
            optionsEn = listOf("To eat quickly", "To face a painful situation with courage", "To argue violently", "To avoid responsibilities"),
            optionsHi = listOf("जल्दी खाना", "कठिन या अप्रिय स्थिति का साहसपूर्वक सामना करना", "झगड़ा करना", "जिम्मेदारी से बचना"),
            correctIndex = 1,
            explanationEn = "'To bite the bullet' means to decide to do something difficult or unpleasant that one has been putting off or hesitating over.",
            explanationHi = "'Bite the bullet' का अर्थ किसी कठिन या अप्रिय परिस्थिति का हिम्मत से मुकाबला करना है।",
            sourceUrl = "https://dictionary.cambridge.org/dictionary/english/bite-the-bullet",
            isVerified = true
        ),
        // English Grammar
        QuizQuestion(
            id = "gram_1",
            categoryId = "grammar",
            difficulty = Difficulty.EASY,
            questionEn = "Select the grammatically correct sentence:",
            questionHi = "व्याकरणिक दृष्टि से सही वाक्य का चयन करें:",
            optionsEn = listOf(
                "Neither of the two candidates have arrived.",
                "Neither of the two candidates has arrived.",
                "Neither candidate were ready.",
                "Neither of them are present."
            ),
            optionsHi = listOf(
                "Neither of the two candidates have arrived.",
                "Neither of the two candidates has arrived.",
                "Neither candidate were ready.",
                "Neither of them are present."
            ),
            correctIndex = 1,
            explanationEn = "'Neither' when used as a pronoun takes a singular verb: 'Neither of the candidates has arrived'.",
            explanationHi = "'Neither of' के साथ एकवचन (singular) क्रिया 'has' का प्रयोग होता है, 'have' का नहीं।",
            sourceUrl = "https://dictionary.cambridge.org/grammar/british-grammar/neither-neither-nor-and-not-either",
            isVerified = true
        ),
        // Interview Preparation
        QuizQuestion(
            id = "interview_1",
            categoryId = "interview",
            difficulty = Difficulty.EASY,
            questionEn = "In behavioral interviews, what does the acclaimed 'STAR' answering technique stand for?",
            questionHi = "साक्षात्कार में व्यवहार संबंधी प्रश्नों के लिए 'STAR' तकनीक का पूर्ण रूप क्या है?",
            optionsEn = listOf(
                "Skill, Talent, Attitude, Result",
                "Situation, Task, Action, Result",
                "Start, Talk, Answer, Review",
                "Story, Topic, Analysis, Recommendation"
            ),
            optionsHi = listOf(
                "स्किल, टैलेंट, एटीट्यूड, रिजल्ट",
                "सिचुएशन (Situation), टास्क (Task), एक्शन (Action), रिजल्ट (Result)",
                "स्टार्ट, टॉक, आंसर, रिव्यू",
                "स्टोरी, टॉपिक, एनालिसिस, रिकमेंडेशन"
            ),
            correctIndex = 1,
            explanationEn = "STAR stands for Situation (context), Task (challenge/goal), Action (steps taken by you), Result (quantified outcome achieved).",
            explanationHi = "STAR विधि का उपयोग अनुभव बताने के लिए किया जाता है: Situation (परिस्थिति), Task (कार्य/चुनौती), Action (आपके द्वारा उठाया गया कदम), Result (परिणाम)।",
            sourceUrl = "https://www.nationalcareers.service.gov.uk/careers-advice/interview-advice/the-star-method",
            isVerified = true
        ),
        // Workplace Communication
        QuizQuestion(
            id = "work_1",
            categoryId = "workplace",
            difficulty = Difficulty.EASY,
            questionEn = "When addressing a formal professional email to a client or team manager, which subject line is most effective?",
            questionHi = "किसी ग्राहक या प्रबंधक को औपचारिक ईमेल भेजते समय कौन सी विषय रेखा (Subject line) सबसे उपयुक्त है?",
            optionsEn = listOf(
                "Urgent please open ASAP!!!",
                "Project Alpha: Weekly Sprint Status Report - Oct 25",
                "Hey quick question",
                "Important work details attached"
            ),
            optionsHi = listOf(
                "Urgent please open ASAP!!!",
                "Project Alpha: Weekly Sprint Status Report - Oct 25",
                "Hey quick question",
                "Important work details attached"
            ),
            correctIndex = 1,
            explanationEn = "A professional subject line should be specific, concise, searchable, and clearly define the topic and date.",
            explanationHi = "पेशेवर ईमेल की विषय पंक्ति स्पष्ट, संक्षिप्त और संदर्भ युक्त होनी चाहिए ताकि प्राप्तकर्ता तुरंत समझ सके कि ईमेल किस विषय पर है।",
            sourceUrl = "https://hbr.org/2016/11/how-to-write-an-email-with-military-precision",
            isVerified = true
        ),
        // Computer Basics
        QuizQuestion(
            id = "comp_1",
            categoryId = "computer",
            difficulty = Difficulty.EASY,
            questionEn = "Which standard keyboard shortcut is universally used in text editors and operating systems to 'Redo' an undone action?",
            questionHi = "टेक्स्ट एडिटर्स और कंप्यूटर में पूर्ववत (Undo) की गई क्रिया को दोबारा करने (Redo) के लिए किस शॉर्टकट का उपयोग होता है?",
            optionsEn = listOf("Ctrl + Z", "Ctrl + Y", "Ctrl + R", "Ctrl + Shift + X"),
            optionsHi = listOf("Ctrl + Z", "Ctrl + Y", "Ctrl + R", "Ctrl + Shift + X"),
            correctIndex = 1,
            explanationEn = "Ctrl + Z undoes the last action, while Ctrl + Y (or Ctrl + Shift + Z on some platforms) redoes it.",
            explanationHi = "Ctrl + Z अंतिम कार्य को पूर्ववत (Undo) करता है, जबकि Ctrl + Y उसे पुनः (Redo) लागू करता है।",
            sourceUrl = "https://support.microsoft.com/en-us/windows/keyboard-shortcuts-in-windows",
            isVerified = true
        ),
        // Digital Safety
        QuizQuestion(
            id = "safe_1",
            categoryId = "digital_safety",
            difficulty = Difficulty.EASY,
            questionEn = "If you receive money via UPI or QR code, do you need to enter your UPI PIN on your phone?",
            questionHi = "यदि कोई व्यक्ति आपको UPI या QR कोड के माध्यम से पैसे भेजता है, तो क्या आपको अपना UPI PIN दर्ज करने की आवश्यकता होती है?",
            optionsEn = listOf(
                "Yes, UPI PIN is mandatory for both sending and receiving",
                "No, UPI PIN is strictly required ONLY when sending/deducting money",
                "Yes, but only if the amount exceeds ₹1,000",
                "Yes, to verify your bank account identity"
            ),
            optionsHi = listOf(
                "हाँ, भेजने और प्राप्त करने दोनों के लिए UPI PIN अनिवार्य है",
                "नहीं, UPI PIN केवल पैसे भेजने (खाते से कटने) पर ही दर्ज किया जाता है",
                "हाँ, पर केवल ₹1,000 से अधिक राशि पर",
                "हाँ, बैंक खाते की पहचान सत्यापित करने के लिए"
            ),
            correctIndex = 1,
            explanationEn = "A golden rule of UPI: You NEVER need to enter your UPI PIN to receive money. Entering your PIN always authorizes money to leave your account.",
            explanationHi = "UPI का स्वर्णिम नियम: पैसे प्राप्त करने के लिए कभी भी UPI PIN दर्ज नहीं किया जाता। पिन दर्ज करने का अर्थ है आपके खाते से पैसे कटना।",
            sourceUrl = "https://www.npci.org.in/what-we-do/upi/safety-guidelines",
            isVerified = true
        ),
        // Financial Literacy
        QuizQuestion(
            id = "fin_1",
            categoryId = "finance",
            difficulty = Difficulty.EASY,
            questionEn = "What is the primary purpose of maintaining an 'Emergency Fund'?",
            questionHi = "'आपातकालीन निधि' (Emergency Fund) रखने का मुख्य उद्देश्य क्या होता है?",
            optionsEn = listOf(
                "To speculate in high-risk stock day trading",
                "To cover 3 to 6 months of essential living expenses during unforeseen job loss or medical crises",
                "To purchase luxury gadgets during festival sales",
                "To avoid paying taxes completely"
            ),
            optionsHi = listOf(
                "शेयर बाजार में सट्टा लगाने के लिए",
                "नौकरी छूटने या चिकित्सा संकट के समय 3 से 6 महीने के बुनियादी खर्चों को वहन करने के लिए",
                "त्योहारों में महंगे सामान खरीदने के लिए",
                "टैक्स से पूरी तरह बचने के लिए"
            ),
            correctIndex = 1,
            explanationEn = "Financial advisors recommend keeping 3 to 6 months of essential living expenses in liquid, low-risk accounts to handle unexpected life events without debt.",
            explanationHi = "आपातकालीन निधि का अर्थ 3 से 6 महीने के आवश्यक खर्चों की बचत है, जो अचानक नौकरी जाने या बीमारी जैसी अनपेक्षित स्थिति में काम आती है।",
            sourceUrl = "https://rbi.org.in/FinancialEducation/",
            isVerified = true
        )
,
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_constitution_1950",
            categoryId = "polity",
            difficulty = Difficulty.EASY,
            questionEn = "When did the Constitution of India come into effect?",
            questionHi = "भारत का संविधान कब लागू हुआ था?",
            optionsEn = listOf("15 August 1947", "26 January 1950", "26 November 1949", "2 October 1950"),
            optionsHi = listOf("15 अगस्त 1947", "26 जनवरी 1950", "26 नवंबर 1949", "2 अक्टूबर 1950"),
            correctIndex = 1,
            explanationEn = "The Constitution of India came into effect on 26 January 1950, celebrated as Republic Day.",
            explanationHi = "भारत का संविधान 26 जनवरी 1950 को लागू हुआ। इसी दिन गणतंत्र दिवस मनाया जाता है।",
            sourceUrl = "https://knowindia.india.gov.in/profile/constitution.php",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_jupiter_largest",
            categoryId = "science",
            difficulty = Difficulty.EASY,
            questionEn = "Which is the largest planet in the Solar System?",
            questionHi = "सौरमंडल का सबसे बड़ा ग्रह कौन-सा है?",
            optionsEn = listOf("Saturn", "Earth", "Jupiter", "Mars"),
            optionsHi = listOf("शनि", "पृथ्वी", "बृहस्पति", "मंगल"),
            correctIndex = 2,
            explanationEn = "Jupiter is the largest planet in the Solar System.",
            explanationHi = "बृहस्पति सौरमंडल का सबसे बड़ा ग्रह है।",
            sourceUrl = "https://science.nasa.gov/jupiter/",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_rajasthan_area",
            categoryId = "geography",
            difficulty = Difficulty.EASY,
            questionEn = "Which Indian state is the largest by area?",
            questionHi = "क्षेत्रफल के आधार पर भारत का सबसे बड़ा राज्य कौन-सा है?",
            optionsEn = listOf("Madhya Pradesh", "Maharashtra", "Uttar Pradesh", "Rajasthan"),
            optionsHi = listOf("मध्य प्रदेश", "महाराष्ट्र", "उत्तर प्रदेश", "राजस्थान"),
            correctIndex = 3,
            explanationEn = "Rajasthan is the largest Indian state by area.",
            explanationHi = "राजस्थान क्षेत्रफल के आधार पर भारत का सबसे बड़ा राज्य है।",
            sourceUrl = "https://knowindia.india.gov.in/states-uts/",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_photosynthesis_co2",
            categoryId = "science",
            difficulty = Difficulty.EASY,
            questionEn = "Which gas do plants mainly absorb from the air during photosynthesis?",
            questionHi = "प्रकाश संश्लेषण के दौरान पौधे हवा से मुख्य रूप से कौन-सी गैस ग्रहण करते हैं?",
            optionsEn = listOf("Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen"),
            optionsHi = listOf("ऑक्सीजन", "कार्बन डाइऑक्साइड", "नाइट्रोजन", "हाइड्रोजन"),
            correctIndex = 1,
            explanationEn = "Plants use carbon dioxide and water to make sugars during photosynthesis, releasing oxygen.",
            explanationHi = "प्रकाश संश्लेषण में पौधे कार्बन डाइऑक्साइड और पानी से भोजन बनाते हैं तथा ऑक्सीजन छोड़ते हैं।",
            sourceUrl = "https://www.britannica.com/science/photosynthesis",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_national_animal_tiger",
            categoryId = "gk",
            difficulty = Difficulty.EASY,
            questionEn = "What is the national animal of India?",
            questionHi = "भारत का राष्ट्रीय पशु कौन-सा है?",
            optionsEn = listOf("Lion", "Bengal tiger", "Elephant", "Leopard"),
            optionsHi = listOf("सिंह", "बंगाल टाइगर", "हाथी", "तेंदुआ"),
            correctIndex = 1,
            explanationEn = "The Bengal tiger is the national animal of India.",
            explanationHi = "बंगाल टाइगर भारत का राष्ट्रीय पशु है।",
            sourceUrl = "https://knowindia.india.gov.in/national-identity-elements/national-animal.php",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_ganga_source",
            categoryId = "geography",
            difficulty = Difficulty.EASY,
            questionEn = "The Ganga River begins in which Himalayan glacier region?",
            questionHi = "गंगा नदी का उद्गम हिमालय के किस हिमनद क्षेत्र से जुड़ा है?",
            optionsEn = listOf("Siachen Glacier", "Gangotri Glacier", "Zemu Glacier", "Pindari Glacier"),
            optionsHi = listOf("सियाचिन हिमनद", "गंगोत्री हिमनद", "जेमू हिमनद", "पिंडारी हिमनद"),
            correctIndex = 1,
            explanationEn = "The headstream Bhagirathi rises at Gaumukh near the Gangotri Glacier.",
            explanationHi = "गंगा की प्रमुख उद्गम धारा भागीरथी, गंगोत्री हिमनद के पास गौमुख से निकलती है।",
            sourceUrl = "https://www.britannica.com/place/Ganges-River",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_fundamental_rights",
            categoryId = "polity",
            difficulty = Difficulty.EASY,
            questionEn = "Which of these is a category of Fundamental Rights in the Indian Constitution?",
            questionHi = "भारतीय संविधान में इनमें से कौन-सी मौलिक अधिकारों की श्रेणी है?",
            optionsEn = listOf("Right to Equality", "Right to Property as a Fundamental Right", "Right to Unlimited Private Arms", "Right to Avoid All Taxes"),
            optionsHi = listOf("समानता का अधिकार", "संपत्ति का मौलिक अधिकार", "असीमित निजी हथियार रखने का अधिकार", "सभी करों से बचने का अधिकार"),
            correctIndex = 0,
            explanationEn = "The Right to Equality is a Fundamental Right under Articles 14–18. The Right to Property is no longer a Fundamental Right.",
            explanationHi = "समानता का अधिकार अनुच्छेद 14–18 के अंतर्गत मौलिक अधिकार है। संपत्ति का अधिकार अब मौलिक अधिकार नहीं है।",
            sourceUrl = "https://legislative.gov.in/constitution-of-india/",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_inr_code",
            categoryId = "gk",
            difficulty = Difficulty.EASY,
            questionEn = "What is the international currency code for the Indian rupee?",
            questionHi = "भारतीय रुपये का अंतरराष्ट्रीय मुद्रा कोड क्या है?",
            optionsEn = listOf("INR", "IND", "IRN", "RSI"),
            optionsHi = listOf("INR", "IND", "IRN", "RSI"),
            correctIndex = 0,
            explanationEn = "INR is the ISO 4217 currency code for the Indian rupee.",
            explanationHi = "INR भारतीय रुपये का ISO 4217 मुद्रा कोड है।",
            sourceUrl = "https://www.iso.org/iso-4217-currency-codes.html",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_tagore_anthem",
            categoryId = "history",
            difficulty = Difficulty.EASY,
            questionEn = "Who wrote the Indian national anthem, Jana Gana Mana?",
            questionHi = "भारत का राष्ट्रीय गान “जन गण मन” किसने लिखा था?",
            optionsEn = listOf("Bankim Chandra Chattopadhyay", "Rabindranath Tagore", "Munshi Premchand", "Sarojini Naidu"),
            optionsHi = listOf("बंकिमचंद्र चट्टोपाध्याय", "रवीन्द्रनाथ टैगोर", "मुंशी प्रेमचंद", "सरोजिनी नायडू"),
            correctIndex = 1,
            explanationEn = "Jana Gana Mana was written by Rabindranath Tagore.",
            explanationHi = "“जन गण मन” की रचना रवीन्द्रनाथ टैगोर ने की थी।",
            sourceUrl = "https://knowindia.india.gov.in/national-identity-elements/national-anthem.php",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_nitrogen_atmosphere",
            categoryId = "science",
            difficulty = Difficulty.EASY,
            questionEn = "Which gas makes up the largest share of Earth’s atmosphere?",
            questionHi = "पृथ्वी के वायुमंडल में सबसे अधिक मात्रा में कौन-सी गैस है?",
            optionsEn = listOf("Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen"),
            optionsHi = listOf("ऑक्सीजन", "कार्बन डाइऑक्साइड", "नाइट्रोजन", "हाइड्रोजन"),
            correctIndex = 2,
            explanationEn = "Nitrogen makes up about 78% of Earth’s atmosphere.",
            explanationHi = "नाइट्रोजन पृथ्वी के वायुमंडल का लगभग 78 प्रतिशत हिस्सा है।",
            sourceUrl = "https://science.nasa.gov/earth/facts/",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_parliament_houses",
            categoryId = "polity",
            difficulty = Difficulty.EASY,
            questionEn = "What are the two Houses of the Parliament of India?",
            questionHi = "भारत की संसद के दो सदन कौन-से हैं?",
            optionsEn = listOf("Lok Sabha and Rajya Sabha", "Legislative Assembly and Legislative Council", "Lok Sabha and State Assembly", "Rajya Sabha and Supreme Court"),
            optionsHi = listOf("लोकसभा और राज्यसभा", "विधानसभा और विधान परिषद", "लोकसभा और राज्य विधानसभा", "राज्यसभा और सर्वोच्च न्यायालय"),
            correctIndex = 0,
            explanationEn = "The Parliament of India consists of the President and two Houses: Lok Sabha and Rajya Sabha.",
            explanationHi = "भारत की संसद में राष्ट्रपति तथा दो सदन—लोकसभा और राज्यसभा—शामिल हैं।",
            sourceUrl = "https://sansad.in/ls",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_mercury_liquid_metal",
            categoryId = "science",
            difficulty = Difficulty.MEDIUM,
            questionEn = "Which metal is liquid at ordinary room temperature?",
            questionHi = "सामान्य कमरे के तापमान पर कौन-सी धातु तरल होती है?",
            optionsEn = listOf("Iron", "Copper", "Aluminium", "Mercury"),
            optionsHi = listOf("लोहा", "तांबा", "एल्युमिनियम", "पारा"),
            correctIndex = 3,
            explanationEn = "Mercury is a metal that is liquid at ordinary room temperature.",
            explanationHi = "पारा ऐसी धातु है जो सामान्य कमरे के तापमान पर तरल रहती है।",
            sourceUrl = "https://www.britannica.com/science/mercury-chemical-element",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_sun_is_star",
            categoryId = "science",
            difficulty = Difficulty.EASY,
            questionEn = "What kind of celestial object is the Sun?",
            questionHi = "सूर्य किस प्रकार का खगोलीय पिंड है?",
            optionsEn = listOf("A planet", "A star", "A moon", "An asteroid"),
            optionsHi = listOf("एक ग्रह", "एक तारा", "एक उपग्रह", "एक क्षुद्रग्रह"),
            correctIndex = 1,
            explanationEn = "The Sun is a star that produces energy through nuclear fusion.",
            explanationHi = "सूर्य एक तारा है, जिसमें नाभिकीय संलयन से ऊर्जा उत्पन्न होती है।",
            sourceUrl = "https://science.nasa.gov/sun/",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_national_flower_lotus",
            categoryId = "gk",
            difficulty = Difficulty.EASY,
            questionEn = "What is the national flower of India?",
            questionHi = "भारत का राष्ट्रीय फूल कौन-सा है?",
            optionsEn = listOf("Rose", "Sunflower", "Lotus", "Jasmine"),
            optionsHi = listOf("गुलाब", "सूरजमुखी", "कमल", "चमेली"),
            correctIndex = 2,
            explanationEn = "The lotus is recognised as the national flower of India.",
            explanationHi = "कमल भारत का राष्ट्रीय फूल माना जाता है।",
            sourceUrl = "https://knowindia.india.gov.in/national-identity-elements/national-flower.php",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_freedom_movement_bhagat",
            categoryId = "history",
            difficulty = Difficulty.EASY,
            questionEn = "Which of these people was a prominent revolutionary in India’s freedom movement?",
            questionHi = "भारत के स्वतंत्रता आंदोलन में इनमें से कौन प्रमुख क्रांतिकारी थे?",
            optionsEn = listOf("Samrat Ashoka", "Harshavardhana", "Bhagat Singh", "Chandragupta Maurya"),
            optionsHi = listOf("सम्राट अशोक", "हर्षवर्धन", "भगत सिंह", "चंद्रगुप्त मौर्य"),
            correctIndex = 2,
            explanationEn = "Bhagat Singh was a prominent revolutionary who opposed British colonial rule.",
            explanationHi = "भगत सिंह ब्रिटिश औपनिवेशिक शासन के विरुद्ध संघर्ष करने वाले प्रमुख क्रांतिकारी थे।",
            sourceUrl = "https://amritmahotsav.nic.in/",
            isVerified = true
        ),
        // Added from the interactive GK quizzes requested by the user.
        QuizQuestion(
            id = "added_cpu_full_form",
            categoryId = "computer",
            difficulty = Difficulty.EASY,
            questionEn = "What does CPU stand for in computing?",
            questionHi = "कंप्यूटर में CPU का पूरा नाम क्या है?",
            optionsEn = listOf("Central Processing Unit", "Computer Personal Utility", "Central Program User", "Control Processing Usage"),
            optionsHi = listOf("Central Processing Unit", "Computer Personal Utility", "Central Program User", "Control Processing Usage"),
            correctIndex = 0,
            explanationEn = "CPU stands for Central Processing Unit and processes program instructions.",
            explanationHi = "CPU का पूरा नाम Central Processing Unit है; यह प्रोग्राम निर्देशों को संसाधित करता है।",
            sourceUrl = "https://www.britannica.com/technology/central-processing-unit",
            isVerified = true
        )
    )

    fun getQuestionsByCategory(categoryId: String): List<QuizQuestion> {
        val list = allQuestions.filter { it.categoryId == categoryId }
        return if (list.isNotEmpty()) list else allQuestions.take(5)
    }

    fun getDailyQuestions(limit: Int = 5, dayOffset: Int = 0): List<QuizQuestion> {
        if (allQuestions.isEmpty()) return emptyList()
        val startIndex = (dayOffset * limit) % allQuestions.size
        val result = mutableListOf<QuizQuestion>()
        for (i in 0 until limit) {
            result.add(allQuestions[(startIndex + i) % allQuestions.size])
        }
        return result
    }
}
