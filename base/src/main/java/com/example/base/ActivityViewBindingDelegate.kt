package com.example.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingDelegate<T : ViewBinding>(activity: Activity, clazz: Class<T>) :
    ReadOnlyProperty<Activity, T> {

    private var viewBinding: T? = null
    private val inflaterMethod = clazz.inflaterMethod()

    init {
        when (activity) {
            is FragmentActivity -> activity.lifecycle.onDestroy { destroyed() }
            is AppCompatActivity -> activity.lifecycle.onDestroy { destroyed() }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return viewBinding?.run { this } ?: let {
            val bind = inflaterMethod.invoke(null, thisRef.layoutInflater) as T
            bind.apply { viewBinding = this }
        }
    }

    private fun destroyed() {
        viewBinding = null
    }
}































































































