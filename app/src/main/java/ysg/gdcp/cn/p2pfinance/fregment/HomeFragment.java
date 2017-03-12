package ysg.gdcp.cn.p2pfinance.fregment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/11.
 */

public class HomeFragment extends Fragment {
    @Bind(R.id.title_left)
    ImageView TitleLeft;
    @Bind(R.id.title_tv)
    TextView TitleTv;
    @Bind(R.id.title_right)
    ImageView TitleRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXMLView(R.layout.fragment_home);
        ButterKnife.bind(this,view);
        initTitle();
        return view;
    }

    private void initTitle() {
        TitleLeft.setVisibility(View.INVISIBLE);
        TitleRight.setVisibility(View.INVISIBLE);
    }
}
