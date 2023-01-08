package com.example.mvvmjetpack.data.mapper

import com.example.mvvmjetpack.base.ITransformer
import com.example.mvvmjetpack.data.entities.LoginEntity
import com.example.mvvmjetpack.domain.model.Gender
import com.example.mvvmjetpack.domain.model.UserInfo

object UserInfoTransform : ITransformer<LoginEntity, UserInfo> {
    override fun transform(input: LoginEntity): UserInfo = when (input.gender) {
        0 -> UserInfo(input.accessToken, input.userName, Gender.Man)
        1 -> UserInfo(input.accessToken, input.userName, Gender.Woman)
        else -> UserInfo(input.accessToken, input.userName, Gender.Unknown)
    }

}