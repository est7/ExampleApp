package com.example.mvvmjetpack.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.mvvmjetpack.base.BaseViewModel
import com.example.mvvmjetpack.domain.model.UserInfo
import com.example.mvvmjetpack.domain.usercase.login.LoginUserCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    class Success(val userInfo: UserInfo) : LoginState()
    data class Error(val msg: String) : LoginState()
}

class LoginViewModel(private val loginUserCase: LoginUserCase) : BaseViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(account: String, pwd: String) {
        viewModelScope.launch {
            loginUserCase.login(account, pwd).onStart {
                _loginState.value = LoginState.Loading
            }
                .catch {
                    _loginState.value = LoginState.Error(it.message ?: "未知错误")
                }
                .collect {
                    _loginState.value = LoginState.Success(it)
                }
        }
    }

}
