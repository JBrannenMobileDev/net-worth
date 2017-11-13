package nationalmerchantsassociation.mynetworth.view_layer.activities;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.view_layer.custom.CustomBarchatRenderer;
import nationalmerchantsassociation.mynetworth.view_layer.dialog_fragments.AddItemAlertDialog;
import nationalmerchantsassociation.mynetworth.view_layer.presenters.MainPresenter;
import nationalmerchantsassociation.mynetworth.view_layer.presenters.MainPresenterImp;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainInterface{

    @BindView(R.id.dashboard_bar_chart) BarChart mChart;
    @BindView(R.id.fab) FloatingActionButton addItem;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.net_worth_tv)TextView netWorth;
    @BindView(R.id.assets_tv)TextView assetsTv;
    @BindView(R.id.debt_tv)TextView debtTv;
    @BindView(R.id.empty_state_iv)ImageView emptyStateImage;
    @BindView(R.id.empty_state_tv)TextView emptyStateTv;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setTitle(getString(R.string.empty_string));
        presenter = new MainPresenterImp(this);
        initListeners();
        initBarChart();
        presenter.onCreate();
    }

    @Override
    public void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }

    private void initListeners() {
        addItem.setOnClickListener(view -> new AddItemAlertDialog().show(getSupportFragmentManager(), null));

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        assetsTv.setOnClickListener(view -> {
            Intent intent = new Intent(this, AssetsActivity.class);
            startActivity(intent);
        });

        debtTv.setOnClickListener(view -> {
            Intent intent = new Intent(this, DebtsActivity.class);
            startActivity(intent);
        });
    }

    public void initBarChart() {
        mChart.setDrawBarShadow(false);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        mChart.setFitBars(true);
        mChart.getLegend().setEnabled(false);
        mChart.setRenderer(new CustomBarchatRenderer(mChart, mChart.getAnimator(), mChart.getViewPortHandler(), getApplicationContext()));

        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(false);

        YAxis yAxisLeft = mChart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setEnabled(false);

        YAxis yAxisRight = mChart.getAxisRight();
        yAxisRight.setEnabled(false);
    }

    @Override
    public void setData(float assets, float debts) {
        if(assets == 0 && debts == 0){
            emptyStateImage.setVisibility(View.VISIBLE);
            emptyStateTv.setVisibility(View.VISIBLE);
            mChart.setVisibility(View.GONE);
        }else {
            emptyStateImage.setVisibility(View.GONE);
            emptyStateTv.setVisibility(View.GONE);
            mChart.setVisibility(View.VISIBLE);
            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
            entries.add(new BarEntry(0, assets));
            entries.add(new BarEntry(1, debts));

            BarDataSet set1 = new BarDataSet(entries, "BarDataSet");
            int barColor = rgb("#ffffff");
            set1.setColors(barColor);
            if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
                set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
                set1.setValues(entries);
                mChart.getData().notifyDataChanged();
                mChart.notifyDataSetChanged();
                mChart.animateY(1500);
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
                mChart.setData(data);
                mChart.invalidate();
                mChart.animateY(1500);
            }
        }
    }

    @Override
    public void onNetWorthReceived(int previousValue, int netWorthValue) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        if(netWorthValue == 0){
            netWorth.setText("     $0     ");
        }else {
            ValueAnimator animator = ValueAnimator.ofInt(previousValue, netWorthValue);
            animator.setDuration(1500);
            animator.addUpdateListener(animation -> netWorth.setText("$" + formatter.format(animation.getAnimatedValue())));
            animator.start();
        }
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
