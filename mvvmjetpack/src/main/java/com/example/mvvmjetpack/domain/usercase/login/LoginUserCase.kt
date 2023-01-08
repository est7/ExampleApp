package com.example.mvvmjetpack.domain.usercase.login

import com.example.mvvmjetpack.domain.base.BaseUserCase
import com.example.mvvmjetpack.domain.contract.ILoginRepository
import com.example.mvvmjetpack.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

/**
 * 在Use Case层中进行数据的操作和转化，
 * 所有情况下ViewModel都不应该更改来自 API 的任何数据，它只会将其发送到view.
 */
class LoginUserCase(private val loginRepository: ILoginRepository) : BaseUserCase() {

    suspend fun login(accout: String, pwd: String): Flow<UserInfo> {
        return loginRepository.login(accout, pwd)
    }

    suspend fun logout(): Flow<Unit> {
        return loginRepository.logout("token")
    }
}
