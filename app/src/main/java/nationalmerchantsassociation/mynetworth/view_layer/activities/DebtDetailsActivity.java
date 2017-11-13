package nationalmerchantsassociation.mynetworth.view_layer.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.view_layer.custom.NumberTextWatcher;
import nationalmerchantsassociation.mynetworth.view_layer.dialog_fragments.DeleteAlertDialog;
import nationalmerchantsassociation.mynetworth.view_layer.presenters.AssetDetailsPresenter;
import nationalmerchantsassociation.mynetworth.view_layer.presenters.AssetDetailsPresenterInterface;
import nationalmerchantsassociation.mynetworth.view_layer.presenters.DebtDetailsPresenter;
import nationalmerchantsassociation.mynetworth.view_layer.presenters.DebtDetailsPresenterInterface;

public class DebtDetailsActivity extends AppCompatActivity implements DeleteAlertDialog.YesSelected, DebtDetailsInterface{

    @BindView(R.id.toolbar_asset_details)Toolbar toolbar;
    @BindView(R.id.asset_details_value)EditText valueEt;
    @BindView(R.id.category_spinner)MaterialSpinner categorySpinner;
    @BindView(R.id.category_saved_tv)TextView categoryNameTv;
    private DebtDetailsPresenterInterface presenter;
    private String debtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_details);
        ButterKnife.bind(this);
        presenter = new DebtDetailsPresenter(this);
        presenter.onCreate();
        initToolbar();
        debtName = getIntent().getStringExtra("debtName");
        setTitle(debtName);
        presenter.initData(debtName);

        initListeners();
    }

    private void initListeners() {
        valueEt.addTextChangedListener(new NumberTextWatcher(valueEt, "#,###"));
        categorySpinner.setOnClickListener(view -> {
            initSpinner();
            categoryNameTv.setVisibility(View.GONE);
        });
    }

    public void initSpinner(){
        String[] assetCategories = getResources().getStringArray(R.array.asset_category_names);
        List<String> categories = new ArrayList<>(Arrays.asList(assetCategories));
        Collections.sort(categories);
        categorySpinner.setItems(categories);
        categorySpinner.setOnItemSelectedListener((view, position, id, item) ->
                presenter.SpinnerItemSelected(categories.get(position)));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window w = getWindow(); // in Activity's onCreate() for instance
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
        if (id == R.id.action_save_asset) {
            presenter.onSave();
            return true;
        }
        if (id == R.id.action_delete_asset) {
            presenter.onDelete();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setValueEt(String value){
        valueEt.setText(value);
    }

    @Override
    public void launchAreYouSureDialog(String assetName) {
        DeleteAlertDialog alert = new DeleteAlertDialog();
        Bundle args = new Bundle();
        args.putString("name", assetName);
        alert.setArguments(args);
        alert.show(getSupportFragmentManager(), "are_you_sure_alert");
    }

    @Override
    public void onDelete(String debtName) {
        DataManager.getInstance().deleteDebt(debtName);
        Toast.makeText(this, "Debt deleted", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onSave(String category) {
        Debt debtToSave = new Debt();
        String marketValue = valueEt.getText().toString().replaceAll("[^0-9.]", "");
        debtToSave.setName(debtName);
        debtToSave.setValue(Double.valueOf(marketValue));
        debtToSave.setCategory(category);
        DataManager.getInstance().insertOrUpdateDebt(debtToSave);
        Toast.makeText(this, "Debt saved", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void setSavedCategoryName(String category){
        categoryNameTv.setText(category);
    }
}
