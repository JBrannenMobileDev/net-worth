package nationalmerchantsassociation.mynetworth.view_layer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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

public class CreateDebtActivity extends AppCompatActivity {

    @BindView(R.id.debt_value_input)EditText valueInput;
    @BindView(R.id.toolbar_create_debt)Toolbar toolbar;
    @BindView(R.id.debt_name_input)EditText nameInput;
    @BindView(R.id.category_spinner)MaterialSpinner categorySpinner;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_debt);
        ButterKnife.bind(this);
        initToolbar();
        setTitle(getString(R.string.create_debt_title));
        initListeners();
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
        getMenuInflater().inflate(R.menu.add_asset, menu);
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
            saveAsset();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveAsset() {
        String trueValue = valueInput.getText().toString().replaceAll("[^0-9.]", "");
        Integer amount = Integer.valueOf(trueValue);
        if(amount > 0 && !nameInput.getText().toString().isEmpty()){
            Debt debt = new Debt(amount, nameInput.getText().toString(), category);
            DataManager.getInstance().insertOrUpdateDebt(debt);
            finish();
        }else{
            if(amount == 0)
                Toast.makeText(this, "Invalid value", Toast.LENGTH_SHORT).show();
            if(nameInput.getText().toString().isEmpty())
                Toast.makeText(this, "Name required", Toast.LENGTH_SHORT).show();
        }
    }

    public void initSpinner(){
        String[] assetCategories = getResources().getStringArray(R.array.debt_category_names);
        List<String> categories = new ArrayList<>(Arrays.asList(assetCategories));
        Collections.sort(categories);
        categorySpinner.setItems(categories);
        categorySpinner.setOnItemSelectedListener((view, position, id, item) -> category = categories.get(position));
    }

    private void initListeners() {
        valueInput.addTextChangedListener(new NumberTextWatcher(valueInput, "#,###"));
        categorySpinner.setOnClickListener(view -> initSpinner());
    }
}
