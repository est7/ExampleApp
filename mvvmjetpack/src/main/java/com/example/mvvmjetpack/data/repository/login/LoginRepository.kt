package com.example.mvvmjetpack.data.repository.login

import com.example.mvvmjetpack.data.source.local.LoginLocalDataSource
import com.example.mvvmjetpack.data.source.remote.LoginRemoteDataSource
import com.example.mvvmjetpack.domain.contract.ILoginRepository
import com.example.mvvmjetpack.data.mapper.UserInfoTransform
import com.example.mvvmjetpack.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginRepository(
    private val loginLocalDataSource: LoginLocalDataSource,
    private val loginRemoteDataSource: LoginRemoteDataSource
) : ILoginRepository {
    override suspend fun login(userName: String, password: String): Flow<UserInfo> {
        return loginRemoteDataSource.login(userName, password).map {
            UserInfoTransform.transform(it)
        }
    }

    override suspend fun logout(token: String): Flow<Unit> {
        TODO()
    }

}
