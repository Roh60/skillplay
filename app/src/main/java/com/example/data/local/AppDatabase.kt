package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserProfileEntity::class,
        LessonProgressEntity::class,
        ChallengeRecordEntity::class,
        EnglishProgressEntity::class,
        CommunityPostEntity::class,
        CommunityCommentEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun lessonDao(): LessonDao
    abstract fun challengeDao(): ChallengeDao
    abstract fun englishDao(): EnglishDao
    abstract fun communityDao(): CommunityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "skillplay_database"
                )
                    // Never silently erase learning progress when the schema changes.
                    // Add an explicit Room Migration before increasing the database version.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
