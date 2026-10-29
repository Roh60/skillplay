package com.example.data.dataset

import com.example.data.model.Lesson
import com.example.data.model.LessonSection

object LessonBank {
    val allLessons: List<Lesson> = listOf(
        // GK
        Lesson(
            id = "lesson_gk_1",
            categoryId = "gk",
            titleEn = "National Symbols & Emblems of India",
            titleHi = "भारत के राष्ट्रीय प्रतीक और राजचिह्न",
            estimatedMinutes = 5,
            summaryEn = "Understand the origin, legal adoption, and deep cultural symbolism behind the Tiranga, Lion Capital, and National Anthem.",
            summaryHi = "तिरंगा, अशोक स्तम्भ सिंह शीर्ष और राष्ट्रगान के पीछे के इतिहास और प्रतीकात्मक महत्व को समझें।",
            sections = listOf(
                LessonSection(
                    headingEn = "The State Emblem of India",
                    headingHi = "भारत का राजचिह्न",
                    contentEn = "Adopted on 26 January 1950 from the Lion Capital of Ashoka at Sarnath (circa 250 BCE). It features four Asiatic lions standing back to back, symbolizing power, courage, confidence, and pride. On the frieze are sculptures in high relief of an elephant, a galloping horse, a bull, and a lion, separated by intervening Dharma Chakras.",
                    contentHi = "26 जनवरी 1950 को सारनाथ स्थित अशोक के सिंह स्तम्भ को राजचिह्न के रूप में अपनाया गया। इसमें चार शेर एक-दूसरे की ओर पीठ किए हुए हैं जो शक्ति, साहस, आत्मविश्वास और गौरव के प्रतीक हैं। चक्र के साथ घोड़ा, बैल, हाथी और शेर की आकृतियां उकेरी गई हैं।",
                    keyTakeawayEn = "The motto 'Satyameva Jayate' inscribed in Devanagari script is taken from Mundaka Upanishad.",
                    keyTakeawayHi = "देवनागरी में लिखा 'सत्यमेव जयते' मुण्डकोपनिषद से लिया गया है।"
                ),
                LessonSection(
                    headingEn = "The National Flag (Tiranga)",
                    headingHi = "राष्ट्रीय ध्वज (तिरंगा)",
                    contentEn = "Designed by Pingali Venkayya and adopted by the Constituent Assembly on 22 July 1947. The top saffron band indicates strength and courage; the white middle band indicates peace and truth with Dharma Chakra (24 spokes in navy blue); and the bottom green band shows fertility, growth, and auspiciousness of the land. The ratio of width to length is 2:3.",
                    contentHi = "पिंगली वेंकैया द्वारा डिजाइन किया गया तिरंगा 22 जुलाई 1947 को संविधान सभा द्वारा अपनाया गया। केसरिया रंग शक्ति और साहस, सफेद रंग शांति और सत्य, 24 तीलियों वाला चक्र निरंतर प्रगति और हरा रंग समृद्धि का प्रतीक है। चौड़ाई और लंबाई का अनुपात 2:3 है।",
                    keyTakeawayEn = "Flag ratio is strictly 2:3, and the wheel has 24 spokes representing 24 hours of constant righteous duty.",
                    keyTakeawayHi = "ध्वज का अनुपात 2:3 होता है और चक्र में 24 तीलियां होती हैं।"
                )
            ),
            quizQuestionIds = listOf("gk_1", "gk_2")
        ),
        // History
        Lesson(
            id = "lesson_hist_1",
            categoryId = "history",
            titleEn = "Key Milestones of the Indian Freedom Movement",
            titleHi = "भारतीय स्वतंत्रता संग्राम के प्रमुख पड़ाव",
            estimatedMinutes = 6,
            summaryEn = "Trace the momentous trajectory from the 1857 First War of Independence to Non-Cooperation, Civil Disobedience, and Quit India.",
            summaryHi = "1857 के प्रथम स्वतंत्रता संग्राम से लेकर असहयोग, सविनय अवज्ञा और भारत छोड़ो आंदोलन के इतिहास का संक्षिप्त अध्ययन।",
            sections = listOf(
                LessonSection(
                    headingEn = "The 1857 Revolt & Awakening",
                    headingHi = "1857 का संग्राम और जनचेतना",
                    contentEn = "Often called the First War of Indian Independence, the rebellion began in Meerut and spread across northern and central India under leaders like Rani Lakshmibai, Mangal Pandey, Kunwar Singh, and Tatya Tope. It led to the end of the East India Company's rule and direct governance by the British Crown under the Government of India Act 1858.",
                    contentHi = "1857 की क्रांति ने भारत में जनचेतना का नया अध्याय शुरू किया। रानी लक्ष्मीबाई, मंगल पांडे, कुंवर सिंह और तात्या टोपे जैसे वीरों के संघर्ष के बाद ईस्ट इंडिया कंपनी का शासन समाप्त होकर ब्रिटिश क्राउन के अधीन हो गया।",
                    keyTakeawayEn = "Government of India Act 1858 transferred power from company to crown.",
                    keyTakeawayHi = "1858 के अधिनियम द्वारा भारत का शासन ब्रिटिश क्राउन को सौंपा गया।"
                ),
                LessonSection(
                    headingEn = "Gandhian Mass Movements",
                    headingHi = "गांधीवादी जन आंदोलन",
                    contentEn = "Gandhiji returned from South Africa in 1915 and transformed the national struggle through Ahimsa (non-violence) and Satyagraha. The Non-Cooperation Movement (1920-22), the Salt Satyagraha Dandi March (1930), and the decisive Quit India Movement (1942) with the mantra 'Do or Die' mobilized millions across all walks of life.",
                    contentHi = "1920 का असहयोग आंदोलन, 1930 का नमक सत्याग्रह और 1942 का भारत छोड़ो आंदोलन ('करो या मरो') ने देश के कोने-कोने में नागरिकों को स्वतंत्रता के लिए एकजुट किया।",
                    keyTakeawayEn = "Dandi March covered 240 miles to break salt monopolies peacefully.",
                    keyTakeawayHi = "दांडी मार्च 240 मील की ऐतिहासिक पदयात्रा थी।"
                )
            ),
            quizQuestionIds = listOf("hist_1", "hist_2")
        ),
        // Polity
        Lesson(
            id = "lesson_polity_1",
            categoryId = "polity",
            titleEn = "Constitution of India: Preamble & Fundamental Rights",
            titleHi = "भारतीय संविधान: प्रस्तावना और मौलिक अधिकार",
            estimatedMinutes = 5,
            summaryEn = "Master the supreme legal architecture of India crafted by Dr. B.R. Ambedkar and the Constituent Assembly.",
            summaryHi = "डॉ. बी.आर. अम्बेडकर और संविधान सभा द्वारा निर्मित सर्वोच्च कानूनी ढांचे का अध्ययन।",
            sections = listOf(
                LessonSection(
                    headingEn = "The Preamble",
                    headingHi = "संविधान की प्रस्तावना",
                    contentEn = "The Preamble proclaims India to be a 'Sovereign, Socialist, Secular, Democratic Republic' committed to securing Justice, Liberty, Equality, and Fraternity for all citizens. The words 'Socialist', 'Secular', and 'Integrity' were added by the 42nd Constitutional Amendment Act of 1976.",
                    contentHi = "प्रस्तावना भारत को एक 'सम्पूर्ण प्रभुत्व-सम्पन्न, समाजवादी, पंथनिरपेक्ष, लोकतंत्रात्मक गणराज्य' घोषित करती है। 42वें संशोधन (1976) द्वारा समाजवादी, पंथनिरपेक्ष और अखंडता शब्द जोड़े गए।",
                    keyTakeawayEn = "The Preamble acts as the key to the minds of the Constitution framers.",
                    keyTakeawayHi = "प्रस्तावना संविधान की आत्मा और मार्गदर्शक सिद्धांत है।"
                ),
                LessonSection(
                    headingEn = "Fundamental Rights (Part III)",
                    headingHi = "मौलिक अधिकार (भाग III)",
                    contentEn = "Enshrined in Articles 12 to 35, Fundamental Rights are justiciable, meaning citizens can approach High Courts (Article 226) or the Supreme Court (Article 32) directly if violated. They encompass Right to Equality (Arts 14-18), Right to Freedom (Arts 19-22), Right against Exploitation (Arts 23-24), Freedom of Religion (Arts 25-28), and Constitutional Remedies (Art 32).",
                    contentHi = "अनुच्छेद 12 से 35 तक मौलिक अधिकार दिए गए हैं। इनके उल्लंघन पर नागरिक सीधे उच्च न्यायालय (अनुच्छेद 226) या सर्वोच्च न्यायालय (अनुच्छेद 32) में याचिका दायर कर सकते हैं।",
                    keyTakeawayEn = "Article 32 was termed the 'Heart and Soul of the Constitution' by Dr. Ambedkar.",
                    keyTakeawayHi = "डॉ. अम्बेडकर ने अनुच्छेद 32 को संविधान का हृदय और आत्मा कहा था।"
                )
            ),
            quizQuestionIds = listOf("polity_1", "polity_2")
        ),
        // Digital Safety
        Lesson(
            id = "lesson_safety_1",
            categoryId = "digital_safety",
            titleEn = "UPI & Cyber Hygiene: Shielding Your Financial Privacy",
            titleHi = "यूपीआई और साइबर स्वच्छता: अपनी वित्तीय सुरक्षा कैसे करें",
            estimatedMinutes = 4,
            summaryEn = "Practical, life-saving rules to defend against fraudulent QR scans, fake customer care numbers, and phishing scams.",
            summaryHi = "फर्जी क्यूआर कोड, फर्जी कस्टमर केयर और फिशिंग घोटालों से बचने के जरूरी व्यावहारिक नियम।",
            sections = listOf(
                LessonSection(
                    headingEn = "The Golden Rule of UPI PIN",
                    headingHi = "यूपीआई पिन का स्वर्णिम नियम",
                    contentEn = "UPI PIN is required ONLY to debit money from your bank account. Scammers frequently message users claiming: 'Scan this QR code or enter your 6-digit PIN to receive ₹5,000 lottery/refund'. As soon as you enter your PIN, the funds are debited from you, not credited.",
                    contentHi = "यूपीआई पिन की आवश्यकता केवल आपके खाते से पैसे कटने (भेजने) के समय होती है। पैसे प्राप्त करने के लिए किसी पिन या क्यूआर कोड को स्कैन करने की जरूरत नहीं होती।",
                    keyTakeawayEn = "Never enter UPI PIN to receive money under any circumstance.",
                    keyTakeawayHi = "पैसे प्राप्त करने के लिए कभी भी पिन दर्ज न करें।"
                ),
                LessonSection(
                    headingEn = "Reporting Cyber Fraud Promptly",
                    headingHi = "साइबर फ्रॉड की तुरंत रिपोर्ट करना",
                    contentEn = "If fraud occurs, immediately call the National Cyber Crime Helpline '1930' and log a complaint on https://cybercrime.gov.in. Contact your bank immediately to freeze the transaction within the 'golden hour'.",
                    contentHi = "यदि कोई वित्तीय धोखाधड़ी होती है, तो बिना देर किए राष्ट्रीय हेल्पलाइन 1930 पर कॉल करें और cybercrime.gov.in पर रिपोर्ट दर्ज करें।",
                    keyTakeawayEn = "National Cybercrime Helpline: 1930.",
                    keyTakeawayHi = "राष्ट्रीय साइबर हेल्पलाइन नंबर 1930 है।"
                )
            ),
            quizQuestionIds = listOf("safe_1")
        ),
        // Financial Literacy
        Lesson(
            id = "lesson_finance_1",
            categoryId = "finance",
            titleEn = "Smart Money: The 50-30-20 Rule & Compounding",
            titleHi = "स्मार्ट मनी: 50-30-20 नियम और चक्रवृद्धि की शक्ति",
            estimatedMinutes = 5,
            summaryEn = "Build financial freedom through disciplined budgeting, emergency buffers, and long-term compounding.",
            summaryHi = "अनुशासित बजट, आपातकालीन बचत और चक्रवृद्धि ब्याज की शक्ति से वित्तीय मजबूती हासिल करें।",
            sections = listOf(
                LessonSection(
                    headingEn = "The 50/30/20 Budgeting Framework",
                    headingHi = "50/30/20 बजट नियम",
                    contentEn = "Divide your monthly take-home income into three buckets: 50% for Needs (rent, groceries, electricity, essential medicines), 30% for Wants (dining out, entertainment, hobbies), and at least 20% for Savings & Debt repayment (emergency fund, SIPs, PPF).",
                    contentHi = "अपनी मासिक आय को तीन भागों में बांटें: 50% बुनियादी आवश्यकताएं (किराया, राशन, बिजली), 30% इच्छाएं और मनोरंजन, और कम से कम 20% बचत एवं निवेश (म्यूचुअल फंड एसआईपी, पीपीएफ)।",
                    keyTakeawayEn = "Save and invest first before spending on discretionary wants.",
                    keyTakeawayHi = "खर्च करने से पहले बचत और निवेश को प्राथमिकता दें।"
                )
            ),
            quizQuestionIds = listOf("fin_1")
        ),
        // Interview Preparation
        Lesson(
            id = "lesson_interview_1",
            categoryId = "interview",
            titleEn = "Ace Your HR Round with the STAR Technique",
            titleHi = "STAR तकनीक से एचआर साक्षात्कार में सफलता पाएं",
            estimatedMinutes = 5,
            summaryEn = "Structure your answers with clarity and confidence when asked 'Tell me about a challenging situation you handled'.",
            summaryHi = "कठिन परिस्थितियों के उदाहरणों को आत्मविश्वास और स्पष्टता के साथ प्रस्तुत करना सीखें।",
            sections = listOf(
                LessonSection(
                    headingEn = "Breaking Down STAR",
                    headingHi = "STAR तकनीक का विभाजन",
                    contentEn = "S - Situation (Set the scene briefly in 1-2 lines).\nT - Task (What exact responsibility did you have?).\nA - Action (What specific initiatives did YOU take? Use 'I', not just 'we').\nR - Result (What was the measurable outcome or lesson learned?).",
                    contentHi = "S - Situation (परिस्थिति स्पष्ट करें), T - Task (आपकी क्या जिम्मेदारी थी), A - Action (आपने क्या ठोस कदम उठाया), R - Result (क्या सकारात्मक परिणाम मिला)।",
                    keyTakeawayEn = "Interviewers value quantifiable results and personal accountability.",
                    keyTakeawayHi = "परिणामों को संख्यात्मक रूप में बताना सबसे प्रभावी होता है।"
                )
            ),
            quizQuestionIds = listOf("interview_1")
        )
    )

    fun getLessonsForCategory(categoryId: String): List<Lesson> {
        val list = allLessons.filter { it.categoryId == categoryId }
        if (list.isNotEmpty()) return list
        // Generate a standard lesson template if specific one not seeded yet
        return listOf(
            Lesson(
                id = "lesson_${categoryId}_intro",
                categoryId = categoryId,
                titleEn = "Foundations of ${categoryId.replace('_', ' ').replaceFirstChar { it.uppercase() }}",
                titleHi = "${categoryId.replace('_', ' ').replaceFirstChar { it.uppercase() }} का मूल परिचय",
                estimatedMinutes = 5,
                summaryEn = "Core principles, practical applications, and high-frequency examination topics.",
                summaryHi = "मूल सिद्धांत, व्यावहारिक अनुप्रयोग और महत्वपूर्ण परीक्षा विषय।",
                sections = listOf(
                    LessonSection(
                        headingEn = "Core Concept",
                        headingHi = "मूल सिद्धांत",
                        contentEn = "This learning module introduces foundational guidelines, real-world examples, and step-by-step problem-solving methods tested in competitive examinations and career readiness.",
                        contentHi = "यह शिक्षण मॉड्यूल प्रतियोगी परीक्षाओं और करियर की तैयारी में पूछे जाने वाले बुनियादी नियमों और उदाहरणों की रूपरेखा प्रस्तुत करता है।",
                        keyTakeawayEn = "Consistent daily practice reinforces long-term memory retention.",
                        keyTakeawayHi = "नियमित अभ्यास से विषय की समझ गहरी होती है।"
                    )
                ),
                quizQuestionIds = QuestionBank.getQuestionsByCategory(categoryId).map { it.id }
            )
        )
    }

    fun getLessonById(id: String): Lesson? {
        return allLessons.find { it.id == id } ?: allLessons.firstOrNull()
    }
}
