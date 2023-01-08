package com.example.mvvmjetpack.base.apiservices.user

import com.example.mvvmjetpack.data.entities.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserApi {

    suspend fun login(accout: String, pwd: String): Flow<LoginEntity> =
        withContext(Dispatchers.IO) {
            delay(2000)
            flow { emit(LoginEntity("tokenbidasfjalsdfj", "Nick_Lee", 0)) }
        }
}


/*
@ApiMaxLogLevel(MaxLogLevel.HEADERS)
interface UserApi {

    @Headers("Content-Type: text/plain; charset=utf-8")
    @POST("api/account/login/")
    fun login(@Body body: RequestBody): Flow<LoginEntity>

    @Headers("Content-Type: text/plain; charset=utf-8")
    @POST("api/account/existsuser/")
    fun existUser(@Body body: RequestBody): Flow<ExistUser>
}*/
