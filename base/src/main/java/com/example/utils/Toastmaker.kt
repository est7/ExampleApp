package com.example.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.utils.ktx.isAtLeastStarted

interface ToastMaker {

    fun <T, R : CharSequence> T?.toastWith(block: (T) -> R) {
        if (this == null) return
        block(this).toast()
    }

    fun <T, R : CharSequence> T?.toastLongWith(block: (T) -> R) {
        if (this == null) return
        block(this).toast()
    }

    fun CharSequence.toast() {
        toast(this@ToastMaker, this, Toast.LENGTH_SHORT)
    }

    fun CharSequence.toastLong() {
        toast(this@ToastMaker, this, Toast.LENGTH_LONG)
    }

    private fun toast(toastMaker: Any, toast: CharSequence, duration: Int) {
        if (toastMaker is LifecycleOwner) {
            if (!toastMaker.isAtLeastStarted) return
        }
        when (toastMaker) {
            is Activity ->
                Toast.makeText(toastMaker, toast, duration).show()
            is Fragment ->
                Toast.makeText(toastMaker.requireActivity(), toast, duration).show()
            is View ->
                Toast.makeText(toastMaker.context, toast, duration).show()
            else -> Unit
        }
    }
}