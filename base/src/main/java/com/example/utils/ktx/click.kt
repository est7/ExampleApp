@file:Suppress("unused")

package com.xiaoma.flow.func.ktx

import android.view.View

fun View?.setOnCanClickListener(
    time: Long = ClickRecord.CAN_CLICK_INTERVAL,
    listener: View.OnClickListener?
) {
    if (this == null) return
    if (listener == null) {
        setOnClickListener(null)
        return
    }
    this.setOnClickListener(ClickListenerWrapper(time, listener))
}

object ClickRecord {
    const val CAN_CLICK_INTERVAL = 300L
    private var lastClickTimestamp = 0L

    fun cannotClick(time: Long) = (getClickInterval() < time).also { cannotClick ->
        if (!cannotClick) recordClick()
    }

    private fun recordClick() {
        lastClickTimestamp = System.currentTimeMillis()
    }

    private fun getClickInterval(): Long {
        return System.currentTimeMillis() - lastClickTimestamp
    }
}

private open class ClickListenerWrapper(
    val time: Long,
    val host: View.OnClickListener?
) : View.OnClickListener {

    companion object {
        private val TAG = "${ClickListenerWrapper::class.java.name}Tag"
    }

    override fun onClick(v: View?) {
        if (ClickRecord.cannotClick(time)) {
            "skip click".logW(TAG)
            return
        }
        onCanClick(v)
    }

    private fun onCanClick(v: View?) {
        host?.onClick(v)
    }
}