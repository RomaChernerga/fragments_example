package com.example.mvp_mvvm_new.ui.login

import androidx.annotation.MainThread

interface LoginContracts {

    @MainThread
    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    @MainThread
    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanges()
    }


}