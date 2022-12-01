package com.application.others.coordinatorlayout

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.application.others.R
import com.application.others.databinding.FragmentCoordinatorLayout03Binding
import com.example.base.binding


class CoordinatorLayoutFragment_03 : Fragment() {
    private val binding by binding<FragmentCoordinatorLayout03Binding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var contentView = super.onCreateView(inflater, container, savedInstanceState)
        if (contentView == null) {
            contentView = binding.root
        }
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in 0..50) {
            binding.tv.append(
                """
            ${i.toString() + "\n"}
            """.trimIndent()
            )
        }
    }
}

class ScrollToTopBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {
    var offsetTotal = 0
    var scrolling = false



    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency.id == R.id.scrollView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return true
    }
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        offset(child, dyConsumed)
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        return super.onTouchEvent(parent, child, ev)
    }

    fun offset(child: View, dy: Int) {
        val old = offsetTotal
        var top = offsetTotal - dy
        top = Math.max(top, -child.height)
        top = Math.min(top, 0)
        offsetTotal = top
        if (old == offsetTotal) {
            scrolling = false
            return
        }
        val delta = offsetTotal - old
        child.offsetTopAndBottom(delta)
        scrolling = true
    }
}