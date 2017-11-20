package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;

/**
 * Created by jbrannen on 11/10/17.
 */

public class AssetEditPresenterImp implements AssetEditPresenter {

    private AssetEditView view;
    private Realm realm;
    private DataManager dataManager;
    private Asset asset;
    private String category;

    public AssetEditPresenterImp(AssetEditView assetDetailsView, Realm mainUiRealm, DataManager dataManager) {
        this.view = assetDetailsView;
        this.realm = mainUiRealm;
        this.dataManager = dataManager;
    }

    @Override
    public void onCreate(String assetName, String date) {
        initData(assetName);
    }

    @Override
    public void initSpinner(String[] stringArray) {
        List<String> categories = new ArrayList<>(Arrays.asList(stringArray));
        Collections.sort(categories);
        view.initSpinner(categories);
    }

    @Override
    public void onDeleteAsset(String assetName) {
        dataManager.deleteAsset(assetName);
    }

    @Override
    public void onDelete() {
        view.launchAreYouSureDialog(asset.getName());
    }

    public void initData(String assetName) {
        asset = realm.where(Asset.class).equalTo("name", assetName).findFirst();
        category = asset.getCategory();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        view.setValueEt("$" + formatter.format(asset.getCurrentValueItem()));
        view.setSavedCategoryName(asset.getCategory());
    }

    @Override
    public void SpinnerItemSelected(String categoryName) {
        category = categoryName;
    }

    @Override
    public void onSave(String value) {
        Asset assetToSave = new Asset();
        assetToSave.setName(asset.getName());
        assetToSave.getCurrentValueItem().setValue(Double.valueOf(value));
        assetToSave.setCategory(category);
        dataManager.insertOrUpdateAsset(assetToSave);
        view.onSave();
    }
}
