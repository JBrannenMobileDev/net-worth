package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_update;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.view_layer.activities.main.NumberTextWatcher;


public class DebtUpdateActivity extends AppCompatActivity implements DebtUpdateView {


    @BindView(R.id.toolbar_update_debt)Toolbar toolbar;
    @BindView(R.id.debt_value_input)EditText valueEt;
    @BindView(R.id.month_spinner)MaterialSpinner monthSpinner;
    @BindView(R.id.year_spinner)MaterialSpinner yearSpinner;
    @Inject DebtUpdatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_debt_value);
        ButterKnife.bind(this);
        initToolbar();
        setTitle(getIntent().getStringExtra("debtName"));
        presenter.onCreate(getIntent().getStringExtra("debtName"));
        initListeners();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.update_button)
    public void onSaveClicked(){
        presenter.onUpdate(Double.valueOf(valueEt.getText().toString().replaceAll("[^0-9.]", "")));
    }

    @Override
    public void onPostExecute(String debtName) {
        finishActivityForResult(debtName);
    }

    @Override
    public void finishActivityForResult(String debtName) {
        Intent intent = getIntent();
        intent.putExtra("debtName", debtName);
        setResult(0, intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        presenter.onBackPressed();
    }


    private void initListeners() {
        valueEt.addTextChangedListener(new NumberTextWatcher(valueEt, "#,###"));
    }

    @Override
    public void initDateSpinners(List<String> months, List<Integer> years){
        monthSpinner.setItems(months);
        presenter.saveMonthSelection(months.get(0));
        monthSpinner.setOnItemSelectedListener((view, position, id, item) ->
                presenter.saveMonthSelection(months.get(position)));

        yearSpinner.setItems(years);
        presenter.saveYearSelection(years.get(0));
        yearSpinner.setOnItemSelectedListener((view, position, id, item) ->
                presenter.saveYearSelection(years.get(position)));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window w = getWindow(); // in Activity's onResume() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.debt_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_debt) {
            presenter.onUpdate(Double.valueOf(valueEt.getText().toString().replaceAll("[^0-9.]", "")));
            return true;
        }
        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdate(String debtName) {
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        onPostExecute(debtName);
    }
}
