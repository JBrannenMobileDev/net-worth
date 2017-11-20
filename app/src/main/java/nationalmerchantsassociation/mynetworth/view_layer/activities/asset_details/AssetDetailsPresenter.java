package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface AssetDetailsPresenter {
    void onCreate(String assetName);
    void onAssetValueSelected(String date);
    void onLineChartValueSelected(float x);
    void onLineChartNotSelected();
}
