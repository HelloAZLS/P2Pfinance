package ysg.gdcp.cn.p2pfinance.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.common.BaseActivity;

/**
 * Created by Administrator on 2017/3/16 22:02.
 *
 * @author ysg
 */

public class PieChartActivity extends BaseActivity {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.chart)
    PieChart chart;
    @Override
    protected void initData() {
        // apply styling
        chart.getDescription().setEnabled(false);
        //绘制最内层的圆的半径
        chart.setHoleRadius(42f);
        //绘制包裹最内层圆的外层圆的半径
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText("资产分配");
        chart.setCenterTextSize(14f);
        //是否适用百分比显示值
        chart.setUsePercentValues(false);

        PieData mChartData = generateDataPie();
//        mChartData.setValueFormatter(new PercentFormatter());
        mChartData.setValueTextSize(14f);
        mChartData.setValueTextColor(Color.RED);
        // set data
        chart.setData((PieData) mChartData);

        //饼图指示器绘制
        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setYEntrySpace(0f);
        l.setYOffset(20f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateXY(900, 900);
    }
    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i+1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(d);
        return cd;
    }

    @OnClick(R.id.title_left)
    public void back(View v) {
        closeCurrentActivity();
    }

    @Override
    protected void initTitle() {
        titleTv.setText("饼状图展示");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_prichart;
    }
}
