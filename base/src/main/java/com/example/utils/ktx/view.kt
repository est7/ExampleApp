@file:Suppress("unused")

package com.xiaoma.flow.func.ktx

import android.view.View
import android.view.ViewGroup

fun takeVisibility(show: Boolean) = if (show) View.VISIBLE else View.GONE

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.hideToLeft() = apply {
    post(object : Runnable {
        override fun run() {
            removeCallbacks(this)
            val layoutParams = layoutParams
            val marginLeft = if (layoutParams is ViewGroup.MarginLayoutParams) {
                layoutParams.leftMargin
            } else 0
            translationX = -(width.toFloat() + marginLeft)
        }
    })
}

fun View.hideToTop() = apply {
    post(object : Runnable {
        override fun run() {
            removeCallbacks(this)
            val layoutParams = layoutParams
            val marginTop = if (layoutParams is ViewGroup.MarginLayoutParams) {
                layoutParams.topMargin
            } else 0
            translationY = -(height.toFloat() + marginTop)
        }
    })
}

fun View.hideToRight() = apply {
    post(object : Runnable {
        override fun run() {
            removeCallbacks(this)
            val layoutParams = layoutParams
            val marginRight = if (layoutParams is ViewGroup.MarginLayoutParams) {
                layoutParams.rightMargin
            } else 0
            translationY = width.toFloat() + marginRight
        }
    })
}

fun View.hideToBottom() = apply {
    post(object : Runnable {
        override fun run() {
            removeCallbacks(this)
            val layoutParams = layoutParams
            val marginBottom = if (layoutParams is ViewGroup.MarginLayoutParams) {
                layoutParams.bottomMargin
            } else 0
            translationX = height.toFloat() + marginBottom
        }
    })
}
