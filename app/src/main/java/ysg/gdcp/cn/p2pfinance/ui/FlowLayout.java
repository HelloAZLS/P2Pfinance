package ysg.gdcp.cn.p2pfinance.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/3/15 09:16.
 *
 * @author ysg
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        int lineHeight = 0;
        int lineWidth = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            if (lineWidth + measuredWidth + params.leftMargin + params.rightMargin > widthSize) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
                lineWidth=measuredWidth+ params.leftMargin + params.rightMargin;
                lineHeight=measuredHeight+params.topMargin+params.bottomMargin;
            } else {
                lineWidth += measuredWidth + params.leftMargin + params.rightMargin;
                lineHeight=Math.max(lineHeight,measuredHeight+params.topMargin+params.bottomMargin);

            }
            if (i==count-1){
                width=Math.max(width,lineWidth);
                height+=lineHeight;
            }

        }


        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;
    }
}
