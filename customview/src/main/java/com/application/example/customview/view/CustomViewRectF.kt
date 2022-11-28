import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 *https://blog.csdn.net/qq_38436214/article/details/107983588
 * @description
 * @param
 * @return
 * @author est7
 * @time 2022/11/28 11:16
 */
public class CustomViewRectF extends View {

    public CustomViewRectF (Context context) {
        super(context);
    }

    public CustomViewRectF (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Paint customPaint(int color) {
        Paint paint = new Paint();
        paint.setColor(color);//画笔颜色
        paint.setStyle(Paint.Style.FILL);//实心
        paint.setStrokeWidth(6);//画笔宽度
        paint.setAntiAlias(true);//光滑
        return paint;
    }

    /**
     * 在纸上画矩形
     * @param canvas 纸
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画矩形  以两个点来画，起点和终点，通常是左上为起点，右下为终点  以下面这个图来看
         * 参数一：起点的Y轴坐标
         * 参数二：起点的X轴坐标
         * 参数三：终点的Y轴坐标
         * 参数四：终点的Y轴坐标
         *
         *      *
         *      *
         *      *  top
         *  ****************
         *      *          *
         * left *          *  right
         *      *          *
         *      *          *
         *      ******************
         *         bottom  *
         *                 *
         *                 *
         *  可以看到，左和上无限延长就会在一个点，右和下也是如此，这样应该理解了吧
         *
         */
        RectF rectF = new RectF(10, 10, 200, 200);
        canvas.drawRect(rectF, customPaint(Color.BLUE));


        //第二种
        RectF rectF = new RectF(10, 160, 100, 200);//长方形
        canvas.drawRect(rectF, customPaint(Color.GREEN));

        RectF rectF2 = new RectF(100, 120, 190, 200);//长方形2
        canvas.drawRect(rectF2, customPaint(Color.YELLOW));

        RectF rectF3 = new RectF(190, 80, 280, 200);//长方形3
        canvas.drawRect(rectF3, customPaint(Color.BLUE));

        RectF rectF4 = new RectF(280, 40, 370, 200);//长方形4
        canvas.drawRect(rectF4, customPaint(Color.RED));

//        第三种
        RectF rectF = new RectF(10, 10, 300, 100);//长方形
        canvas.drawRect(rectF, customPaint(Color.GREEN));

        RectF rectF2 = new RectF(300, 10, 390, 300);//长方形2
        canvas.drawRect(rectF2, customPaint(Color.YELLOW));

        RectF rectF3 = new RectF(100, 300, 390, 390);//长方形3
        canvas.drawRect(rectF3, customPaint(Color.BLUE));

        RectF rectF4 = new RectF(10, 100, 100, 390);//长方形4
        canvas.drawRect(rectF4, customPaint(Color.RED));

        //第四种

        RectF rectF = new RectF(10, 100, 300, 190);//长方形
        canvas.drawRect(rectF, customPaint(Color.GREEN));

        RectF rectF2 = new RectF(300, 10, 390, 300);//长方形2
        canvas.drawRect(rectF2, customPaint(Color.YELLOW));

        RectF rectF3 = new RectF(190, 300, 480, 390);//长方形3
        canvas.drawRect(rectF3, customPaint(Color.BLUE));

        RectF rectF4 = new RectF(100, 190, 190, 480);//长方形4
        canvas.drawRect(rectF4, customPaint(Color.RED));
    }

}