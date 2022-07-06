package com.example.mvp_mvvm_new

import androidx.annotation.MainThread
import java.lang.Error

class LoginContracts {

    @MainThread
    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanges()
    }


}