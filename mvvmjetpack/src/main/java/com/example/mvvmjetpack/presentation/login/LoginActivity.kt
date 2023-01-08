package com.example.mvvmjetpack.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.base.binding
import com.example.mvvmjetpack.MainActivity
import com.example.mvvmjetpack.data.repository.login.LoginRepository
import com.example.mvvmjetpack.databinding.ActivityLoginBinding
import com.example.utils.ktx.launchRepeatOnCreated
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding by binding<ActivityLoginBinding>()

    private val loginModel by lazy { LoginViewModel(LoginRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
        observeEvents()
    }

    private fun setupViews() {

        binding.btnLogin.setOnClickListener {
            loginModel.login()?.observe(this, Observer { user ->
                user?.let {
                    AppPrefsUtils.putLong(BaseConstant.SP_USER_ID, it.id)
                    AppPrefsUtils.putString(BaseConstant.SP_USER_NAME, it.account)
                    val intent = Intent(context, MainActivity::class.java)
                    context!!.startActivity(intent)
                    Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show()
                }
            })

        }

        binding.btnRegister.setOnClickListener {

        }

        binding.etAccountk

        binding.etPwd
    }

    private fun observeEvents() {
        launchRepeatOnCreated {

//            nfcViewModel.event.collect {
//                when (it) {
//                    NfcState.WrongCvc -> handleWrongCvc()
//                    NfcState.LimitCvcInput -> handleLimitCvcInput()
//                }
//            }
        }
    }

}
