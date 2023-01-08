package com.example.mvvmjetpack.data.source.remote

import com.example.mvvmjetpack.data.entities.LoginEntity
import kotlinx.coroutines.flow.Flow

interface ILoginRemoteDataSource{
    suspend fun login(username: String, password: String): Flow<LoginEntity>
    suspend fun logout()
}