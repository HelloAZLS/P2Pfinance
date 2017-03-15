package ysg.gdcp.cn.p2pfinance.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by Administrator on 2017/3/15 16:05.
 *
 * @author ysg
 */

public class DrawableUtils {
    public static GradientDrawable getDrawable(int rgb, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(rgb);
        drawable.setGradientType(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(radius);
        drawable.setStroke(UIutils.dp2px(1), rgb);
        return drawable;
    }

    public static StateListDrawable getSelector(Drawable normalDrawable, Drawable pressDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }

}
