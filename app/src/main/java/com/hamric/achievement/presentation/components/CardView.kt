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
import com.hamric.achievement.R
import com.hamric.achievement.domain.model.Achievement

@Composable
fun CardView(
    item: Achievement,
    onDetailTap: () -> Unit
) {
    // State for status dot color (recomposes when item changes)
    val statusDotColor = remember(item.currentTarget, item.minimumTarget) {
        if (item.currentTarget > item.minimumTarget) Color(0xFF4CAF50) else Color.Red
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column {
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

                    // Info Icon (assuming you have the icon in res/drawable)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info_circle),
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
                            append(item.currentTarget.toString())
                        }
                        append(" dari")
                        if (item.type == "Coverage") append(" Target")
                        append(" ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(item.target.toString())
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

            // Detail Button
            Button(
                onClick = onDetailTap,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF5F5F5)
                ),
                contentPadding = PaddingValues(horizontal = 10.dp)
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