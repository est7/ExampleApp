@file:Suppress("unused")

package com.xiaoma.flow.func.ktx

import android.os.Parcel

inline fun Parcel?.use(block: Parcel.() -> Unit) {
    if (this == null) return
    try {
        this.block()
    } finally {
        this.recycle()
    }
}