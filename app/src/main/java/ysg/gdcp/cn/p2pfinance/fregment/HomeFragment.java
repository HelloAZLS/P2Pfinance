package ysg.gdcp.cn.p2pfinance.fregment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.common.AppNetConfig;
import ysg.gdcp.cn.p2pfinance.domain.Image;
import ysg.gdcp.cn.p2pfinance.domain.Index;
import ysg.gdcp.cn.p2pfinance.domain.Product;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by Administrator on 2017/3/11.
 */
////// TODO: 2017/3/13  测试一下
public class HomeFragment extends Fragment {

    AsyncHttpClient client = new AsyncHttpClient();
    @Bind(R.id.title_left)
    ImageView TitleLeft;
    @Bind(R.id.title_tv)
    TextView TitleTv;
    @Bind(R.id.title_right)
    ImageView TitleRight;
    @Bind(R.id.vp_barner)
    ViewPager vpBarner;
    @Bind(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.myscrollview)
    ScrollView myScrollView;
    private Index index;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXMLView(R.layout.fragment_home);
        ButterKnife.bind(this, view);
        initTitle();
        initData();

        return view;
    }

    private void initData() {
        index = new Index();
        client.post(AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                Log.i("返回的数据",content);
                JSONObject jsonObject = parseObject(content);
                String proInfo = jsonObject.getString("proInfo");
                Product product = parseObject(proInfo, Product.class);
                String imageArr = jsonObject.getString("imageArr");
                List<Image> imageList = JSON.parseArray(imageArr, Image.class);
                index.product = product;
                index.imageList = imageList;
                vpBarner.setAdapter(new MyAdapter());
                circleBarner.setViewPager(vpBarner);

            }

            @Override
            public void onFailure(Throwable error, String content) {
                Toast.makeText(getActivity(), "请求服务器数据异常.....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTitle() {
        TitleLeft.setVisibility(View.INVISIBLE);
        TitleRight.setVisibility(View.INVISIBLE);
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return index.imageList == null ? 0 : index.imageList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = index.imageList.get(position).IMAURL;
            ImageView view = new ImageView(getActivity());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load(imageUrl).into(view);
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
