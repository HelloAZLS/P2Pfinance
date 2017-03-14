package ysg.gdcp.cn.p2pfinance.fregment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/11.
 */

public class TouZiFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.tab_indictor)
    TabPageIndicator tabIndictor;
    @Bind(R.id.pager)
    ViewPager pager;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initTitle() {
        titleTv.setText("我要投资");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData(String content) {
        initFragment();
        pager.setAdapter(new MyAdapter(getFragmentManager()));

        tabIndictor.setViewPager(pager);
    }

    private void initFragment() {
        ProductListFragmemt productListFragmemt = new ProductListFragmemt();
        ProductReCommendFragmemt productReCommendFragmemt = new ProductReCommendFragmemt();
        ProductHotFragmemt productHotFragmemt = new ProductHotFragmemt();
        fragmentList.add(productListFragmemt);
        fragmentList.add(productReCommendFragmemt);
        fragmentList.add(productHotFragmemt);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_touzi;
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIutils.getStringArr(R.array.touzi_tab)[position];
        }
    }
}
