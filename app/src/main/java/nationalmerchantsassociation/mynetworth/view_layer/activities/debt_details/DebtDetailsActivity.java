package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

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

public class DebtDetailsActivity extends AppCompatActivity implements DebtDetailsView {

    @BindView(R.id.toolbar_debt_details)Toolbar toolbar;
    @BindView(R.id.debt_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.debt_line_chart)LineChart lineChart;
    @BindView(R.id.linechart_title_tv)TextView lineChartTitle;
    @Inject
    DebtDetailsPresenter presenter;
    @Inject LineChartUtil lineChartUtil;
    private RecyclerViewAdapterDebtDetails adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BaseCallback<ValueItem> debtSelectedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_details);
        ButterKnife.bind(this);
        setTitle(getString(R.string.debts_title));
        initToolbar();
        initCallbacks();
        initListeners();
        lineChartUtil.initLineChart(lineChart, getApplicationContext());
        presenter.onCreate(getIntent().getStringExtra("debtName"));
    }

    @OnClick(R.id.debt_details_fab)
    public void onAddDebtClicked(){
        launchUpdateAssetValue();
    }

    private void launchUpdateAssetValue() {
//        Intent intent = new Intent(getApplicationContext(), UpdateDebtActivity.class);
//        startActivity(intent);
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
        debtSelectedCallback = new BaseCallback<ValueItem>() {
            @Override
            public void onResponse(ValueItem item) {
                presenter.onDebtValueSelected(item.getDate());
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
    }

    @Override
    public void startEditActivity(String debtName, String date){
//        Intent intent = new Intent(getApplicationContext(), DebtsEditActivity.class);
//        intent.putExtra("debtName", debtName);
//        intent.putExtra("date", date);
//        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.debts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
//            Intent intent = new Intent(this, DebtEditActivity.class);
//            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initRecycler(List<ValueItem> debtValues){
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterDebtDetails(debtValues, debtSelectedCallback, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void updateRecycler() {
        adapter.notifyDataSetChanged();//TODO implement realm fine grained updates
    }

    @Override
    public void setTitleWithTotal(double sum, String debtName) {
        setTitle(debtName + "  $" + TextFormatterUtil.getCurrencyFormatter().format((int)sum));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public void setLineChartData(List<Integer> debtValues){
        lineChartUtil.udateDataset(debtValues);
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
