package ysg.gdcp.cn.p2pfinance.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import ysg.gdcp.cn.p2pfinance.domain.Login;

/**
 * Created by Administrator on 2017/3/15 22:01.
 *
 * @author ysg
 */

public abstract class BaseActivity extends FragmentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();

    }

    public void closeCurrentActivity() {
        AppManager.getInstance().removeCurent();
    }

    /**
     * 保存登录信息
     *
     * @param login
     */
    public void saveLogin(Login login) {
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("UF_ACC", login.UF_ACC);
        edit.putString("UF_AVATAR_URL", login.UF_AVATAR_URL);
        edit.putString("UF_IS_CERT", login.UF_IS_CERT);
        edit.putString("UF_PHONE", login.UF_PHONE);
        edit.commit();
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public Login getLogin() {
        Login login = new Login();
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        login.UF_ACC = sp.getString("UF_ACC", "");
        login.UF_AVATAR_URL = sp.getString("UF_AVATAR_URL", "");
        login.UF_IS_CERT = sp.getString("UF_IS_CERT", "");
        login.UF_PHONE = sp.getString("UF_PHONE", "");
        return login;
    }

    /**
     * 跳转到目标ACtivity
     *
     * @param clzz
     * @param bundle
     */
    public void gotoActivity(Class clzz, Bundle bundle) {
        Intent intent = new Intent(this, clzz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    protected abstract void initData();

    protected abstract void initTitle();

    public abstract int getLayoutId();
}
