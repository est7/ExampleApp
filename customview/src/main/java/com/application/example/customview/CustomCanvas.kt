package  com.application.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View


class CustomCanvas @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPos(canvas)
    }


    private fun drawPos(canvas: Canvas) {
        //设置画笔圆帽
        mRedPaint.strokeCap = Paint.Cap.ROUND
        mRedPaint.strokeWidth = 20F
        //绘制点
        canvas.drawPoint(100F, 100F, mRedPaint)
        canvas.drawPoints(floatArrayOf(200F, 100F, 200F, 200F, 200F, 300F), mRedPaint)
    }


}