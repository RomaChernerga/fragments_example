package com.example.mvp_mvvm_new.ui.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mvp_mvvm_new.App
import com.example.mvp_mvvm_new.app
import com.example.mvp_mvvm_new.data.LoginUsecaseImpl
import com.example.mvp_mvvm_new.databinding.ActivityMainBinding
import com.example.mvp_mvvm_new.domain.LoginUsecase


class MainActivity : AppCompatActivity(), LoginContracts.View {
    lateinit var binding: ActivityMainBinding
    private var presenter: LoginContracts.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = restorePresenter()
        presenter?.onAttach(this)

        onClickButtonLogin()
    }
    /** Клик по кнопке Enter **/
    private fun onClickButtonLogin() {
        binding.btnEnterLogin.setOnClickListener {
            presenter?.onLogin(
                binding.eTextLogin.text.toString(),
                binding.eTextPassword.text.toString()
            )
        }
    }

    /** создаем презентер для последующей загрузки при необходимости **/
    private fun restorePresenter() : LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter

        return presenter ?: LoginPresenter(app.loginUseCase)
    }

    /** методом возвращаем презентер **/
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }


    @MainThread
    override fun setSuccess() {
        binding.apply {
            eTextLogin.isVisible = false
            eTextPassword.isVisible = false
            btnEnterLogin.isVisible = false
        }
        binding.root.setBackgroundColor(Color.GREEN)
    }
    @MainThread
    override fun setError(error: String) {



        Toast.makeText(this, "Error $error", Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showProgress() {
        binding.btnEnterLogin.isEnabled = false
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.btnEnterLogin.isEnabled = true
    }

    /** Скрытие клавиатуры **/
    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}