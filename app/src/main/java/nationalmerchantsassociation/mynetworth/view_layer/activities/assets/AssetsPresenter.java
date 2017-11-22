package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface AssetsPresenter {
    void onCreate();
    void onLineChartValueSelected(float x);
    void onLineChartNotSelected();
    void onDestroy();
}
