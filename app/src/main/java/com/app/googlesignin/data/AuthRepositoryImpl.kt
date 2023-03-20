package com.app.googlesignin.data

import com.app.googlesignin.core.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = auth.currentUser!!

    override fun signInAnonymously(): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = auth.signInAnonymously().await()
            emit(Resource.Success(result))
        }.catch { emit(Resource.Error(it.message.toString())) }
    }

    override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = auth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch { emit(Resource.Error(it.message.toString())) }
    }

    override fun signInOut() = auth.signOut()


}