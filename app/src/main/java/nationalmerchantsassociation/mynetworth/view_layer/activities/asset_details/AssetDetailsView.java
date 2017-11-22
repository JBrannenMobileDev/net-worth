package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface AssetDetailsView {
    void initRecycler(List<ValueItem> assetValues);
    void updateRecycler();
    void setTitleWithTotal(double sum, String asset);
    void startEditActivity(String assetName, String date);
    void setLineChartData(List<Integer> assetValues);
    void setLineChartTitle(String title);
    void highlightSelectedItem(String date);
    void launchUpdateActivity(String assetName);
}
