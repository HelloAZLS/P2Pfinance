package ysg.gdcp.cn.p2pfinance.fregment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;
import butterknife.OnClick;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.activity.BarChartActivity;
import ysg.gdcp.cn.p2pfinance.activity.ChongZhiActivity;
import ysg.gdcp.cn.p2pfinance.activity.LineChartActivity;
import ysg.gdcp.cn.p2pfinance.activity.LoginActivity;
import ysg.gdcp.cn.p2pfinance.activity.PieChartActivity;
import ysg.gdcp.cn.p2pfinance.activity.TiXianActivity;
import ysg.gdcp.cn.p2pfinance.activity.UserInfoActivity;
import ysg.gdcp.cn.p2pfinance.common.BaseActivity;
import ysg.gdcp.cn.p2pfinance.domain.Login;
import ysg.gdcp.cn.p2pfinance.utils.BitmapUtils;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/11.
 */

public class MeFragment extends BaseFragment {


    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.icon_time)
    RelativeLayout iconTime;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @Bind(R.id.chongzhi)
    ImageView chongzhi;
    @Bind(R.id.tixian)
    ImageView tixian;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichang)
    TextView llZichang;
    @Bind(R.id.ll_zhanquan)
    TextView llZhanquan;

    @Override
    protected void initTitle() {
        titleTv.setText("我的资产");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.title_right)
    public void clickSettings(View view) {
        ((BaseActivity) getActivity()).gotoActivity(UserInfoActivity.class, null);
    }
    @OnClick(R.id.tixian)
    public void clickTX(View view) {
        ((BaseActivity) getActivity()).gotoActivity(TiXianActivity.class, null);
    }
    @OnClick(R.id.chongzhi)
    public void clickChongzhi(View view) {
        ((BaseActivity) getActivity()).gotoActivity(ChongZhiActivity.class, null);
    }
    @OnClick(R.id.ll_touzi)
    public void clickLine(View view) {
        ((BaseActivity) getActivity()).gotoActivity(LineChartActivity.class, null);
    }

    @OnClick(R.id.ll_touzi_zhiguan)
    public void clickBar(View view) {
        ((BaseActivity) getActivity()).gotoActivity(BarChartActivity.class, null);
    }
    @OnClick(R.id.ll_zichang)
    public void clickPie(View view) {
        ((BaseActivity) getActivity()).gotoActivity(PieChartActivity.class, null);
    }


    @Override
    protected void initData(String content) {
        // isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String uf_acc = sp.getString("UF_ACC", "");
        if (TextUtils.isEmpty(uf_acc)) {
            //未登录
            showLoginDialog();
        } else {
            dealUser();
        }
    }

    private void dealUser() {
        Login login = ((BaseActivity) getActivity()).getLogin();
        textView11.setText(login.UF_ACC);
        Picasso.with(getActivity()).load(login.UF_AVATAR_URL).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap zoom = BitmapUtils.zoom(source, UIutils.dp2px(62), UIutils.dp2px(62));
                Bitmap bitmap = BitmapUtils.circleBitMap(zoom);
                source.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "";
            }
        });
    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录");
        builder.setMessage("必须先登录.....");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();
                ((BaseActivity) getActivity()).gotoActivity(LoginActivity.class, null);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return null;
    }
}
