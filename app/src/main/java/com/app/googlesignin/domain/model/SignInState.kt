package com.app.googlesignin.domain.model

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String = ""
)