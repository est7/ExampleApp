package com.example.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method

/**
 *
 * @author: est7
 * @date: 2022/11/25
 */
inline fun <reified T : ViewBinding> Activity.binding() =
    ActivityViewBindingDelegate(this, T::class.java)

inline fun <reified T : ViewBinding> Fragment.binding() =
    FragmentViewBindingDelegate(this, T::class.java)

fun <T> Class<T>.bindMethod(): Method =
    getMethod("bind", View::class.java)


fun Lifecycle.onDestroy(destroyed: () -> Unit) {
    addObserver(LifecycleDestroy(this, destroyed))
}

fun <T> Class<T>.inflaterMethod(): Method =
    getMethod("inflate", LayoutInflater::class.java)

/**
 * description 监听lifecycle生命周期销毁方法
 */
class LifecycleDestroy(var lifecycle: Lifecycle?, val destroyed: () -> Unit) :
    LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (source.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            destroyed()

            lifecycle?.apply {
                removeObserver(this@LifecycleDestroy)
                lifecycle = null
            }
        }
    }
}

inline fun <reified T> Context.startActivity(extras: Intent.() -> Unit = {}) {

    Intent(this, T::class.java).apply(extras).also { startActivity(it) }
}



class AndroidExtension {

}