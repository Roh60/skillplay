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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Rule
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
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.SkillPlayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuidelinesScreen(
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
                        text = if (isHindi) "समुदाय दिशानिर्देश" else "Community Guidelines",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("guidelines_back_button")
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
                        containerColor = SaffronPrimary.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Rule,
                                contentDescription = null,
                                tint = SaffronPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = if (isHindi) "सुरक्षित और सकारात्मक शिक्षण वातावरण" else "Safe & Positive Learning Environment",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isHindi)
                                "स्किलप्ले इंडिया एक सम्मानजनक, समावेशी और ज्ञान-समृद्ध मंच है। सभी सदस्यों को इन नियमों का पालन करना अनिवार्य है।"
                            else
                                "SkillPlay India is a respectful, supportive, and knowledge-focused space. All members agree to uphold these standards.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            val rules = listOf(
                Pair(
                    if (isHindi) "1. परस्पर सम्मान और सौहार्द" else "1. Mutual Respect & Kindness",
                    if (isHindi) "अपमानजनक भाषा, घृणास्पद भाषण, व्यक्तिगत आक्षेप या उपहास बर्दाश्त नहीं किया जाएगा। हमेशा रचनात्मक संवाद रखें।"
                    else "Zero tolerance for harassment, derogatory language, hate speech, or trolling. Encourage and elevate fellow learners."
                ),
                Pair(
                    if (isHindi) "2. प्रामाणिक और सत्यापित जानकारी" else "2. Authentic Study Content",
                    if (isHindi) "परीक्षा और सामान्य ज्ञान के प्रश्नों में केवल सत्यापित तथ्यों को साझा करें। भ्रामक या अफवाह फैलाने वाली सामग्री पोस्ट न करें।"
                    else "Ensure questions and study tips are factually verified. Do not share unsubstantiated rumors or fake exam alerts."
                ),
                Pair(
                    if (isHindi) "3. स्पैम और विज्ञापनों का निषेध" else "3. No Spam or Commercial Ads",
                    if (isHindi) "किसी भी वाणिज्यिक उत्पाद, भुगतान वाले पाठ्यक्रमों या बाहरी लिंक का अनावश्यक प्रचार वर्जित है।"
                    else "Spamming promotional links, paid Telegram channels, or unauthorized commercial advertisements is prohibited."
                ),
                Pair(
                    if (isHindi) "4. गोपनीयता का सम्मान" else "4. Protect Personal Privacy",
                    if (isHindi) "सार्वजनिक पोस्ट या टिप्पणियों में अपना या दूसरों का फ़ोन नंबर, ईमेल या व्यक्तिगत विवरण साझा न करें।"
                    else "Never publish private phone numbers, home addresses, OTPs, or personally identifiable details in public threads."
                ),
                Pair(
                    if (isHindi) "5. मॉडरेशन और रिपोर्टिंग" else "5. Community Moderation & Reporting",
                    if (isHindi) "यदि कोई पोस्ट अनुचित लगती है, तो तुरंत 'Report' विकल्प का उपयोग करें। मॉडरेटर कतार में उसकी जांच कर उसे हटाया जाएगा।"
                    else "Use the in-app Report button whenever encountering violating content. Reported posts are reviewed and hidden."
                )
            )

            items(rules.size) { idx ->
                val (title, desc) = rules[idx]
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
                            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
