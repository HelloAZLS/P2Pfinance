package ysg.gdcp.cn.p2pfinance.fregment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.ui.FlowLayout;
import ysg.gdcp.cn.p2pfinance.utils.DrawableUtils;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/14 17:17.
 *
 * @author ysg
 */

public class ProductHotFragmemt extends Fragment {
    @Bind(R.id.flow)
    FlowLayout flow;

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "shi jie shi ge da sha bi","中学老师购买车辆",
            "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转", "HelloWorld",
            "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "天真"};
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXMLView(R.layout.fragment_product_hot);
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        random = new Random();
        for (String data : datas) {
            TextView view = new TextView(getActivity());
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin=UIutils.dp2px(10);
            params.rightMargin=UIutils.dp2px(10);
            params.topMargin=UIutils.dp2px(10);
            params.bottomMargin=UIutils.dp2px(10);
            view.setLayoutParams(params);
            view.setText(data);
            Log.i("你好",""+data);
            int r = random.nextInt(210);
            int g = random.nextInt(210);
            int b = random.nextInt(210);
            view.setBackground(
                    DrawableUtils.getSelector(DrawableUtils.getDrawable(Color.rgb(r,g,b),UIutils.dp2px(5)),
                    DrawableUtils.getDrawable(Color.WHITE,UIutils.dp2px(5))));
            //view.setBackground(DrawableUtils.getDrawable(Color.rgb(r,g,b),UIutils.dp2px(5)));
            int padding = UIutils.dp2px(10);
            view.setPadding(padding,padding,padding,padding);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            flow.addView(view);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
