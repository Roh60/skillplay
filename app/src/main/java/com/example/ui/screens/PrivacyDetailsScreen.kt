package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ChakraBlue
import com.example.ui.theme.IndiaGreen
import com.example.ui.viewmodel.SkillPlayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyDetailsScreen(
    viewModel: SkillPlayViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val isHindi by viewModel.isHindi.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHindi) "गोपनीयता और डेटा सुरक्षा" else "Privacy & Data Security",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("privacy_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ChakraBlue.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = null,
                                tint = ChakraBlue,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = if (isHindi) "उपयोगकर्ता-प्रथम गोपनीयता सिद्धांत" else "User-First Privacy Principles",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isHindi)
                                "SkillPlay India आपको बिना किसी व्यक्तिगत जानकारी या खाते के अनिवार्य पंजीकरण के पूरी तरह से सीखने और अभ्यास करने की स्वतंत्रता देता है।"
                            else
                                "SkillPlay India lets you learn, test, and practice freely without mandating personal emails, phone numbers, or third-party trackers.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            val privacyPoints = listOf(
                Pair(
                    if (isHindi) "1. स्थानीय भंडारण (On-Device Storage)" else "1. Pure Local On-Device Storage",
                    if (isHindi) "आपकी सभी अध्ययन प्रगति, क्विज़ सटीकता, स्ट्रीक रिकॉर्ड और सहेजे गए पाठ केवल आपके डिवाइस के स्थानीय Room डेटाबेस में सुरक्षित रहते हैं।"
                    else "All your quiz scores, completed lessons, daily streaks, and saved bookmarks reside safely in your phone's private Room database."
                ),
                Pair(
                    if (isHindi) "2. कोई संवेदनशील अनुमति नहीं (Zero Dangerous Permissions)" else "2. Zero Sensitive Permissions",
                    if (isHindi) "यह ऐप कॉन्टैक्ट्स, कैमरा, गैलरी या स्थान जैसी संवेदनशील अनुमतियों की मांग नहीं करता।"
                    else "The app does not ask for or access your contacts, camera, photos, or GPS location."
                ),
                Pair(
                    if (isHindi) "3. स्थानीय डेमो समुदाय (Private Demo Community)" else "3. Local Demo Community",
                    if (isHindi) "समुदाय पोस्ट और टिप्पणियां आपके फ़ोन पर स्थानीय रूप से कार्य करती हैं और किसी अज्ञात सार्वजनिक सर्वर पर लीक नहीं होती हैं।"
                    else "Community interactions run within a safe local demo environment, ensuring no unsolicited exposure of your study notes."
                ),
                Pair(
                    if (isHindi) "4. एक-क्लिक डेटा विलोपन (Instant Data Erasure)" else "4. One-Click Instant Data Erasure",
                    if (isHindi) "आप सेटिंग्स स्क्रीन से कभी भी अपना संपूर्ण डेटा एक क्लिक में पूरी तरह से मिटा सकते हैं।"
                    else "You have full sovereign control to permanently purge your local database at any time from Settings."
                )
            )

            items(privacyPoints.size) { idx ->
                val (title, text) = privacyPoints[idx]
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = IndiaGreen,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
