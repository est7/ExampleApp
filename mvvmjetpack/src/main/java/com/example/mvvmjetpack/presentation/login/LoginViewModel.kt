package com.example.mvvmjetpack.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmjetpack.base.BaseViewModel
import com.example.mvvmjetpack.domain.model.UserInfo
import com.example.mvvmjetpack.domain.usercase.login.LoginUserCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserCase: LoginUserCase) : BaseViewModel() {


    fun login(accout: String, pwd: String) {
        viewModelScope.launch {
            loginUserCase.login(accout, pwd).catch {

            }.collect {

            }
        }
    }

}