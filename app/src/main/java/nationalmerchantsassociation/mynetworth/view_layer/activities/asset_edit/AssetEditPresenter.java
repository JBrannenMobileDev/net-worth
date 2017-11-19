package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;


/**
 * Created by jbrannen on 11/10/17.
 */

public interface AssetEditPresenter {
    void onDelete();
    void SpinnerItemSelected(String categoryName);
    void onSave(String accessToken);
    void onCreate(String assetName);
    void initSpinner(String[] stringArray);
    void onDeleteAsset(String assetName);
}
