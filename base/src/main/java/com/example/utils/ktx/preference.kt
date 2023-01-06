/*
@file:Suppress("unused")

package com.example.utils.ktx

import android.os.Parcelable
import com.tencent.mmkv.MMKV

fun String.save(key: String) = MMKV.defaultMMKV().encode(key, this)
fun ByteArray.save(key: String) = MMKV.defaultMMKV().encode(key, this)
fun Parcelable.save(key: String) = MMKV.defaultMMKV().encode(key, this)

fun Boolean.save(key: String) = MMKV.defaultMMKV().encode(key, this)
fun Int.save(key: String) = MMKV.defaultMMKV().encode(key, this)
fun Long.save(key: String) = MMKV.defaultMMKV().encode(key, this)
fun Float.save(key: String) = MMKV.defaultMMKV().encode(key, this)
fun Double.save(key: String) = MMKV.defaultMMKV().encode(key, this)

inline fun <reified T> load(key: String): T? {
    val mmkv = MMKV.defaultMMKV()
    return when (val clazz: Class<T> = T::class.java) {
        String::class.java -> mmkv.decodeString(key) as T?
        ByteArray::class.java -> mmkv.decodeBytes(key) as T?
        java.lang.Boolean::class.java -> mmkv.decodeBool(key) as T?
        java.lang.Integer::class.java -> mmkv.decodeInt(key) as T?
        java.lang.Long::class.java -> mmkv.decodeLong(key) as T?
        java.lang.Float::class.java -> mmkv.decodeFloat(key) as T?
        java.lang.Double::class.java -> mmkv.decodeDouble(key) as T?
        else -> {
            if (Parcelable::class.java.isAssignableFrom(clazz)) {
                mmkv.decodeParcelable(key, clazz as Class<Parcelable>) as T?
            } else {
                throw IllegalStateException()
            }
        }
    }
}

inline fun <reified T> load(key: String, defaultValue: T?): T? {
    val mmkv = MMKV.defaultMMKV()

    if (defaultValue == null) {
        if (!mmkv.containsKey(key)) return null
        return load<T>(key)
    }

    return when (val clazz: Class<T> = T::class.java) {
        String::class.java -> mmkv.decodeString(key, defaultValue as? String) as T?
        ByteArray::class.java -> mmkv.decodeBytes(key, defaultValue as? ByteArray) as T?
        java.lang.Boolean::class.java -> mmkv.decodeBool(key, defaultValue as Boolean) as T?
        java.lang.Integer::class.java -> mmkv.decodeInt(key, defaultValue as Int) as T?
        java.lang.Long::class.java -> mmkv.decodeLong(key, defaultValue as Long) as T?
        java.lang.Float::class.java -> mmkv.decodeFloat(key, defaultValue as Float) as T?
        java.lang.Double::class.java -> mmkv.decodeDouble(key, defaultValue as Double) as T?
        else -> {
            if (Parcelable::class.java.isAssignableFrom(clazz)) {
                mmkv.decodeParcelable(
                    key,
                    clazz as Class<Parcelable>,
                    defaultValue as Parcelable
                ) as T?
            } else {
                throw IllegalStateException()
            }
        }
    }
}*/
