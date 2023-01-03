package  com.application.example.customview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.ComposePathEffect
import android.graphics.CornerPathEffect
import android.graphics.DashPathEffect
import android.graphics.DiscretePathEffect
import android.graphics.EmbossMaskFilter
import android.graphics.LightingColorFilter
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.RadialGradient
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.SumPathEffect
import android.graphics.SweepGradient
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi


class CustomPaint @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    private val mGreenPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.GREEN
    }

    private val mBluePaint = Paint(mRedPaint).apply {
        style = Paint.Style.STROKE
        color = Color.BLUE
        strokeWidth = 40f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //testStyle(canvas)
        //testOfCap(canvas)
//        testOfJoin(canvas)
//        dashEffect(canvas)
//        cornerEffect(canvas)
//        discreteEffect(canvas)
//        PathDashEffect(canvas)
//        composeEffect(canvas)
//        sumEffect(canvas)
//        setSweepShader(canvas)
//        setLinearShader(canvas)
//        setRadialShader(canvas)
//        setBitmapShader(canvas)

//        setComposeShader(canvas) //todo


//        setLightingColorFilter(canvas)
//        setPorterDuffColorFilter(canvas)
//        setColorMatrixColorFilter(canvas)

//        setTextStyle(canvas)
//        setTextShadowLayer(canvas)
//        setFilterBitmap(canvas)
        setMaskFilter(canvas)
//        setMaskFilterBitmap(canvas)
    }


    private fun setMaskFilter(canvas: Canvas) {
        canvas.save()
        // 设置画笔遮罩滤镜 ,传入度数和样式
        mGreenPaint.style = Paint.Style.FILL
        mGreenPaint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.NORMAL)
        canvas.drawRect(0f, 0f, 200f, 200f, mGreenPaint)
        canvas.translate(0f, 300f)

        mGreenPaint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.INNER)
        canvas.drawRect(0f, 0f, 200f, 200f, mGreenPaint)
        canvas.translate(0f, 300f)

        mGreenPaint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.OUTER)
        canvas.drawRect(0f, 0f, 200f, 200f, mGreenPaint)
        canvas.translate(0f, 300f)

        mGreenPaint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID)
        canvas.drawRect(0f, 0f, 200f, 200f, mGreenPaint)
        canvas.translate(0f, 300f)


        mRedPaint.setTextSize(300F)
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mRedPaint.setMaskFilter(EmbossMaskFilter(floatArrayOf(2f, 2f, 2f), 0.3f, 8f, 3f))
        canvas.drawText("Hello World", 0f, 0f, mRedPaint)

        canvas.restore()
    }


    private fun setMaskFilterBitmap(canvas: Canvas) {
        canvas.save()

        val srcBitmap = BitmapFactory.decodeResource(resources, R.drawable.rank_banner_avatar_5)
        // 获取位图的Alpha通道图
        val shadowBitmap = srcBitmap.extractAlpha()
        mGreenPaint.color = Color.DKGRAY
        // 设置画笔遮罩滤镜 ,传入度数和样式
        mGreenPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)
        // 先绘制阴影
        canvas.drawBitmap(shadowBitmap, 200F, 200f, mGreenPaint)
        // 画原图
        canvas.drawBitmap(srcBitmap, 200f, 200f, null)

    }


    private fun setTextShadowLayer(canvas: Canvas) {
        canvas.save()
        val tempPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        tempPaint.strokeWidth = 4f
        tempPaint.color = Color.BLUE
        mRedPaint.setShadowLayer(20F, 4F, 4F, Color.parseColor("#000000"));
        canvas.translate(500F, 200F)
        mRedPaint.setTypeface(Typeface.SANS_SERIF)
        mRedPaint.setTypeface(Typeface.DEFAULT) //字体
        mRedPaint.setTextSize(100F)
        canvas.drawText("SANS_SERIF", 0F, 0F, mRedPaint)
        canvas.drawRect(0F, 0F, 10f, 50F, tempPaint)
    }


    private fun setFilterBitmap(canvas: Canvas) {
        val mainBitmap =
            BitmapFactory.decodeResource(getResources(), R.drawable.rank_banner_avatar_5);
        val matrix = Matrix()
        matrix.setScale(5F, 5F);//放大矩阵

        mRedPaint.setFilterBitmap(false);
        canvas.drawBitmap(mainBitmap, matrix, mRedPaint);

        canvas.translate(-500F, -300F);
        mRedPaint.setFilterBitmap(true);
        canvas.drawBitmap(mainBitmap, matrix, mRedPaint);

    }


    private fun setTextStyle(canvas: Canvas) {
        canvas.save()
        val tempPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        tempPaint.strokeWidth = 4f
        tempPaint.color = Color.BLUE

        canvas.translate(500F, 200F)
        mRedPaint.setTypeface(Typeface.SANS_SERIF)
        mRedPaint.setTextAlign(Paint.Align.LEFT)
        mRedPaint.setTypeface(Typeface.DEFAULT) //字体
        mRedPaint.setTextSize(100F)

        canvas.drawText("SANS_SERIF", 0F, 0F, mRedPaint)
        canvas.drawRect(0F, 0F, 10f, 50F, tempPaint)

        canvas.translate(0F, 150F)
        mRedPaint.setTextAlign(Paint.Align.RIGHT)
        mRedPaint.setTypeface(Typeface.SERIF)
        canvas.drawText("SERIF", 0F, 0F, mRedPaint)
        canvas.drawRect(0F, 0F, 10f, 50F, tempPaint)

        canvas.translate(0F, 150F)
        mRedPaint.setTextAlign(Paint.Align.CENTER)
        mRedPaint.setTypeface(Typeface.MONOSPACE)
        canvas.drawText("MONOSPACE", 0F, 0F, mRedPaint)
        canvas.drawRect(0F, 0F, 10f, 50F, tempPaint)

        mRedPaint.setStyle(Paint.Style.FILL);
        canvas.restore()
    }

    private fun setColorMatrixColorFilter(canvas: Canvas) {

        canvas.save();
        canvas.translate(0f, 0F);
        mRedPaint.setStyle(Paint.Style.FILL);

        val mainBitmap =
            BitmapFactory.decodeResource(getResources(), R.drawable.rank_banner_avatar_5);
        mRedPaint.setStyle(Paint.Style.FILL);
        canvas.save()

        for (floats in FloatMarixImg.getList()) {
            val colorMatrix = ColorMatrix(floats)
            mRedPaint.colorFilter = ColorMatrixColorFilter(colorMatrix)
            canvas.drawBitmap(mainBitmap, 0f, 0f, mRedPaint);
            canvas.translate(0f, 250f)
            canvas.drawRect(0F, 0F, 200f, 200F, mRedPaint);
        }
    }

    private fun setPorterDuffColorFilter(canvas: Canvas) {
        val mainBitmap =
            BitmapFactory.decodeResource(getResources(), R.drawable.rank_banner_avatar_7);
        mRedPaint.setStyle(Paint.Style.FILL);
        canvas.save()
        mRedPaint.setColorFilter(
            PorterDuffColorFilter(
                Color.parseColor("#FF0000"),//红
                PorterDuff.Mode.DARKEN
            )
        )
        canvas.drawBitmap(mainBitmap, 0f, 0f, mRedPaint);

        canvas.translate(350f, 0f);
        mRedPaint.setColorFilter(
            PorterDuffColorFilter(
                Color.parseColor("#FF0000"),//红
                PorterDuff.Mode.LIGHTEN,
            )
        );
        canvas.drawBitmap(mainBitmap, 0f, 0F, mRedPaint);

        canvas.translate(350f, 0f);
        mRedPaint.setColorFilter(
            PorterDuffColorFilter(
                Color.parseColor("#FF0000"),//红
                PorterDuff.Mode.OVERLAY,

                )
        );
        canvas.drawBitmap(mainBitmap, 0F, 0F, mRedPaint);
        canvas.restore();

    }


    private fun setLightingColorFilter(canvas: Canvas) {
        val mainBitmap =
            BitmapFactory.decodeResource(getResources(), R.drawable.rank_banner_avatar_6);
        mRedPaint.setStyle(Paint.Style.FILL);
        canvas.save()
        mRedPaint.setColorFilter(
            LightingColorFilter(
                Color.parseColor("#FFFFFF"),//白
                Color.parseColor("#00550000")//红
            )
        );
        canvas.drawBitmap(mainBitmap, 0f, 0f, mRedPaint);

        canvas.translate(350f, 0f);
        mRedPaint.setColorFilter(
            LightingColorFilter(
                Color.parseColor("#FF0000"),//红
                Color.parseColor("#00ff00")//绿
            )
        );
        canvas.drawBitmap(mainBitmap, 0f, 0F, mRedPaint);

        canvas.translate(350f, 0f);
        mRedPaint.setColorFilter(
            LightingColorFilter(
                Color.parseColor("#FF0000"),//红
                Color.parseColor("#000000")//黑
            )
        );
        canvas.drawBitmap(mainBitmap, 0F, 0F, mRedPaint);
        canvas.restore();


    }


    private fun testStyle(canvas: Canvas) {
        val rect = Rect(0, 0, 100, 100)
        mRedPaint.strokeWidth = 15f
        canvas.save()

        mRedPaint.style = Paint.Style.FILL
        canvas.translate(0f, 100F)
        canvas.drawRect(rect, mRedPaint)

        canvas.translate(150F, 0F)
        mRedPaint.style = Paint.Style.STROKE
        canvas.drawRect(rect, mRedPaint)

        canvas.translate(150F, 0F)
        mRedPaint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawRect(rect, mRedPaint)

        canvas.restore()
        mRedPaint.strokeWidth = 40F
    }


    /**
     * 线帽型状测试：Paint.Cap.BUTT、Paint.Cap.ROUND、Paint.Cap.SQUARE
     *
     * @param canvas
     */
    private fun testOfCap(canvas: Canvas) {
        mRedPaint.strokeWidth = 20F
        canvas.save()
        canvas.translate(150F, 200F)
        //线帽测试：
        mRedPaint.strokeCap = Paint.Cap.BUTT//无头(默认)
        canvas.drawLine(0F, 0F, 0F, 200F, mRedPaint)
        canvas.translate(50F, 0F)
        mRedPaint.strokeCap = Paint.Cap.ROUND//圆头
        canvas.drawLine(0F, 0F, 0F, 200F, mRedPaint)
        canvas.translate(50F, 0F)
        mRedPaint.strokeCap = Paint.Cap.SQUARE//方头
        canvas.drawLine(0F, 0F, 0F, 200F, mRedPaint)
        canvas.restore()
    }


    /**
     * 角型测试：Paint.Join.BEVEL、Paint.Join.ROUND、Paint.Join.MITER
     *
     * @param canvas
     */
    private fun testOfJoin(canvas: Canvas) {
        mRedPaint.setStyle(Paint.Style.STROKE)
        mRedPaint.setStrokeWidth(40F)
        val path = Path()
        path.moveTo(30F, 0F)
        path.lineTo(0F, 100F)
        path.lineTo(100F, 100F)
        path.lineTo(100F, 200F)

        canvas.save()
        canvas.translate(100F, 100F)
        mRedPaint.strokeJoin = Paint.Join.BEVEL//直线(默认)
        canvas.drawPath(path, mRedPaint)

        canvas.translate(150F, 0F)
        mRedPaint.strokeJoin = Paint.Join.ROUND//圆角
        canvas.drawPath(path, mRedPaint)

        canvas.translate(150F, 0F)
        mRedPaint.strokeJoin = Paint.Join.MITER//锐角
        canvas.drawPath(path, mRedPaint)
        canvas.restore()
    }


    /**
     * 虚线测试
     *
     * @param canvas
     */
    private fun dashEffect(canvas: Canvas) {
//        显示100，隐藏50,显示50，隐藏50,的循环
        mBluePaint.pathEffect = DashPathEffect(floatArrayOf(100F, 50F, 50F, 50F), 0F)
        val path = Path()
        path.moveTo(100F, 650F)
        path.lineTo(1000F, 650F)
        canvas.drawPath(path, mBluePaint)


        //显示100，隐藏50,显示60，隐藏50,的循环,偏移：mDashOffSet
        val mDashOffSet = 50f // 100f的一半
        mBluePaint.pathEffect = DashPathEffect(floatArrayOf(100F, 50F, 50F, 50F), mDashOffSet)
        val pathOffset50 = Path()
        pathOffset50.moveTo(100F, 750F)
        pathOffset50.lineTo(1000F, 750F)
        canvas.drawPath(pathOffset50, mBluePaint)

    }


    /**
     * 圆角折线
     *
     * @param canvas
     */
    private fun cornerEffect(canvas: Canvas) {

        // 不断改变mEffectCorner圆角大小可以看到效果
        val mEffectCorner = 20f
        mBluePaint.setPathEffect(CornerPathEffect(mEffectCorner))


        val path = Path()
        path.moveTo(550F, 550F);
        path.lineTo(900F, 300F);
        path.lineTo(1000F, 550F);
        canvas.drawPath(path, mBluePaint);
        //蓝色辅助线
        val tempPaint = Paint();
        tempPaint.setStyle(Paint.Style.STROKE);
        tempPaint.setColor(Color.BLUE);
        tempPaint.setStrokeWidth(4F);
        tempPaint.setPathEffect(DashPathEffect(floatArrayOf(20F, 20F), 0F));


        val helpPath = Path();
        helpPath.moveTo(550F, 550F);
        helpPath.lineTo(900F, 300F);
        helpPath.lineTo(1000F, 550F);
        canvas.drawPath(helpPath, tempPaint);
    }


    /**
     * 离散路径
     *
     * @param canvas
     */
    private fun discreteEffect(canvas: Canvas) {
        canvas.save();//保存画布状态
        canvas.translate(0F, 100F);
        val path = Path();
        // 定义路径的起点
        path.moveTo(100F, 0F);
        path.lineTo(600F, 100F);
        path.lineTo(1000F, 0F);
        //第一个参数：将原来的路径切成多长的线段,越小，所切成的小线段越多
        //第二参数：被切成的每个小线段的可偏移距离。越大，每个线段的可偏移距离就越大。
        mBluePaint.setPathEffect(DiscretePathEffect(2F, 5F));
        mBluePaint.setStrokeWidth(2F);
        canvas.drawPath(path, mBluePaint);
        canvas.translate(0F, 100F);
        mBluePaint.setPathEffect(DiscretePathEffect(2F, 20F));
        canvas.drawPath(path, mBluePaint);

        canvas.translate(0F, 100F);
        mBluePaint.setPathEffect(DiscretePathEffect(20F, 20F));
        canvas.drawPath(path, mBluePaint);

        canvas.restore();//重新储存画布状态
    }

    /**
     * 路径点样路径样式
     *
     * @param canvas
     */
    private fun PathDashEffect(canvas: Canvas) {
        canvas.save();
        val path = Path();
        // 定义路径的起点
        path.moveTo(100F, 100F);
        path.lineTo(300F, 400F);
        path.lineTo(1000F, 300F);
        //变形过渡

//        val shape = CommonPath.nStarPath(5, 16F, 4F)
        val shape = CommonPath.commonPath()

        val mDashOffSet = 0F
        //Path shape:表示[路径点样],这里是一个五角星
        //float advance：表示两个[路径点样]间的距离
        //float phase：路径绘制偏移距离
        //Style style：表示在遇到转角时的过渡样式
        // ----Style.ROTATE表示通过旋转[路径点样]来过渡转角；
        // ----Style.MORPH表示通过变形[路径点样]来过渡转角；
        // ----Style.TRANSLATE表示通过位移[路径点样]来过渡转角。
        mBluePaint.pathEffect = PathDashPathEffect(
            shape, 60F, mDashOffSet, PathDashPathEffect.Style.ROTATE
        )
        canvas.drawPath(path, mBluePaint);
        canvas.restore();
        //旋转过渡
        canvas.save();
        canvas.translate(0F, 200F);
        mBluePaint.setPathEffect(
            PathDashPathEffect(
                shape, 60F, mDashOffSet, PathDashPathEffect.Style.MORPH
            )
        );
        canvas.drawPath(path, mBluePaint);
        canvas.restore();

        //移动过渡
        canvas.save();
        canvas.translate(0F, 500F);
        mBluePaint.setPathEffect(
            PathDashPathEffect(
                shape, 60F, mDashOffSet, PathDashPathEffect.Style.TRANSLATE
            )
        );
        canvas.drawPath(path, mBluePaint);
        canvas.restore();
    }


    /**
     * 叠加样式
     *
     * @param canvas
     */
    private fun composeEffect(canvas: Canvas) {
        mBluePaint.setStyle(Paint.Style.STROKE)
        mBluePaint.setStrokeWidth(4F)

        canvas.save()
        canvas.translate(0F, 100F)
        val path = Path()
        val mDashOffSet = 0f
        // 定义路径的起点
        path.moveTo(100F, 80F);
        path.lineTo(600F, 300F);
        path.lineTo(1000F, 80F);

        val shape = CommonPath.commonPath()
        val effect1 = PathDashPathEffect(shape, 40F, mDashOffSet, PathDashPathEffect.Style.ROTATE)
        val effect2 = DiscretePathEffect(20F, 20F);

        mBluePaint.setPathEffect(ComposePathEffect(effect1, effect2))//离散效果+样点效果
        mGreenPaint.setPathEffect(effect2)

        canvas.drawPath(path, mBluePaint)
        canvas.drawPath(path, mGreenPaint)

        canvas.restore()

    }


    /**
     * 叠加样式
     *
     * @param canvas
     */
    private fun sumEffect(canvas: Canvas) {
        mBluePaint.setStyle(Paint.Style.STROKE)
        mBluePaint.setStrokeWidth(4F)

        canvas.save()
        canvas.translate(0F, 100F)
        val path = Path()
        // 定义路径的起点
        path.moveTo(100F, 80F);
        path.lineTo(600F, 300F);
        path.lineTo(1000F, 80F);

        val dPathEffect = DashPathEffect(floatArrayOf(10F, 10F), 0F)
        val cPathEffect = CornerPathEffect(100F)
        mBluePaint.pathEffect = SumPathEffect(dPathEffect, cPathEffect)
        canvas.drawPath(path, mBluePaint)

        canvas.restore()

    }


    private val shaderEffects = arrayListOf<Shader>()

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setLinearShader(canvas: Canvas) {
        val colorStart = Color.parseColor("#FF0000")
        val colorEnd = Color.parseColor("#00FF00")
        canvas.save();
        canvas.translate(0f, 0F);
        mRedPaint.setStyle(Paint.Style.FILL);

        shaderEffects.add(
            LinearGradient(
                0F, 50F, 400F, 50F, colorStart, colorEnd, Shader.TileMode.MIRROR
            )
        )

        shaderEffects.add(
            LinearGradient(
                0F, 0F, 400F, 100F, colorStart, colorEnd, Shader.TileMode.MIRROR
            )
        )

        shaderEffects.add(
            LinearGradient(
                0F, 0F, 800F, 100F, colorStart, colorEnd, Shader.TileMode.MIRROR
            )
        )


        shaderEffects.add(
            LinearGradient(
                0F, 0F, 400F, 100F, colorStart, colorEnd, Shader.TileMode.CLAMP
            )
        )
        shaderEffects.add(
            LinearGradient(
                0F, 0F, 400F, 100F, colorStart, colorEnd, Shader.TileMode.REPEAT
            )
        )
        shaderEffects.add(
            LinearGradient(
                0F, 0F, 400F, 100F, colorStart, colorEnd, Shader.TileMode.DECAL
            )
        )

        for (i in shaderEffects.indices) {
            mRedPaint.setShader(shaderEffects[i])
            canvas.translate(0f, 200f)
            canvas.drawRect(0F, 0F, 800F, 100F, mRedPaint);
        }


    }

    private fun setSweepShader(canvas: Canvas) {
        val colorStart = Color.parseColor("#FF0000")
        val colorEnd = Color.parseColor("#00FF00")
        canvas.save();
        canvas.translate(200f, 200F)

        mRedPaint.setStyle(Paint.Style.FILL)

        val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE)
        val positions = floatArrayOf(0f, 0.5f, 0.75f, 0.75f, 1f)

        shaderEffects.add(
            SweepGradient(
                0F, 0F, colors, positions
            )
        )

        for (i in shaderEffects.indices) {
            mRedPaint.setShader(shaderEffects[i])
            canvas.drawCircle(0F, 0F, 200F, mRedPaint);
        }
    }


    private fun setRadialShader(canvas: Canvas) {
        val colorStart = Color.parseColor("#FF0000")
        val colorEnd = Color.parseColor("#00FF00")
        canvas.save();
        canvas.translate(0f, 0F);
        mRedPaint.setStyle(Paint.Style.FILL);

        shaderEffects.add(
            RadialGradient(
                100F, 100F, 50F, colorStart, colorEnd, Shader.TileMode.MIRROR
            )
        )

        shaderEffects.add(
            RadialGradient(
                100F, 100F, 50F, colorStart, colorEnd, Shader.TileMode.REPEAT
            )
        )

        shaderEffects.add(
            RadialGradient(
                100F, 100F, 50F, colorStart, colorEnd, Shader.TileMode.CLAMP
            )
        )


        for (i in shaderEffects.indices) {
            mRedPaint.setShader(shaderEffects[i])
            canvas.translate(0f, 250f)
            canvas.drawRect(0F, 0F, 200f, 200F, mRedPaint);
        }


    }


    private fun setBitmapShader(canvas: Canvas) {
        //加载图片，生成图片着色器
        val bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rank_banner_avatar_7);
        val bs = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mRedPaint.setShader(bs);
        mRedPaint.setTextSize(150F);
        mRedPaint.setStrokeWidth(10F);
        mRedPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(100F, 100F, 50f, mRedPaint)
    }


    private fun setComposeShader(canvas: Canvas) {
        val colorStart = Color.parseColor("#FF0000")
        val colorEnd = Color.parseColor("#00FF00")
        canvas.save();
        canvas.translate(0f, 0F);
        mRedPaint.setStyle(Paint.Style.FILL);


//        ComposeShader()


        for (i in shaderEffects.indices) {
            mRedPaint.setShader(shaderEffects[i])
            canvas.translate(0f, 200f)
            canvas.drawRect(0F, 0F, 800F, 100F, mRedPaint);
        }


    }


}