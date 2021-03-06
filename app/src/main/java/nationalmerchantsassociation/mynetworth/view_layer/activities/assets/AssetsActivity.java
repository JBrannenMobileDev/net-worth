package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.R;

import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;
import nationalmerchantsassociation.mynetworth.utils.LineChartUtil;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;
import nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details.AssetDetailsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset.CreateAssetActivity;

public class AssetsActivity extends AppCompatActivity implements AssetsContract.View{

    @BindView(R.id.toolbar_assets)Toolbar toolbar;
    @BindView(R.id.assets_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.assets_line_chart)LineChart lineChart;
    @BindView(R.id.linechart_title_tv)TextView lineChartTitle;
    @Inject AssetsContract.Presenter presenter;
    @Inject LineChartUtil lineChartUtil;
    private RecyclerViewAdapterAssets adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BaseCallback<Asset> assetSelectedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        ButterKnife.bind(this);
        setTitle(getString(R.string.assets_title));
        initToolbar();
        initCallbacks();
        initListeners();
        lineChartUtil.initLineChart(lineChart, getApplicationContext());
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.onCreate();
    }

    @Override
    public void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }


    private void initCallbacks() {
        assetSelectedCallback = new BaseCallback<Asset>() {
            @Override
            public void onResponse(Asset asset) {
                Intent intent = new Intent(getApplicationContext(), AssetDetailsActivity.class);
                intent.putExtra("assetName", asset.getName());
                startActivity(intent);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
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

    @OnClick(R.id.assets_fab)
    public void onAddAssetClicked(){
        launchAddAssetIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assets_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            launchAddAssetIntent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchAddAssetIntent(){
        Intent intent = new Intent(this, CreateAssetActivity.class);
        startActivity(intent);
    }

    @Override
    public void initRecycler(RealmResults<Asset> assets){
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterAssets(assets, assetSelectedCallback);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void updateRecycler() {
        adapter.notifyDataSetChanged();//TODO implement realm fine grained updates
    }

    @Override
    public void setTitleWithTotal(double sum) {
        setTitle(getString(R.string.assets_title) + "  $" + TextFormatterUtil.getCurrencyFormatter().format((int)sum));
    }

    @Override
    public void setLineChartTitle(String title) {
        lineChartTitle.setText(title);
    }

    @Override
    public void setLineChartData(List<Integer> assets) {
        lineChartUtil.updateDataset(assets);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window w = getWindow(); // in Activity's onResume() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
