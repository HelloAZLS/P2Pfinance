package ysg.gdcp.cn.p2pfinance.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.common.BaseActivity;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/16 14:42.
 *
 * @author ysg
 */

public class TiXianActivity extends BaseActivity {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.account_zhifubao)
    TextView accountZhifubao;
    @Bind(R.id.select_bank)
    RelativeLayout selectBank;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.input_money)
    EditText inputMoney;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleTv.setText("提现");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_left)
    public void back(View v) {
        closeCurrentActivity();
    }

    @OnClick(R.id.btn_tixian)
    public void clickTiX(View v) {
        Toast.makeText(TiXianActivity.this, "嘿嘿，这是假的，你真以为送出去的钱还能要回来吗？你太天真了·····", Toast.LENGTH_SHORT).show();
        UIutils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeCurrentActivity();
            }
        },2000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tixian;
    }
}
