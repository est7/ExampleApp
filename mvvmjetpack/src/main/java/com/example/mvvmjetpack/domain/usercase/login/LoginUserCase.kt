package com.example.mvvmjetpack.domain.usercase.login

import com.example.mvvmjetpack.base.exception.ApiException
import com.example.mvvmjetpack.base.net.Result
import com.example.mvvmjetpack.base.net.data
import com.example.mvvmjetpack.domain.base.BaseUserCase
import com.example.mvvmjetpack.domain.contract.ILoginRepository
import com.example.mvvmjetpack.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 在Use Case层中进行数据的操作和转化，
 * 所有情况下ViewModel都不应该更改来自 API 的任何数据，它只会将其发送到view.
 */
class LoginUserCase(private val loginRepository: ILoginRepository) : BaseUserCase() {

    suspend fun login(accout: String, pwd: String): Flow<UserInfo> {
        val userinfoResult = loginRepository.login(accout, pwd)

        return userinfoResult.map {
            when (it) {
                is Result.Success -> {
                    it.data
                }

                is Result.Error -> {
                    if (it.throwable is ApiException) {
                        throw it.throwable
                    } else {
                        throw Exception("服务器错误")
                    }
                }

                else -> {
                    throw Exception("未知错误")
                }
            }
        }

    }

    suspend fun logout(): Flow<Unit> {
        return loginRepository.logout("token")
    }
}
