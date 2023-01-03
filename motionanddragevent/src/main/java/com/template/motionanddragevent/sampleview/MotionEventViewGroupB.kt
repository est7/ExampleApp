package com.template.motionanddragevent.sampleview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * @author: est7
 * @date: 2022/12/30
 */
class MotionEventViewGroupB @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewGroupB dispatchTouchEventB")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewGroupB onInterceptTouchEventB")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewGroupB onTouchEventB")
        return super.onTouchEvent(event)
    }

    companion object {
        const val TAG = "MotionEvent"
    }
}