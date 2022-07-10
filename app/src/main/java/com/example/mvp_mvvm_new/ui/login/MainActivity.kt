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
import com.example.mvp_mvvm_new.app
import com.example.mvp_mvvm_new.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
//    private var presenter: LoginContracts.Presenter? = null
    private var viewModel: LoginContracts.ViewModel? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        presenter = restorePresenter()
        viewModel = restoreViewModel()
//        presenter?.onAttach(this)
//        viewModel?.onAttach(this)

        onClickButtonLogin()

        viewModel?.shouldShowProgress?.subscribe(handler) { shouldShow ->
            if(shouldShow == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel?.isSuccess?.subscribe(handler) {
            if(it == true) {
                setSuccess()
            }
        }
        viewModel?.errorText?.subscribe(handler) {
            it?.let {
                val success = viewModel?.isSuccess?.value
                if(success == false) {
                    setError(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.apply {
            isSuccess.unSubscribeAll()
            errorText.unSubscribeAll()
            shouldShowProgress.unSubscribeAll()
        }
    }


    /** Клик по кнопке Enter **/
    private fun onClickButtonLogin() {
        binding.btnEnterLogin.setOnClickListener {
//            presenter?.onLogin(
            viewModel?.onLogin(
                binding.eTextLogin.text.toString(),
                binding.eTextPassword.text.toString()
            )
        }
    }

//    /** создаем презентер для последующей загрузки при необходимости **/
//    private fun restorePresenter() : LoginViewModel {
//        val presenter = lastCustomNonConfigurationInstance as? LoginViewModel
//
//        return presenter ?: LoginViewModel(app.loginUseCase)
//    }

        /** создаем презентер для последующей загрузки при необходимости **/
    private fun restoreViewModel() : LoginViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel

        return viewModel ?: LoginViewModel(app.loginUseCase)
    }

        /** методом возвращаем viewModel **/
    override fun onRetainCustomNonConfigurationInstance(): Any? {
//        return presenter
        return viewModel
    }

        /** Теперь реализация прописывается тут в onCreate подсредствои подписки */
//    @MainThread
//    override fun setSuccess() {
//        binding.apply {
//            eTextLogin.isVisible = false
//            eTextPassword.isVisible = false
//            btnEnterLogin.isVisible = false
//        }
//        binding.root.setBackgroundColor(Color.GREEN)
//    }

    @MainThread
    private fun setSuccess() {
        binding.apply {
            eTextLogin.isVisible = false
            eTextPassword.isVisible = false
            btnEnterLogin.isVisible = false
        }
        binding.root.setBackgroundColor(Color.GREEN)
    }

        /** Теперь реализация прописывается тут в onCreate подсредствои подписки */
//    @MainThread
//    override fun setError(error: String) {
//        Toast.makeText(this, "Error $error", Toast.LENGTH_SHORT).show()
//    }

    @MainThread
    fun setError(error: String) {
        Toast.makeText(this, "Error $error", Toast.LENGTH_SHORT).show()
    }

        /** Теперь реализация прописывается тут в onCreate подсредствои подписки */
//     @MainThread
//    overide fun showProgress() {
//        binding.btnEnterLogin.isEnabled = false
//        hideKeyboard(this)
//    }
    @MainThread
    private fun showProgress() {
        binding.btnEnterLogin.isEnabled = false
        hideKeyboard(this)
    }

        /** Теперь реализация прописывается тут в onCreate подсредствои подписки */
//    override fun hideProgress() {
//        binding.btnEnterLogin.isEnabled = true
//    }
    @MainThread
    private fun hideProgress() {
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