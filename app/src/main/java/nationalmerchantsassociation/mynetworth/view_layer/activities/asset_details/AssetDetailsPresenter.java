package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import java.util.List;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.LineChartDataMapper;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;
import nationalmerchantsassociation.mynetworth.utils.ValueItemUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

public class AssetDetailsPresenter implements AssetDetailsContract.Presenter {
    private static final String EMPTY_STRING = "";
    private AssetDetailsContract.View view;
    private Realm realm;
    private String assetName;
    private String newAssetName;
    private AssetDetailsModel model;
    private Asset asset;

    public AssetDetailsPresenter(AssetDetailsContract.View assetsView, Realm mainUiRealm) {
        this.view = assetsView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onResume(String assetName) {
        if(newAssetName == null) {
            this.assetName = assetName;
        }
        initData();
    }

    @Override
    public void onAssetValueSelected(String date) {
        view.startEditActivity(assetName, date);
    }

    @Override
    public void onLineChartValueSelected(float x) {
        view.highlightSelectedItem(model.getDates().get((int)x));
    }

    @Override
    public void onLineChartNotSelected() {
        view.highlightSelectedItem(EMPTY_STRING);
    }

    @Override
    public void onUpdateClicked() {
        view.startUpdateActivity(assetName);
    }

    @Override
    public void onActivityResult(String assetName) {
        this.assetName = assetName;
        this.newAssetName = assetName;
        initData();
    }

    @Override
    public void onEditSelected() {
        view.startEditActivity(assetName, asset.getCategory());
    }

    private void initData() {
        asset = realm.where(Asset.class).equalTo("name", assetName).findFirst();
        if(asset != null) {
            asset.addChangeListener(assetsRealtime -> {
                view.updateRecycler();
                if(asset.isValid()) {
                    setDefaultState();
                }
            });
            view.initRecycler(ValueItemUtil.sortByDate(asset.getAssetValues()));
            setDefaultState();
        }
    }

    private AssetDetailsModel buildAssetDetailsModel(List<ValueItem> assetValues, int range){
        this.model = LineChartDataMapper.mapAssetDetails(assetValues, range);
        return this.model;
    }

    private void setDefaultState(){
        view.setTitleWithTotal(asset.getCurrentValue(), assetName);
        view.setLineChartData(buildAssetDetailsModel(asset.getAssetValues(), LineChartDataMapper.MONTHS_6).getAssetValues());
        view.setLineChartTitle(TextFormatterUtil.buildLineChartTitle("6M",
                (model.getAssetValues().get(5))-(model.getAssetValues().get(0)), model.getAssetValues().get(5)));
    }
}
