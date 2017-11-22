package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_update;

import java.util.List;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface AssetUpdateView {
    void initDateSpinners(List<String> months, List<Integer> years);
    void onUpdate(String assetName);
    void onPostExecute(String assetName);
    void finishActivityForResult(String assetName);
}
