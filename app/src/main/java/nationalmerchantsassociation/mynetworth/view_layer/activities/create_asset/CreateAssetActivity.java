package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.view_layer.activities.main.NumberTextWatcher;

public class CreateAssetActivity extends AppCompatActivity implements CreateAssetView{

    @BindView(R.id.toolbar_create_asset)Toolbar toolbar;
    @BindView(R.id.asset_value_input)EditText valueInput;
    @BindView(R.id.asset_name_input)EditText nameInput;
    @BindView(R.id.category_spinner)MaterialSpinner categorySpinner;
    @BindView(R.id.month_spinner)MaterialSpinner monthSpinner;
    @BindView(R.id.year_spinner)MaterialSpinner yearSpinner;
    @Inject CreateAssetPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_asset);
        ButterKnife.bind(this);
        initToolbar();
        setTitle(getString(R.string.create_asset_title));
        initListeners();
        presenter.initDateSpinners();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_asset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save_asset) {
            presenter.saveAsset(valueInput.getText().toString().replaceAll("[^0-9.]", ""),
                    nameInput.getText().toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initSpinners(List<String> categories){
        categorySpinner.setItems(categories);
        categorySpinner.setOnItemSelectedListener((view, position, id, item) -> {
            presenter.saveCategorySelection(categories.get(position));
        });
    }

    @Override
    public void initDateSpinners(List<String> months, List<Integer> years){
        monthSpinner.setItems(months);
        monthSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            presenter.saveMonthSelection(months.get(position));
        });

        yearSpinner.setItems(years);
        yearSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            presenter.saveYearSelection(years.get(position));
        });
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void createValueErrorToast() {
        Toast.makeText(this, "Value must be greater than 0.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createNameErrorToast() {
        Toast.makeText(this, "Name required", Toast.LENGTH_SHORT).show();
    }

    private void initListeners() {
        valueInput.addTextChangedListener(new NumberTextWatcher(valueInput, "#,###"));
        categorySpinner.setOnClickListener(view -> presenter.initSpinner(getResources().getStringArray(R.array.asset_category_names)));
    }
}
