package  com.application.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class CustomPath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPath(canvas)
    }


    private fun drawPath(canvas: Canvas) {
        val canvasWidth = canvas.width
        val deltaX = canvasWidth / 4
        val deltaY = (deltaX * 0.75).toInt()

        mRedPaint.setStrokeWidth(4f) //设置线宽

        /*--------------------------用Path画填充面-----------------------------*/

        mRedPaint.setStyle(
            Paint.Style.FILL
        ) //设置画笔为填充模式
        val path = Path()
        //向Path中加入Arc
        val arcRecF = RectF(0f, 0f, deltaX.toFloat(), deltaY.toFloat())
        path.addArc(arcRecF, 0f, 135f)
        //向Path中加入Oval
        val ovalRecF = RectF(deltaX.toFloat(), 0f, (deltaX * 2).toFloat(), deltaY.toFloat())
        path.addOval(ovalRecF, Path.Direction.CCW)
        //向Path中添加Circle
        path.addCircle(
            (deltaX * 2.5).toFloat(),
            (deltaY / 2).toFloat(),
            (deltaY / 2).toFloat(),
            Path.Direction.CCW
        )
        //向Path中添加Rect
        val rectF = RectF((deltaX * 3).toFloat(), 0f, (deltaX * 4).toFloat(), deltaY.toFloat())
        path.addRect(rectF, Path.Direction.CCW)
        canvas.drawPath(path, mRedPaint)

        /*--------------------------用Path画线--------------------------------*/
        mRedPaint.setStyle(Paint.Style.STROKE) //设置画笔为线条模式
        canvas.translate(0f, (deltaY * 2).toFloat())
        canvas.drawPath(path, mRedPaint)

        /*-----------------使用lineTo、arcTo、quadTo、cubicTo画线--------------*/
        mRedPaint.setStyle(Paint.Style.STROKE) //设置画笔为线条模式
        canvas.translate(0f, (deltaY * 2).toFloat())
        val path3 = Path()
        //用pointList记录不同的path的各处的连接点
        val pointList: MutableList<Point> = ArrayList()
        //1. 第一部分，绘制线段
        path3.moveTo(0f, 0f)
        path3.lineTo((deltaX / 2).toFloat(), 0f) //绘制线段
        pointList.add(Point(0, 0))
        pointList.add(Point(deltaX / 2, 0))
        //2. 第二部分，绘制椭圆右上角的四分之一的弧线
        val arcRecF1 = RectF(0f, 0f, deltaX.toFloat(), deltaY.toFloat())
        path3.arcTo(arcRecF1, 270f, 90f) //绘制圆弧
        pointList.add(Point(deltaX, deltaY / 2))
        //3. 第三部分，绘制椭圆左下角的四分之一的弧线
        //注意，我们此处调用了path的moveTo方法，将画笔的移动到我们下一处要绘制arc的起点上
        path3.moveTo(deltaX * 1.5f, deltaY.toFloat())
        val arcRecF2 = RectF(deltaX.toFloat(), 0f, (deltaX * 2).toFloat(), deltaY.toFloat())
        path3.arcTo(arcRecF2, 90f, 90f) //绘制圆弧
        pointList.add(Point((deltaX * 1.5).toInt(), deltaY))
        //4. 第四部分，绘制二阶贝塞尔曲线
        //二阶贝塞尔曲线的起点就是当前画笔的位置，然后需要添加一个控制点，以及一个终点
        //再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 1.5f, deltaY.toFloat())
        //绘制二阶贝塞尔曲线
        path3.quadTo((deltaX * 2).toFloat(), 0f, deltaX * 2.5f, (deltaY / 2).toFloat())
        pointList.add(Point((deltaX * 2.5).toInt(), deltaY / 2))
        //5. 第五部分，绘制三阶贝塞尔曲线，三阶贝塞尔曲线的起点也是当前画笔的位置
        //其需要两个控制点，即比二阶贝赛尔曲线多一个控制点，最后也需要一个终点
        //再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 2.5f, (deltaY / 2).toFloat())
        //绘制三阶贝塞尔曲线
        path3.cubicTo(
            (deltaX * 3).toFloat(), 0f, deltaX * 3.5f, 0f, (deltaX * 4).toFloat(), deltaY.toFloat()
        )
        pointList.add(Point(deltaX * 4, deltaY))

        //Path准备就绪后，真正将Path绘制到Canvas上
        canvas.drawPath(path3, mRedPaint)

        //最后绘制Path的连接点，方便我们大家对比观察
        mRedPaint.setStrokeWidth(10f) //将点的strokeWidth要设置的比画path时要大
        mRedPaint.setStrokeCap(Paint.Cap.ROUND) //将点设置为圆点状
        mRedPaint.setColor(-0xffff01) //设置圆点为蓝色
        for (p in pointList) {
            //遍历pointList，绘制连接点
            canvas.drawPoint(p.x.toFloat(), p.y.toFloat(), mRedPaint)
        }
    }


}