package ysg.gdcp.cn.p2pfinance.fregment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/14 17:17.
 *
 * @author ysg
 */

public class ProductHotFragmemt extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXMLView(R.layout.fragment_product_hot);
        return view;
    }
}
