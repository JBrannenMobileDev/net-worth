package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;
import nationalmerchantsassociation.mynetworth.utils.LineChartUtil;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;
import nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit.AssetEditActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.asset_update.AssetUpdateActivity;

public class AssetDetailsActivity extends AppCompatActivity implements AssetDetailsView {

    @BindView(R.id.toolbar_asset)Toolbar toolbar;
    @BindView(R.id.asset_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.asset_line_chart)LineChart lineChart;
    @BindView(R.id.linechart_title_tv)TextView lineChartTitle;
    @Inject AssetDetailsPresenter presenter;
    @Inject LineChartUtil lineChartUtil;
    private RecyclerViewAdapterAssetDetails adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BaseCallback<ValueItem> assetSelectedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_details);
        ButterKnife.bind(this);
        setTitle(getString(R.string.assets_title));
        initToolbar();
        initCallbacks();
        initListeners();
    }

    @Override
    public void onResume(){
        super.onResume();
        lineChartUtil.initLineChart(lineChart, getApplicationContext());
        presenter.onResume(getIntent().getStringExtra("assetName"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1)
            presenter.onActivityResult(data.getStringExtra("assetName"));
        if(resultCode == 2)
            finish();
    }

    @OnClick(R.id.update_asset_tv)
    public void onUpdateClicked(){
        presenter.onUpdateClicked();
    }

    @Override
    public void startUpdateActivity(String assetName){
        Intent intent = new Intent(this, AssetUpdateActivity.class);
        intent.putExtra("assetName", assetName);
        startActivityForResult(intent, 1);
    }

    @Override
    public void startEditActivity(String assetName, String categoryName){
        Intent intent = new Intent(getApplicationContext(), AssetEditActivity.class);
        intent.putExtra("assetName", assetName);
        intent.putExtra("assetCategory", categoryName);
        startActivityForResult(intent, 1);
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

    private void initCallbacks() {
        assetSelectedCallback = new BaseCallback<ValueItem>() {
            @Override
            public void onResponse(ValueItem item) {
                presenter.onAssetValueSelected(item.getDate());
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.asset_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit_asset) {
            presenter.onEditSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initRecycler(List<ValueItem> assetValues){
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterAssetDetails(assetValues, assetSelectedCallback, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void updateRecycler() {
        adapter.notifyDataSetChanged();//TODO implement realm fine grained updates
    }

    @Override
    public void setTitleWithTotal(double sum, String assetName) {
        setTitle(assetName + "  $" + TextFormatterUtil.getCurrencyFormatter().format((int)sum));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window w = getWindow(); // in Activity's onResume() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public void setLineChartData(List<Integer> assetValues){
        lineChartUtil.updateDataset(assetValues);
    }

    @Override
    public void setLineChartTitle(String title) {
        lineChartTitle.setText(title);
    }

    @Override
    public void highlightSelectedItem(String date) {
        adapter.setHighllightItemDate(date);
        adapter.notifyDataSetChanged();
    }
}
