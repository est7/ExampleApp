@file:Suppress("unused")

package com.xiaoma.flow.func.ktx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.withContext
import okio.ByteString.Companion.toByteString
import okio.buffer
import okio.source
import java.io.File

suspend fun String.md5() = withContext(Dispatchers.Default) {
    try {
        toByteArray().toByteString().md5().hex()
    } catch (e: Exception) {
        "calculate string md5 failure".logW("Md5Tag", e = e, trace = false)
        ""
    }
}

suspend fun File.md5() = withContext(Dispatchers.Default) {
    try {
        if (!exists()) return@withContext ""
        runInterruptible {
            source()
        }.use { source ->
            source.buffer().use { input ->
                runInterruptible {
                    input.readByteString()
                }.md5().hex()
            }
        }
    } catch (e: Exception) {
        "calculate file md5 failure".logW("Md5Tag", e = e, trace = false)
        ""
    }
}
