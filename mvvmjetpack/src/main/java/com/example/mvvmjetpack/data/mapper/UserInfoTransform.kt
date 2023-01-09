package com.example.mvvmjetpack.data.mapper

import com.example.mvvmjetpack.base.ITransformer
import com.example.mvvmjetpack.base.exception.ApiException
import com.example.mvvmjetpack.base.net.NetworkResponse
import com.example.mvvmjetpack.base.net.toResult
import com.example.mvvmjetpack.data.entities.LoginEntity
import com.example.mvvmjetpack.domain.model.Gender
import com.example.mvvmjetpack.domain.model.UserInfo
import  com.example.mvvmjetpack.base.net.Result

object UserInfoTransform : ITransformer<NetworkResponse<LoginEntity>, Result<UserInfo>> {
    override fun transform(input: NetworkResponse<LoginEntity>): Result<UserInfo> {
        return input.toResult(UserInfoTransform::mapper)

    }

    private fun mapper(input: LoginEntity): UserInfo = when (input.gender) {
        0 -> UserInfo(input.accessToken, input.userName, Gender.Man)
        1 -> UserInfo(input.accessToken, input.userName, Gender.Woman)
        else -> UserInfo(input.accessToken, input.userName, Gender.Unknown)
    }

}