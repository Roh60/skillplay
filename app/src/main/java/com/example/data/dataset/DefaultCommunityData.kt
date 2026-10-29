package com.example.data.dataset

import com.example.data.local.CommunityCommentEntity
import com.example.data.local.CommunityPostEntity
import com.example.data.model.LeaderboardUser

object DefaultCommunityData {
    val initialPosts = listOf(
        CommunityPostEntity(
            id = 1L,
            authorName = "Ananya Sharma",
            authorHandle = "@ananya_civil",
            authorAvatar = 1,
            badgeTitle = "Polity Scholar",
            content = "Quick study tip for UPSC and State PCS aspirants: Revise Articles 14 to 32 every Sunday morning. Connecting Fundamental Rights with recent landmark Supreme Court judgments makes the concepts stick much better!",
            postType = "text",
            likeCount = 24,
            isLiked = false,
            createdAt = System.currentTimeMillis() - 7200000L
        ),
        CommunityPostEntity(
            id = 2L,
            authorName = "Rajesh Verma",
            authorHandle = "@rajesh_quant",
            authorAvatar = 2,
            badgeTitle = "Math Ace",
            content = "Weekly Community Poll: When solving Profit & Loss problems in competitive exams, which technique helps you solve under 40 seconds?",
            postType = "poll",
            pollOptions = "Fraction/Ratio Method:::Standard Formula SP-CP:::100 Base Assumption:::Elimination of Options",
            pollVotes = "58,14,32,19",
            userVotedIndex = -1,
            likeCount = 38,
            isLiked = false,
            createdAt = System.currentTimeMillis() - 14400000L
        ),
        CommunityPostEntity(
            id = 3L,
            authorName = "Pooja Hegde",
            authorHandle = "@pooja_eng",
            authorAvatar = 3,
            badgeTitle = "Vocabulary Pro",
            content = "Word of the Day: 'Ubiquitous' (adjective) - Present, appearing, or found everywhere. Example: 'Smartphones have become ubiquitous across rural and urban India.' Try framing your own sentence below!",
            postType = "text",
            likeCount = 42,
            isLiked = false,
            createdAt = System.currentTimeMillis() - 28800000L
        )
    )

    val initialComments = listOf(
        CommunityCommentEntity(
            id = 1L,
            postId = 1L,
            authorName = "Vikram Singh",
            authorAvatar = 4,
            content = "Great advice! The correlation with Article 21 rights is especially crucial in mains exams.",
            createdAt = System.currentTimeMillis() - 3600000L
        ),
        CommunityCommentEntity(
            id = 2L,
            postId = 3L,
            authorName = "Kavita Rao",
            authorAvatar = 5,
            content = "UPI payments are now ubiquitous in every local market stall. Thanks for the example!",
            createdAt = System.currentTimeMillis() - 18000000L
        )
    )

    val weeklyLeaderboard = listOf(
        LeaderboardUser(rank = 1, name = "Aarav Patel", avatarIndex = 0, stateName = "Gujarat", points = 1840, streak = 21),
        LeaderboardUser(rank = 2, name = "Sneha Mukherjee", avatarIndex = 1, stateName = "West Bengal", points = 1720, streak = 18),
        LeaderboardUser(rank = 3, name = "Karthik Nair", avatarIndex = 2, stateName = "Kerala", points = 1690, streak = 15),
        LeaderboardUser(rank = 4, name = "Harpreet Kaur", avatarIndex = 3, stateName = "Punjab", points = 1530, streak = 12),
        LeaderboardUser(rank = 5, name = "Deepak Yadav", avatarIndex = 4, stateName = "Uttar Pradesh", points = 1480, streak = 14),
        LeaderboardUser(rank = 6, name = "Meera Joshi", avatarIndex = 5, stateName = "Maharashtra", points = 1390, streak = 9)
    )
}
