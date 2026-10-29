<div align="center">
<img width="1200" height="475" alt="GHBanner" src="https://ai.google.dev/static/site-assets/images/share-ais-513315318.png" />
</div>

# Run and deploy your AI Studio app

This contains everything you need to run your app locally.

View your app in AI Studio: https://ai.studio/apps/604e6f63-daac-4ee8-9e14-52585feab143

## Run Locally

**Prerequisites:**  [Android Studio](https://developer.android.com/studio)


1. Open Android Studio
2. Select **Open** and choose the directory containing this project
3. Allow Android Studio to fix any incompatibilities as it imports the project.
4. No Gemini AI or AI API key is used or required by this project.
5. Remove this line from the app's `build.gradle.kts` file: `signingConfig = signingConfigs.getByName("debugConfig")`
6. Run the app on an emulator or physical device
7. If you have already published your app in AI Studio, please [request upload key reset](https://support.google.com/googleplay/android-developer/answer/9842756#zippy=%2Crequest-an-upload-key-reset) in Google Play Console.
## Daily Learning Toolkit (No AI)

Home screen से Daily Learning Toolkit खोलें। इसमें local daily checklist, Word of the Day, revision quiz shortcuts, interview/workplace English prompts, और local progress summary है. No Gemini or other AI API is used. The checklist is stored on the device.


## Study Boost (no AI)
Home screen's Study Boost entry opens weekly local quiz scoring, a progress summary, wrong-answer-based weak-topic detection, manual weak-topic revision, searchable question bookmarks, saved/deletable notes, local study-group membership, and a five-minute countdown challenge. This is local-first. Live competition rankings, real comparison with other users, shared group chat, account identity, and cloud sync must be added in the next Firebase/backend phase. No Gemini or AI API is used.


## Daily Return System (No AI)
Study Boost now includes an IST-based daily learning plan: a date-rotating 5-question daily challenge, a daily topic quiz, and a lesson selected for the current day. Daily completion markers use date-specific local preferences and refresh when the date changes while the screen is open. Quiz completion is recorded from the quiz result screen. Previously earned XP, saved notes, bookmarks, and lesson history are not cleared by the daily reset. This remains local-only until Firebase is added; content rotates from the bundled question and lesson banks, so it does not fetch live or newly authored content.


## English Learning Suite (offline, no AI)
The English Practice Hub now includes 13 learning tracks: vocabulary, grammar, sentence correction, everyday conversations, interview practice, workplace communication, reading comprehension, Think in English, Sentence Builder, Sentence Practice, Sentence Structure, Paragraph Writing, and IELTS-style speaking prompts. The bundle contains 57 fixed offline exercises across the 13 tracks (including additional vocabulary, grammar, conversation, interview, workplace, sentence-building, writing practice, and 12 daily-use conversation exercises for shops, doctors, medical stores, teachers, students, and family members). Each exercise includes fixed feedback, explanations and sample frameworks; Android Text-to-Speech can read prompts/sample answers aloud. Users can save vocabulary exercises to Favorites and filter the vocabulary track to favorites. Completed exercises and notes use the existing local Room progress. This is not an AI tutor, does not generate responses, does not score pronunciation, and does not claim an official IELTS score. The content is original practice content, not copied from Quicklish.


### English curriculum expansion

Added 26 original fixed exercises covering reliable/improve/opportunity vocabulary, simple present and past, articles, shopping/directions/appointments, interview strengths/weaknesses, professional emails and status updates, sentence questions/plans/opinions, negative sentences, because clauses, quantifiers, reading inference, paragraph conclusions, natural follow-up responses, and IELTS-style answer organization. All content is bundled locally. No AI tutor, generated content, or AI pronunciation scoring is present.


## Interactive GK Quiz update (offline, no AI)
Added 16 bilingual GK questions based on the two interactive quiz sets. Topics include Indian Constitution, planets, Indian geography, photosynthesis, national symbols, Ganga, Fundamental Rights, currency code, national anthem, atmosphere, Parliament, mercury, the Sun, freedom movement, and CPU. Each question has four options, an answer key, explanation, and source URL. Questions join the existing category/daily quiz bank and local scoring flow. The current app supports single-answer MCQs; True/False and multi-select items from the chat have been adapted to compatible four-option MCQs. No Gemini or other AI API was added.
