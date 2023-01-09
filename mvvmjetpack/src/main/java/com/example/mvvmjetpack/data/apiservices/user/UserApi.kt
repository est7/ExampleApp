package com.example.mvvmjetpack.data.apiservices.user

import com.example.mvvmjetpack.base.net.NetworkResponse
import com.example.mvvmjetpack.data.entities.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserApi {
    companion object {
        const val PWD_WRONG_ERROR = -10001
    }

    suspend fun login(account: String, pwd: String): Flow<NetworkResponse<LoginEntity>> =
        withContext(Dispatchers.IO) {
            flow {
                emit(mockRequest(account, pwd))
            }
        }
}


suspend fun mockRequest(account: String, pwd: String): NetworkResponse<LoginEntity> {
    delay(2000)
    return if (account == "123" && pwd == "123") {
        val loginEntity = LoginEntity("tokenbidasfjalsdfj", "Nick_Lee", 0)
        NetworkResponse.Success(loginEntity)
    } else {
        NetworkResponse.BizError(UserApi.PWD_WRONG_ERROR, "密码错误")
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
