package ysg.gdcp.cn.p2pfinance.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

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
                lineWidth = measuredWidth + params.leftMargin + params.rightMargin;
                lineHeight = measuredHeight + params.topMargin + params.bottomMargin;
            } else {
                lineWidth += measuredWidth + params.leftMargin + params.rightMargin;
                lineHeight = Math.max(lineHeight, measuredHeight + params.topMargin + params.bottomMargin);

            }
            if (i == count - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }

        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    private List<List<View>> allViews = new ArrayList<>();
    private List<Integer> allHeights = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int count = getChildCount();

        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            MarginLayoutParams mp = (MarginLayoutParams) child.getLayoutParams();
            if (lineWidth + childWidth + mp.leftMargin + mp.rightMargin > width) {
                //换行
                allViews.add(lineViews);
                allHeights.add(lineHeight);
                lineViews = new ArrayList<>();
                lineWidth = 0;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;

            }
            //不换行
            lineWidth += childWidth + mp.rightMargin + mp.leftMargin;
            lineHeight = Math.max(lineHeight, childHeight + mp.topMargin + mp.bottomMargin);
            lineViews.add(child);
            if (i == count - 1) {
                allViews.add(lineViews);
                allHeights.add(lineHeight);
            }
        }
        int left = 0;
        int top = 0;
        for (int i = 0; i < allViews.size(); i++) {
            int  currentLineHeight = allHeights.get(i);
            List<View> views = allViews.get(i);
            for (View view : views) {
                int viewWidth  = view.getMeasuredWidth();
                int widthHeight  = view.getMeasuredHeight();
                MarginLayoutParams mp = (MarginLayoutParams) view.getLayoutParams();
                int lc = left+mp.leftMargin;
                int tc = top+mp.topMargin;
                int rc = lc+viewWidth;
                int bc = tc+widthHeight;
                view.layout(lc,tc,rc,bc);
                left+=viewWidth+mp.rightMargin+mp.leftMargin;
            }
            left=0;
            top+=currentLineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;
    }
}
