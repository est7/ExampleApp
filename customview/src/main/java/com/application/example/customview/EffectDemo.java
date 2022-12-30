package com.application.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <com.application.example.customview.EffectDemo
 * android:layout_width="match_parent"
 * android:layout_height="300dp"/>
 *
 * @author: est7
 * @date: 2022/12/29
 */
public class EffectDemo extends View {
    public EffectDemo(Context context) {
        super(context);
    }

    public EffectDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EffectDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint mPaint;
    private Path mPath;
    private PathEffect[] pathEffects = new PathEffect[7];
    private float mPhase = 5;


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        initPath();
    }

    private void initPath() {
        mPath = new Path();
        mPath.moveTo(10, 50);
        for (int i = 0; i < 20; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }

        pathEffects[0] = null;
        pathEffects[1] = new CornerPathEffect(20);
        pathEffects[2] = new DashPathEffect(new float[]{20, 10}, mPhase);
        pathEffects[3] = new DiscretePathEffect(5.0f, 10.0f);

        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        pathEffects[4] = new PathDashPathEffect(path, 20, mPhase, PathDashPathEffect.Style.ROTATE);

        pathEffects[5] = new ComposePathEffect(pathEffects[2], pathEffects[1]);
        pathEffects[6] = new SumPathEffect(pathEffects[4], pathEffects[3]);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < pathEffects.length; i++) {
            mPaint.setPathEffect(pathEffects[i]);
            canvas.drawPath(mPath, mPaint);
            canvas.translate(0, 250);
        }
    }
}