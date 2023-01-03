package  com.application.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class CustomCanvasDrawAndClip @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val density = 3f

    private val mRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        drawPos(canvas)
        drawArc(canvas)
//        drawOval(canvas)
    }


    private fun drawPos(canvas: Canvas) {
        //设置画笔圆帽
        mRedPaint.strokeCap = Paint.Cap.ROUND
        mRedPaint.strokeWidth = 20F
        //绘制点
        canvas.drawPoint(100F, 100F, mRedPaint)
        canvas.drawPoints(floatArrayOf(200F, 100F, 200F, 200F, 200F, 300F), mRedPaint)
    }


    private fun drawArc(canvas: Canvas) {
        val canvasWidth = canvas.width
        val canvasHeight = canvas.height
        val count = 5

        val ovalHeight = (canvasHeight / (count + 1)).toFloat()
        val left: Float = 10 * density
        val top = 0f
        val right = canvasWidth - left
        val rectF = RectF(left, top, right, ovalHeight)
        mRedPaint.setStrokeWidth(2 * density) //设置线宽
        mRedPaint.setColor(-0x743a46) //设置颜色
        mRedPaint.setStyle(Paint.Style.FILL) //默认设置画笔为填充模式

        //绘制用drawArc绘制完整的椭圆
        canvas.translate(0f, ovalHeight / count)
        canvas.drawArc(rectF, 0f, 360f, true, mRedPaint)

        //绘制椭圆的四分之一,起点是钟表的3点位置，从3点绘制到6点的位置
        canvas.translate(0f, ovalHeight + ovalHeight / count)
        canvas.drawArc(rectF, 0f, 90f, true, mRedPaint)

        //绘制椭圆的四分之一,将useCenter设置为false
        canvas.translate(0f, ovalHeight + ovalHeight / count)
        canvas.drawArc(rectF, 0f, 90f, false, mRedPaint)

        //绘制椭圆的四分之一，只绘制轮廓线
        mRedPaint.setStyle(Paint.Style.STROKE) //设置画笔为线条模式
        canvas.translate(0f, ovalHeight + ovalHeight / count)
        canvas.drawArc(rectF, 0f, 90f, true, mRedPaint)

        //绘制带有轮廓线的椭圆的四分之一
        //1. 先绘制椭圆的填充部分
        mRedPaint.setStyle(Paint.Style.FILL) //设置画笔为填充模式
        canvas.translate(0f, ovalHeight + ovalHeight / count)
        canvas.drawArc(rectF, 0f, 90f, true, mRedPaint)
        //2. 再绘制椭圆的轮廓线部分
        mRedPaint.setStyle(Paint.Style.STROKE) //设置画笔为线条模式
        mRedPaint.setColor(-0xffff01) //设置轮廓线条为蓝色
        canvas.drawArc(rectF, 0f, 90f, true, mRedPaint)
    }


    private fun drawOval(canvas: Canvas) {
        val canvasWidth = canvas.width
        val canvasHeight = canvas.height
        val quarter = (canvasHeight / 4).toFloat()
        val left: Float = 10 * density
        val top = 0f
        val right = canvasWidth - left
        val rectF = RectF(left, top, right, quarter)

        //绘制椭圆形轮廓线
        mRedPaint.setStyle(Paint.Style.STROKE) //设置画笔为画线条模式
        mRedPaint.setStrokeWidth(2 * density) //设置线宽
        mRedPaint.setColor(-0x743a46) //设置线条颜色
        canvas.translate(0f, quarter / 4)
        canvas.drawOval(rectF, mRedPaint)

        //绘制椭圆形填充面
        mRedPaint.setStyle(Paint.Style.FILL) //设置画笔为填充模式
        canvas.translate(0f, quarter + quarter / 4)
        canvas.drawOval(rectF, mRedPaint)

        //画两个椭圆，形成轮廓线和填充色不同的效果
        canvas.translate(0f, quarter + quarter / 4)
        //1. 首先绘制填充色
        mRedPaint.setStyle(Paint.Style.FILL) //设置画笔为填充模式
        canvas.drawOval(rectF, mRedPaint) //绘制椭圆形的填充效果
        //2. 将线条颜色设置为蓝色，绘制轮廓线
        mRedPaint.setStyle(Paint.Style.STROKE) //设置画笔为线条模式
        mRedPaint.setColor(-0xffff01) //设置填充色为蓝色
        canvas.drawOval(rectF, mRedPaint) //设置椭圆的轮廓线
    }


}