package ysg.gdcp.cn.p2pfinance.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/17 07:43.
 *
 * @author ysg
 */

public class AlawysTextView extends TextView {
    public AlawysTextView(Context context) {
        super(context);
    }

    public AlawysTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlawysTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return  true;
    }
}
