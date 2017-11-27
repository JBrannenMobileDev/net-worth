package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

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
import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;
import nationalmerchantsassociation.mynetworth.utils.LineChartUtil;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt.CreateDebtActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details.DebtDetailsActivity;

public class DebtsActivity extends AppCompatActivity implements DebtsView{

    @BindView(R.id.toolbar_debts)Toolbar toolbar;
    @BindView(R.id.debts_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.debts_line_chart)LineChart lineChart;
    @BindView(R.id.linechart_title_tv)TextView lineChartTitle;
    @Inject DebtsPresenter presenter;
    @Inject LineChartUtil lineChartUtil;
    private RecyclerViewAdapterDebts adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BaseCallback<Debt> debtSelectedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debts);
        ButterKnife.bind(this);
        setTitle(getString(R.string.debts_title));
        initToolbar();
        initCallbacks();
        initListeners();
        lineChartUtil.initLineChart(lineChart, getApplicationContext());
        presenter.onCreate();
    }

    @Override
    public void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }

    private void initCallbacks(){
        debtSelectedCallback = new BaseCallback<Debt>() {
            @Override
            public void onResponse(Debt debt) {
                Intent intent = new Intent(getApplicationContext(), DebtDetailsActivity.class);
                intent.putExtra("debtName", debt.getName());
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

    @OnClick(R.id.debts_fab)
    public void onAddAssetClicked(){
        launchAddDebtIntent();
    }

    @Override
    public void updateRecycler() {
        adapter.notifyDataSetChanged();//TODO implement realm fine grained updates
    }

    @Override
    public void initRecycler(RealmResults<Debt> debts){
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterDebts(debts, debtSelectedCallback);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assets_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            launchAddDebtIntent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchAddDebtIntent(){
        Intent intent = new Intent(this, CreateDebtActivity.class);
        startActivity(intent);
    }

    @Override
    public void setTitleWithTotal(double sum) {
        setTitle(getString(R.string.debts_title) + "  $" + TextFormatterUtil.getCurrencyFormatter().format((int)sum));
    }

    @Override
    public void setLineChartData(List<Integer> debts) {
        lineChartUtil.updateDataset(debts);
    }

    @Override
    public void setLineChartTitle(String title) {
        lineChartTitle.setText(title);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window w = getWindow(); // in Activity's onResume() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
