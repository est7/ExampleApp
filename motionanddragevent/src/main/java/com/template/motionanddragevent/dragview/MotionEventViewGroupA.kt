package com.template.motionanddragevent.dragview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * @author: est7
 * @date: 2022/12/30
 */
class MotionEventViewGroupA @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewGroupA dispatchTouchEventA")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewGroupA onInterceptTouchEventA")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e(TAG, "MotionEventViewGroupA onTouchEventA")
        return super.onTouchEvent(event)
    }

    companion object {
        const val TAG = "MotionEvent"
    }
}