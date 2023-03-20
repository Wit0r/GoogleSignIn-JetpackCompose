package com.app.googlesignin.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.googlesignin.core.Resource
import com.app.googlesignin.data.AuthRepository
import com.app.googlesignin.domain.model.GoogleSignInState
import com.app.googlesignin.domain.model.SignInState
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {

    val currentUser: FirebaseUser?
        get() = repository.currentUser!!

    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun signInAnonymous() = viewModelScope.launch {
        repository.signInAnonymously().collect { result ->
            when(result) {
                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message!!))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success"))
                }
            }
        }
    }

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when(result) {
                is Resource.Error -> {
                    _googleState.value = GoogleSignInState(error = result.message!!)
                }
                is Resource.Loading -> {
                    _googleState.value = GoogleSignInState(loading = true)
                }
                is Resource.Success -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }
            }
        }
    }

    fun signOut() = repository.signInOut()

}