package nationalmerchantsassociation.mynetworth.view_layer.activities;

/**
 * Created by jbrannen on 11/10/17.
 */

public interface AssetDetailsInterface {
    void launchAreYouSureDialog(String assetName);
    void setValueEt(String value);
    void onSave(String category);
    void setSavedCategoryName(String category);
}
