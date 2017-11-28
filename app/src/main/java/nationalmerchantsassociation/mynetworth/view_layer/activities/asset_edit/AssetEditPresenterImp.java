package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/22/17.
 */

public class AssetEditPresenterImp implements AssetEditPresenter {

    private String assetNamePrevious;
    private String assetName;
    private String assetCategory;
    private AssetEditView view;
    private DataManager dataManager;

    public AssetEditPresenterImp(AssetEditView assetUpdateView, DataManager dataManager) {
        this.view = assetUpdateView;
        this.dataManager = dataManager;
    }

    @Override
    public void onCreate(String assetName, String assetCategory) {
        this.assetName = assetName;
        this.assetCategory = assetCategory;
        this.assetNamePrevious = assetName;
        view.initAssetName(assetName);
        view.initAssetCategory(assetCategory);
    }

    @Override
    public void initSpinner(String[] assetCategories) {
        List<String> categories = new ArrayList<>(Arrays.asList(assetCategories));
        Collections.sort(categories);
        view.initSpinners(categories);
    }

    @Override
    public void saveAsset(String assetName) {
        dataManager.updateAsset(assetName, assetCategory, assetNamePrevious);
        view.onPostExecute(assetName);
    }

    @Override
    public void deleteAsset() {
        dataManager.deleteAsset(assetName);
        view.finishActivityForResult(assetName, 2);
    }

    @Override
    public void onBackPressed() {
        view.finishActivityForResult(assetName, 1);
    }

    @Override
    public void saveCategorySelection(String assetCategory) {
        this.assetCategory = assetCategory;
    }
}
