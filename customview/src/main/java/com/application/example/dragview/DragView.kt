


/**
 *https://blog.csdn.net/mingyueyixi/article/details/70288632
 * @description
 * @param
 * @return
 * @author est7
 * @time 2022/11/29 14:15
 */
class BehaviorDependencyButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

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
