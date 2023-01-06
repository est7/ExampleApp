@file:Suppress("unused")

package com.example.utils.ktx

import android.util.Log

enum class Level {
    Verbose,
    Debug,
    Info,
    Warn,
    Error,
}

inline fun <T> T.logV(
    tag: String? = null,
    e: Throwable? = null,
    trace: Boolean = true,
    map: (T) -> String
) = log(tag, e, Level.Verbose, trace, map)

inline fun <T> T.logD(
    tag: String? = null,
    e: Throwable? = null,
    trace: Boolean = true,
    map: (T) -> String
) = log(tag, e, Level.Debug, trace, map)

inline fun <T> T.logI(
    tag: String? = null,
    e: Throwable? = null,
    trace: Boolean = true,
    map: (T) -> String
) = log(tag, e, Level.Info, trace, map)

inline fun <T> T.logW(
    tag: String? = null,
    e: Throwable? = null,
    trace: Boolean = true,
    map: (T) -> String
) = log(tag, e, Level.Warn, trace, map)

inline fun <T> T.logE(
    tag: String? = null,
    e: Throwable? = null,
    trace: Boolean = true,
    map: (T) -> String
) = log(tag, e, Level.Error, trace, map)

inline fun <T> T.log(
    tag: String? = null,
    e: Throwable? = null,
    level: Level = Level.Debug,
    trace: Boolean,
    map: (T) -> String
): T {
    map(this).log(tag, e, level, trace)
    return this
}

fun String.logV(tag: String? = null, e: Throwable? = null, trace: Boolean = true) =
    log(tag, e, Level.Verbose, trace)

fun String.logD(tag: String? = null, e: Throwable? = null, trace: Boolean = true) =
    log(tag, e, Level.Debug, trace)

fun String.logI(tag: String? = null, e: Throwable? = null, trace: Boolean = true) =
    log(tag, e, Level.Info, trace)

fun String.logW(tag: String? = null, e: Throwable? = null, trace: Boolean = true) =
    log(tag, e, Level.Warn, trace)

fun String.logE(tag: String? = null, e: Throwable? = null, trace: Boolean = true) =
    log(tag, e, Level.Error, trace)

fun String.log(
    tag: String? = null,
    e: Throwable? = null,
    level: Level = Level.Debug,
    trace: Boolean
) {
    val ln = System.lineSeparator()
    val here = Throwable().stackTrace[0]
    val methodStack = getInvokeStack { here.className == it.className }

    val logLocate = if (methodStack != null && trace) "> at $methodStack$ln" else ""
    val finalTag = "LOG_TAG[${getTag(tag, methodStack)}]"
    val finalLog = "\uD83E\uDD2A$ln$logLocate$this"

    if (e != null) {
        when (level) {
            Level.Verbose -> Log.v(finalTag, finalLog, e)
            Level.Debug -> Log.d(finalTag, finalLog, e)
            Level.Info -> Log.i(finalTag, finalLog, e)
            Level.Warn -> Log.w(finalTag, finalLog, e)
            Level.Error -> Log.e(finalTag, finalLog, e)
        }
    } else {
        when (level) {
            Level.Verbose -> Log.v(finalTag, finalLog)
            Level.Debug -> Log.d(finalTag, finalLog)
            Level.Info -> Log.i(finalTag, finalLog)
            Level.Warn -> Log.w(finalTag, finalLog)
            Level.Error -> Log.e(finalTag, finalLog)
        }
    }
}

fun getTag(tag: String?, methodStack: StackTraceElement?): String {
    if (!tag.isNullOrBlank()) return tag
    if (methodStack == null) return ""
    return try {
        var clazz = Class.forName(methodStack.className)
        if (clazz.isAnonymousClass) {
            val groups = Regex("^(.*?)\\\$")
                .findAll(methodStack.className)
                .flatMap(MatchResult::groupValues)
                .toList()
            if (groups.size < 2) return ""
            clazz = Class.forName(groups[1])
        }
        clazz.simpleName
    } catch (e: Exception) {
        ""
    }
}

fun getInvokeStack(ignore: (StackTraceElement) -> Boolean): StackTraceElement? {
    Throwable().stackTrace.forEach {
        if (ignore(it)) return@forEach
        return it
    }
    return null
}