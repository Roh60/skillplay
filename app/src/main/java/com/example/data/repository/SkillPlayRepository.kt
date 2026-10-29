package com.example.data.repository

import androidx.room.withTransaction

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
import com.example.data.model.CategoriesData
import com.example.data.model.EnglishExercise
import com.example.data.model.EnglishModuleType
import com.example.data.model.LearningCategory
import com.example.data.model.Lesson
import com.example.data.model.QuizQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface ISkillPlayRepository {
    fun getUserProfileFlow(): Flow<UserProfileEntity?>
    suspend fun getUserProfile(): UserProfileEntity
    suspend fun updateUserProfile(user: UserProfileEntity)
    suspend fun updateLanguage(lang: String)
    suspend fun updateGoalAndInterests(goalTrack: String, interestsCsv: String)
    suspend fun resetAllData()

    // Lessons
    fun getAllLessonProgressFlow(): Flow<List<LessonProgressEntity>>
    fun getSavedLessonsFlow(): Flow<List<LessonProgressEntity>>
    suspend fun completeLesson(lessonId: String, categoryId: String, scorePercent: Int, xpEarned: Int)
    suspend fun toggleSaveLesson(lessonId: String, categoryId: String)

    // Daily Challenges
    fun getAllChallengesFlow(): Flow<List<ChallengeRecordEntity>>
    suspend fun claimDailyChallenge(dateKey: String, score: Int, total: Int, xp: Int): Boolean

    // English
    fun getAllEnglishProgressFlow(): Flow<List<EnglishProgressEntity>>
    suspend fun recordEnglishProgress(exerciseId: String, moduleType: String, notes: String)

    // Community
    fun getVisibleCommunityPostsFlow(): Flow<List<CommunityPostEntity>>
    fun getReportedPostsFlow(): Flow<List<CommunityPostEntity>>
    fun getCommentsForPostFlow(postId: Long): Flow<List<CommunityCommentEntity>>
    suspend fun createPost(authorName: String, authorHandle: String, avatar: Int, content: String, postType: String, pollOptions: String): Long
    suspend fun toggleLikePost(postId: Long)
    suspend fun votePoll(postId: Long, optionIndex: Int)
    suspend fun addComment(postId: Long, authorName: String, avatar: Int, content: String)
    suspend fun reportPost(postId: Long)
    suspend fun blockUser(authorHandle: String)
    suspend fun deletePost(postId: Long)
}

class SkillPlayRepository(
    private val database: AppDatabase
) : ISkillPlayRepository {

    private val userDao = database.userDao()
    private val lessonDao = database.lessonDao()
    private val challengeDao = database.challengeDao()
    private val englishDao = database.englishDao()
    private val communityDao = database.communityDao()

    suspend fun initializeDefaultsIfNeeded() = withContext(Dispatchers.IO) {
        val existingUser = userDao.getUser()
        if (existingUser == null) {
            val todayStr = getTodayDateString()
            userDao.upsertUser(
                UserProfileEntity(
                    id = 1,
                    displayName = "Bharat Learner",
                    avatarIndex = 0,
                    stateLocation = "New Delhi, India",
                    totalXp = 0,
                    streakDays = 0,
                    lastActiveDate = todayStr,
                    targetDailyMinutes = 15,
                    languagePref = "en",
                    statedInterests = "gk,polity,interview,digital_safety",
                    primaryGoalTrack = "competitive_exams"
                )
            )
        } else {
            // Check streak continuity
            checkAndUpdateStreak(existingUser)
        }

        if (communityDao.getPostsCount() == 0) {
            DefaultCommunityData.initialPosts.forEach { post ->
                communityDao.insertPost(post)
            }
            DefaultCommunityData.initialComments.forEach { comment ->
                communityDao.insertComment(comment)
            }
        }
    }

    private fun getTodayDateString(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    private suspend fun checkAndUpdateStreak(user: UserProfileEntity) {
        val todayStr = getTodayDateString()
        if (user.lastActiveDate == todayStr) return

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            val lastDate = sdf.parse(user.lastActiveDate)
            val todayDate = sdf.parse(todayStr)
            if (lastDate != null && todayDate != null) {
                val diffDays = (todayDate.time - lastDate.time) / (1000 * 60 * 60 * 24)
                val newStreak = when {
                    diffDays == 1L -> user.streakDays + 1
                    diffDays > 1L -> 1 // Streak broken
                    else -> user.streakDays
                }
                userDao.upsertUser(user.copy(streakDays = newStreak, lastActiveDate = todayStr))
            }
        } catch (_: Exception) {
            userDao.upsertUser(user.copy(lastActiveDate = todayStr))
        }
    }

    override fun getUserProfileFlow(): Flow<UserProfileEntity?> = userDao.getUserFlow()

    override suspend fun getUserProfile(): UserProfileEntity = withContext(Dispatchers.IO) {
        userDao.getUser() ?: UserProfileEntity(lastActiveDate = getTodayDateString())
    }

    override suspend fun updateUserProfile(user: UserProfileEntity) = withContext(Dispatchers.IO) {
        userDao.upsertUser(user)
    }

    override suspend fun updateLanguage(lang: String) = withContext(Dispatchers.IO) {
        val user = getUserProfile()
        userDao.upsertUser(user.copy(languagePref = lang))
    }

    override suspend fun updateGoalAndInterests(goalTrack: String, interestsCsv: String) = withContext(Dispatchers.IO) {
        val user = getUserProfile()
        userDao.upsertUser(user.copy(primaryGoalTrack = goalTrack, statedInterests = interestsCsv))
    }

    override suspend fun resetAllData() = withContext(Dispatchers.IO) {
        userDao.clearUser()
        lessonDao.clearAll()
        challengeDao.clearAll()
        englishDao.clearAll()
        communityDao.clearPosts()
        communityDao.clearComments()
        initializeDefaultsIfNeeded()
    }

    override fun getAllLessonProgressFlow(): Flow<List<LessonProgressEntity>> =
        lessonDao.getAllProgressFlow()

    override fun getSavedLessonsFlow(): Flow<List<LessonProgressEntity>> =
        lessonDao.getSavedLessonsFlow()

    override suspend fun completeLesson(
        lessonId: String,
        categoryId: String,
        scorePercent: Int,
        xpEarned: Int
    ) = withContext(Dispatchers.IO) {
        val existing = lessonDao.getProgress(lessonId)
        val isFirstTime = existing == null || existing.completedAt == 0L
        lessonDao.upsertProgress(
            LessonProgressEntity(
                lessonId = lessonId,
                categoryId = categoryId,
                completedAt = System.currentTimeMillis(),
                scorePercent = scorePercent,
                isSaved = existing?.isSaved ?: false
            )
        )
        if (isFirstTime && xpEarned > 0) {
            val user = getUserProfile()
            userDao.upsertUser(
                user.copy(
                    totalXp = user.totalXp + xpEarned,
                    totalQuizzesTaken = user.totalQuizzesTaken + 1,
                    totalQuizzesCorrect = user.totalQuizzesCorrect + (scorePercent * 5 / 100),
                    totalQuestionsAnswered = user.totalQuestionsAnswered + 5
                )
            )
        }
    }

    override suspend fun toggleSaveLesson(lessonId: String, categoryId: String) = withContext(Dispatchers.IO) {
        val existing = lessonDao.getProgress(lessonId)
        if (existing != null) {
            lessonDao.upsertProgress(existing.copy(isSaved = !existing.isSaved))
        } else {
            lessonDao.upsertProgress(
                LessonProgressEntity(
                    lessonId = lessonId,
                    categoryId = categoryId,
                    completedAt = 0L,
                    scorePercent = 0,
                    isSaved = true
                )
            )
        }
    }

    override fun getAllChallengesFlow(): Flow<List<ChallengeRecordEntity>> =
        challengeDao.getAllChallengesFlow()

    override suspend fun claimDailyChallenge(
        dateKey: String,
        score: Int,
        total: Int,
        xp: Int
    ): Boolean = withContext(Dispatchers.IO) {
        database.withTransaction {
            val existing = challengeDao.getChallenge(dateKey)
            if (existing != null && existing.isClaimed) {
                return@withTransaction false
            }
            challengeDao.insertChallenge(
                ChallengeRecordEntity(
                    challengeKey = dateKey,
                    completedAt = System.currentTimeMillis(),
                    score = score.coerceAtLeast(0).coerceAtMost(total.coerceAtLeast(0)),
                    totalQuestions = total.coerceAtLeast(0),
                    xpEarned = xp.coerceAtLeast(0),
                    isClaimed = true
                )
            )
            val user = userDao.getUser() ?: UserProfileEntity(lastActiveDate = getTodayDateString())
            userDao.upsertUser(
                user.copy(
                    totalXp = user.totalXp + xp.coerceAtLeast(0),
                    totalQuizzesTaken = user.totalQuizzesTaken + 1,
                    totalQuizzesCorrect = user.totalQuizzesCorrect + score.coerceAtLeast(0).coerceAtMost(total.coerceAtLeast(0)),
                    totalQuestionsAnswered = user.totalQuestionsAnswered + total.coerceAtLeast(0)
                )
            )
            true
        }
    }

    override fun getAllEnglishProgressFlow(): Flow<List<EnglishProgressEntity>> =
        englishDao.getAllProgressFlow()

    override suspend fun recordEnglishProgress(
        exerciseId: String,
        moduleType: String,
        notes: String
    ) = withContext(Dispatchers.IO) {
        database.withTransaction {
            val isFirstCompletion = englishDao.getProgress(exerciseId) == null
            englishDao.upsertProgress(
                EnglishProgressEntity(
                    exerciseId = exerciseId,
                    moduleType = moduleType,
                    completedAt = System.currentTimeMillis(),
                    userNotes = notes.take(2000)
                )
            )
            if (isFirstCompletion) {
                val user = userDao.getUser() ?: UserProfileEntity(lastActiveDate = getTodayDateString())
                userDao.upsertUser(user.copy(totalXp = user.totalXp + 25))
            }
        }
    }

    override fun getVisibleCommunityPostsFlow(): Flow<List<CommunityPostEntity>> =
        communityDao.getVisiblePostsFlow()

    override fun getReportedPostsFlow(): Flow<List<CommunityPostEntity>> =
        communityDao.getReportedPostsFlow()

    override fun getCommentsForPostFlow(postId: Long): Flow<List<CommunityCommentEntity>> =
        communityDao.getCommentsForPostFlow(postId)

    override suspend fun createPost(
        authorName: String,
        authorHandle: String,
        avatar: Int,
        content: String,
        postType: String,
        pollOptions: String
    ): Long = withContext(Dispatchers.IO) {
        val cleanContent = content.trim().take(1000)
        require(cleanContent.isNotBlank()) { "Post content cannot be empty" }
        val cleanOptions = if (postType == "poll") {
            pollOptions.split(":::").map { it.trim().take(120) }.filter { it.isNotBlank() }.distinct().take(4)
                .joinToString(":::")
        } else ""
        require(postType != "poll" || cleanOptions.split(":::").count() >= 2) {
            "A poll needs at least two different options"
        }
        val safeType = if (postType == "poll") "poll" else "text"
        val post = CommunityPostEntity(
            authorName = authorName.trim().take(80).ifBlank { "Learner" },
            authorHandle = authorHandle.trim().take(80),
            authorAvatar = avatar.coerceAtLeast(0),
            content = cleanContent,
            postType = safeType,
            pollOptions = cleanOptions,
            pollVotes = if (cleanOptions.isNotEmpty()) {
                cleanOptions.split(":::").map { "0" }.joinToString(",")
            } else "",
            userVotedIndex = -1,
            likeCount = 0,
            isLiked = false,
            createdAt = System.currentTimeMillis()
        )
        communityDao.insertPost(post)
    }

    override suspend fun toggleLikePost(postId: Long) = withContext(Dispatchers.IO) {
        val post = communityDao.getPostById(postId) ?: return@withContext
        val newLiked = !post.isLiked
        val newCount = if (newLiked) post.likeCount + 1 else (post.likeCount - 1).coerceAtLeast(0)
        communityDao.updatePost(post.copy(isLiked = newLiked, likeCount = newCount))
    }

    override suspend fun votePoll(postId: Long, optionIndex: Int) = withContext(Dispatchers.IO) {
        val post = communityDao.getPostById(postId) ?: return@withContext
        if (post.postType != "poll" || post.userVotedIndex != -1) return@withContext
        val votes = post.pollVotes.split(",").map { (it.toIntOrNull() ?: 0).coerceAtLeast(0) }.toMutableList()
        if (optionIndex in votes.indices) {
            votes[optionIndex] = votes[optionIndex] + 1
            communityDao.updatePost(
                post.copy(
                    pollVotes = votes.joinToString(","),
                    userVotedIndex = optionIndex
                )
            )
        }
    }

    override suspend fun addComment(
        postId: Long,
        authorName: String,
        avatar: Int,
        content: String
    ): Unit = withContext(Dispatchers.IO) {
        val cleanContent = content.trim().take(500)
        if (cleanContent.isBlank() || communityDao.getPostById(postId) == null) return@withContext
        communityDao.insertComment(
            CommunityCommentEntity(
                postId = postId,
                authorName = authorName.trim().take(80).ifBlank { "Learner" },
                authorAvatar = avatar.coerceAtLeast(0),
                content = cleanContent,
                createdAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun reportPost(postId: Long) = withContext(Dispatchers.IO) {
        val post = communityDao.getPostById(postId) ?: return@withContext
        communityDao.updatePost(post.copy(isReported = true))
    }

    override suspend fun blockUser(authorHandle: String) = withContext(Dispatchers.IO) {
        val handle = authorHandle.trim().take(80)
        if (handle.isBlank()) return@withContext
        communityDao.blockPostsByAuthor(handle)
    }

    override suspend fun deletePost(postId: Long) = withContext(Dispatchers.IO) {
        database.withTransaction {
            communityDao.deleteCommentsForPost(postId)
            communityDao.deletePost(postId)
        }
    }
}
