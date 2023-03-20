package com.app.googlesignin.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.googlesignin.core.Constants.ANONYMOUS_DISPLAY_NAME
import com.app.googlesignin.core.Constants.ANONYMOUS_PHOTO_URL
import com.app.googlesignin.presentation.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val picturePhoto = viewModel.currentUser?.photoUrl ?: ANONYMOUS_PHOTO_URL
    val userName = viewModel.currentUser?.displayName ?: ANONYMOUS_DISPLAY_NAME

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = Shapes.Full),
                model = picturePhoto,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Hello, $userName!!",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}