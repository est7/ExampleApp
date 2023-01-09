package com.example.mvvmjetpack.data.source.remote

import com.example.mvvmjetpack.data.apiservices.user.UserApi

class LoginRemoteDataSource(private val userApi: UserApi) : ILoginRemoteDataSource {

    override suspend fun login(username: String, password: String) = userApi.login(username, password)


    override suspend fun logout(){

    }
}
