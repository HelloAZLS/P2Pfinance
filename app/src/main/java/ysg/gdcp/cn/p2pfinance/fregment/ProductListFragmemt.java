package ysg.gdcp.cn.p2pfinance.fregment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.common.AppNetConfig;
import ysg.gdcp.cn.p2pfinance.domain.Product;
import ysg.gdcp.cn.p2pfinance.ui.RoundProgress;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/14 17:17.
 *
 * @author ysg
 */

public class ProductListFragmemt extends Fragment {
    @Bind(R.id.lv)
    ListView lv;

    private AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIutils.getXMLView(R.layout.fragment_product_list);
        ButterKnife.bind(this, view);
        initTitle();
        initData();
        return view;
    }

    private void initTitle() {
    }

    private void initData() {
        client.post(AppNetConfig.PRODUCT, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.getBoolean("success")) {
                    String data = jsonObject.getString("data");
                    List<Product> products = JSON.parseArray(data, Product.class);
                    lv.setAdapter(new MyAdapter(products));
                }

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyAdapter extends BaseAdapter {


        private List<Product> products;

        public MyAdapter(List<Product> products) {
            this.products = products;
        }

        @Override
        public int getCount() {
            return products == null ? 0 : products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            Product product = products.get(position);
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.PMinZouZi.setText(product.minTouMoney);
            viewHolder.PMoney.setText(product.money);
            viewHolder.pName.setText(product.name);
            viewHolder.pSuodingDays.setText(product.suodingDays);
            viewHolder.pYearlv.setText(product.yearLv);
            viewHolder.pProgress.setProgress(Integer.parseInt(product.progress));
            return convertView;
        }
    }

    static class ViewHolder {

        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView PMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingDays;
        @Bind(R.id.p_minzouzi)
        TextView PMinZouZi;
        @Bind(R.id.p_progresss)
        RoundProgress pProgress;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


    }
}
