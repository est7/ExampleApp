package com.application.example.edgeTransparentView
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.opengl.ETC1.getHeight

import android.opengl.ETC1.getWidth
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.application.example.customview.R


/*
class EdgeTransparentView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var position = 0
    private var drawSize = 0f
    private val topMask = 0x01
    private val bottomMask = topMask shl 1
    private val leftMask = topMask shl 2
    private val rightMask = topMask shl 3
    private var mWidth = 0
    private var mHeight = 0


    private fun init(context: Context, attrs: AttributeSet?) {
        mPaint.style = Paint.Style.FILL
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.EdgeTransparentView)
        position = typedArray.getInt(R.styleable.EdgeTransparentView_edge_position, 0)
        drawSize = typedArray.getDimension(
            R.styleable.EdgeTransparentView_edge_width,
            ScreenUtil.dp2px(20)
        )
        typedArray.recycle()
    }

    protected fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
    }

    protected fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initShader()
        mWidth = android.opengl.ETC1.getWidth()
        mHeight = android.opengl.ETC1.getHeight()
    }

    //渐变颜色
    private val mGradientColors = intArrayOf(-0x1, 0x00000000)

    //渐变位置
    private val mGradientPosition = floatArrayOf(0f, 1f)

    init {
        init(context, attrs)
    }

    private fun initShader() {
        mPaint.setShader(
            LinearGradient(
                0,
                0,
                0,
                drawSize,
                mGradientColors,
                mGradientPosition,
                Shader.TileMode.CLAMP
            )
        )
    }

    protected fun drawChild(canvas: Canvas, child: View?, drawingTime: Long): Boolean {
        val layerSave: Int = canvas.saveLayer(
            0,
            0,
            android.opengl.ETC1.getWidth(),
            android.opengl.ETC1.getHeight(),
            null,
            Canvas.ALL_SAVE_FLAG
        )
        val drawChild: Boolean = super.drawChild(canvas, child, drawingTime)
        if (position == 0 || position and topMask != 0) {
            canvas.drawRect(0, 0, mWidth, drawSize, mPaint)
        }
        if (position == 0 || position and bottomMask != 0) {
            val save: Int = canvas.save()
            canvas.rotate(180, mWidth / 2f, mHeight / 2f)
            canvas.drawRect(0, 0, mWidth, drawSize, mPaint)
            canvas.restoreToCount(save)
        }
        val offset = (mHeight - mWidth) / 2f
        if (position == 0 || position and leftMask != 0) {
            val saveCount: Int = canvas.save()
            canvas.rotate(270, mWidth / 2f, mHeight / 2f)
            canvas.translate(0, offset)
            canvas.drawRect(0 - offset, 0, mWidth + offset, drawSize, mPaint)
            canvas.restoreToCount(saveCount)
        }
        if (position == 0 || position and rightMask != 0) {
            val saveCount: Int = canvas.save()
            canvas.rotate(90, mWidth / 2f, mHeight / 2f)
            canvas.translate(0, offset)
            canvas.drawRect(0 - offset, 0, mWidth + offset, drawSize, mPaint)
            canvas.restoreToCount(saveCount)
        }
        canvas.restoreToCount(layerSave)
        return drawChild
    }

    protected fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}*/
