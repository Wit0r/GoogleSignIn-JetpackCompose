package com.app.googlesignin.presentation.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.googlesignin.R
import com.app.googlesignin.presentation.MainViewModel
import com.app.googlesignin.presentation.login.components.SignInComponent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource as Icon

@Composable
@ExperimentalMaterial3Api
fun LoginScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit,
    googleIcon: Painter = Icon(R.drawable.google_icon),
    anonymousIcon: Painter = Icon(R.drawable.anonymous_icon)
) {

    val googleSignInState = viewModel.googleState.value
    val state = viewModel.signInState.collectAsState(initial = null)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            content = {
                if (googleSignInState.loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                if (state.value?.isLoading == true) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignInComponent(
                icon = googleIcon,
                title = "Sign in with Google",
                contentDescription = stringResource(R.string.google_description),
                onClick = {
                    scope.launch {
                        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .requestIdToken("#WEBCLIENT") // YOUR WEB CLIENT ID (IN FIREBASE)
                            .build()

                        val googleSingInClient = GoogleSignIn.getClient(context, gso)
                        launcher.launch(googleSingInClient.signInIntent)
                    }
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            SignInComponent(
                icon = anonymousIcon,
                title = "Sign in Anonymous",
                contentDescription = stringResource(R.string.anonymous_description),
                onClick = {
                    scope.launch {
                        viewModel.signInAnonymous()
                    }
                }
            )

        }
    }


    /// Google Sign in
    LaunchedEffect(key1 = googleSignInState.success) {
        scope.launch {
            if (googleSignInState.success != null) {
                navigateToProfileScreen()
            }
        }
    }

    
    
    // Anonymous Sign in
    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                navigateToProfileScreen()
            }
        }
    }

}