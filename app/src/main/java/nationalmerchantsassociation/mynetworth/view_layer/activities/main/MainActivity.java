package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;
import nationalmerchantsassociation.mynetworth.utils.LineChartUtil;
import nationalmerchantsassociation.mynetworth.utils.PixelDpConversionUtil;
import nationalmerchantsassociation.mynetworth.view_layer.activities.assets.AssetsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.debts.DebtsActivity;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;
import static nationalmerchantsassociation.mynetworth.utils.MonthConversionUtil.monthIntTOString;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.dashboard_bar_chart) BarChart barChart;
    @BindView(R.id.line_chart)LineChart lineChart;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.net_worth_tv)TextView netWorth;
    @BindView(R.id.empty_state_iv)ImageView emptyStateImage;
    @BindView(R.id.empty_state_tv)TextView emptyStateTv;
    @BindView(R.id.linechart_title_tv)TextView lineChartTitleTv;
    @BindView(R.id.six_month_tv)TextView sixMonths;
    @BindView(R.id.one_year_tv)TextView oneYear;
    @BindView(R.id.five_year_tv)TextView fiveYears;
    @BindView(R.id.ten_year_tv)TextView tenYears;
    @BindView(R.id.all)TextView all;

    @Inject MainContract.Presenter presenter;
    @Inject LineChartUtil lineChartUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        toolbar.setTitle(CustomDateFormatter.createDate(monthIntTOString(Calendar.getInstance().get(Calendar.MONTH)), Calendar.getInstance().get(Calendar.YEAR)));
        initListeners();
        initBarChart();
        lineChartUtil.initLineChart(lineChart, getApplicationContext());
        presenter.onCreate();
    }

    @Override
    public void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick (R.id.assets_tv)
    public void onAssetsTvClicked(){
        Intent intent = new Intent(this, AssetsActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.debt_tv)
    public void onDebtsTvClicked(){
        Intent intent = new Intent(this, DebtsActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.six_month_tv)
    public void onSixMonthSelected(){
        presenter.onSixMonthsSelected();
        setAllOptionsToDefaultColor();
        sixMonths.setTextColor(Color.WHITE);
    }

    @OnClick (R.id.one_year_tv)
    public void onOneYearhSelected(){
        presenter.onOneYearSelected();
        setAllOptionsToDefaultColor();
        oneYear.setTextColor(Color.WHITE);
    }

    @OnClick (R.id.five_year_tv)
    public void onFiveYearSelected(){
        presenter.onFiveYearsSelected();
        setAllOptionsToDefaultColor();
        fiveYears.setTextColor(Color.WHITE);
    }

    @OnClick (R.id.ten_year_tv)
    public void onTenYearSelected(){
        presenter.onTenYearsSelected();
        setAllOptionsToDefaultColor();
        tenYears.setTextColor(Color.WHITE);
    }

    @OnClick (R.id.all)
    public void onAllSelected(){
        presenter.onAllSelected();
        setAllOptionsToDefaultColor();
        all.setTextColor(Color.WHITE);
    }

    private void setAllOptionsToDefaultColor() {
        sixMonths.setTextColor(getResources().getColor(R.color.colorPrimary));
        oneYear.setTextColor(getResources().getColor(R.color.colorPrimary));
        fiveYears.setTextColor(getResources().getColor(R.color.colorPrimary));
        tenYears.setTextColor(getResources().getColor(R.color.colorPrimary));
        all.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private void initListeners() {
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                presenter.onLineChartValueSelected(e.getX());
            }

            @Override
            public void onNothingSelected() {
                presenter.onLineChartNotSelected();
            }
        });
    }

    public void initBarChart() {
        barChart.setDrawBarShadow(false);
        barChart.getDescription().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.setFitBars(false);
        barChart.getLegend().setEnabled(false);
        barChart.setViewPortOffsets(PixelDpConversionUtil.pxFromDp(getApplicationContext(), 50f), 0, PixelDpConversionUtil.pxFromDp(getApplicationContext(), 50f), 0);
        barChart.setRenderer(new CustomBarChartRenderer(barChart, barChart.getAnimator(), barChart.getViewPortHandler(), getApplicationContext()));
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);
        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setEnabled(false);
        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);
    }

    @Override
    public void setData(float assets, float debts, boolean animate) {
        emptyStateImage.setVisibility(View.GONE);
        emptyStateTv.setVisibility(View.GONE);
        barChart.setVisibility(View.VISIBLE);
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(0, assets));
        entries.add(new BarEntry(1, debts));

        BarDataSet set1 = new BarDataSet(entries, "BarDataSet");
        int barColor = rgb("#ffffff");
        set1.setColors(barColor);
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(entries);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
            if(animate) {
                barChart.animateY(1500);
            }else{
                barChart.invalidate();
            }
        } else {
            BarData data = new BarData(set1);
            data.setBarWidth(.95f);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(16f);
            data.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> {
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                return "$" + formatter.format(value);
            });
            data.setDrawValues(true);
            barChart.setData(data);
            barChart.invalidate();
            barChart.animateY(1500);
        }
    }



    @Override
    public void setAnimatedNetWorth(int previous, int current, long time) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        ValueAnimator animator = ValueAnimator.ofInt(previous, current);
        animator.setDuration(time);
        animator.addUpdateListener(animation -> netWorth.setText("$" + formatter.format(animation.getAnimatedValue())));
        animator.start();
    }

    @Override
    public void initEmpyState() {
        emptyStateImage.setVisibility(View.VISIBLE);
        emptyStateTv.setVisibility(View.VISIBLE);
        barChart.setVisibility(View.GONE);
    }

    @Override
    public void setStaticNetWorth(String zero_net_worth) {
        netWorth.setText(zero_net_worth);
    }

    @Override
    public void setLineChartData(List<Integer> netWorths){
        lineChartUtil.updateDataset(netWorths);
    }

    @Override
    public void setTitle(String date) {
        toolbar.setTitle(date);
    }

    @Override
    public void setLineChartTitle(String lineChartTitle) {
        lineChartTitleTv.setText(lineChartTitle);
    }
}
