package com.template.motionanddragevent.sampleview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author: est7
 * @date: 2022/12/30
 */
class MotionEventViewC @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewC dispatchTouchEventC")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewC onTouchEventC")
        return super.onTouchEvent(event)
    }

    companion object {
        const val TAG = "MotionEvent"
    }
}