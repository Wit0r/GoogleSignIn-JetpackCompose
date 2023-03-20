package com.app.googlesignin.data

import com.app.googlesignin.core.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val currentUser: FirebaseUser?
    fun signInAnonymously(): Flow<Resource<AuthResult>>
    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>

    fun signInOut()

}