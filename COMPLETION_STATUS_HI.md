# SkillPlay India — सुधार और वास्तविक स्थिति

यह पैकेज मूल स्क्रीन, Kotlin/Compose UI, Room database और sample learning content को बनाए रखते हुए data-safety/community सुधार और बिना AI वाला Daily Learning Toolkit जोड़ता है।

## इस अपडेट में किए गए सुधार

8. **Gemini AI नहीं है** — कोई Gemini SDK/API call या API key आवश्यक नहीं है।
9. **Daily Learning Toolkit जोड़ा** — Home screen से खुलने वाला नया screen जिसमें (a) रोज़ के 5 काम की local checklist, (b) date-based Word of the Day, (c) revision के लिए category quiz shortcuts, (d) interview/workplace English practice prompts, और (e) XP/lesson/challenge progress summary है। Daily checklist Android SharedPreferences में इसी फोन पर रहती है।

1. **गलत शुरुआती XP/streak हटाए** — नए user की शुरुआत 0 XP और 0 streak से होगी; पहले से मौजूद profile को इस बदलाव से overwrite नहीं किया जाता।
2. **Daily challenge claim transaction में** — challenge record और XP/profile update एक Room transaction में होते हैं; एक ही date की duplicate claim को रोकने की जाँच transaction के अंदर होती है। Score, total और XP की invalid negative/over-limit values भी clamp होती हैं।
3. **English exercise पर XP farming रोकी** — किसी exercise की पहली completion पर ही XP मिलता है; उसे दोबारा save करने से बार-बार XP नहीं जुड़ता। Notes 2,000 characters तक सीमित हैं।
4. **Community block feature लागू** — `blockUser()` अब चुने हुए handle की posts को local feed से छिपाता है।
5. **Post/comment validation** — खाली post/comment स्वीकार नहीं होते; text lengths सीमित हैं; poll में कम-से-कम दो अलग options चाहिए और अधिकतम चार options रखे जाते हैं।
6. **Post delete cleanup** — post हटाते समय उससे जुड़े comments भी delete होते हैं।
7. **Silent database wipe हटाया** — Room का `fallbackToDestructiveMigration()` हटाया गया है, ताकि future schema change पर app चुपचाप user data मिटाए नहीं। Database schema बदलने से पहले explicit Room Migration जोड़ना जरूरी होगा।

## अभी भी बाकी — इसे production-ready कहने से पहले

- इस ZIP में `gradlew`, `gradlew.bat` और `gradle-wrapper.jar` मौजूद नहीं हैं। इस environment में internet/DNS भी उपलब्ध नहीं था, इसलिए official wrapper download करके Gradle build/test चलाना संभव नहीं हुआ। **इसलिए compile/build success प्रमाणित नहीं है।**
- Firebase project config (`google-services.json`) नहीं है। Login, cloud sync और shared online community इस पैकेज में configured/verified नहीं हैं; मौजूदा community local-device Room data है।
- Gemini AI और किसी AI API का उपयोग नहीं किया जाता। कोई AI API key आवश्यक नहीं है। Daily Toolkit की checklist local SharedPreferences में सेव होती है; यह shared cloud sync नहीं है।
- Question bank में source URLs/`isVerified` flags हैं, लेकिन इससे independent factual verification सिद्ध नहीं होती।
- Release keystore, signed AAB, Play Console testing, privacy policy URL, moderation backend/rules और real-device QA बाकी हैं।

## अगला सुरक्षित build step

1. ZIP को extract करके पूरा project Android Studio में खोलें।
2. Gradle sync करें। अगर wrapper missing error आए, Android Studio/Gradle tooling से project के लिए official Gradle wrapper generate करें; कोई random wrapper JAR इस्तेमाल न करें।
3. पहले Debug build और unit tests चलाएँ। Compile errors ठीक करने के बाद ही, यदि जरूरत हो, Firebase/cloud features जोड़ें।
4. Firebase जोड़ने से पहले Firebase project बनाकर सही Android package `com.aistudio.skillplay.kxmpzq` register करें और `google-services.json` project root नहीं, `app/` folder में रखें।
5. Existing Room schema की पुरानी published version हो तो उसका schema लेकर explicit migration लिखें; destructive migration वापस न जोड़ें।
6. Release signing secrets को GitHub/ZIP में commit न करें; Play Console upload key अलग सुरक्षित रखें।

**ईमानदार स्थिति:** यह सुधारित source package है, verified APK/AAB नहीं। Build, cloud features और Play Store readiness की पुष्टि अभी बाकी है।


10. **Study Boost जोड़ा** — Home screen से नया screen खुलता है जिसमें weekly quiz practice shortcuts, device-local preparation summary, manually marked weak topics और उन topics के quiz shortcuts, local bookmarks/notes, local study-group join selections और daily 5-minute challenge check-in शामिल हैं। यह Gemini/AI के बिना काम करता है।

**Study Boost की सीमाएँ:** Weekly competition की live leaderboard/दूसरे users से comparison, shared study-group chat और cross-device sync अभी नहीं हैं—इनके लिए secure online backend और user identity चाहिए। Weak topics user खुद mark करता है; गलत answers की पूरी cross-session history अभी स्वतः collect नहीं होती। Notes/groups/5-minute check-in इस डिवाइस पर ही रहते हैं।


## Daily Return System update
- Study Boost में IST के अनुसार daily plan जोड़ा गया: daily quiz, date-based topic practice और daily lesson।
- Daily quiz/topic completion quiz result स्क्रीन पर दर्ज होती है। Daily plan की date बदलने पर केवल daily status बदलता है; XP, bookmarks, notes और पुरानी lesson progress सुरक्षित रहती है।
- Daily content bundled question/lesson banks से rotate होता है; अभी live content feed या Firebase sync नहीं है।
- Android build और device testing अभी बाकी है क्योंकि इस package में Gradle wrapper script/jar उपलब्ध नहीं है और environment में Gradle installed नहीं है।


11. **English Learning Suite बढ़ाया** — Quicklish में दिखाए गए सामान्य गैर-AI learning concepts के आधार पर छह नए offline tracks जोड़े: Think in English, Sentence Builder, Sentence Practice, Sentence Structure, Paragraph Writing और IELTS-style prompts। मौजूदा Vocabulary, Grammar, Sentence Correction, Conversations, Interview, Workplace और Reading tracks सुरक्षित हैं।
12. **शब्दावली Favorites और TTS** — Vocabulary exercises को फोन पर favorites में सेव/फिल्टर किया जा सकता है। Android Text-to-Speech prompts और sample answers पढ़ सकता है। यह pronunciation scoring या AI feedback नहीं देता।


13. **English curriculum expansion** — 26 नए fixed offline exercises जोड़े गए: Vocabulary, Grammar, Real-life Conversations, Interview Practice, Workplace/Professional English, Sentence Builder/Practice/Structure, Sentence Correction, Reading, Paragraph Writing, Think in English और IELTS-style speaking. कुल bundled English practice exercises 57 हैं (7 मूल + 12 Quicklish-style non-AI + 26 expansion + 12 daily-use)। प्रत्येक में सही उत्तर, fixed explanation/sample answer और tips हैं। यह AI-generated content या pronunciation scoring नहीं है।


## Daily-use English sentences added

Added 12 fixed offline exercises with English/Hindi meanings and sample dialogues for: shop shopping, doctor appointments, medical-store conversations, student-to-teacher requests, student-to-student communication, and family conversations. These are part of the existing Everyday Conversations track; no AI/API or cloud dependency was added.


## अंतिम source-level QA (2026-10-29)
- English tracks: 13
- Bundled English exercises: 57
- Daily-use conversation exercises: 12; सभी 12 IDs मुख्य EnglishPracticeBank में शामिल हैं।
- सभी English exercise IDs unique पाए गए।
- जिन exercises में MCQ options हैं, उनमें 4 options और valid correct-answer index की जाँच सफल रही।
- English data layer का Kotlin compile check सफल रहा।
- Unit test में पुराना 7-track assertion ठीक करके 13 tracks किया और exercise integrity/daily-use integration test जोड़ा।
- Full Android Gradle build, Android unit test suite, emulator/device QA और signed AAB अभी सत्यापित नहीं हैं: environment में Gradle executable/wrapper JAR और Android SDK build setup उपलब्ध नहीं था; Gradle distribution download भी DNS/network के कारण नहीं हो पाया।


## Interactive GK Quiz update (2026-10-29)
- दोनों चैट GK quizzes के सभी 16 विषयों पर आधारित प्रश्न मौजूदा `QuestionBank.kt` में जोड़े गए।
- संविधान, ग्रह, भारतीय राज्य, प्रकाश संश्लेषण, राष्ट्रीय पशु/फूल, गंगा, मौलिक अधिकार, INR, राष्ट्रगान, वायुमंडल, संसद, पारा, सूर्य, स्वतंत्रता आंदोलन और CPU शामिल हैं।
- हर प्रश्न में Hindi/English text, चार विकल्प, सही उत्तर, explanation और source URL है। ये मौजूदा category quiz, daily quiz rotation और local score flow में उपलब्ध हैं।
- मौजूदा ऐप quiz engine single-answer MCQ है; इसलिए चैट में हुए True/False और multi-select सवालों को app engine के compatible चार-विकल्प वाले MCQ रूप में बदला गया। Multi-select interaction अभी ऐप में नहीं जोड़ा गया।
- QuestionBank + model का Kotlin compile check सफल रहा। Full Android Gradle build, unit tests और device test अब भी verified नहीं हैं।
