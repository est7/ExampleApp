package com.example.mvvmjetpack.domain.contract

import com.example.mvvmjetpack.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
   suspend fun login(
        userName: String,
        password: String
    ): Flow<UserInfo>

    suspend fun logout(token:String): Flow<Unit>


}