package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface AssetEditPresenter {
    void onCreate(String assetName, String assetCategory);
    void onBackPressed();
    void saveCategorySelection(String assetCategory);
    void initSpinner(String[] stringArray);
    void saveAsset(String assetName);
    void deleteAsset();
}
