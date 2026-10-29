package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserFlow(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    suspend fun getUser(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUser(user: UserProfileEntity)

    @Query("DELETE FROM user_profile")
    suspend fun clearUser()
}

@Dao
interface LessonDao {
    @Query("SELECT * FROM lesson_progress")
    fun getAllProgressFlow(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId LIMIT 1")
    suspend fun getProgress(lessonId: String): LessonProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: LessonProgressEntity)

    @Query("SELECT * FROM lesson_progress WHERE isSaved = 1")
    fun getSavedLessonsFlow(): Flow<List<LessonProgressEntity>>

    @Query("DELETE FROM lesson_progress")
    suspend fun clearAll()
}

@Dao
interface ChallengeDao {
    @Query("SELECT * FROM challenge_records ORDER BY completedAt DESC")
    fun getAllChallengesFlow(): Flow<List<ChallengeRecordEntity>>

    @Query("SELECT * FROM challenge_records WHERE challengeKey = :key LIMIT 1")
    suspend fun getChallenge(key: String): ChallengeRecordEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenge(record: ChallengeRecordEntity)

    @Query("DELETE FROM challenge_records")
    suspend fun clearAll()
}

@Dao
interface EnglishDao {
    @Query("SELECT * FROM english_progress")
    fun getAllProgressFlow(): Flow<List<EnglishProgressEntity>>

    @Query("SELECT * FROM english_progress WHERE exerciseId = :exerciseId LIMIT 1")
    suspend fun getProgress(exerciseId: String): EnglishProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: EnglishProgressEntity)

    @Query("DELETE FROM english_progress")
    suspend fun clearAll()
}

@Dao
interface CommunityDao {
    @Query("SELECT * FROM community_posts WHERE isReported = 0 AND isBlockedAuthor = 0 ORDER BY createdAt DESC")
    fun getVisiblePostsFlow(): Flow<List<CommunityPostEntity>>

    @Query("SELECT * FROM community_posts WHERE isReported = 1 ORDER BY createdAt DESC")
    fun getReportedPostsFlow(): Flow<List<CommunityPostEntity>>

    @Query("SELECT * FROM community_posts WHERE id = :id LIMIT 1")
    suspend fun getPostById(id: Long): CommunityPostEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: CommunityPostEntity): Long

    @Update
    suspend fun updatePost(post: CommunityPostEntity)

    @Query("DELETE FROM community_posts WHERE id = :id")
    suspend fun deletePost(id: Long)

    @Query("DELETE FROM community_comments WHERE postId = :postId")
    suspend fun deleteCommentsForPost(postId: Long)

    @Query("UPDATE community_posts SET isBlockedAuthor = 1 WHERE LOWER(authorHandle) = LOWER(:authorHandle)")
    suspend fun blockPostsByAuthor(authorHandle: String): Int

    @Query("SELECT * FROM community_comments WHERE postId = :postId ORDER BY createdAt ASC")
    fun getCommentsForPostFlow(postId: Long): Flow<List<CommunityCommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommunityCommentEntity): Long

    @Query("DELETE FROM community_posts")
    suspend fun clearPosts()

    @Query("DELETE FROM community_comments")
    suspend fun clearComments()

    @Query("SELECT COUNT(*) FROM community_posts")
    suspend fun getPostsCount(): Int
}
