package ysg.gdcp.cn.p2pfinance.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import ysg.gdcp.cn.p2pfinance.R;

/**
 * Created by Administrator on 2017/3/13 14:32.
 *
 * @author ysg
 */

public class RoundProgress extends View {

    private int roundProgressColor;
    private float roundWidth;
    private int roundColor;
    private int textColor;
    private float textSize;
    private int max = 100;
    private int progress = 0;
    private Paint paint = new Paint();

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.RED);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.GREEN);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize, 15);
        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWidth, 5);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        int center = getWidth() / 2;
        int radius = (int) (center - roundWidth / 2);
        canvas.drawCircle(center, center, radius, paint);

        //绘制文本
        float textWidth = paint.measureText(progress + "%");
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        canvas.drawText(progress + "%", center - textWidth / 2, center + textSize / 2, paint);

        //绘制圆环
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundProgressColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (progress > 100) {
            this.progress = 100;
        }
        postInvalidate();
    }
}
