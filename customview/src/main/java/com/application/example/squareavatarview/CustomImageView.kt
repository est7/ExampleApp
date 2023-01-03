package  com.application.example.squareavatarview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.application.example.customview.R
import com.application.example.squareavatarview.Direction.LEFT
import com.application.example.squareavatarview.Direction.NONE
import com.application.example.squareavatarview.Direction.TOP
import com.application.example.squareavatarview.Direction.RIGHT
import com.application.example.squareavatarview.Direction.BOTTOM
import kotlin.math.sqrt


class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var mHeight: Float = -1f
    private var mWidth: Float = -1f
    private val clipPath: Path = Path()
    private val borderPath: Path = Path()
    private val borderPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var start: Direction = NONE
    private var end: Direction = NONE
    private var distance: Float = 0f

    private var borderEnabled: Boolean = false
    private var borderSize: Float = 0f
    private var borderColor: Int = Color.BLACK

    /**
     * 圆角的大小
     */
    private val mRadius: Float = 32F

    //
    private var mRadiusY: Float = 0F

    private var mRadiusZ: Float = 0F

    init {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.DiagonalImageView).apply {
                start = Direction.get(getInt(R.styleable.DiagonalImageView_di_start, 0))
                end = Direction.get(getInt(R.styleable.DiagonalImageView_di_end, 0))
                distance =
                    getDimensionPixelSize(R.styleable.DiagonalImageView_di_distance, 0).toFloat()
                borderEnabled = getBoolean(R.styleable.DiagonalImageView_di_borderEnabled, false)
                borderSize =
                    getDimensionPixelSize(R.styleable.DiagonalImageView_di_borderSize, 0).toFloat()
                borderColor = getColor(R.styleable.DiagonalImageView_di_borderColor, Color.BLACK)
                recycle()
            }

            borderPaint.apply {
                style = Paint.Style.STROKE
                color = borderColor
                strokeWidth = borderSize
                setPathEffect(CornerPathEffect(10f))
            }

            // https://developer.android.com/guide/topics/graphics/hardware-accel.html#unsupported
            val layerType =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) LAYER_TYPE_HARDWARE else LAYER_TYPE_SOFTWARE
            setLayerType(layerType, null)
        }
    }

    override fun invalidate() {
        super.invalidate()
        setClipPath()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setClipPath()
    }

    private fun setupRadius() {
        mRadiusY = (mRadius * mHeight) / distance
        mRadiusZ = sqrt((mRadius * mRadius + mRadiusY * mRadiusY).toDouble()).toFloat()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        if (!clipPath.isEmpty) {
            canvas?.clipPath(clipPath)
        }
        super.dispatchDraw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        if (clipPath.isEmpty) {
            super.onDraw(canvas)
            return
        }

        canvas?.apply {
            save()
            clipPath(clipPath)
            super.onDraw(this)
            if (!borderPath.isEmpty) {
                drawPath(borderPath, borderPaint)
            }

            restore()
        }
    }


    private fun setClipPath() {
        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()
        if (width <= 0 || height <= 0) return

        mWidth = width
        mHeight = height
        setupRadius()
        clipPath.reset()
        borderPath.reset()

        when (start) {
            LEFT -> createLeftPath(width, height)
            TOP -> createTopPath(width, height)
            RIGHT -> createRightPath(width, height)
            BOTTOM -> createBottomPath(width, height)
            else -> return
        }

        clipPath.close()
        borderPath.close()
    }

    private fun isTopOrLeft(): Boolean = end == TOP || end == LEFT

    private fun createTopPath(width: Float, height: Float) {
        if (isTopOrLeft()) {
            clipPath.apply {
                moveTo(0f, 0f)
                lineTo(width, distance)
                lineTo(width, height)
                lineTo(0f, height)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(0f, 0f)
                    lineTo(width, distance)
                }
            }
        } else {
            clipPath.apply {
                moveTo(0f, distance)
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(0f, distance)
                    lineTo(width, 0f)
                }
            }
        }
    }

    private fun createBottomPath(width: Float, height: Float) {
        if (isTopOrLeft()) {
            clipPath.apply {
                moveTo(0f, 0f)
                lineTo(width, 0f)
                lineTo(width, height - distance)
                lineTo(0f, height)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(0f, height)
                    lineTo(width, height - distance)
                }
            }
        } else {
            clipPath.apply {
                moveTo(0f, 0f)
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height - distance)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(0f, height - distance)
                    lineTo(width, height)
                }
            }
        }
    }

    private fun createLeftPath(width: Float, height: Float) {
        if (isTopOrLeft()) {
            clipPath.apply {
                moveTo(distance, 0f)
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(distance, 0f)
                    lineTo(0f, height)
                }
            }
        } else {
            clipPath.apply {
                moveTo(0f, 0f)
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(distance, height)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(0f, 0f)
                    lineTo(distance, height)
                }
            }
        }
    }

    private fun createRightPath(width: Float, height: Float) {
        if (isTopOrLeft()) {
            clipPath.apply {
//                moveTo(0f, 0f)
//                lineTo(width, 0f)
//                lineTo(width - distance, height)
//                lineTo(0f, height)


                moveTo(0f, 0f)
                lineTo(width - mRadius, 0f)
//                quadTo(width, 0f, width, mRadius)
                //this is magic

                //接近正确
                /*
                                cubicTo(
                                    width + 20f, 0f,
                                    width, mRadius + 8f,
                                    width - distance, height - mRadius
                                )

                                                cubicTo(
                    width + 20f, 0f,
                    width - mRadius, mRadius + 60f,
                    width - distance, height + 80f
                )

                */

//                quadTo(width + 20f, 0f, width-4f,60f)

                cubicTo(
                    width + 20f,
                    0f,
                    width - mRadius + 10,
                    mRadius + 30f,
                    width - distance + mRadius + 40,
                    height
                )

                lineTo(width - distance + mRadius, height - mRadiusY)

                cubicTo(
                    width - distance,
                    height,
                    width - distance,
                    height,
                    width - distance - mRadius,
                    height
                )

                lineTo(0f + mRadius, height)
                quadTo(0f, height, 0f, height - mRadius)

                lineTo(0f, mRadius)
                quadTo(0f, 0f, mRadius, 0f)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(width + 2, 0f)
                    lineTo(width - distance + 2, height)
                }
            }
        } else {
            clipPath.apply {
                moveTo(0f, 0f)
                lineTo(width - distance, 0f)
                lineTo(width, height)
                lineTo(0f, height)
            }

            if (borderEnabled) {
                borderPath.apply {
                    moveTo(width - distance, 0f)
                    lineTo(width, height)
                }
            }
        }
    }
}