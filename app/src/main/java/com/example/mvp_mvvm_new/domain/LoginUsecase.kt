package com.example.mvp_mvvm_new.domain

import androidx.annotation.MainThread

interface LoginUsecase {
    fun login(
        login: String,
        password: String,
        @MainThread callBack: (Boolean) -> Unit
    )
}


