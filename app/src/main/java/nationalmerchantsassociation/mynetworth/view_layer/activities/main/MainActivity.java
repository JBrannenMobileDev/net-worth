package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.utils.PixelDpConversionUtil;
import nationalmerchantsassociation.mynetworth.view_layer.activities.assets.AssetsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.debts.DebtsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.dialog_fragments.AddItemAlertDialog;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @BindView(R.id.dashboard_bar_chart) BarChart barChart;
    @BindView(R.id.line_chart)LineChart lineChart;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.net_worth_tv)TextView netWorth;
    @BindView(R.id.empty_state_iv)ImageView emptyStateImage;
    @BindView(R.id.empty_state_tv)TextView emptyStateTv;

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setTitle(getString(R.string.empty_string));
        initListeners();
        initBarChart();
        initLineChart();
        presenter.onCreate();
    }

    @Override
    public void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick (R.id.fab)
    public void onAddItemClicked(){
        new AddItemAlertDialog().show(getSupportFragmentManager(), "add_item_alert");
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

    private void initListeners() {
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initLineChart() {
        LineData data = getData(30, 100);
        // add some transparency to the color with "& 0x90FFFFFF"
        setupLineChart(lineChart, data, getResources().getColor(R.color.colorPrimary));
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
    public void setData(float assets, float debts) {
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
            barChart.animateY(1500);
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

    private void setupLineChart(LineChart chart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleColorHole(color);
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setPinchZoom(false);
        chart.setBackgroundColor(Color.TRANSPARENT);
        chart.setViewPortOffsets(PixelDpConversionUtil.pxFromDp(getApplicationContext(), 50f), 0, PixelDpConversionUtil.pxFromDp(getApplicationContext(), 50f), 0);
        chart.setData(data);
        Legend l = chart.getLegend();
        l.setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setSpaceTop(40);
        chart.getAxisLeft().setSpaceBottom(40);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        chart.animateX(500);
    }

    private LineData getData(int count, float range) {

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");

        set1.setLineWidth(4f);
        set1.setCircleRadius(2f);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setDrawCircleHole(false);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);

        // create a data object with the datasets
        LineData data = new LineData(set1);

        return data;
    }

    @Override
    public void setAnimatedNetWorth(int previous, int current) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        ValueAnimator animator = ValueAnimator.ofInt(previous, current);
        animator.setDuration(1500);
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
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
