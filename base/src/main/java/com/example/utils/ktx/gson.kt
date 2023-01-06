/*
package com.example.utils.ktx

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xiaoma.flow.func.ktx.logW

inline fun <reified T> String.fromJson(): T? = try {
    val type = object : TypeToken<T>() {}.type
    Gson().fromJson<T>(this, type)
} catch (e: Exception) {
    "json parse failure: ${T::class.java}".logW("GsonTag", e = e, trace = false)
    null
}

fun Any.toJson(): String = Gson().toJson(this)*/
