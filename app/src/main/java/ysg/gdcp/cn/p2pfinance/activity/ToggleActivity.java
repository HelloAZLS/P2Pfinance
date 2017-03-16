package ysg.gdcp.cn.p2pfinance.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.OnClick;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.common.BaseActivity;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/17 07:18.
 *
 * @author ysg
 */

public class ToggleActivity extends BaseActivity {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.tg)
    ToggleButton tg;
    @Override
    protected void initData() {
tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            UIutils.toast("手势密码已经开始",false);
        }else {
            UIutils.toast("手势密码已经关闭",false);
        }
    }
});
    }

    @OnClick(R.id.title_left)
    public void back(View v) {
        closeCurrentActivity();
    }

    @Override
    protected void initTitle() {
        titleTv.setText("饼状图展示");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_toggle;
    }
}
