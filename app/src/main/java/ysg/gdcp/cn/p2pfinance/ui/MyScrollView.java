package ysg.gdcp.cn.p2pfinance.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;


/**
 * Created by Administrator on 2017/3/12 16:19.
 *
 * @author ysg
 */

public class MyScrollView extends ScrollView {
    private View innerView;
    private float y;
    private Rect normal = new Rect();
    private boolean animationFinish =true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount > 0) {
            innerView = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerView == null) {
            return super.onTouchEvent(ev);
        } else {
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 处理点击事件
     * @param ev
     */
    private void commonTouchEvent(MotionEvent ev) {
        if (animationFinish){
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float preY = y == 0 ? ev.getY() : y;
                    float nowY = ev.getY();
                    int detailY = (int) (preY - nowY);
                    y = nowY;
                    if (isNeedMove()) {
                        if (normal.isEmpty()) {
                            normal.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(), innerView.getBottom());
                        }
                        innerView.layout(innerView.getLeft(), innerView.getTop() - detailY / 2, innerView.getRight(), innerView.getBottom() - detailY / 2);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    y = 0;
                    if (isNeedAnimation()) {
                        animation();
                    }
                    break;
            }
        }

    }

    /**
     * 松开收之后的动画
     */
    private void animation() {
        TranslateAnimation animation = new TranslateAnimation(0,0,0,normal.top-innerView.getTop());
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish =false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerView.clearAnimation();
                innerView.layout(normal.left,normal.top,normal.right,normal.bottom);
                normal.setEmpty();
                animationFinish =true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerView.startAnimation(animation);
    }

    /**
     * 是否需要动画
     *
     * @return
     */
    private boolean isNeedAnimation() {
      return  !normal.isEmpty();
    }

    /**
     * 是否需要移动
     *
     * @return
     */
    private boolean isNeedMove() {
        int offset = innerView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;

    }
}
