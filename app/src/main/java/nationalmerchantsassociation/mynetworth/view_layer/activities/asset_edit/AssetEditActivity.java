package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.view_layer.activities.main.NumberTextWatcher;


public class AssetEditActivity extends AppCompatActivity implements AssetsEditContract.View {


    @BindView(R.id.toolbar_edit_asset)Toolbar toolbar;
    @BindView(R.id.asset_name_input)EditText nameInput;
    @BindView(R.id.category_spinner)MaterialSpinner categorySpinner;
    @Inject AssetsEditContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_asset);
        ButterKnife.bind(this);
        initToolbar();
        setTitle(getIntent().getStringExtra("assetName"));
        presenter.onCreate(getIntent().getStringExtra("assetName"), getIntent().getStringExtra("assetCategory"));
        initListeners();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.save_button)
    public void onSaveclicked(){
        presenter.saveAsset(nameInput.getText().toString());
    }

    @Override
    public void onPostExecute(String assetName) {
        finishActivityForResult(assetName, 1);
    }

    @Override
    public void finishActivityForResult(String assetName, int resultCode) {
        Intent intent = getIntent();
        intent.putExtra("assetName", assetName);
        setResult(resultCode, intent);
        finish();
    }

    @Override
    public void initAssetName(String assetName) {
        nameInput.setText(assetName);
    }

    @Override
    public void initAssetCategory(String assetCategory) {
        categorySpinner.setText(assetCategory);
    }

    @Override
    public void onBackPressed(){
        presenter.onBackPressed();
    }

    private void initListeners() {
        categorySpinner.setOnClickListener(view -> presenter.initSpinner(getResources().getStringArray(R.array.asset_category_names)));
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
        getMenuInflater().inflate(R.menu.asset_edit, menu);
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
            presenter.saveAsset(nameInput.getText().toString());
            return true;
        }
        if (id == R.id.action_delete_asset) {
            presenter.deleteAsset();
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
