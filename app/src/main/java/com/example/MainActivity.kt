package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.SkillPlayTopBar
import com.example.ui.navigation.AppScreen
import com.example.ui.navigation.MainTab
import com.example.ui.screens.CategoryLessonsScreen
import com.example.ui.screens.ChallengesScreen
import com.example.ui.screens.CommunityScreen
import com.example.ui.screens.DailyToolkitScreen
import com.example.ui.screens.StudyBoostScreen
import com.example.ui.screens.EnglishModuleDetailScreen
import com.example.ui.screens.EnglishPracticeHubScreen
import com.example.ui.screens.GuidelinesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LeaderboardScreen
import com.example.ui.screens.LearnScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.ModerationQueueScreen
import com.example.ui.screens.PrivacyDetailsScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.QuizResultScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ReviewWrongAnswersScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.SkillPlayTheme
import com.example.ui.viewmodel.SkillPlayViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: SkillPlayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillPlayTheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: SkillPlayViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()
    val currentScreen by viewModel.currentScreen.collectAsState()
    val user by viewModel.userProfile.collectAsState()
    val isHindi by viewModel.isHindi.collectAsState()
    val userMessage by viewModel.userMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(userMessage) {
        userMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.dismissUserMessage()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val screen = currentScreen) {
            is AppScreen.MainNavigation -> {
                Scaffold(
                    topBar = {
                        SkillPlayTopBar(
                            title = if (isHindi) "स्किलप्ले इंडिया" else "SkillPlay India",
                            streak = user?.streakDays ?: 1,
                            totalXp = user?.totalXp ?: 120,
                            isHindi = isHindi,
                            onToggleLanguage = { viewModel.toggleLanguage() }
                        )
                    },
                    bottomBar = {
                        SkillPlayBottomBar(
                            currentTab = currentTab,
                            isHindi = isHindi,
                            onSelectTab = { viewModel.selectTab(it) }
                        )
                    },
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentTab) {
                            MainTab.Home -> HomeScreen(viewModel = viewModel)
                            MainTab.Learn -> LearnScreen(viewModel = viewModel)
                            MainTab.Challenges -> ChallengesScreen(viewModel = viewModel)
                            MainTab.Community -> CommunityScreen(viewModel = viewModel)
                            MainTab.Profile -> ProfileScreen(viewModel = viewModel)
                        }
                    }
                }
            }

            is AppScreen.CategoryLessons -> {
                CategoryLessonsScreen(
                    categoryId = screen.categoryId,
                    viewModel = viewModel
                )
            }

            is AppScreen.LessonView -> {
                LessonDetailScreen(
                    lessonId = screen.lessonId,
                    viewModel = viewModel
                )
            }

            is AppScreen.QuizActive -> {
                QuizScreen(
                    categoryId = screen.categoryId,
                    difficulty = screen.difficulty,
                    isDaily = screen.isDaily,
                    viewModel = viewModel
                )
            }

            is AppScreen.QuizResult -> {
                QuizResultScreen(
                    score = screen.score,
                    total = screen.total,
                    categoryId = screen.categoryId,
                    isDaily = screen.isDaily,
                    wrongAnswers = screen.wrongAnswers,
                    viewModel = viewModel
                )
            }

            is AppScreen.ReviewWrong -> {
                ReviewWrongAnswersScreen(
                    wrongAnswers = screen.wrongAnswers,
                    viewModel = viewModel
                )
            }

            is AppScreen.DailyToolkit -> {
                DailyToolkitScreen(viewModel = viewModel, onBack = { viewModel.navigateBack() })
            }

            is AppScreen.StudyBoost -> {
                StudyBoostScreen(viewModel = viewModel, onBack = { viewModel.navigateBack() })
            }

            is AppScreen.EnglishHub -> {
                EnglishPracticeHubScreen(viewModel = viewModel)
            }

            is AppScreen.EnglishModuleView -> {
                EnglishModuleDetailScreen(
                    moduleType = screen.moduleType,
                    viewModel = viewModel
                )
            }

            is AppScreen.FullLeaderboard -> {
                LeaderboardScreen(viewModel = viewModel)
            }

            is AppScreen.CommunityGuidelines -> {
                GuidelinesScreen(viewModel = viewModel)
            }

            is AppScreen.ModerationQueue -> {
                ModerationQueueScreen(viewModel = viewModel)
            }

            is AppScreen.Settings -> {
                SettingsScreen(viewModel = viewModel)
            }

            is AppScreen.PrivacyDetails -> {
                PrivacyDetailsScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun SkillPlayBottomBar(
    currentTab: MainTab,
    isHindi: Boolean,
    onSelectTab: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("main_bottom_nav")
    ) {
        val tabs = listOf(
            Triple(MainTab.Home, Icons.Filled.Home, Icons.Outlined.Home),
            Triple(MainTab.Learn, Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
            Triple(MainTab.Challenges, Icons.Filled.EmojiEvents, Icons.Outlined.EmojiEvents),
            Triple(MainTab.Community, Icons.Filled.Groups, Icons.Outlined.Groups),
            Triple(MainTab.Profile, Icons.Filled.Person, Icons.Outlined.Person)
        )

        tabs.forEach { (tab, filledIcon, outlinedIcon) ->
            val isSelected = currentTab == tab
            NavigationBarItem(
                selected = isSelected,
                onClick = { onSelectTab(tab) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) filledIcon else outlinedIcon,
                        contentDescription = tab.titleEn
                    )
                },
                label = {
                    Text(
                        text = if (isHindi) tab.titleHi else tab.titleEn,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = SaffronPrimary.copy(alpha = 0.18f),
                    selectedIconColor = SaffronPrimary,
                    selectedTextColor = SaffronPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.testTag("nav_tab_${tab.route}")
            )
        }
    }
}
