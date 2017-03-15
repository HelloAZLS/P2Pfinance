package ysg.gdcp.cn.p2pfinance.fregment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.activity.LoginActivity;
import ysg.gdcp.cn.p2pfinance.common.BaseActivity;

/**
 * Created by Administrator on 2017/3/11.
 */

public class MeFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView TitleLeft;
    @Bind(R.id.title_tv)
    TextView TitleTv;
    @Bind(R.id.title_right)
    ImageView TitleRight;

    @Override
    protected void initTitle() {
        TitleTv.setText("我的资产");
        TitleLeft.setVisibility(View.INVISIBLE);
        TitleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData(String content) {
        isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String uf_acc = sp.getString("UF_ACC", "");
        if (TextUtils.isEmpty(uf_acc)) {
            //未登录
            showLoginDialog();
        } else {

        }
    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录");
        builder.setMessage("必须先登录.....");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();
                ((BaseActivity)getActivity()).gotoActivity(LoginActivity.class,null);
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
