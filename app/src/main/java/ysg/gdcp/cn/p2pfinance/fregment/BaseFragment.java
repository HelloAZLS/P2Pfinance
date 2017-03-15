package ysg.gdcp.cn.p2pfinance.fregment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import ysg.gdcp.cn.p2pfinance.ui.LoadingPager;

/**
 * Created by Administrator on 2017/3/13 22:22.
 *
 * @author ysg
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPager = new LoadingPager(container.getContext()) {
            @Override
            protected void OnSucess(ResultState resultState, View sucessView) {
                ButterKnife.bind(BaseFragment.this, sucessView);
                initTitle();
                initData(resultState.getContent());
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected String url() {
                return getUrl();
            }

            @Override
            public int layoutId() {
                return getLayoutId();
            }
        };

        return loadingPager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public  void show(){
        loadingPager.show();
    }
    protected abstract void initTitle();

    protected abstract void initData(String content);

    public abstract int getLayoutId();

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

}
