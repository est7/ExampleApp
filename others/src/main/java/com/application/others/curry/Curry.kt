package com.application.others.curry

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import java.io.OutputStream

class Curry {
    fun main() {
        ::log.curried()("est")(System.out)("HelloWorld")


    }

    private fun log(tag: String, target: OutputStream, message: Any?) {
        //打日志"\n"是换行的意思
        target.write("[$tag] $message\n".toByteArray())

    }

    val startAct = { context: Context ->
        { clazz: Class<*> ->
            { extras: Intent.() -> Unit ->
                Intent(context, clazz).apply(extras).also {
                    startActivity(context, it, null)
                }
            }
        }

    }

    fun startAct1(context: Context, clazz: Class<*>, extras: Intent.() -> Unit = {}) =
        Intent(context, clazz).apply(extras).also {
            startActivity(context, it, null)
        }


}

fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried() =
    fun(p1: P1) = fun(p2: P2) = fun(p3: P3) = this(p1, p2, p3)


fun <P1, P2, R> Function2<P1, P2, R>.curried() = fun(p1: P1) = fun(p2: P2) = this(p1, p2)
