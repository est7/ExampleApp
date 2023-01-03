package  com.application.example.customview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.BLUE
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.Color.YELLOW
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi


class CustomCanvasXferMode @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var usableHeight: Int = 0
    private var usableWidth: Int = 0
    private val mRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    private val mGreenPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.GREEN
        //设置画笔圆帽
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 20F
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        usableWidth = width - (paddingLeft + paddingRight)
        usableHeight = height - (paddingTop + paddingBottom)

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        drawPos(canvas)
//        drawBitmap(canvas)
        testOfXfermode(canvas)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun testOfXfermode(canvas: Canvas) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        //新建图层
        //屏幕canvas不是无色的和透明的（默认是白色的和不透明的），
        // 也就是说屏幕的Bitmap在为Config.ARGB_8888的情况下，
        // 那么它的ARGB值就分别是255、255、255、255，
        // 这显然将会对Xfermode的合成造成影响。
        // 但如果这样的影响是在你的预期范围内的话，可以考虑直接绘制到屏幕canvas上。
        val layerID = canvas.saveLayer(
            0f, 0f, usableWidth.toFloat(), usableHeight.toFloat(), mRedPaint
        )

        val makeDst = makeDst(200f, 200f)
        canvas.drawBitmap(makeDst, 0f, 0f, mRedPaint)

        mRedPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        canvas.translate(100f, 100f)

        val makeSrc = makeSrc(200f, 200f)
        canvas.drawBitmap(makeSrc, 0f, 0f, mRedPaint)
        mRedPaint.xfermode = null
        //还原图层
        canvas.restoreToCount(layerID)
    }

    /**
     *
     * @description 创建红色圆形
     * @param
     * @return
     * @author est7
     * @time 2022/12/30 14:12
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    fun makeDst(w: Float, h: Float): Bitmap {
        val bm = Bitmap.createBitmap(w.toInt(), h.toInt(), Bitmap.Config.ARGB_8888);
        val c = Canvas(bm);
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
//        p.setColor(RED)
        p.color = Color.argb(255, 255, 0, 0)
        c.drawOval(RectF(0F, 0f, w, h), p)
        return bm
    }

    /**
     *
     * @description 创建绿色矩形
     * @param
     * @return
     * @author est7
     * @time 2022/12/30 14:11
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    fun makeSrc(w: Float, h: Float): Bitmap {
        val bm = Bitmap.createBitmap(w.toInt(), h.toInt(), Bitmap.Config.ARGB_8888);
        val c = Canvas(bm)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)


//        p.setColor(GREEN)
        //来点透明度比较容易观察
        p.color = Color.argb(255, 0, 0, 255)
        c.drawRect(0f, 0f, w, h, p)
        return bm
    }


    private fun drawBitmap(canvas: Canvas) {
        val createBitmap = createBitmap(Color.CYAN)

        canvas.drawBitmap(createBitmap, 50F, 50F, mRedPaint)

        canvas.translate(0f, 300f)

        mGreenPaint.colorFilter = PorterDuffColorFilter(
            Color.parseColor("#FF0000"),//红
            PorterDuff.Mode.LIGHTEN,
        )

        canvas.drawBitmap(createBitmap, 50F, 50F, mGreenPaint)


    }


    /**
     * 创建一个Bitmap
     *
     * @param color 背景色
     * @return bitmap
     */
    private fun createBitmap(color: Int): Bitmap {
        //创建一个ARGB_8888,宽高200的bitmap
        val bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        //使用Bitmap创建一个canvas画板，画板上的一切都会保留在bitmap上
        val bitmapCanvas = Canvas(bitmap);
        //接下来就是在画板上操作
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        p.setColor(color)
        val rect = Rect(0, 0, bitmap.getWidth(), bitmap.getHeight())
        bitmapCanvas.drawRect(rect, p)
        p.setColor(Color.GRAY)
        p.setStrokeWidth(3f)
        bitmapCanvas.drawLine(0f, 0f, 200f, 200f, p)
        bitmapCanvas.drawLine(200f, 0f, 0f, 200f, p)
        return bitmap;
    }

    private fun drawPos(canvas: Canvas) {
        //绘制点
        canvas.drawPoint(100F, 100F, mRedPaint)
        canvas.drawPoints(floatArrayOf(200F, 100F, 200F, 200F, 200F, 300F), mRedPaint)
    }


}