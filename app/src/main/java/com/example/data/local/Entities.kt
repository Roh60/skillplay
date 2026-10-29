package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val displayName: String = "Bharat Learner",
    val avatarIndex: Int = 0,
    val stateLocation: String = "New Delhi, India",
    val totalXp: Int = 150,
    val streakDays: Int = 2,
    val lastActiveDate: String = "2026-10-25",
    val targetDailyMinutes: Int = 15,
    val languagePref: String = "en",
    val isPrivateProfile: Boolean = false,
    val totalQuizzesTaken: Int = 3,
    val totalQuizzesCorrect: Int = 12,
    val totalQuestionsAnswered: Int = 15,
    val statedInterests: String = "gk,polity,interview,digital_safety",
    val primaryGoalTrack: String = "competitive_exams"
)

@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey val lessonId: String,
    val categoryId: String,
    val completedAt: Long = 0L,
    val scorePercent: Int = 0,
    val isSaved: Boolean = false
)

@Entity(tableName = "challenge_records")
data class ChallengeRecordEntity(
    @PrimaryKey val challengeKey: String,
    val completedAt: Long = System.currentTimeMillis(),
    val score: Int = 0,
    val totalQuestions: Int = 5,
    val xpEarned: Int = 0,
    val isClaimed: Boolean = true
)

@Entity(tableName = "english_progress")
data class EnglishProgressEntity(
    @PrimaryKey val exerciseId: String,
    val moduleType: String,
    val completedAt: Long = System.currentTimeMillis(),
    val userNotes: String = ""
)

@Entity(tableName = "community_posts")
data class CommunityPostEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val authorName: String,
    val authorHandle: String,
    val authorAvatar: Int,
    val badgeTitle: String = "Active Scholar",
    val content: String,
    val postType: String = "text", // "text" or "poll"
    val pollOptions: String = "", // Delimited by ":::"
    val pollVotes: String = "", // Delimited by ","
    val userVotedIndex: Int = -1,
    val likeCount: Int = 0,
    val isLiked: Boolean = false,
    val isReported: Boolean = false,
    val isBlockedAuthor: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "community_comments")
data class CommunityCommentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val postId: Long,
    val authorName: String,
    val authorAvatar: Int,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)
