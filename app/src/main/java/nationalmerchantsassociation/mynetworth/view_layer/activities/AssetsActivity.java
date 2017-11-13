package nationalmerchantsassociation.mynetworth.view_layer.activities;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;
import nationalmerchantsassociation.mynetworth.view_layer.adapters.RecyclerViewAdapterAssets;

public class AssetsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_assets)Toolbar toolbar;
    @BindView(R.id.assets_recycler_view) RecyclerView mRecyclerView;
    private RecyclerViewAdapterAssets adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        ButterKnife.bind(this);
        setTitle("Assets");
        realm = Realm.getDefaultInstance();
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        initToolbar();
        initData();
    }

    private void initData() {
        RealmResults<Asset> assets = realm.where(Asset.class).findAllSorted("name");
        BaseCallback<Asset> assetSelectedCallback = new BaseCallback<Asset>() {
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
        assets.addChangeListener(assets1 -> initRecycler(assets1, assetSelectedCallback));
        initRecycler(assets, assetSelectedCallback);
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
            Intent intent = new Intent(this, CreateAssetActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecycler(RealmResults<Asset> assets, BaseCallback<Asset> assetSelectedCallback){
        adapter = new RecyclerViewAdapterAssets(assets, assetSelectedCallback);
        mRecyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}
