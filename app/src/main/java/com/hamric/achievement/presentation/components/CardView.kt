package com.hamric.achievement.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamric.achievement.ui.theme.Green60
import com.hamric.achievement.R
import com.hamric.achievement.domain.model.Achievement
import com.hamric.achievement.utils.formatWithSeparatorPattern
import com.hamric.achievement.ui.theme.Dark2
import com.hamric.achievement.ui.theme.Dark20
import com.hamric.achievement.ui.theme.red

@Composable
fun CardView(
    item: Achievement,
    onDetailTap: () -> Unit
) {
    val statusDotColor = remember(item.currentTarget, item.minimumTarget) {
        if (item.currentTarget > item.minimumTarget) Green60 else red
    }

    // ✅ Main container with border stroke (matching SwiftUI's .overlay)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Dark20,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))  // Clips content to same rounded shape
    ) {
        // White background content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            // Main Content
            Column(modifier = Modifier.padding(10.dp)) {
                // Header Row
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Status Dot
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(statusDotColor)
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.label,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.info_circle),
                        contentDescription = "Info",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Progress Text
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(item.currentTarget.formatWithSeparatorPattern(3u,'.'))
                        }
                        append(" dari")
                        if (item.type == "Coverage") append(" Target")
                        append(" ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(item.target.formatWithSeparatorPattern(3u,'.'))
                        }
                    },
                    fontSize = 12.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Progress Bar
                val progress = (item.currentTarget.toFloat() / item.target.toFloat()) * 100f
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LinearProgressIndicator(
                        progress = { progress / 100f },
                        modifier = Modifier
                            .weight(1f)
                            .height(8.dp),
                        trackColor = Color.LightGray,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${progress.toInt()}%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Bottom Button (matching SwiftUI)
            Button(
                onClick = onDetailTap,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Dark2
                ),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Lihat Detail ${item.type} >",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CardViewPreview() {
    MaterialTheme {
        CardView(
            item = Achievement(
                label = "Coverage",
                type = "Coverage",
                minimumTarget = 1000u,
                currentTarget = 10000u,
                target = 20000u
            )
        ) {}
    }
}
