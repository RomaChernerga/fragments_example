package com.example.mvp_mvvm_new.ui.login

import androidx.annotation.MainThread
import com.example.mvp_mvvm_new.utils.Publisher

interface LoginContracts {

    // MVP =  Model - View - Presenter
    /** проблемы:
     * 1. Восстановление состояния,
     * 2. Большая связность
     * 3. Наличие промежуточного кода(проверка на null, постоянные вызовы View)
     *
     * (M) <- (P) <-> (V)
     **/
    //  |
    // MVVM = Model - View - ViewModel
    /** задачи для MVVM
     *   (M) <- (MV) <- (V)
     **/

    /** View больше не нужен в MVVM, ее роль достается подпискам во ViewModel **/
//    @MainThread
//    interface View {
//        fun setSuccess()
//        fun setError(error: String)
//        fun showProgress()
//        fun hideProgress()
//    }

    /**
     * class Activity {
     *
     *  fun onCreate () {
     *      viewModel.shouldShowProgress.subscribe { it ->
     *          if(it) {
     *              dialog.show()
     *          } else {
     *              dialog.dismiss()
     *          }
     *      }
     *  }
     *
     * }
     */

    @MainThread
    interface ViewModel {
        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>

        @MainThread
        fun onLogin(login: String, password: String)
        fun onCredentialsChanges()
    }

//    @MainThread
//    interface Presenter {
//        fun onAttach(view: View)
//        fun onLogin(login: String, password: String)
//        fun onCredentialsChanges()
//    }


}