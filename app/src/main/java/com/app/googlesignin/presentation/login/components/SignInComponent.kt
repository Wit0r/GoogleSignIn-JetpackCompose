package com.app.googlesignin.presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
@ExperimentalMaterial3Api
fun SignInComponent(
    icon: Painter?,
    title: String,
    onClick: () -> Unit,
    contentDescription: String,
    cardHeight: Dp = 50.dp
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Icon(
                    modifier = Modifier.size(cardHeight/2),
                    painter = icon!!,
                    contentDescription = contentDescription
                )
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize
                )
            }
        },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = onClick
    )
}