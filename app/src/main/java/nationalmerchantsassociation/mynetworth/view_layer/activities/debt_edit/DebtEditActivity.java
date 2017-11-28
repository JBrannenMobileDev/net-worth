package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_edit;

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


public class DebtEditActivity extends AppCompatActivity implements DebtEditView {


    @BindView(R.id.toolbar_edit_debt)Toolbar toolbar;
    @BindView(R.id.debt_name_input)EditText nameInput;
    @BindView(R.id.category_spinner)MaterialSpinner categorySpinner;
    @Inject DebtEditPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_debt);
        ButterKnife.bind(this);
        initToolbar();
        setTitle(getIntent().getStringExtra("debtName"));
        presenter.onCreate(getIntent().getStringExtra("debtName"), getIntent().getStringExtra("debtCategory"));
        initListeners();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.save_button)
    public void onSaveclicked(){
        presenter.saveDebt(nameInput.getText().toString());
    }

    @Override
    public void onPostExecute(String debtName) {
        finishActivityForResult(debtName, 1);
    }

    @Override
    public void finishActivityForResult(String debtName, int resultCode) {
        Intent intent = getIntent();
        intent.putExtra("debtName", debtName);
        setResult(resultCode, intent);
        finish();
    }

    @Override
    public void initDebtName(String debtName) {
        nameInput.setText(debtName);
    }

    @Override
    public void initDebtCategory(String debtCategory) {
        categorySpinner.setText(debtCategory);
    }

    @Override
    public void onBackPressed(){
        presenter.onBackPressed();
    }

    private void initListeners() {
        categorySpinner.setOnClickListener(view -> presenter.initSpinner(getResources().getStringArray(R.array.debt_category_names)));
    }

    @Override
    public void initSpinners(List<String> categories){
        categorySpinner.setItems(categories);
        categorySpinner.setOnItemSelectedListener((view, position, id, item) ->
                presenter.saveCategorySelection(categories.get(position)));
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
        getMenuInflater().inflate(R.menu.debt_edit, menu);
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
            presenter.saveDebt(nameInput.getText().toString());
            return true;
        }
        if (id == R.id.action_delete_debt) {
            presenter.deleteDebt();
            return true;
        }
        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdate(String assetName) {
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        onPostExecute(assetName);
    }
}
