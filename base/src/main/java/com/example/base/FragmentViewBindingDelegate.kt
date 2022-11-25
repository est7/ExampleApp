package com.example.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import kotlin.LazyThreadSafetyMode.NONE
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class FragmentViewBindingDelegate<T :ViewBinding>(fragment: Fragment, clazz: Class<T>) :
    ReadOnlyProperty<Fragment, T> {

    private var viewBinding: T? = null
    private val bindMethod = clazz.bindMethod()
    private val inflaterMethod by lazy(NONE) { clazz.inflaterMethod() }

    init {
        fragment.lifecycle.onDestroy { destroyed() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {

        return viewBinding?.run { this } ?: let {
            val bind = try {
                bindMethod.invoke(null, thisRef.view) as T
            } catch (e: InvocationTargetException) {
                //兼容BaseDialogFragment在onCreateDialog设置setContentView时获取viewbinding
                inflaterMethod.invoke(null, thisRef.layoutInflater) as T
            }
            bind.apply { viewBinding = this }
        }
    }

    private fun destroyed() {
        viewBinding = null
    }
}































































































