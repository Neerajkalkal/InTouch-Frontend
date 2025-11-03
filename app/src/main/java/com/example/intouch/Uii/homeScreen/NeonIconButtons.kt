package com.example.intouch.Uii.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NeonIconButtons() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B12)), // dark background
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NeonButton(icon = Icons.Filled.Search)
        }
    }
}

@Composable
fun NeonButton(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = Color(0xFF151929),
                shape = RoundedCornerShape(11.dp)
            )
            .border(
                width = 0.5.dp,
                color = Color(0xFF151929).copy(alpha = 0.3f),
                shape = RoundedCornerShape(11.dp)
            )
            .shadow(
                elevation = 13.dp,
                spotColor = Color(0xFF151929).copy(alpha = 0.6f),
                ambientColor = Color(0xFF151929).copy(alpha = 0.6f),
                shape = RoundedCornerShape(11.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF4E7CFF),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0B0B12)
@Composable
fun NeonIconButtonsPreview() {
    NeonIconButtons()
}


@Composable
fun NeonIconButton() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B12)), // dark background
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NeonButton(icon = Icons.Filled.MoreVert)
        }
    }
}
