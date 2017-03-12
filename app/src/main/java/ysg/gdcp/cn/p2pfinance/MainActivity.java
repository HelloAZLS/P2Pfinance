package ysg.gdcp.cn.p2pfinance;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ysg.gdcp.cn.p2pfinance.fregment.HomeFragment;
import ysg.gdcp.cn.p2pfinance.fregment.MeFragment;
import ysg.gdcp.cn.p2pfinance.fregment.MoreFragment;
import ysg.gdcp.cn.p2pfinance.fregment.TouZiFragment;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.ll_home)
    LinearLayout llHome;
    @Bind(R.id.iv_touzi)
    ImageView ivTouZi;
    @Bind(R.id.tv_touzi)
    TextView tvTouZi;
    @Bind(R.id.ll_touzi)
    LinearLayout llTouZi;

    @Bind(R.id.iv_me)
    ImageView ivMe;
    @Bind(R.id.tv_me)
    TextView tvMe;
    @Bind(R.id.ll_me)
    LinearLayout llMe;

    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.tv__more)
    TextView tvMore;
    @Bind(R.id.ll_more)
    LinearLayout llMore;
    private HomeFragment homeFragment;
    private MeFragment meFragment;
    private TouZiFragment touziFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        setSelect(0);
    }

    @OnClick({R.id.ll_home, R.id.ll_touzi, R.id.ll_me, R.id.ll_more})
    public void changeTab(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_touzi:
                setSelect(1);
                break;
            case R.id.ll_me:
                setSelect(2);
                break;
            case R.id.ll_more:
                setSelect(3);
                break;

        }
    }

    private void setSelect(int i) {
        FragmentManager manager = getSupportFragmentManager();
        ft = manager.beginTransaction();
        hideFragment();
        resetTab();
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.content, homeFragment);
                }
                ivHome.setImageResource(R.drawable.bid01);
                tvHome.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(homeFragment);
                break;
            case 1:
                if (touziFragment == null) {
                    touziFragment = new TouZiFragment();
                    ft.add(R.id.content, touziFragment);
                }
                ivTouZi.setImageResource(R.drawable.bid03);
                tvTouZi.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(touziFragment);
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.content, meFragment);
                }
                ivMe.setImageResource(R.drawable.bid05);
                tvMe.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(meFragment);
                break;
            case 3:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.content, moreFragment);
                }
                ivMore.setImageResource(R.drawable.bid07);
                tvMore.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(moreFragment);
                break;

        }
        ft.commit();
    }

    private void resetTab() {
        ivHome.setImageResource(R.drawable.bid02);
        ivTouZi.setImageResource(R.drawable.bid04);
        ivMe.setImageResource(R.drawable.bid06);
        ivMore.setImageResource(R.drawable.bid08);
        tvHome.setTextColor(UIutils.getColor(R.color.home_back_unselected));
        tvTouZi.setTextColor(UIutils.getColor(R.color.home_back_unselected));
        tvMe.setTextColor(UIutils.getColor(R.color.home_back_unselected));
        tvMore.setTextColor(UIutils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
        if (touziFragment != null) {
            ft.hide(touziFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }

    }


}
