package com.application.others.coordinatorlayout

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import androidx.fragment.app.Fragment
import com.application.others.R
import com.application.others.databinding.FragmentCoordinatorLayout02Binding
import com.example.base.binding
import kotlin.properties.Delegates


class CoordinatorLayoutFragment_02 : Fragment() {
    private val binding by binding<FragmentCoordinatorLayout02Binding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var contentView = super.onCreateView(inflater, container, savedInstanceState)
        if (contentView == null) {
            contentView = binding.root
        }

        return contentView
    }

}


class BehaviorDependencyButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatButton(context, attrs, defStyleAttr) {

    val TAG = "BehaviorDependencyButton"
    private var downX by Delegates.notNull<Float>()
    private var downY by Delegates.notNull<Float>()
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                var x = event.x
                var y = event.y
                var dx = x - downX
                var dy = y - downY

                if (dx != 0f && dy != 0f) {
                    layout(
                        left + dx.toInt(), top + dy.toInt(), right + dx.toInt(), bottom + dy.toInt()
                    )
                }
            }


            else -> {}
        }
        return true
    }
}

/**
 *自定义 Behavior，让 child 跟随 dependency：
 * @description
 * @param
 * @return
 * @author est7
 * @time 2022/11/29 14:28
 */
class CustomBehavior constructor(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<TextView>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout, child: TextView, dependency: View
    ): Boolean {
        return dependency.id == R.id.foobtn
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout, child: TextView, dependency: View
    ): Boolean {
        child.y = dependency.y + dependency.height

        return true
    }
}
