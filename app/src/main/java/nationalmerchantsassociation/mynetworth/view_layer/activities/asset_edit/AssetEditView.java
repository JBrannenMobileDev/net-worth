package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;

import java.util.List;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface AssetEditView {
    void initSpinners(List<String> categories);
    void onUpdate(String assetName);
    void onPostExecute(String assetName);
    void finishActivityForResult(String assetName);
    void initAssetName(String assetName);
    void initAssetCategory(String assetCategory);
}
