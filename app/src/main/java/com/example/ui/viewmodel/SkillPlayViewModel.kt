package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dataset.DefaultCommunityData
import com.example.data.dataset.EnglishPracticeBank
import com.example.data.dataset.LessonBank
import com.example.data.dataset.QuestionBank
import com.example.data.local.AppDatabase
import com.example.data.local.ChallengeRecordEntity
import com.example.data.local.CommunityCommentEntity
import com.example.data.local.CommunityPostEntity
import com.example.data.local.EnglishProgressEntity
import com.example.data.local.LessonProgressEntity
import com.example.data.local.UserProfileEntity
import com.example.data.model.BadgesData
import com.example.data.model.CategoriesData
import com.example.data.model.Difficulty
import com.example.data.model.EnglishExercise
import com.example.data.model.EnglishModuleType
import com.example.data.model.LearningCategory
import com.example.data.model.Lesson
import com.example.data.model.QuizQuestion
import com.example.data.model.QuizUserAnswer
import com.example.data.model.SkillBadge
import com.example.data.repository.SkillPlayRepository
import com.example.ui.navigation.AppScreen
import com.example.ui.navigation.MainTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SkillPlayViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SkillPlayRepository(AppDatabase.getDatabase(application))

    // Navigation state
    private val _currentTab = MutableStateFlow<MainTab>(MainTab.Home)
    val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

    private val _screenStack = MutableStateFlow<List<AppScreen>>(listOf(AppScreen.MainNavigation))
    val currentScreen: StateFlow<AppScreen> = _screenStack.asStateFlow().let { flow ->
        MutableStateFlow<AppScreen>(AppScreen.MainNavigation).also { state ->
            viewModelScope.launch {
                flow.collect { list -> state.value = list.lastOrNull() ?: AppScreen.MainNavigation }
            }
        }
    }

    // User profile and language
    val userProfile: StateFlow<UserProfileEntity?> = repository.getUserProfileFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val isHindi: StateFlow<Boolean> = MutableStateFlow(false).also { state ->
        viewModelScope.launch {
            userProfile.collect { user ->
                state.value = (user?.languagePref == "hi")
            }
        }
    }

    // Progress and challenges
    val lessonProgressList: StateFlow<List<LessonProgressEntity>> = repository.getAllLessonProgressFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val challengeRecords: StateFlow<List<ChallengeRecordEntity>> = repository.getAllChallengesFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val englishProgressList: StateFlow<List<EnglishProgressEntity>> = repository.getAllEnglishProgressFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedLessons: StateFlow<List<LessonProgressEntity>> = repository.getSavedLessonsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Personalized Learning Recommendations
    val personalizedRecommendations: StateFlow<List<com.example.data.model.PathRecommendation>> = combine(
        userProfile,
        lessonProgressList
    ) { user, progressList ->
        if (user != null) {
            com.example.data.engine.RecommendationEngine.generateRecommendations(user, progressList)
        } else {
            emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Community
    val communityPosts: StateFlow<List<CommunityPostEntity>> = repository.getVisibleCommunityPostsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val reportedPosts: StateFlow<List<CommunityPostEntity>> = repository.getReportedPostsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Active Comments dialog/screen
    private val _selectedPostIdForComments = MutableStateFlow<Long?>(null)
    val selectedPostIdForComments: StateFlow<Long?> = _selectedPostIdForComments.asStateFlow()

    private val _currentComments = MutableStateFlow<List<CommunityCommentEntity>>(emptyList())
    val currentComments: StateFlow<List<CommunityCommentEntity>> = _currentComments.asStateFlow()

    // Active Quiz State
    private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

    private val _quizIndex = MutableStateFlow(0)
    val quizIndex: StateFlow<Int> = _quizIndex.asStateFlow()

    private val _selectedOption = MutableStateFlow<Int?>(null)
    val selectedOption: StateFlow<Int?> = _selectedOption.asStateFlow()

    private val _isAnswerSubmitted = MutableStateFlow(false)
    val isAnswerSubmitted: StateFlow<Boolean> = _isAnswerSubmitted.asStateFlow()

    private val _quizAnswers = MutableStateFlow<MutableList<QuizUserAnswer>>(mutableListOf())
    val quizAnswers: StateFlow<List<QuizUserAnswer>> = _quizAnswers.asStateFlow()

    private val _activeQuizCategory = MutableStateFlow("gk")
    val activeQuizCategory: StateFlow<String> = _activeQuizCategory.asStateFlow()

    private val _isDailyQuiz = MutableStateFlow(false)
    val isDailyQuiz: StateFlow<Boolean> = _isDailyQuiz.asStateFlow()

    // Notification toast / banner message
    private val _userMessage = MutableStateFlow<String?>(null)
    val userMessage: StateFlow<String?> = _userMessage.asStateFlow()

    init {
        viewModelScope.launch {
            repository.initializeDefaultsIfNeeded()
        }
    }

    fun dismissUserMessage() {
        _userMessage.value = null
    }

    // Navigation functions
    fun selectTab(tab: MainTab) {
        _currentTab.value = tab
        _screenStack.value = listOf(AppScreen.MainNavigation)
    }

    fun navigateTo(screen: AppScreen) {
        _screenStack.value = _screenStack.value + screen
    }

    fun navigateBack(): Boolean {
        val currentList = _screenStack.value
        if (currentList.size > 1) {
            _screenStack.value = currentList.dropLast(1)
            return true
        }
        return false
    }

    fun toggleLanguage() {
        viewModelScope.launch {
            val current = userProfile.value?.languagePref ?: "en"
            val newLang = if (current == "en") "hi" else "en"
            repository.updateLanguage(newLang)
        }
    }

    fun updateProfile(name: String, avatar: Int, stateLocation: String, targetDailyMinutes: Int) {
        viewModelScope.launch {
            val user = repository.getUserProfile()
            repository.updateUserProfile(
                user.copy(
                    displayName = name.trim().ifEmpty { user.displayName },
                    avatarIndex = avatar,
                    stateLocation = stateLocation.trim().ifEmpty { user.stateLocation },
                    targetDailyMinutes = targetDailyMinutes
                )
            )
            _userMessage.value = if (isHindi.value) "प्रोफ़ाइल सफलतापूर्वक अपडेट हो गई" else "Profile updated successfully"
        }
    }

    fun updateInterestsAndGoal(goalTrack: String, selectedCategoryIds: Set<String>) {
        viewModelScope.launch {
            repository.updateGoalAndInterests(goalTrack, selectedCategoryIds.joinToString(","))
            _userMessage.value = if (isHindi.value) "सीखने का मार्ग अनुकूलित किया गया!" else "Personalized learning path updated!"
        }
    }

    fun resetAllData() {
        viewModelScope.launch {
            repository.resetAllData()
            // Keep the app's reset action consistent across Room and local Study Boost preferences.
            getApplication<Application>().getSharedPreferences("skillplay_study_boost", 0).edit().clear().apply()
            _screenStack.value = listOf(AppScreen.MainNavigation)
            _currentTab.value = MainTab.Home
            _userMessage.value = if (isHindi.value) "सभी डेटा रीसेट कर दिया गया है" else "All local data has been reset"
        }
    }

    // Lesson Actions
    fun toggleSaveLesson(lessonId: String, categoryId: String) {
        viewModelScope.launch {
            repository.toggleSaveLesson(lessonId, categoryId)
        }
    }

    fun completeLessonReading(lessonId: String, categoryId: String) {
        viewModelScope.launch {
            repository.completeLesson(lessonId, categoryId, 100, 20)
            _userMessage.value = if (isHindi.value) "पाठ पूर्ण! +20 XP अर्जित किए" else "Lesson completed! +20 XP earned"
        }
    }

    // Quiz Lifecycle
    fun startCategoryQuiz(categoryId: String, difficulty: Difficulty = Difficulty.EASY) {
        val questions = QuestionBank.getQuestionsByCategory(categoryId)
        _activeQuizCategory.value = categoryId
        _isDailyQuiz.value = false
        _quizQuestions.value = questions.shuffled().take(5)
        _quizIndex.value = 0
        _selectedOption.value = null
        _isAnswerSubmitted.value = false
        _quizAnswers.value = mutableListOf()
        navigateTo(AppScreen.QuizActive(categoryId, difficulty, isDaily = false))
    }

    fun startDailyChallenge() {
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val dayHash = todayStr.hashCode().let { if (it < 0) -it else it }
        val questions = QuestionBank.getDailyQuestions(limit = 5, dayOffset = dayHash)

        _activeQuizCategory.value = "daily"
        _isDailyQuiz.value = true
        _quizQuestions.value = questions
        _quizIndex.value = 0
        _selectedOption.value = null
        _isAnswerSubmitted.value = false
        _quizAnswers.value = mutableListOf()
        navigateTo(AppScreen.QuizActive("daily", Difficulty.MEDIUM, isDaily = true))
    }

    fun selectQuizOption(index: Int) {
        if (!_isAnswerSubmitted.value) {
            _selectedOption.value = index
        }
    }

    fun submitCurrentAnswer() {
        val selected = _selectedOption.value ?: return
        val currentQ = _quizQuestions.value.getOrNull(_quizIndex.value) ?: return

        _isAnswerSubmitted.value = true
        val updatedAnswers = _quizAnswers.value.toMutableList().apply {
            add(QuizUserAnswer(currentQ, selected))
        }
        _quizAnswers.value = updatedAnswers
    }

    fun proceedToNextQuestion() {
        val nextIdx = _quizIndex.value + 1
        if (nextIdx < _quizQuestions.value.size) {
            _quizIndex.value = nextIdx
            _selectedOption.value = null
            _isAnswerSubmitted.value = false
        } else {
            // Finish quiz
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        val answers = _quizAnswers.value
        val correctCount = answers.count { it.isCorrect }
        val total = answers.size
        val category = _activeQuizCategory.value
        val isDaily = _isDailyQuiz.value
        val wrong = answers.filter { !it.isCorrect }

        // Local-only study analytics. Firebase sync can be added later without changing quiz flow.
        runCatching {
            val prefs = getApplication<Application>().getSharedPreferences("skillplay_study_boost", 0)
            val previousWrong = prefs.getStringSet("wrong_question_ids", emptySet())?.toMutableSet() ?: mutableSetOf()
            wrong.forEach { previousWrong.add(it.question.id) }
            prefs.edit()
                .putStringSet("wrong_question_ids", previousWrong)
                .putInt("wrong_count_${category}", prefs.getInt("wrong_count_${category}", 0) + wrong.size)
                .putInt("weekly_points_${SimpleDateFormat("YYYY-ww", Locale.US).format(Date())}",
                    prefs.getInt("weekly_points_${SimpleDateFormat("YYYY-ww", Locale.US).format(Date())}", 0) + correctCount)
                .putInt("weekly_quizzes_${SimpleDateFormat("YYYY-ww", Locale.US).format(Date())}",
                    prefs.getInt("weekly_quizzes_${SimpleDateFormat("YYYY-ww", Locale.US).format(Date())}", 0) + 1)
                .apply()
        }

        viewModelScope.launch {
            if (isDaily) {
                val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val xpAward = correctCount * 15 + 25 // 25 bonus for completing daily challenge
                val claimed = repository.claimDailyChallenge(todayStr, correctCount, total, xpAward)
                if (!claimed) {
                    _userMessage.value = if (isHindi.value) "आज की दैनिक चुनौती पहले ही दर्ज है" else "Today's daily challenge already claimed"
                }
            } else {
                val scorePercent = if (total > 0) (correctCount * 100 / total) else 0
                repository.completeLesson("quiz_$category", category, scorePercent, correctCount * 10)
            }
        }

        navigateTo(AppScreen.QuizResult(correctCount, total, category, isDaily, wrong))
    }

    fun retryQuiz() {
        _quizIndex.value = 0
        _selectedOption.value = null
        _isAnswerSubmitted.value = false
        _quizAnswers.value = mutableListOf()
        val currentList = _screenStack.value
        // Pop result screen and stay on active quiz
        if (currentList.size > 1) {
            _screenStack.value = currentList.dropLast(1)
        }
    }

    // English Practice
    fun recordEnglishCompletion(exercise: EnglishExercise, userNotes: String = "") {
        viewModelScope.launch {
            repository.recordEnglishProgress(exercise.id, exercise.moduleType.name, userNotes)
            _userMessage.value = if (isHindi.value) "अभ्यास पूरा हुआ! +25 XP" else "Practice recorded! +25 XP"
        }
    }

    // Community Actions
    fun createCommunityPost(content: String, postType: String = "text", pollOptions: String = "") {
        if (content.isBlank()) return
        viewModelScope.launch {
            val user = repository.getUserProfile()
            val handle = "@" + user.displayName.lowercase(Locale.ROOT).replace(" ", "_")
            repository.createPost(
                authorName = user.displayName,
                authorHandle = handle,
                avatar = user.avatarIndex,
                content = content.trim(),
                postType = postType,
                pollOptions = pollOptions
            )
            _userMessage.value = if (isHindi.value) "पोस्ट सफलतापूर्वक प्रकाशित हुई" else "Post created successfully"
        }
    }

    fun toggleLike(postId: Long) {
        viewModelScope.launch {
            repository.toggleLikePost(postId)
        }
    }

    fun votePoll(postId: Long, index: Int) {
        viewModelScope.launch {
            repository.votePoll(postId, index)
        }
    }

    fun openComments(postId: Long) {
        _selectedPostIdForComments.value = postId
        viewModelScope.launch {
            repository.getCommentsForPostFlow(postId).collect {
                _currentComments.value = it
            }
        }
    }

    fun closeComments() {
        _selectedPostIdForComments.value = null
        _currentComments.value = emptyList()
    }

    fun addComment(postId: Long, text: String) {
        if (text.isBlank()) return
        viewModelScope.launch {
            val user = repository.getUserProfile()
            repository.addComment(postId, user.displayName, user.avatarIndex, text.trim())
        }
    }

    fun reportPost(postId: Long) {
        viewModelScope.launch {
            repository.reportPost(postId)
            _userMessage.value = if (isHindi.value) "पोस्ट की समीक्षा के लिए रिपोर्ट की गई" else "Post reported for moderation review"
        }
    }

    fun deletePost(postId: Long) {
        viewModelScope.launch {
            repository.deletePost(postId)
            _userMessage.value = if (isHindi.value) "पोस्ट हटा दी गई" else "Post deleted"
        }
    }

    fun isDailyChallengeCompletedToday(): Boolean {
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        return challengeRecords.value.any { it.challengeKey == todayStr && it.isClaimed }
    }
}
