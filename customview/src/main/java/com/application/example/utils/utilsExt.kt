package com.application.example

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat

/**
 *
 * @author: est7
 * @date: 2022/12/26
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
val Int.dp
    get() = this.toFloat().dp.toInt()