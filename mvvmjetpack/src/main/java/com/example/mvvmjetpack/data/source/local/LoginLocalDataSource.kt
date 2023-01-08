package com.example.mvvmjetpack.data.source.local

import com.example.mvvmjetpack.domain.model.UserInfo
import com.example.mvvmjetpack.utils.save

class LoginLocalDataSource {
    fun saveUserInfo(userInfo: UserInfo) {
        userInfo.save("key_userinfo")
    }

}
