package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_update;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface AssetUpdatePresenter {
    void onCreate(String assetName);
    void saveMonthSelection(String month);
    void saveYearSelection(Integer year);
    void onUpdate(String value);
    void onBackPressed();
}
