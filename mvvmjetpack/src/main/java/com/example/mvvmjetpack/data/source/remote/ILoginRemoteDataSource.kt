package com.example.mvvmjetpack.data.source.remote

import com.example.mvvmjetpack.base.net.NetworkResponse
import com.example.mvvmjetpack.data.entities.LoginEntity
import kotlinx.coroutines.flow.Flow

interface ILoginRemoteDataSource{
    suspend fun login(username: String, password: String): Flow<NetworkResponse<LoginEntity>>
    suspend fun logout()
}