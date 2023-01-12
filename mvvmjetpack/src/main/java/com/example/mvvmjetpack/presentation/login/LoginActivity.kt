package com.example.mvvmjetpack.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.base.binding
import com.example.mvvmjetpack.data.apiservices.user.UserApi
import com.example.mvvmjetpack.data.repository.login.LoginRepository
import com.example.mvvmjetpack.data.source.local.LoginLocalDataSource
import com.example.mvvmjetpack.data.source.remote.LoginRemoteDataSource
import com.example.mvvmjetpack.databinding.ActivityLoginBinding
import com.example.mvvmjetpack.domain.usercase.login.LoginUserCase
import com.example.mvvmjetpack.presentation.MainActivity
import com.example.utils.ktx.launchRepeatOnCreated
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding by binding<ActivityLoginBinding>()

    private val loginViewModel by lazy {
        LoginViewModel(
            LoginUserCase(
                LoginRepository(
                    LoginLocalDataSource(), LoginRemoteDataSource(UserApi())
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupViews()
        observeEvents()
    }

    private fun setupViews() {
//        binding.etAccount
//        binding.etPwd

        binding.btnLogin.setOnClickListener {
            loginViewModel.login(binding.etAccount.text.toString(), binding.etPwd.text.toString())

        }

        binding.btnRegister.setOnClickListener {

        }
    }

    private fun observeEvents() {
        launchRepeatOnCreated {
            loginViewModel.loginState.collect {
                when (it) {
                    is LoginViewModel.LoginState.Success -> {
                        switchLoading(false)
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }

                    is LoginViewModel.LoginState.Error -> {
                        switchLoading(false)
                        Snackbar.make(binding.root, it.msg, Snackbar.LENGTH_SHORT).show()
                    }

                    LoginViewModel.LoginState.Idle -> {
                        //初始状态，可以从本地获取数据
                        switchLoading(false)
                    }

                    LoginViewModel.LoginState.Loading -> {
                        // 可以显示一个Loading
                        switchLoading(true)
                    }
                }
            }


        }
    }


    private fun switchLoading(visible: Boolean) {
            if (visible) {
                binding.loading.show()
            } else {
                binding.loading.hide()
            }
    }

}
