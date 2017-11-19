package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;

import java.util.List;


/**
 * Created by jbrannen on 11/10/17.
 */

public interface AssetEditView {
    void launchAreYouSureDialog(String assetName);
    void setValueEt(String value);
    void onSave();
    void setSavedCategoryName(String category);
    void initSpinner(List<String> categories);
}
