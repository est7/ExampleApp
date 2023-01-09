package com.example.mvvmjetpack.domain.contract

import com.example.mvvmjetpack.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow
import com.example.mvvmjetpack.base.net.Result

interface ILoginRepository {
   suspend fun login(
        userName: String,
        password: String
    ): Flow<Result<UserInfo>>

    suspend fun logout(token:String): Flow<Unit>


}