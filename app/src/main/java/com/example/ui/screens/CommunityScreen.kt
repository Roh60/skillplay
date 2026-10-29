package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Poll
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.CommunityCommentEntity
import com.example.data.local.CommunityPostEntity
import com.example.ui.components.UserAvatarView
import com.example.ui.navigation.AppScreen
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()
    val posts by viewModel.communityPosts.collectAsState()
    val user by viewModel.userProfile.collectAsState()
    val selectedPostIdForComments by viewModel.selectedPostIdForComments.collectAsState()
    val currentComments by viewModel.currentComments.collectAsState()

    var showCreateDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Local Demo Notice & Guidelines Bar
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ChakraBlue.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = ChakraBlue,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isHindi) "स्थानीय समुदाय (डेमो मोड - डिवाइस पर सुरक्षित)" else "Local Community (Demo Mode - Saved on device)",
                                fontWeight = FontWeight.Bold,
                                color = ChakraBlue,
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (isHindi)
                                "ज्ञान साझा करें, पोल बनाएं, प्रश्न पूछें। सभी पोस्ट आपके फ़ोन पर सुरक्षित रूप से संग्रहीत हैं।"
                            else
                                "Share study tips, create polls, vote, and discuss questions. All posts stored locally on your device.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = ChakraBlue.copy(alpha = 0.15f),
                                modifier = Modifier
                                    .clickable { viewModel.navigateTo(AppScreen.CommunityGuidelines) }
                                    .testTag("community_guidelines_chip")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Rule,
                                        contentDescription = null,
                                        tint = ChakraBlue,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isHindi) "नियम व दिशानिर्देश" else "Guidelines",
                                        color = ChakraBlue,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .clickable { viewModel.navigateTo(AppScreen.ModerationQueue) }
                                    .testTag("moderation_queue_chip")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Shield,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp),
                                        tint = SaffronPrimary
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isHindi) "मॉडरेशन कतार" else "Moderation",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = SaffronPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Post creation preview bar
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCreateDialog = true }
                        .testTag("create_post_trigger_card")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        UserAvatarView(avatarIndex = user?.avatarIndex ?: 0, size = 38)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = if (isHindi) "कुछ विचार, अध्ययन सुझाव या पोल साझा करें..." else "Share a study tip, query, or poll...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Community Feed
            items(posts) { post ->
                CommunityPostCard(
                    post = post,
                    isHindi = isHindi,
                    currentUserName = user?.displayName ?: "",
                    onLike = { viewModel.toggleLike(post.id) },
                    onVote = { optIdx -> viewModel.votePoll(post.id, optIdx) },
                    onOpenComments = { viewModel.openComments(post.id) },
                    onReport = { viewModel.reportPost(post.id) },
                    onDelete = { viewModel.deletePost(post.id) }
                )
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { showCreateDialog = true },
            containerColor = SaffronPrimary,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .testTag("community_fab")
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Create Post")
        }
    }

    // Create Post Dialog
    if (showCreateDialog) {
        CreatePostDialog(
            isHindi = isHindi,
            onDismiss = { showCreateDialog = false },
            onSubmit = { content, postType, pollOptions ->
                viewModel.createCommunityPost(content, postType, pollOptions)
                showCreateDialog = false
            }
        )
    }

    // Comments Bottom Sheet
    if (selectedPostIdForComments != null) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.closeComments() },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            CommentsSheetContent(
                postId = selectedPostIdForComments!!,
                comments = currentComments,
                isHindi = isHindi,
                onAddComment = { text ->
                    viewModel.addComment(selectedPostIdForComments!!, text)
                },
                onClose = { viewModel.closeComments() }
            )
        }
    }
}

@Composable
fun CommunityPostCard(
    post: CommunityPostEntity,
    isHindi: Boolean,
    currentUserName: String,
    onLike: () -> Unit,
    onVote: (Int) -> Unit,
    onOpenComments: () -> Unit,
    onReport: () -> Unit,
    onDelete: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    var isFollowing by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("community_post_${post.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Author Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                UserAvatarView(avatarIndex = post.authorAvatar, size = 42)
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = post.authorName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SaffronPrimary.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = post.badgeTitle,
                                color = SaffronPrimary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Text(
                        text = post.authorHandle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Follow toggle
                TextButton(
                    onClick = { isFollowing = !isFollowing },
                    modifier = Modifier.testTag("follow_button_${post.id}")
                ) {
                    Text(
                        text = if (isFollowing) (if (isHindi) "फॉलो किया" else "Following")
                        else (if (isHindi) "+ फॉलो" else "+ Follow"),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isFollowing) IndiaGreen else MaterialTheme.colorScheme.primary
                    )
                }

                // More Options Menu
                Box {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(if (isHindi) "पोस्ट रिपोर्ट करें" else "Report Post") },
                            leadingIcon = { Icon(Icons.Default.Flag, contentDescription = null) },
                            onClick = {
                                showMenu = false
                                onReport()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(if (isHindi) "उपयोगकर्ता को ब्लॉक करें" else "Block User") },
                            leadingIcon = { Icon(Icons.Default.PersonRemove, contentDescription = null) },
                            onClick = {
                                showMenu = false
                                onReport() // marks & suppresses
                            }
                        )
                        if (post.authorName == currentUserName) {
                            DropdownMenuItem(
                                text = { Text(if (isHindi) "हटाएं" else "Delete Post", color = ErrorRed) },
                                leadingIcon = { Icon(Icons.Default.DeleteOutline, contentDescription = null, tint = ErrorRed) },
                                onClick = {
                                    showMenu = false
                                    onDelete()
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Post Content Text
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            )

            // If Poll Post, render options with voting
            if (post.postType == "poll" && post.pollOptions.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                val options = post.pollOptions.split(":::")
                val votes = post.pollVotes.split(",").map { it.toIntOrNull() ?: 0 }
                val totalVotes = votes.sum().coerceAtLeast(1)

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    options.forEachIndexed { idx, opt ->
                        val optVotes = votes.getOrElse(idx) { 0 }
                        val votePercent = (optVotes.toFloat() / totalVotes).coerceIn(0f, 1f)
                        val hasVotedThis = post.userVotedIndex == idx

                        Card(
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (hasVotedThis) SaffronPrimary.copy(alpha = 0.12f)
                                else MaterialTheme.colorScheme.surfaceVariant
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(enabled = post.userVotedIndex == -1) {
                                    onVote(idx)
                                }
                                .testTag("poll_option_${post.id}_$idx")
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = opt,
                                        fontWeight = if (hasVotedThis) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (post.userVotedIndex != -1) {
                                        Text(
                                            text = "${(votePercent * 100).toInt()}%",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = SaffronPrimary
                                        )
                                    }
                                }

                                if (post.userVotedIndex != -1) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    LinearProgressIndicator(
                                        progress = { votePercent },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(4.dp),
                                        color = SaffronPrimary,
                                        trackColor = MaterialTheme.colorScheme.surface,
                                        strokeCap = StrokeCap.Round
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action Row: Like & Comment
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onLike() }
                        .padding(end = 16.dp, top = 4.dp, bottom = 4.dp)
                        .testTag("like_button_${post.id}")
                ) {
                    Icon(
                        imageVector = if (post.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (post.isLiked) ErrorRed else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${post.likeCount}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onOpenComments() }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("comment_button_${post.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = "Comment",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(19.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isHindi) "टिप्पणियां" else "Comments",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun CreatePostDialog(
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (content: String, postType: String, pollOptions: String) -> Unit
) {
    var selectedType by remember { mutableStateOf("text") }
    var content by remember { mutableStateOf("") }
    var pollOpt1 by remember { mutableStateOf("") }
    var pollOpt2 by remember { mutableStateOf("") }
    var pollOpt3 by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (isHindi) "नई पोस्ट बनाएं" else "Create Community Post",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                TabRow(
                    selectedTabIndex = if (selectedType == "text") 0 else 1,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    Tab(
                        selected = selectedType == "text",
                        onClick = { selectedType = "text" },
                        text = { Text(if (isHindi) "विचार / सुझाव" else "Text Post") }
                    )
                    Tab(
                        selected = selectedType == "poll",
                        onClick = { selectedType = "poll" },
                        text = { Text(if (isHindi) "पोल (Poll)" else "Poll") }
                    )
                }

                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = {
                        Text(
                            text = if (selectedType == "poll")
                                (if (isHindi) "पोल का प्रश्न यहाँ लिखें..." else "Ask a question for your poll...")
                            else
                                (if (isHindi) "अध्ययन सुझाव या अनुभव साझा करें..." else "Share your learning tip, query or update..."),
                            fontSize = 13.sp
                        )
                    },
                    minLines = 3,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("create_post_input")
                )

                if (selectedType == "poll") {
                    Text(
                        text = if (isHindi) "विकल्प (कम से कम 2 भरें):" else "Poll Options (enter at least 2):",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = pollOpt1,
                        onValueChange = { pollOpt1 = it },
                        placeholder = { Text("Option 1", fontSize = 12.sp) },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = pollOpt2,
                        onValueChange = { pollOpt2 = it },
                        placeholder = { Text("Option 2", fontSize = 12.sp) },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = pollOpt3,
                        onValueChange = { pollOpt3 = it },
                        placeholder = { Text("Option 3 (Optional)", fontSize = 12.sp) },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val pollString = if (selectedType == "poll") {
                        listOf(pollOpt1, pollOpt2, pollOpt3).filter { it.isNotBlank() }.joinToString(":::")
                    } else ""
                    onSubmit(content, selectedType, pollString)
                },
                enabled = content.isNotBlank() && (selectedType == "text" || (pollOpt1.isNotBlank() && pollOpt2.isNotBlank())),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                modifier = Modifier.testTag("publish_post_button")
            ) {
                Text(text = if (isHindi) "प्रकाशित करें" else "Publish", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = if (isHindi) "रद्द करें" else "Cancel")
            }
        }
    )
}

@Composable
fun CommentsSheetContent(
    postId: Long,
    comments: List<CommunityCommentEntity>,
    isHindi: Boolean,
    onAddComment: (String) -> Unit,
    onClose: () -> Unit
) {
    var newCommentText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isHindi) "टिप्पणियां (${comments.size})" else "Comments (${comments.size})",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f, fill = false)
                .height(280.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (comments.isEmpty()) {
                item {
                    Text(
                        text = if (isHindi) "अभी तक कोई टिप्पणी नहीं है। पहली टिप्पणी करें!" else "No comments yet. Be the first to share your thoughts!",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                items(comments) { comment ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                            .padding(10.dp)
                    ) {
                        UserAvatarView(avatarIndex = comment.authorAvatar, size = 32)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = comment.authorName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = comment.content,
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Comment Input
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = newCommentText,
                onValueChange = { newCommentText = it },
                placeholder = {
                    Text(
                        text = if (isHindi) "सकारात्मक टिप्पणी लिखें..." else "Write a constructive comment...",
                        fontSize = 13.sp
                    )
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("comment_input_field")
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (newCommentText.isNotBlank()) {
                        onAddComment(newCommentText)
                        newCommentText = ""
                    }
                },
                enabled = newCommentText.isNotBlank(),
                modifier = Modifier.testTag("send_comment_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = if (newCommentText.isNotBlank()) SaffronPrimary else Color.Gray
                )
            }
        }
    }
}
